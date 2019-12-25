package com.yxycoding.demo.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest {



    public static void main(String[] args) {

        String testStr ="this is a test. {[sum(a,b,c)]} is ? \r\n the other test  {[sum(q,w,e,r,t)]} is ? ";
        //sum 以下正则匹配 {[sum(  开头，之后任意数据 ，再之后是 )]}
        //查询匹配的group有三个，
        Pattern pattern = Pattern.compile("(\\{\\[sum\\()(.*)(\\)\\]\\})");
        Matcher findMatcher = pattern.matcher(testStr);
        while (findMatcher.find()) {
            System.out.println(findMatcher.group(0));//  结果为：{[sum(a,b,c)]}
            System.out.println(findMatcher.group(1));//  结果为：{[sum(
            System.out.println(findMatcher.group(2));// 结果为：a,b,c
            System.out.println(findMatcher.group(3));//结果为： )]}

            testStr= testStr.replace(findMatcher.group(0),"sumtest  ");
        }

        System.out.println(testStr);
    }
}
