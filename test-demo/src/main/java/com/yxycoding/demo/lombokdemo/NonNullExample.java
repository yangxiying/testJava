package com.yxycoding.demo.lombokdemo;/*
 * @author yangxy
 * @date 2020/12/23 12:02
 */

import lombok.NonNull;

/*
方法上使用@NonNull注解可以做非空判断，如果传入空值的话会直接抛出NullPointerException。
 */
public class NonNullExample {
    private String name;
    public NonNullExample(@NonNull String name){
        System.out.println(name);
        this.name = name;
    }

    public static void main(String[] args) {
        new NonNullExample("test");
        //会抛出NullPointerException
        new NonNullExample(null);
    }

}
