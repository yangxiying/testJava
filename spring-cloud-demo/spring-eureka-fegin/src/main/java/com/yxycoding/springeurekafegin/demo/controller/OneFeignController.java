package com.yxycoding.springeurekafegin.demo.controller;

import com.yxycoding.springeurekafegin.demo.feignclient.OneClientFeignInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class OneFeignController {


    @Autowired
    OneClientFeignInterface oneClientFeignInterface;

    @GetMapping("/hi")
    public String hi(@RequestParam String name ){
        return oneClientFeignInterface.hiService(name);
    }

    @GetMapping("/hifeign")
    public String hiRibbon(@RequestParam String name ){
        return "hi " + name +" this is feign demo";
    }
}
