package com.yxycoding.demo.rest;/*
 * @author yangxy
 * @date 2020/9/3 14:29
 */

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestDemo {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        //如果 请求的地址不通、调不到服务，使用getForObject会拋异常
//        Object forObject = restTemplate.getForObject("http://sdf/sdfsdf", Object.class);

//        如果 请求的地址不通、调不到服务，会拋异常
//        restTemplate.exchange("http://sdf/sdfsdf", HttpMethod.GET,null,Object.class);


        ResponseEntity<Object> exchange = restTemplate.exchange("http://jifen.xiaomy.net/prod-api/videodir/HYXY0.m3u8", HttpMethod.GET, null, Object.class);

        System.out.println("======="+exchange.getStatusCode());
    }
}
