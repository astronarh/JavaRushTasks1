package com.javarush.task.task34.task3410.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ShkerdinVA on 21.06.2017.
 */
public class LevelLoader {
    private Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) throws IOException {
        /*Player player;
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
        return new GameObjects(walls, boxes, homes, player);*/

        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = null;

        int loopLevel;
        if (level > 60)
        {
            loopLevel = level % 60;
        } else
        {
            loopLevel = level;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(levels.toFile())))
        {
            int readLevel = 0;
            int x;
            int y = Model.FIELD_SELL_SIZE / 2;
            boolean isLevelMap = false;

            String line;
            while ((line = reader.readLine()) != null)
            {
                if (line.contains("Maze:"))
                {
                    readLevel = Integer.valueOf(line.split(" ")[1]);
                    continue;
                }
                if (readLevel == loopLevel)
                {
                    if (line.length() == 0)
                    {
                        boolean isEnd = isLevelMap;

                        isLevelMap = !isLevelMap;

                        if (isEnd && !isLevelMap)
                        {
                            break;
                        } else
                        {
                            continue;
                        }
                    }
                    if (isLevelMap)
                    {
                        x = Model.FIELD_SELL_SIZE / 2;

                        char[] chars = line.toCharArray();
                        for (char c : chars)
                        {
                            switch (c)
                            {
                                case 'X':
                                    walls.add(new Wall(x, y));
                                    break;
                                case '*':
                                    boxes.add(new Box(x, y));
                                    break;
                                case '.':
                                    homes.add(new Home(x, y));
                                    break;
                                case '&':
                                    boxes.add(new Box(x, y));
                                    homes.add(new Home(x, y));
                                    break;
                                case '@':
                                    player = new Player(x, y);
                            }
                            x += Model.FIELD_SELL_SIZE;
                        }
                        y += Model.FIELD_SELL_SIZE;
                    }
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return new GameObjects(walls, boxes, homes, player);
    }
}
