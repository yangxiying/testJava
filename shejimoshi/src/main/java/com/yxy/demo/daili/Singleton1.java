package com.yxy.demo.daili;

//懒汉写法（线程不安全）
public class Singleton1 {
    private static  Singleton1 singleton1;
    private Singleton1(){

    }

    public static Singleton1 getInstance(){
        if(null == singleton1){
            singleton1 = new Singleton1();
        }
        return singleton1;
    }
}
