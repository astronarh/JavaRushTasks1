package com.javarush.task.task26.task2610;


import java.util.concurrent.BlockingQueue;

/**
 * Created by ShkerdinVA on 12.05.2017.
 */
public class Consumer implements Runnable{
    private BlockingQueue queue;

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }
}
