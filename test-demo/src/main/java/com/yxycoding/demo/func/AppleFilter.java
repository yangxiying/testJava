package com.yxycoding.demo.func;

import com.yxycoding.demo.func.jdk7.AppleFilter7;
import com.yxycoding.demo.func.jdk7.AppleIsGreen;

import java.util.ArrayList;
import java.util.List;

public class AppleFilter {

    /**
     * 选出所 有的绿苹果
     *
     * @param inventory
     * @return
     */
    List<AppleT> filterGreenApples(List<AppleT> inventory) {
        List<AppleT> result = new ArrayList<>();
        for (AppleT apple : inventory) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return (result);
    }

    /**
     * 选出所有的超过150g的苹果
     *
     * @param inventory
     * @return
     */
    List<AppleT> filterHeavyApples(List<AppleT> inventory) {
        List<AppleT> result = new ArrayList<>();
        for (AppleT apple : inventory) {
            if (apple.getWeight() > 150) {
                result.add(apple);
            }
        }
        return (result);
    }

    /**
     * 这 两个方法只有一行不同： if里面 那行条件。可以如下简化：
     * 定义一个接口， Predicate,方法入参是接口，使用时，传入方法
     */

    List<AppleT> filterApples(List<AppleT> inventory, Predicate<AppleT> p) {
        List<AppleT> result = new ArrayList<>();
        for (AppleT apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return (result);
    }

    public static boolean isGreenApple(AppleT apple) {
        return ("green".equals(apple.getColor()));
    }

    public static boolean isHeavyApple(AppleT apple) {
        return (apple.getWeight() > 150);
    }


    List<AppleT> filter7Apples(List<AppleT> inventory, AppleFilter7 p) {
        List<AppleT> result = new ArrayList<>();
        for (AppleT apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return (result);
    }

    public static void main(String[] args) {

        AppleT apple = new AppleT();
        apple.setColor("green");
        apple.setWeight(100);

        AppleT apple2 = new AppleT();
        apple2.setColor("red");
        apple2.setWeight(600);

        List<AppleT> list = new ArrayList<>();
        list.add(apple);
        list.add(apple2);

        AppleFilter appleFilter = new AppleFilter();

        appleFilter.filterGreenApples(list);
        appleFilter.filterHeavyApples(list);


        //可以如下：
        appleFilter.filterApples(list, AppleFilter::isGreenApple);

        appleFilter.filterApples(list, AppleFilter::isHeavyApple);

        //可以简写为,直接写判断，这个要把握好，如果复杂还是写一个方法如上一样调用
        appleFilter.filterApples(list, a -> a.getWeight() > 150);


        /**
         * 在jdk8之前，没有 lambda的写法，可以如下写 匿名的内部类
         */
        List<AppleT> list1 = appleFilter.filterApples(list, new Predicate<AppleT>() {
            @Override
            public boolean test(AppleT a) {
                return a.getWeight() > 150;
            }
        });

        System.out.println( list1.size());

        //如果判断复杂,不是一两句就可以说明的，就需要实现接口类，把接口类的实例传入
        List<AppleT> list2 = appleFilter.filter7Apples(list, new AppleIsGreen());

    }
}

