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
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class DemoServiceBegin {
    final  DemoService1 demoService1;
//    final private DemoService2 demoService2;

//    public static void main(String[] args) {
//        demoService1.demo1("aaaa");
//    }

    public String demoBegin(String str){

        demoService1.demo1("aaaaaaaaa");
        demoService1.demoService2.demo2("demoService1.demoService2.demo2");
        String ret = StrUtil.format("DemoServiceBegin.demoBegin is ret {}",str);
        log.info(ret);

//        demoService2.demo2("cccccccccc");

        return ret;
    }

}
