package com.javarush.task.task35.task3513;

import java.util.*;

/**
 * Created by ShkerdinVA on 16.06.2017.
 */
public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    int score;
    int maxTile;
    private Stack<Tile[][]> previousStates;
    private Stack<Integer> previousScores;
    private boolean isSaveNeeded = true;

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

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
        this.previousScores = new Stack<Integer>();
        this.previousStates = new Stack<Tile[][]>();
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
        if (isSaveNeeded) saveState(this.gameTiles);
        boolean isChanged = false;
        for (int i = 0; i < FIELD_WIDTH; i++) if (compressTiles(gameTiles[i]) | mergeTiles(gameTiles[i])) isChanged = true;
        if (isChanged) {
            addTile();
            isSaveNeeded = true;
        }
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
        saveState(this.gameTiles);
        rotate();
        left();
        rotate();
        rotate();
        rotate();
    }

    public void right() {
        saveState(this.gameTiles);
        rotate();
        rotate();
        left();
        rotate();
        rotate();
    }

    public void down() {
        saveState(this.gameTiles);
        rotate();
        rotate();
        rotate();
        left();
        rotate();
    }

    public boolean canMove() {
        if (!getEmptyTiles().isEmpty()) return true;
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 1; j < gameTiles.length; j++) {
                if (gameTiles[i][j].value == gameTiles[i][j - 1].value) return true;
            }
        }
        for (int j = 0; j < gameTiles.length; j++) {
            for (int i = 1; i < gameTiles.length; i++) {
                if (gameTiles[i][j].value == gameTiles[i - 1][j].value) return true;
            }
        }
        return false;
    }

    private void saveState(Tile[][] tiles) {
        Tile[][] newTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for(int i = 0; i < FIELD_WIDTH; i++){
            for(int j = 0; j < FIELD_WIDTH; j++){
                newTiles[i][j] = new Tile();
                newTiles[i][j].value = tiles[i][j].value;
            }
        }
        this.previousStates.push(newTiles);
        previousScores.push(score);
        isSaveNeeded = false;
    }

    public void rollback() {
        if (!previousStates.isEmpty() && !previousScores.isEmpty()) {
            this.score = previousScores.pop();
            this.gameTiles = previousStates.pop();
        }
    }

    public void randomMove() {
        int randomInt = ((int) (Math.random() * 100)) % 4;
        switch (randomInt) {
            case 0:
                left();
                break;
            case 1:
                right();
                break;
            case 2:
                up();
                break;
            case 3:
                down();
                break;
        }
    }

    private boolean hasBoardChanged() {
        boolean result = false;
        int sumNow = 0;
        int sumPrevious = 0;
        Tile[][] tmp = previousStates.peek();
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[0].length; j++) {
                sumNow += gameTiles[i][j].value;
                sumPrevious += tmp[i][j].value;
            }
        }
        return sumNow != sumPrevious;
    }

    private MoveEfficiency getMoveEfficiency(Move move) {
        MoveEfficiency moveEfficiency;
        move.move();
        if (hasBoardChanged()) moveEfficiency = new MoveEfficiency(getEmptyTiles().size(), score, move);
        else moveEfficiency = new MoveEfficiency(-1, 0, move);
        rollback();

        return moveEfficiency;
    }

    public void autoMove() {
        PriorityQueue<MoveEfficiency> priorityQueue = new PriorityQueue(4, Collections.reverseOrder());
        priorityQueue.add(getMoveEfficiency(this::left));
        priorityQueue.add(getMoveEfficiency(this::right));
        priorityQueue.add(getMoveEfficiency(this::up));
        priorityQueue.add(getMoveEfficiency(this::down));
        Move move = priorityQueue.peek().getMove();
        move.move();
    }
}
