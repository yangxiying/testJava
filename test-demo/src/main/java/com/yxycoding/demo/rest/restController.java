package com.yxycoding.demo.rest;/*
 * @author yangxy
 * @date 2020/7/10 21:51
 */

import cn.hutool.http.HttpUtil;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/")
public class restController {

    @GetMapping(value = "sayhe")
    public String testSay(){
        return "hello w";
    }

    @PostMapping(value = "posttest", produces = "application/json;charset=utf-8")
    public Object testHi() {
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("id", 123);
        retMap.put("msg", "test");

        Map<String, Object> subMap = new HashMap();
        subMap.put("point", 123456L);
        retMap.put("data", subMap);

        return retMap;
    }


    @PostMapping(value = "otherPost")
    public byte[] qryOtherServer(){
//        String post = HttpUtil.post("http://localhost:8088//posttest", "");
//
//        return  post;


        RestTemplate template = new RestTemplate();

        ResponseEntity<byte[]> exchange = template.exchange("http://localhost:8088//posttest", HttpMethod.POST, null, byte[].class);

        return exchange.getBody();
    }


    @PostMapping(value = "otherPost2")
    public byte[] qryOtherServer2(){
//        String post = HttpUtil.post("http://localhost:8088//posttest", "");
//
//        return  post;


        RestTemplate template = new RestTemplate();

        ResponseEntity<byte[]> exchange = template.exchange("http://localhost:8088//posttest2", HttpMethod.POST, null, byte[].class);

        return exchange.getBody();
    }
}
