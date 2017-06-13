package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.statistic.event.EventDataRow;

/**
 * Created by ShkerdinVA on 13.06.2017.
 */
public class StatisticManager {
    private static StatisticManager ourInstance = new StatisticManager();

    public static StatisticManager getInstance() {
        return ourInstance;
    }

    private StatisticManager() {

    }

    public void register(EventDataRow data) {

    }
}
