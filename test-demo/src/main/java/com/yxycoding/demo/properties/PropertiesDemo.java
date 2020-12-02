package com.yxycoding.demo.properties;/*
 * @author yangxy
 * @date 2020/9/3 14:40
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PropertiesDemo {

    @Autowired
    private static PropertiesComm propertiesComm;


    public static void main(String[] args) {


        propertiesComm.ssss();
//        System.out.println(propertiesComm.getPerstr());

    }
}
