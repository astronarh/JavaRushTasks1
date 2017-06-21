package com.javarush.task.task34.task3410.view;

import com.javarush.task.task34.task3410.controller.EventListener;
import com.javarush.task.task34.task3410.model.Direction;
import com.javarush.task.task34.task3410.model.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Set;

/**
 * Created by ShkerdinVA on 20.06.2017.
 */
public class Field extends JPanel {
    public class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case (KeyEvent.VK_LEFT):
                    eventListener.move(Direction.LEFT);
                    break;
                case (KeyEvent.VK_RIGHT):
                    eventListener.move(Direction.RIGHT);
                    break;
                case (KeyEvent.VK_UP):
                    eventListener.move(Direction.UP);
                    break;
                case (KeyEvent.VK_DOWN):
                    eventListener.move(Direction.DOWN);
                    break;
                case (KeyEvent.VK_R):
                    try {
                        eventListener.restart();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    break;
            }
        }
    }

    private View view;
    private EventListener eventListener;

    public Field(View view) {
        this.view = view;
        KeyHandler keyHandler = new KeyHandler();
        addKeyListener(keyHandler);
        setFocusable(true);
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 300, 300);
        Set<GameObject> gameObjectSet = view.getGameObjects().getAll();
        for (GameObject gameObject : gameObjectSet) gameObject.draw(g);
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }
}
