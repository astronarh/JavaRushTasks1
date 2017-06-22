package com.javarush.task.task27.task2712.kitchen;

import java.util.Arrays;

/**
 * Created by ShkerdinVA on 22.06.2017.
 */
public enum  Dish {
    Fish, Steak, Soup, Juice, Water;

    public static String allDishesToString() {
        return Arrays.toString(Dish.values()).substring(1, Arrays.toString(Dish.values()).length()-1);
    }
}
