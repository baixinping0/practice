package com.baixinping.cache.controller;

import com.baixinping.cache.entity.Response;
import com.baixinping.cache.service.CacheEbi;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("cache")
public class CacheController {

    @Resource
    CacheEbi cacheEbi;
    @RequestMapping("/input")
    public void input(HttpServletRequest request){
        Map<String, String[]> map = request.getParameterMap();
        Set<Map.Entry<String, String[]>> set = map.entrySet();
        for (Map.Entry<String, String[]> entry : set)
            cacheEbi.input(entry.getKey(), (entry.getValue())[0]);
    }

    @RequestMapping("/get")
    public String get(HttpServletRequest request){
        Map<String, String[]> map = request.getParameterMap();
        String key = (request.getParameterValues("key"))[0];
        return cacheEbi.get(key);
    }


}
