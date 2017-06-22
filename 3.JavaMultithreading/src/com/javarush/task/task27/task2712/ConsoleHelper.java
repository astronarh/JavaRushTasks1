package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShkerdinVA on 22.06.2017.
 */
public class ConsoleHelper {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return bufferedReader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> dishList = new ArrayList<>();
        boolean dishSet = false;
        ConsoleHelper.writeMessage("Type to want to eat, to finish, type 'exit'");
        ConsoleHelper.writeMessage(Dish.allDishesToString());
        String typeOfDish = null;
        while (!(typeOfDish = bufferedReader.readLine()).equals("exit")) {
            for (Dish x : Dish.values()) {
                if (x.name().equals(typeOfDish)) {
                    dishList.add(x);
                    dishSet = true;
                }
            }
            if (!dishSet) ConsoleHelper.writeMessage("The name of the dish is not entered correctly.");
            dishSet = false;
        }
        return dishList;
    }
}
