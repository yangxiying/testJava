package com.yxycoding.demo.properties;/*
 * @author yangxy
 * @date 2020/9/3 14:43
 */

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
//@Data
@Slf4j
public class PropertiesComm {

    @Value("${per.sss}")
    private List<String> perList;

    @Value("${server.port}")
    private String perstr;

    public void ssss(){

        log.info(perstr);
    }

}
