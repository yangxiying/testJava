package com.yxycoding.demo.lombak;/*
 * @author yangxy
 * @date 2020/11/4 14:15
 */

import cn.hutool.core.util.StrUtil;
import com.yxycoding.demo.lombak.dao.DemoPojo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Slf4j

public class DemoService1 {

//    final DemoPojo demoPojo;
//
//    @Autowired
//    public DemoService1(DemoPojo demoPojo){
//     this.demoPojo =   demoPojo;
//    }

    final DemoService2 demoService2;

    public DemoService1(DemoService2 demoService2){
        this.demoService2 = demoService2;
    }

    public String demo1(String str){

        String ret = StrUtil.format("DemoService1.demo1 is ret {}",str);
        log.info(ret);
        return ret;
    }


    public String demo(){

        String ret = StrUtil.format("DemoService1.demo1 is ret {}","@Bean");
        log.info(ret);
        return ret;
    }
}
