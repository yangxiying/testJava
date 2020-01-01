package com.yxycoding.demo.myannotiation.normal;

import com.yxycoding.demo.myannotiation.MyAnnotationOne;

import java.lang.reflect.Method;

public class MyTestAnnotation {
    public static void main(String[] args){
        try {
            //获取Student的Class对象
            Class stuClass = Class.forName("com.yxycoding.demo.myannotiation.normal.MyStudent");


            //说明一下，这里形参不能写成Integer.class，应写为int.class
            Method stuMethod = stuClass.getMethod("study",int.class);

            if(stuMethod.isAnnotationPresent(MyAnnotationOne.class)){
                System.out.println("Student类上配置了CherryAnnotation注解！");
                //获取该元素上指定类型的注解
                MyAnnotationOne cherryAnnotation = stuMethod.getAnnotation(MyAnnotationOne.class);
                System.out.println("name: " + cherryAnnotation.name() + ", age: " + cherryAnnotation.age()
                        + ", score: " + cherryAnnotation.arr()[0]);
            }else{
                System.out.println("Student类上没有配置CherryAnnotation注解！");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
