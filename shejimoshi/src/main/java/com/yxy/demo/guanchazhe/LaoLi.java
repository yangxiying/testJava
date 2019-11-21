package com.yxy.demo.guanchazhe;

public class LaoLi implements Person {
    private String name = "小李";

    @Override
    public void getMessage(String msg) {
        System.out.println(name + "接收到了小美的通知，内容是：" + msg);
    }
}
