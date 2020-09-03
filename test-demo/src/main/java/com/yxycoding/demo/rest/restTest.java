package com.yxycoding.demo.rest;/*
 * @author yangxy
 * @date 2020/7/10 21:32
 */

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

@Component
public class restTest {

    public static void main(String[] args) {
//        RestTemplate template = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        RestTemplate template = new RestTemplate();
//        template.setRequestFactory(new HttpComponentsClientHttpRequestFactory());

        //设置Http的Header
        HttpHeaders headers = new HttpHeaders();
//        headers.add("content-type","application/json;charset=utf-8");

        HttpEntity httpEntity = new HttpEntity(null, headers);
        ResponseEntity<byte[]> exchange = template.exchange("http://localhost:8088//posttest", HttpMethod.POST, httpEntity, byte[].class);
//        ResponseEntity<Object> exchange = template.exchange("http://localhost:10001/otherPost", HttpMethod.POST, null, Object.class);


        System.out.println("ssssssssssss");

        byte[] body = exchange.getBody();
        //判断返回参数是否是经过gzip压缩的，压缩的需要进行解压缩使用
        List<String> strings = exchange.getHeaders().get("Content-Encoding");
        if( exchange.getHeaders().containsKey("Content-Encoding") &&
                strings.contains("gzip")) {
            System.out.println("gzip");
            System.out.println(uncompressToStr(exchange.getBody()));
        }else {
            System.out.println("no gzip");

            System.out.println(exchange.getBody());
            if(null!=body &&body.length>0){
                System.out.println(new String(body,0,body.length));
            }
        }


//        byte[] uncompress = uncompress(exchange.getBody());
//        System.out.println(new String(uncompress,0,uncompress.length));



//        ByteArrayInputStream inputStream = new ByteArrayInputStream(exchange.getBody());
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//
//        try {
//            GZIPInputStream gzipOutputStream = new GZIPInputStream(inputStream);
//            byte[] buffer = new byte[256];
//            int n;
//            while ((n = gzipOutputStream.read(buffer)) >= 0) {
//                baos.write(buffer, 0, n);
//            }
//        }catch (Exception e){
//            System.out.println(e);
//        }
//        System.out.println(exchange);
//        System.out.println(baos.toString());
    }



    static byte[] uncompress(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try {
            GZIPInputStream ungzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = ungzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        return out.toByteArray();
    }


    static String uncompressToStr(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try {
            GZIPInputStream ungzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = ungzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        return out.toString();
    }

}
