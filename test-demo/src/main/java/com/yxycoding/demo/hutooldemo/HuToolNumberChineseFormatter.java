package com.yxycoding.demo.hutooldemo;/*
 * @author yangxy
 * @date 2020/12/24 17:13
 */

import cn.hutool.core.convert.NumberChineseFormatter;

public class HuToolNumberChineseFormatter {

    public static void main(String[] args) {
        String tmpa = NumberChineseFormatter.format( 11,false,false);
        System.out.println(tmpa);


    }
}
