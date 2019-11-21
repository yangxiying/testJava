package com.yxy.demo.daili;

//懒汉式写法（线程安全）
public class Singleton2 {

    private static Singleton2 singleton2;
    private Singleton2(){}

    public static synchronized Singleton2 getInstance(){
        if(null == singleton2){
            singleton2 = new Singleton2();
        }
        return singleton2;
    }
}
