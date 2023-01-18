package com.synergy.productService.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Response {

    public Boolean isRequired(Object obj){
        if(obj == null){
            return  true;
        }
        return false;
    }

    public Map templateSuksesGet(Object objek){
        Map map = new HashMap();
        map.put("data", objek);
        map.put("message", "sukses");
        map.put("status", "200");
        return map;
    }

    public Map templateSuksesPost(Object objek){
        Map map = new HashMap();
        map.put("data", objek);
        map.put("message", "sukses");
        map.put("status", "201");
        return map;
    }

    public Map templateError(Object objek){
        Map map = new HashMap();
        map.put("message", objek);
        map.put("status", "400");
        return map;
    }
    public Map urlNotFound(Object objek){
        Map map = new HashMap();
        map.put("message", objek);
        map.put("status", "404");
        return map;
    }
}

