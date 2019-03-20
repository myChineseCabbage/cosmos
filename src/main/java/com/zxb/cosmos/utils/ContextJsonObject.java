package com.zxb.cosmos.utils;

import com.alibaba.fastjson.JSONObject;

import static com.alibaba.fastjson.util.TypeUtils.castToInt;


public class ContextJsonObject  extends JSONObject {

    public String getAsString(String key,String defaultStr){
        Object value = get(key);
        if(value == null){
            value = defaultStr;
        }
        return value.toString();
    }

}
