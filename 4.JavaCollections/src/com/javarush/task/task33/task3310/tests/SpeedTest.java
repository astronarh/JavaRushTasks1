package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ShkerdinVA on 18.05.2017.
 */
public class SpeedTest {
    public long getTimeForGettingIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        Date date = new Date();
        for(String string : strings) ids.add(shortener.getId(string));
        return new Date().getTime() - date.getTime();
    }

    public long getTimeForGettingStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        Date date = new Date();
        for(Long longs : ids) strings.add(shortener.getString(longs));
        return new Date().getTime() - date.getTime();
    }

    @Test
    public void testHashMapStorage(){
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());
        Set<String> strings = new HashSet<>();
        for (int i = 0; i < 10000; i++) strings.add(Helper.generateRandomString());
        Set<Long> longs1 = new HashSet<>();
        Set<Long> longs2 = new HashSet<>();
        long long1 = getTimeForGettingIds(shortener1, strings, longs1);
        long long2 = getTimeForGettingIds(shortener2, strings, longs2);
        Assert.assertTrue(long1 > long2);
        long long3 = getTimeForGettingStrings(shortener1, longs1, new HashSet<>());
        long long4 = getTimeForGettingStrings(shortener2, longs2, new HashSet<>());
        Assert.assertEquals(long3, long4, 30);
    }
}
