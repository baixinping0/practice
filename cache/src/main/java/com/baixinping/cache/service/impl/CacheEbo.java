package com.baixinping.cache.service.impl;

import com.baixinping.cache.dao.CacheDao;
import com.baixinping.cache.service.CacheEbi;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CacheEbo implements CacheEbi{
    @Resource
    CacheDao cacheDao;
    @Override
    public void input(String key, String value) {
        cacheDao.input(key,value);
    }

    @Override
    public String get(String key) {
        return cacheDao.get(key);
    }
}
