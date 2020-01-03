package com.yxycoding.demo.func.jdk7;

import com.yxycoding.demo.func.AppleT;

public class AppleWeight implements AppleFilter7 {
    @Override
    public boolean test(AppleT apple) {
        return (apple.getWeight() > 150);
    }
}
