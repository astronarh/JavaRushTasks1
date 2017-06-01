package com.javarush.task.task28.task2805;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ShkerdinVA on 01.06.2017.
 */
public class MyThread extends Thread {
    private AtomicInteger minPriority = new AtomicInteger(Thread.MIN_PRIORITY);
    private AtomicInteger maxPriority = new AtomicInteger(Thread.MAX_PRIORITY);
    private static AtomicInteger priority = new AtomicInteger(Thread.MIN_PRIORITY);

    public MyThread() {
        run();
    }

    public MyThread(Runnable target) {
        super(target);
        run();
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        run();
    }

    public MyThread(String name) {
        super(name);
        run();
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        run();
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        run();
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        run();
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        run();
    }

    @Override
    public void run() {
        setPriority(priority.intValue());
        if (priority.intValue() == maxPriority.intValue()) priority = minPriority;
        else {
            priority.incrementAndGet();
            //System.out.println("- " + priority + " -");
        }

    }
}
