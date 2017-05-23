package com.javarush.task.task31.task3110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by ShkerdinVA on 23.05.2017.
 */
public class ConsoleHelper {
    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {
        String inString;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            inString = bufferedReader.readLine();
            return inString;
        } catch (IOException e) { return null; }

    }

    public static int readInt() {
        int inInteger;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            inInteger = Integer.parseInt(bufferedReader.readLine());
            return inInteger;
        } catch (IOException e) { return 0; }
    }
}
