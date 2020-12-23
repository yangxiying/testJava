package com.yxycoding.demo.lombokdemo;/*
 * @author yangxy
 * @date 2020/12/23 12:05
 */

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * @Getter/@Setter注解，我们再也不用编写getter/setter方法了
 */
public class GetterSetterExample {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private Integer age;

    public static void main(String[] args) {
        GetterSetterExample example = new GetterSetterExample();
        example.setName("test");
        example.setAge(20);
        System.out.printf("name:%s age:%d",example.getName(),example.getAge());
    }
}
