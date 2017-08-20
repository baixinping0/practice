package com.baixinping.cache.common.cache;

import java.util.List;

public interface Cache {

    /**
     * 通过建获取值
     * @param key
     * @return
     */
    public String getValueFromCache(String key);

    /**
     * 存入键值对
     * @param key
     * @param value
     */
    public void setValueToCache(String key,String value);

    /**
     * 通过模块名获取所有的key
     * @param model
     * @return
     */
    public String getKeys(String model);

}
