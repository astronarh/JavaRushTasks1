package com.javarush.task.task35.task3513;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShkerdinVA on 16.06.2017.
 */
public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    int score;
    int maxTile;

    public Model() {
        /*gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = new Tile();
            }
        }*/
        resetGameTiles();
        this.score = 0;
        this.maxTile = 2;
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

    private boolean compressTiles(Tile[] tiles) {
        boolean isChanged = false;
        Tile temp;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tiles[j].value == 0 && tiles[j + 1].value != 0) {
                    temp = tiles[j];
                    tiles[j] = tiles[j + 1];
                    tiles[j + 1] = temp;
                    isChanged = true;
                }
            }
        }
        return isChanged;
    }

    private boolean mergeTiles(Tile[] tiles) {
        boolean isChanged = false;
        for (int j = 0; j < 3; j++) {
            if (tiles[j].value != 0 && tiles[j].value == tiles[j + 1].value) {
                tiles[j].value = tiles[j].value * 2;
                tiles[j + 1].value = 0;
                if (tiles[j].value > maxTile) maxTile = tiles[j].value;
                score += tiles[j].value;
                isChanged = true;

            }
        }

        if (isChanged) {
            Tile temp;
            for (int j = 0; j < 3; j++) {
                if (tiles[j].value == 0 && tiles[j + 1].value != 0) {
                    temp = tiles[j];
                    tiles[j] = tiles[j + 1];
                    tiles[j + 1] = temp;
                }
            }
        }

        return isChanged;
    }

    public void left() {
        boolean isChanged = false;
        for (int i = 0; i < FIELD_WIDTH; i++) if (compressTiles(gameTiles[i]) | mergeTiles(gameTiles[i])) isChanged = true;
        if (isChanged) addTile();
    }

    private void rotate() {
        for (int i = 0; i < FIELD_WIDTH / 2; i++) {
            for (int j = i; j < FIELD_WIDTH - 1 - i; j++) {
                Tile tmpTile = gameTiles[i][j];
                gameTiles[i][j] = gameTiles[j][FIELD_WIDTH - 1 - i];
                gameTiles[j][FIELD_WIDTH - 1 - i] = gameTiles[FIELD_WIDTH - 1 - i][FIELD_WIDTH - 1 - j];
                gameTiles[FIELD_WIDTH - 1 - i][FIELD_WIDTH - 1 - j] = gameTiles[FIELD_WIDTH - 1 - j][i];
                gameTiles[FIELD_WIDTH - 1 - j][i] = tmpTile;
            }
        }
    }

    public void up() {
        rotate();
        left();
        rotate();
        rotate();
        rotate();
    }

    public void right() {
        rotate();
        rotate();
        left();
        rotate();
        rotate();
    }

    public void down() {
        rotate();
        rotate();
        rotate();
        left();
        rotate();
    }
}
