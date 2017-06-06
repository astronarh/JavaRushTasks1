package com.javarush.task.task28.task2810.model;

/**
 * Created by ShkerdinVA on 06.06.2017.
 */
public class Provider {
    private Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public Provider(Strategy strategy) {
        this.strategy = strategy;
    }
}
