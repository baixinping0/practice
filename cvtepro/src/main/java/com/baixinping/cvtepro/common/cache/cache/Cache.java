package com.baixinping.cvtepro.common.cache.cache;

import java.util.List;

public interface Cache {
	public <T> T getValueFromCache(String model, String chcheKey, Class<T> clazz);
   /**
    * 获取modellist集合
    * @param model 集合所属模块
    * @param chcheKey 集合在模块中的id
    * @param clazz 集合中存放的数据类型
    * @return
    */
    public <T> List<T> getListFromCache(String model, String chcheKey, Class<T> clazz);
    /**
     * 向chche中存放对象(此对象可以是list，set集合。也可以时单个对象)
     * @param model 对象所属的模块名
     * @param chcheKey 对象存放在chche中特定的模块中的id
     * @param value 对象
     */
    public void setValueToCache(String model, String chcheKey, Object value);
}
