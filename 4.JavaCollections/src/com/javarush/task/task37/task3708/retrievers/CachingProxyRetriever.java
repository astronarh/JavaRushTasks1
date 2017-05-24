package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.Storage;

/**
 * Created by ShkerdinVA on 24.05.2017.
 */
public class CachingProxyRetriever implements Retriever{
    private Storage storage;
    private OriginalRetriever originalRetriever;
    private LRUCache<Long, Object> lruCache;

    public CachingProxyRetriever(Storage storage) {
        this.storage = storage;
        this.originalRetriever = new OriginalRetriever(storage);
        lruCache = new LRUCache<>(16);
    }

    @Override
    public Object retrieve(long id) {
        Object object = lruCache.find(id);
        if (object == null) {
            object = originalRetriever.retrieve(id);
            lruCache.set(id, object);
        }
        return object;
    }
}
