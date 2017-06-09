package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Waiter;

/**
 * Created by ShkerdinVA on 09.06.2017.
 */
public class Restaurant {
    public static void main(String... args) {
        Tablet tablet = new Tablet(5);
        Cook firstCook = new Cook("Amigo");
        Waiter firstWaiter = new Waiter();
        tablet.addObserver(firstCook);
        firstCook.addObserver(firstWaiter);
        tablet.createOrder();
    }
}
