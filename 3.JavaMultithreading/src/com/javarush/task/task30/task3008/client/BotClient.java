package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

/**
 * Created by ShkerdinVA on 16.05.2017.
 */
public class BotClient extends Client {
    public class BotSocketThread extends SocketThread {

    }

    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    protected String getUserName() {
        return "date_bot_" + (int) (100 * Math.random());
    }

    public static void main(String... args) {
        BotClient botClient = new BotClient();
        botClient.run();
    }
}
