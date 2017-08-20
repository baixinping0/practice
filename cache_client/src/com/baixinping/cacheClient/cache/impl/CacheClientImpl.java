package com.baixinping.cacheClient.cache.impl;

import com.baixinping.cacheClient.cache.CacheClient;
import com.baixinping.cacheClient.utils.HttpUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class CacheClientImpl implements CacheClient{
    public static final String CACHE_HOST = "cacheHost";
    public static final String USER_KEY = "userKey";

    public static final String URL_SET_HEAD = "http://";
    public static final String URL_SET_FOOT = "/cache/input";

    public static final String URL_GET_HEAD = "http://";
    public static final String URL_GET_FOOT = "/cache/get";


    @Override
    public void set(String key, String value) throws Exception {
        String userKey = getUserKey();
        String cacheHost = getCacheHost();
        HttpUtils.doPost(URL_SET_HEAD+cacheHost+URL_SET_FOOT, userKey+"-"+key+"="+value);
    }
    @Override
    public String get(String key)  throws Exception{
        String userKey = getUserKey();
        String cacheHost = getCacheHost();
        return HttpUtils.doPost(URL_GET_HEAD+cacheHost+URL_GET_FOOT, "key=" +userKey + "-" +key);
    }

    public String getUserKey() throws Exception {
        Properties prop = new Properties();
        try {
            InputStream ins = getClass().getResourceAsStream("/application.properties");
            prop.load(ins);
        } catch(IOException e) {
            e.printStackTrace();
        }
        String userKey = prop.getProperty(USER_KEY);
        String cacheHost = prop.getProperty(CACHE_HOST);
        if (userKey == null || "".equals(userKey))
            throw new Exception("没有配置userkey");
        if (cacheHost == null || "".equals(cacheHost))
            throw new Exception("没有配置cacheHost");
        return userKey;
    }


    public String getCacheHost() throws Exception {
        Properties prop = new Properties();
        try {
            InputStream ins = getClass().getResourceAsStream("/application.properties");
            prop.load(ins);
        } catch(IOException e) {
            e.printStackTrace();
        }
        String cacheHost = prop.getProperty(CACHE_HOST);
        if (cacheHost == null || "".equals(cacheHost))
            throw new Exception("没有配置cacheHost");
        return cacheHost;
    }


}
