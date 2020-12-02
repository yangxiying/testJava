package com.yxycoding.demo.hashmap;/*
 * @author yangxy
 * @date 2020/9/5 10:17
 */


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HashmapDemo {

    public static void main(String[] args) {

        ConcurrentHashMap<Object, Object> manager = new ConcurrentHashMap<>();

        manager.put("1","11111111");
        manager.put("1","22222222");
        manager.put("1","33333333");
        manager.put("1","44444444");
        manager.put("1","55555555");

        manager.put("2","11111111");
        manager.put("2","22222222");
        manager.put("2","33333333");
        manager.put("2","44444444");
        manager.put("2","55555555");

        //验证同一个key，后面的会把前面的给覆盖了。


        System.out.println(manager.containsValue("11111111"));//false
        System.out.println(manager.containsValue("55555555"));//true

        manager.searchValues(1L,(v)->{
            return v.equals("55555555") ?  v : null;
        });

        String aa="55555555";
        Object search = manager.search(1, (k, v) -> {
            return v.equals(aa) ? k : "_";
        });
        manager.remove(search);
        System.out.println("manager.size()==="+manager.size());

        Map<String,String> hamap = new HashMap<>();
        hamap.put("2","111111");
        hamap.put("2","222222");
        hamap.put("2","333333");
        hamap.put("2","444444");
        hamap.put("2","555555");

        hamap.put("1","111111");
        hamap.put("1","222222");
        hamap.put("1","333333");
        hamap.put("1","444444");
        hamap.put("1","555555");
        System.out.println("hamap.size()==="+hamap.size());


    }
}
