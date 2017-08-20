package com.baixinping.cacheClient.cache;

public interface CacheClient {
    public void set(String key, String value) throws Exception;
    public String get(String key) throws Exception;
}
