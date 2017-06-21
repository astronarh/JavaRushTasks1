package com.javarush.task.task34.task3410.controller;

import com.javarush.task.task34.task3410.model.Direction;

import java.io.IOException;

/**
 * Created by ShkerdinVA on 21.06.2017.
 */
public interface EventListener {
    void move(Direction direction);
    void restart() throws IOException;
    void startNextLevel() throws IOException;
    void levelCompleted(int level) throws IOException;
}
