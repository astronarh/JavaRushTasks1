package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShkerdinVA on 09.06.2017.
 */
public class ConsoleHelper {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        String inStrinig;
        List<Dish> dishesList = new ArrayList<>();
        while (!(inStrinig = readString()).equalsIgnoreCase("exit")) {
            try {
                dishesList.add(Dish.valueOf(inStrinig));
            } catch (IllegalArgumentException e) {
                ConsoleHelper.writeMessage(inStrinig + " такого блюда нет");
            }
        }
        return dishesList;
    }
}
