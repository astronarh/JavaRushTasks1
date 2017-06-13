package com.javarush.task.task27.task2712.statistic.event;

import java.util.Date;

/**
 * Created by ShkerdinVA on 13.06.2017.
 */
public class NoAvailableVideoEventDataRow {
    private int totalDuration;
    private Date currentDate;

    public NoAvailableVideoEventDataRow(int totalDuration) {
        this.totalDuration = totalDuration;
        currentDate = new Date();
    }
}
