package com.yxycoding.demo.httpreq.getpost;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

@Slf4j
public class httptest {

    public static void main(String[] args) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("city", "北京");

        String url ="http://10.34.1.199/Home/Login?userName=HZMDZD&userPass=123456";
        String result= HttpUtil.post(url, paramMap);
        log.info("==");


        HttpResponse res = HttpRequest.post(url).execute();
        res.header("Set-Cookie");
        res.body();
        log.info("ssss");

    }
}
