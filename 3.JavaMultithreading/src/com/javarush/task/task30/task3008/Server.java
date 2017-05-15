package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ShkerdinVA on 12.05.2017.
 */
public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void sendBroadcastMessage(Message message) {
        try {
            for (Connection connection : connectionMap.values()) {
                connection.send(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
            ConsoleHelper.writeMessage("Сообщение не отправлено!");
        }
    }

    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        private String serverHandshake(Connection connection)  throws IOException, ClassNotFoundException{
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message message = connection.receive();
                if (message.getType() == MessageType.USER_NAME) {
                    if (!message.getData().isEmpty()) {
                        if (!connectionMap.containsKey(message.getData())) {
                            connectionMap.put(message.getData(), connection);
                            connection.send(new Message(MessageType.NAME_ACCEPTED));
                            return message.getData();
                        }
                    }
                }
            }
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException {
            for (String x : connectionMap.keySet()) {
                Message message = new Message(MessageType.USER_ADDED, x);
                if (!x.equals(userName)) {
                    connection.send(message);
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                if (message.getType() == MessageType.TEXT) {
                    String s = userName + ": " + message.getData();
                    Message formattedMessage = new Message(MessageType.TEXT, s);
                    sendBroadcastMessage(formattedMessage);
                } else {
                    ConsoleHelper.writeMessage("Error");
                }
            }
        }

        @Override
        public void run() {
            ConsoleHelper.writeMessage("Established a remote connection to address: "
                    + socket.getRemoteSocketAddress());
            String userName = null;
            try (Connection connection = new Connection(socket)) {
                userName = serverHandshake(connection);
                Message userAddedMessage = new Message(MessageType.USER_ADDED, userName);
                sendBroadcastMessage(userAddedMessage);
                sendListOfUsers(connection, userName);
                serverMainLoop(connection, userName);
            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage("ERROR occurred while working with remote address!");
            } finally {
                if(userName != null) {
                    connectionMap.remove(userName);
                    Message messageToSend = new Message(MessageType.USER_REMOVED, userName);
                    sendBroadcastMessage(messageToSend);
                }
            }
            ConsoleHelper.writeMessage("Remote connection was closed: " + socket.getRemoteSocketAddress());
        }
    }

    public static void main(String ...args) {
        ConsoleHelper.writeMessage("Введите порт сервера... ");
        int port = ConsoleHelper.readInt();
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            ConsoleHelper.writeMessage("Серввер запущен.");
            while (true) {
                Socket socket = serverSocket.accept();
                Handler handler = new Handler(socket);
                handler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
