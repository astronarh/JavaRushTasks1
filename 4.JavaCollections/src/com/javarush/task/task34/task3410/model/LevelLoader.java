package com.javarush.task.task34.task3410.model;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ShkerdinVA on 21.06.2017.
 */
public class LevelLoader {
    public LevelLoader(Path levels) {
    }

    public GameObjects getLevel(int level) {
        Player player;
        Set<Home> homes = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Wall> walls = new HashSet<>();
        player = new Player(10, 20);
        homes.add(new Home(30, 40));
        boxes.add(new Box(50, 60));
        walls.add(new Wall(70, 80));
        walls.add(new Wall(90, 100));
        walls.add(new Wall(110, 120));
        walls.add(new Wall(130, 140));
        walls.add(new Wall(150, 160));
        walls.add(new Wall(170, 180));
        return new GameObjects(walls, boxes, homes, player);
    }
}
