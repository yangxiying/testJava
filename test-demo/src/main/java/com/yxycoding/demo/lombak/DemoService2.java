package com.yxycoding.demo.lombak;/*
 * @author yangxy
 * @date 2020/11/4 14:15
 */

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Slf4j
public class DemoService2 {

    public String demo2(String str){

        String ret = StrUtil.format("DemoService2.demo2 is ret {}",str);
        log.info(ret);
        return ret;
    }

}
