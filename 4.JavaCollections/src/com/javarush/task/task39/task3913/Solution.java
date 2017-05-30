package com.javarush.task.task39.task3913;

import java.nio.file.Paths;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("D:\\JavaRush\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task39\\task3913\\logs"));
        System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));
        System.out.println(logParser.getNumberOfUserEvents("Eduard Petrovich Morozko", null, null));
        System.out.println(logParser.getDateWhenUserLoggedFirstTime("Eduard Petrovich Morozko", null, null));
        System.out.println(logParser.getDateWhenUserSolvedTask("Amigo", 18, null, null));
        System.out.println(logParser.getDateWhenUserDoneTask("Eduard Petrovich Morozko", 48, null, null));
        System.out.println(logParser.getErrorEvents(null, null));
        System.out.println(logParser.getNumberOfAttemptToSolveTask(18, null, null));
        System.out.println(logParser.getNumberOfSuccessfulAttemptToSolveTask(15, null, null));
        System.out.println(logParser.getAllSolvedTasksAndTheirNumber(null, null));
        System.out.println(logParser.getAllDoneTasksAndTheirNumber(null, null));
        //System.out.println(logParser.execute("get ip for user = \"Eduard Petrovich Morozko\" and date between\n\"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\""));
        System.out.println(logParser.execute("get ip for event = \"LOGIN\" and date between\n\"30.08.2010 0:00:00\" and \"29.2.2028 23:59:59\""));


    }
}