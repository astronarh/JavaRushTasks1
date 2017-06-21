package com.javarush.task.task34.task3410.controller;


import com.javarush.task.task34.task3410.model.Direction;
import com.javarush.task.task34.task3410.model.GameObjects;
import com.javarush.task.task34.task3410.model.Model;
import com.javarush.task.task34.task3410.view.View;

import java.io.IOException;

/**
 * Created by ShkerdinVA on 20.06.2017.
 */
public class Controller implements EventListener{
    private View view;
    private Model model;


    public Controller() throws IOException {
        this.view = new View(this);
        this.model = new Model();
        view.init();
        model.restart();
        model.setEventListener(this);
        view.setEventListener(this);
    }

    public static void main(String... args) throws IOException {
        Controller controller = new Controller();
    }

    @Override
    public void move(Direction direction) {
        try {
            model.move(direction);
        } catch (IOException e) {
            e.printStackTrace();
        }
        view.update();
    }

    @Override
    public void restart() throws IOException {
        model.restart();
        view.update();
    }

    @Override
    public void startNextLevel() throws IOException {
        model.startNextLevel();
        view.update();
    }

    @Override
    public void levelCompleted(int level) throws IOException {
        view.completed(level);
    }

    public GameObjects getGameObjects() {
        return model.getGameObjects();
    }
}
