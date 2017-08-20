package com.baixinping.cache.dao;

public interface CacheDao {
    public void input(String key, String value);
    public String get(String key);
}
