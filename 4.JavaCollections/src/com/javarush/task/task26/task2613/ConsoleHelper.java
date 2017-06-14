package com.javarush.task.task26.task2613;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by ShkerdinVA on 14.06.2017.
 */
public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {
        String input = "";
        try {
            input = bis.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }
}
