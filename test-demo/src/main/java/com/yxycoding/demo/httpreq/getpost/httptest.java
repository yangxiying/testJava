package com.yxycoding.demo.httpreq.getpost;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class httptest {

    public static void main(String[] args) throws JsonProcessingException {
//        HashMap<String, Object> paramMap = new HashMap<>();
//        paramMap.put("city", "北京");
//
//        String url ="http://10.34.1.199/Home/Login?userName=HZMDZD&userPass=123456";
//        String result= HttpUtil.post(url, paramMap);
//        log.info("==");
//
//
//        HttpResponse res = HttpRequest.post(url).execute();
//        res.header("Set-Cookie");
//        res.body();
//        log.info("ssss");

//        String post = HttpUtil.post("http://localhost:8088//posttest2", "");
//
//        System.out.println(post);



        String ss = "{ \n" +
                "  \"data\" : {\n" +
                "    \"point\" : 123456\n" +
                "  },\n" +
                "  \"msg\" : \"test\",\n" +
                "  \"id\" : 123\n" +
                "}";

        Map retmap = new HashMap();
        ObjectMapper objectMapper = new ObjectMapper();
        retmap = objectMapper.readValue(ss,Map.class);

        System.out.println("wer");

    }
}
