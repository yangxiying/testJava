package com.yxycoding.demo.hash;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * jdk 1.8 提供了 MessageDigest 的算法类
 */
public class MessageDigestDemo {

    /**
     * jdk 1.8 进行md5加密码
     *
     * @throws NoSuchAlgorithmException
     */
    public void md5Cacl(String inStr) throws Exception {
        //算法名不区分大小写
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(inStr.getBytes("UTF-8"));

        byte[] result = messageDigest.digest();

        System.out.println("md5加密的16进制为：" + new BigInteger(1, result).toString(16));

    }

    public void sha1Cacl(String inStr) throws Exception {

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");

        messageDigest.update(inStr.getBytes("UTF-8"));

        byte[] result = messageDigest.digest();

        System.out.println("sha-1加密的16进制为：" + new BigInteger(1, result).toString(16));

    }


    public static void main(String[] args) throws Exception {
        MessageDigestDemo demo = new MessageDigestDemo();
        demo.md5Cacl("这是测试的");
        demo.sha1Cacl("这是测试的");
    }
}
