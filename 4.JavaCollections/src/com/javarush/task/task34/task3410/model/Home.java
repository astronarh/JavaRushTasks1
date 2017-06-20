package com.javarush.task.task34.task3410.model;

import java.awt.*;

/**
 * Created by ShkerdinVA on 20.06.2017.
 */
public class Home extends GameObject{
    public Home(int x, int y) {
        super(x, y, 2, 2);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.RED);
        int x = getX() - getWidth() / 2;
        int y = getY() - getHeight() / 2;
        graphics.drawRect(x, y, getWidth(), getHeight());
        graphics.fillRect(x, y, getWidth(), getHeight());
    }
}
