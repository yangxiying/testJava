package com.yxycoding.springeurekafegin.demo.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("one-client")
public interface OneClientFeignInterface {

    @GetMapping("/demo/hi")
    String hiService(@RequestParam String name);
}
