package com.yxycoding.springeurekaclient.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class OneDemoController {

    @Value("${server.port}")
    String port;
    @GetMapping("/hi")
    public String home(@RequestParam String name){
        return "hi " +name +",i am client from port:"+port;
    }
}
