package com.yxycoding.mocker.demo.controller;

import com.yxycoding.mocker.demo.entiy.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockmvcTest {


    @GetMapping("/welcome")
    public String doQryGet(String name){
        return "welcome : " + name;
    }

    @PostMapping("/welcome")
    public String doQryPost(String name){
        return "welcome : " + name;
    }


    @PostMapping("/welcomejson")
    public String doQryPost(@RequestBody User user){
        return "welcome : " + user.getUserName() + "_"+user.getUserAddress();
    }

}
