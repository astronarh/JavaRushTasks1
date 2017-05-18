package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ShkerdinVA on 18.05.2017.
 */
public class FunctionalTest {
    public void testStorage(Shortener shortener) {
        String string1 = Helper.generateRandomString();
        String string2 = Helper.generateRandomString();
        String string3 = string1;
        Long long1 = shortener.getId(string1);
        Long long2 = shortener.getId(string2);
        Long long3 = shortener.getId(string3);
        Assert.assertNotEquals(long2, long1);
        Assert.assertNotEquals(long2, long3);
        Assert.assertEquals(long1, long3);
        Assert.assertEquals(string1, shortener.getString(long1));
        Assert.assertEquals(string2, shortener.getString(long2));
        Assert.assertEquals(string3, shortener.getString(long3));
    }

    @Test
    public void testHashMapStorageStrategy() {
        HashMapStorageStrategy hashMapStorageStrategy = new HashMapStorageStrategy();
        Shortener shortener = new Shortener(hashMapStorageStrategy);
        testStorage(shortener);
    }

    @Test
    public void testOurHashMapStorageStrategy()
    {
        StorageStrategy storageStrategy = new OurHashMapStorageStrategy();
        Shortener shortener = new Shortener(storageStrategy);
        testStorage(shortener);
    }

    @Test
    public void testFileStorageStrategy()
    {
        FileStorageStrategy fileStorageStrategy = new FileStorageStrategy();
        Shortener shortener = new Shortener(fileStorageStrategy);
        testStorage(shortener);
    }

    @Test
    public void testHashBiMapStorageStrategy()
    {
        HashBiMapStorageStrategy hashBiMapStorageStrategy = new HashBiMapStorageStrategy();
        Shortener shortener = new Shortener(hashBiMapStorageStrategy);
        testStorage(shortener);
    }

    @Test
    public void testDualHashBidiMapStorageStrategy()
    {
        DualHashBidiMapStorageStrategy dualHashBidiMapStorageStrategy = new DualHashBidiMapStorageStrategy();
        Shortener shortener = new Shortener(dualHashBidiMapStorageStrategy);
        testStorage(shortener);
    }

    @Test
    public void testOurHashBiMapStorageStrategy()
    {
        OurHashBiMapStorageStrategy ourHashBiMapStorageStrategy = new OurHashBiMapStorageStrategy();
        Shortener shortener = new Shortener(ourHashBiMapStorageStrategy);
        testStorage(shortener);
    }
}
