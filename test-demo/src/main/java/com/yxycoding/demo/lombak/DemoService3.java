package com.yxycoding.demo.lombak;/*
 * @author yangxy
 * @date 2020/11/4 14:15
 */

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
//@RequiredArgsConstructor
public class DemoService3 {

//    final private String strDemo;

    public String demo3(String str){


        String ret = StrUtil.format("DemoService3.demo3 is ret {}",str);
        log.info(ret);
        return ret;
    }

}
