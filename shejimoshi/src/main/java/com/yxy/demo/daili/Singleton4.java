package com.yxy.demo.daili;

//静态内部类
public class Singleton4 {
    public static class SingletonHolder {
        private static final Singleton4 INSTANCE = new Singleton4();
    }

    private Singleton4(){}

    public static Singleton4 getInstance(){
        return SingletonHolder.INSTANCE;
    }
}
