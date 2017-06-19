package com.javarush.task.task35.task3513;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShkerdinVA on 16.06.2017.
 */
public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;

    public Model() {
        /*gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = new Tile();
            }
        }*/
        resetGameTiles();
    }

    private List<Tile> getEmptyTiles() {
        List<Tile> listEmptyTiles = new ArrayList<>();
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].value == 0) listEmptyTiles.add(gameTiles[i][j]);
            }
        }
        return listEmptyTiles;
    }

    void addTile() {
        List<Tile> listEmptyTiles = getEmptyTiles();
        //listEmptyTiles.set((int) (listEmptyTiles.size() * Math.random()), new Tile(Math.random() < 0.9 ? 2 : 4));
        listEmptyTiles.get((int) (listEmptyTiles.size() * Math.random())).value = Math.random() < 0.9 ? 2 : 4;
    }

    void resetGameTiles() {
        this.gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                this.gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
    }
}
