package com.yxycoding.demo.hutooldemo;

/*
 *
 * @author yangxy
 * @date 2020/12/25 10:20
 */

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.map.MapUtil;
import com.google.common.collect.Maps;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MapUtilDemo {

    public static void main(String[] args) {
        HashMap<String, Map > mapa = MapUtil.newHashMap();

        mapa.put("H4-5", Maps.newHashMap());
        mapa.put("H3-4", Maps.newHashMap());
        mapa.put("H2-3", Maps.newHashMap());
        mapa.put("H1-2", Maps.newHashMap());

        System.out.println(mapa);//{H2-3={}, H3-4={}, H4-5={}, H1-2={}}  看结果，存放无序的
//        for (Map.Entry<String, Map> stringMapEntry : mapa.entrySet()) {
//            String key = stringMapEntry.getKey();
//            System.out.println(key);
//        }

        TreeMap<String, Map> sortmap = MapUtil.sort(mapa);

        System.out.println(mapa);//{H2-3={}, H3-4={}, H4-5={}, H1-2={}}
        System.out.println(sortmap);//{H1-2={}, H2-3={}, H3-4={}, H4-5={}}

        sortmap.put("H0-1",null);
        System.out.println(sortmap);//{H0-1=null, H1-2={}, H2-3={}, H3-4={}, H4-5={}}

        sortmap.put("H2.5-3",null);
        System.out.println(sortmap);//{H0-1=null, H1-2={}, H2-3={}, H2.5-3=null, H3-4={}, H4-5={}}
        //treemap默认进度key的排序


        Map<String, Map> buildmap = MapUtil.createMap(HashMap.class);
        buildmap.putAll(sortmap);
        buildmap.put("H1.5-2",null);
        System.out.println(buildmap);//{H1.5-2=null, H2-3={}, H3-4={}, H2.5-3=null, H4-5={}, H0-1=null, H1-2={}}



        List<String> lista = new ArrayList<>();
        lista.add("D4-5");
        lista.add("D5-6");
        lista.add("D3-4");
        lista.add("D2-3");
        lista.add("D1-2");

        System.out.println(lista); //[D4-5, D5-6, D3-4, D2-3, D1-2] 按加入顺序


        Map<Object, Object> mapt = MapUtil.createMap(TreeMap.class);

        mapt.put("H3-4", Maps.newHashMap());
        mapt.put("H4-5", Maps.newHashMap());
        mapt.put("H2-3", Maps.newHashMap());
        mapt.put("H1-2", Maps.newHashMap());

        System.out.println(mapt);//{H1-2={}, H2-3={}, H3-4={}, H4-5={}} 排序

        ConcurrentHashMap<Object, Object> mapCon = MapUtil.newConcurrentHashMap();

        mapCon.put("H3-4", Maps.newHashMap());
        mapCon.put("H4-5", Maps.newHashMap());
        mapCon.put("H2-3", Maps.newHashMap());
        mapCon.put("H1-2", Maps.newHashMap());

        System.out.println(mapCon);////{H1-2={}, H2-3={}, H3-4={}, H4-5={}}排序


        Map<Object, Object> mapLink = new LinkedHashMap();
        mapLink.put("H3-4", Maps.newHashMap());
        mapLink.put("H4-5", Maps.newHashMap());
        mapLink.put("H2-3", Maps.newHashMap());
        mapLink.put("H1-2", Maps.newHashMap());

        System.out.println(mapLink);//{H3-4={}, H4-5={}, H2-3={}, H1-2={}}  按存放顺序

    }
}
