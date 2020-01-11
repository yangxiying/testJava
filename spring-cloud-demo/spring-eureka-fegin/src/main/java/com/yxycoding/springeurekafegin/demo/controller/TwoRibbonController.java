package com.yxycoding.springeurekafegin.demo.controller;

 import com.yxycoding.springeurekafegin.demo.service.OneClientService;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class TwoRibbonController {

    @Autowired
    OneClientService oneClientService;

    @GetMapping("/hir")
    public String hi(@RequestParam String name ){
        return oneClientService.hiService(name);
    }

    @GetMapping("/hirib")
    public String hiRibbon(@RequestParam String name ){
        return "hi " + name +" this is ribbon";
    }
}
