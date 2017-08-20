package com.baixinping.cache.dao.impl;

import com.baixinping.cache.common.cache.Cache;
import com.baixinping.cache.dao.CacheDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class CacheImpl implements CacheDao{
    @Resource
    Cache cache;
    public void setCache(Cache cache) {
        this.cache = cache;
    }

    @Override
    public void input(String key, String value) {
        cache.setValueToCache(key, value);
    }

    @Override
    public String get(String key) {
        return cache.getValueFromCache(key);
    }
}
