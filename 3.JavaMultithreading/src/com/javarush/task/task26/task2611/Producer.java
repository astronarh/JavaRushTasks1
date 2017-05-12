package com.javarush.task.task26.task2611;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ShkerdinVA on 12.05.2017.
 */
public class Producer implements Runnable{
    private ConcurrentHashMap<String, String> map;

    public Producer(ConcurrentHashMap map) {
        this.map = map;
    }

    @Override
    public void run() {
        int counter = 1;
        while (true) {
            try {
                Thread.sleep(500);
                map.put(counter + "", "Some text for " + counter);
                counter++;
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "thread was terminated");
                e.printStackTrace();
            }
        }
    }
}
