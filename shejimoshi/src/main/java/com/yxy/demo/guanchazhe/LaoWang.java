package com.yxy.demo.guanchazhe;

public class LaoWang implements Person {
    private String name = "小王";

    @Override
    public void getMessage(String msg) {
        System.out.println( name + "接收到了小美的通知，内容是：" + msg);
    }
}
