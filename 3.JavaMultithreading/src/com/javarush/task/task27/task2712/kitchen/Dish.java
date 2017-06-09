package com.javarush.task.task27.task2712.kitchen;

import java.util.Arrays;

/**
 * Created by ShkerdinVA on 09.06.2017.
 */
public enum Dish {
    Fish, Steak, Soup, Juice, Water;
    public static String allDishesToString() {
        return values().length == 0 ? "" : Arrays.toString(values()).substring(1, Arrays.toString(values()).length() - 1);
    }
}
