package com.javarush.task.task27.task2712;

/**
 * Created by ShkerdinVA on 22.06.2017.
 */
public class Restaurant {
    public static void main(String... args) {
        //System.out.println(Dish.allDishesToString());
        /*try {
            System.out.println(ConsoleHelper.getAllDishesForOrder());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        new Tablet(5).createOrder();
    }
}
