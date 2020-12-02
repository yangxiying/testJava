package com.yxycoding.demo.imgs;/*
 * @author yangxy
 * @date 2020/9/20 09:12
 */

import cn.hutool.core.img.Img;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import org.springframework.util.ResourceUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;

public class imgChang {

    public static void main(String[] args) throws FileNotFoundException {

        String path1 = System.getProperty("user.dir");
        System.out.println(path1); // .... /testJava/test-demo

        //   获取的是项目的编译后的目录
        //   ..... /testJava/test-demo/target/classes/
        String path = ResourceUtils.getURL("classpath:").getPath();


        String srcImgPath = path+"/imgs/hua-1.JPG";
//        Img from = Img.from(FileUtil.file(srcImgPath));

        BufferedImage read = ImgUtil.read(FileUtil.file(srcImgPath));
        int width = read.getWidth();
        int height= read.getHeight();

//        from.srcImage.getHeight();
//        Image img = from.getImg();
//        int height1 = img.getHeight(null);
//        int height = Img.from(FileUtil.file(srcImgPath)).getImg().getHeight(null);
        System.out.println(height);
        System.out.println(width);

        //scale 缩放图片
        String tarImgPath = path+"/imgs/s_hua_1-sacl1.JPG";
        float is = 0.123123123123f;
        ImgUtil.scale(
                read,
                FileUtil.file(tarImgPath),
                is//缩放比例
        );

        read.flush();


        //缩放为固定的宽高。
//        tarImgPath = path+"/imgs/hua-1-sacl2.jpg";
//        new Color(0,0,0,0);
//        ImgUtil.scale(
//                FileUtil.file(srcImgPath),
//                FileUtil.file(tarImgPath),
//                400,300,new Color(0,0,0,0)
//        );

        //cut 剪裁图片
//        tarImgPath = path+"/imgs/hua-1-cut1.jpg";
//        ImgUtil.cut(FileUtil.file(srcImgPath),FileUtil.file(tarImgPath),
//                new Rectangle(200, 200, 100, 100));

    }
}
