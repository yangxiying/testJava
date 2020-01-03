package com.yxycoding.demo.func.jdk7;

import com.yxycoding.demo.func.AppleT;
import com.yxycoding.demo.func.Predicate;

public class AppleIsGreen implements AppleFilter7 {


    @Override
    public boolean test(AppleT apple) {
        return ("green".equals(apple.getColor()));
    }
}
