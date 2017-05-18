package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ShkerdinVA on 18.05.2017.
 */
public class Solution {
    public static void main(String... args) {
        StorageStrategy strategy = new HashMapStorageStrategy();
        testStrategy(strategy, 10000);
        OurHashMapStorageStrategy strategy2 = new OurHashMapStorageStrategy();
        testStrategy(strategy2, 10000);
        FileStorageStrategy strategy3 = new FileStorageStrategy();
        testStrategy(strategy3, 500);
        OurHashBiMapStorageStrategy strategy4 = new OurHashBiMapStorageStrategy();
        testStrategy(strategy4, 10000);
        HashBiMapStorageStrategy strategy5 = new HashBiMapStorageStrategy();
        testStrategy(strategy5, 10000);
        DualHashBidiMapStorageStrategy strategy6 = new DualHashBidiMapStorageStrategy();
        testStrategy(strategy6, 10000);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> set = new HashSet<>();
        for (String string : strings) set.add(shortener.getId(string));
        return set;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> set = new HashSet<>();
        for (Long longs : keys) set.add(shortener.getString(longs));
        return set;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String> testSetStrings = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            testSetStrings.add(Helper.generateRandomString());
        }
        Shortener shortener = new Shortener(strategy);
        Set<Long> idsSet;
        Date startTime1 = new Date();
        idsSet = getIds(shortener, testSetStrings);
        Date finishTime1 = new Date();
        long msDelay1 = finishTime1.getTime() - startTime1.getTime();
        Helper.printMessage(Long.toString(msDelay1));
        Set<String> stringSet;
        Date startTime2 = new Date();
        stringSet = getStrings(shortener, idsSet);
        Date finishTime2 = new Date();
        long msDelay2 = finishTime2.getTime() - startTime2.getTime();
        Helper.printMessage(Long.toString(msDelay2));
        if (testSetStrings.equals(stringSet)) {
            Helper.printMessage("Тест пройден.");
        } else {
            Helper.printMessage("Тест не пройден.");
        }
    }
}
