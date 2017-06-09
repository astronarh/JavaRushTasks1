package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;

/**
 * Created by ShkerdinVA on 09.06.2017.
 */
public class Restaurant {
    public static void main(String... args) {
        Tablet tablet = new Tablet(5);
        Cook firstCook = new Cook("Amigo");
        tablet.addObserver(firstCook);
        tablet.createOrder();
    }
}
