package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

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

    public static String askCurrencyCode() {
        writeMessage("Введите код валюты, три символа - ");
        while (true) {
            String currencyСode = readString();
            if (currencyСode.length() == 3) return currencyСode.toUpperCase();
            else writeMessage("Код должен содержать три символа, повторите ввод - ");
        }
    }

    public static String[] getValidTwoDigits(String currencyCode) {
        writeMessage("Введите номинал и количество банкнот, два целывх положительных числа - ");
        String[] denominationAndQuantity;
        while (true) {
            denominationAndQuantity = readString().split(" ");
            int denomination = 0;
            int quantity = 0;
            try {
                denomination = Integer.parseInt(denominationAndQuantity[0]);
                quantity = Integer.parseInt(denominationAndQuantity[1]);
            } catch (Exception e) {
                writeMessage("Неверный ввод, введите снова - ");
                continue;
            }
            if (denomination <= 0 || quantity <= 0) {
                writeMessage("Неверный ввод, введите снова - ");
                continue;
            }
            break;
        }
        return denominationAndQuantity;
    }

    public static Operation askOperation() throws InterruptOperationException {
        do {
            writeMessage("Выберете операцию:\n1 - INFO\n2 - DEPOSIT\n3 - WITHDRAW\n4 - EXIT");
            try {
                String operation = readString();
                return Operation.getAllowableOperationByOrdinal(Integer.parseInt(operation));
            } catch (IllegalArgumentException e) {
                writeMessage("Ввод некорректен, попробуйте снова: ");
                continue;
            }
        } while (true);
    }
}
