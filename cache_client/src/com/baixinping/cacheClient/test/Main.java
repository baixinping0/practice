package com.baixinping.cacheClient.test;

import com.baixinping.cacheClient.cache.CacheClient;
import com.baixinping.cacheClient.cache.impl.CacheClientImpl;
import org.junit.Test;

public class Main {
    @Test
    public void testSet() throws Exception {
        CacheClient cacheClient = new CacheClientImpl();
        cacheClient.set("aaa", "哈哈");
    }
    @Test
    public void testGet() throws Exception {
        CacheClient cacheClient = new CacheClientImpl();
        String aa = cacheClient.get("aaa");
        System.out.println("s   " + aa);
    }


}
