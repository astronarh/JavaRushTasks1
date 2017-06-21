package com.javarush.task.task34.task3410.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ShkerdinVA on 21.06.2017.
 */
public class GameObjects {
    private Set<Wall> walls;
    private Set<Box> boxes;
    private Set<Home> homes;
    private Player player;

    public Set<Wall> getWalls() {
        return walls;
    }

    public Set<Box> getBoxes() {
        return boxes;
    }

    public Set<Home> getHomes() {
        return homes;
    }


    public Player getPlayer() {

        return player;
    }

    public GameObjects(Set<Wall> walls, Set<Box> boxes, Set<Home> homes, Player player) {
        this.walls = walls;
        this.boxes = boxes;
        this.homes = homes;
        this.player = player;
    }

    public Set<GameObject> getAll() {
        Set<GameObject> gameObjects = new HashSet<>();
        gameObjects.addAll(getWalls());
        gameObjects.addAll(getBoxes());
        gameObjects.addAll(getHomes());
        gameObjects.add(getPlayer());
        return gameObjects;
    }
}
