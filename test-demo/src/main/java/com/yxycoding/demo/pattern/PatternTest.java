package com.yxycoding.demo.pattern;

import javax.sound.midi.Soundbank;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest {


    public static void main(String[] args) {

        String instr ="this is";
        //快速匹配字符串,该方法适合用于只匹配一次,且匹配全部字符串.
        boolean matches = Pattern.matches(".*", instr);
        System.out.println(matches);
        System.out.println("==============================================");


        String testStr = "this is a test. {[sum(a,b,c)]} is ?  ";
        //sum 以下正则匹配 {[sum(  开头，之后任意数据 ，再之后是 )]}
        //查询匹配的group有三个，
        Pattern pattern = Pattern.compile("(\\{\\[sum\\()(.*)(\\)\\]\\})");
//        Pattern pattern = Pattern.compile("\\{\\[sum\\(.*\\)\\]\\}");
        Matcher findMatcher = pattern.matcher(testStr);
        while (findMatcher.find()) {
            System.out.println(findMatcher.group(0));//  结果为：{[sum(a,b,c)]}
            System.out.println(findMatcher.group(1));//  结果为：{[sum(
            System.out.println(findMatcher.group(2));// 结果为：a,b,c
            System.out.println(findMatcher.group(3));//结果为： )]}

            testStr = testStr.replace(findMatcher.group(0), "sumtest  ");
        }

        System.out.println(testStr);
        System.out.println("==============================================");


        testStr = "this is a test. {[sum(a,b,c)]} is ?  the other test  {[sum(q,w,e,r,t)]} is ? ";
        //sum 以下正则匹配 {[sum(  开头，之后任意数据 ，再之后是 )]}
//        查询匹配的group有三个，
        pattern = Pattern.compile("(\\{\\[sum\\()(.*)(\\)\\]\\})");
        findMatcher = pattern.matcher(testStr);
        while (findMatcher.find()) {
            System.out.println(findMatcher.group(0));//  结果为：{[sum(a,b,c)]} is ?  the other test  {[sum(q,w,e,r,t)]}
            System.out.println(findMatcher.group(1));//  结果为：{[sum(
            System.out.println(findMatcher.group(2));// 结果为：a,b,c)]} is ?  the other test  {[sum(q,w,e,r,t
            System.out.println(findMatcher.group(3));//结果为： )]}

            testStr = testStr.replace(findMatcher.group(0), "sumtest  ");
        }

        System.out.println(testStr);
        System.out.println("==============================================");


        testStr = "this is a test. {[sum(-3.09,4,5.05)]} is ?  the other test  {[sum(11,22,33,44)]} is ?  the other test  {[sum(111)]} is ?";

//        pattern = Pattern.compile("\\{\\[sum\\(|");
//        findMatcher = pattern.matcher(testStr);
//        boolean matches1 = findMatcher.lookingAt();


        //sum 以下正则匹配 {[sum(  开头，之后(数字 . -)有小数，有负数 ，再之后是 )]}
//        查询匹配的group有三个，


        pattern = Pattern.compile("(\\{\\[sum\\()(((\\-?)(\\d+\\.*\\d*)(,)?)+)(\\)\\]\\})");
        findMatcher = pattern.matcher(testStr);
        boolean matches1 = findMatcher.lookingAt();
        System.out.println(matches1);
        while (findMatcher.find()) {
            System.out.println(findMatcher.group(0));//  结果为：{[sum(a,b,c)]} is ?  the other test  {[sum(q,w,e,r,t)]}
            System.out.println(findMatcher.group(1));//  结果为：{[sum(
            System.out.println(findMatcher.group(2));// 结果为：a,b,c)]} is ?  the other test  {[sum(q,w,e,r,t
            System.out.println(findMatcher.group(3));//结果为： )]}

            testStr = testStr.replace(findMatcher.group(0), "sumtest  ");
            System.out.println("-------------------------------------");
        }

        //{[sum(3.09,4,5.05)]}
        //{[sum(
        //3.09,4,5.05
        //5.05
        //-------------------------------------
        //{[sum(11,22,33,44)]}
        //{[sum(
        //11,22,33,44
        //44
        //-------------------------------------
        //{[sum(111)]}
        //{[sum(
        //111
        //111

        System.out.println(testStr);
        System.out.println("==============================================");


    }
}
