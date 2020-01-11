package com.yxycoding.springeurekaribbon.demo.controller;

import com.yxycoding.springeurekaribbon.demo.service.OneClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.rtf.RTFEditorKit;

@RestController
@RequestMapping("/demo")
public class OneRibbonController {

    @Autowired
    OneClientService oneClientService;

    @GetMapping("/hi")
    public String hi(@RequestParam String name ){
        return oneClientService.hiService(name);
    }

    @GetMapping("/hirib")
    public String hiRibbon(@RequestParam String name ){
        return "hi " + name +" this is ribbon";
    }
}
