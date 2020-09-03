package com.yxycoding.demo.file;

import cn.hutool.core.io.FileUtil;
import com.yxycoding.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.naming.NamingEnumeration;
import java.awt.image.BufferedImage;
import java.io.*;

@RestController
@RequestMapping(value = "/test")
@Slf4j
public class FileTest {


    //    @Test
    @GetMapping(value = "/pic", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
//    @GetMapping(value = "/pic")
    public BufferedImage getPic() throws IOException {
        BufferedInputStream inputStream = FileUtil.getInputStream("/Users/yangxiying/Downloads/test.png");
        log.info("ssss");
        BufferedImage read = ImageIO.read(inputStream);

        return read;
    }


    //

    /**
     * 测试这个可用.必须返回为  byte[]
     * postman测试直接显示图片
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/pic2", produces = MediaType.IMAGE_PNG_VALUE)
      public byte[] getPic2() throws IOException {

        byte[] bytes = FileUtil.readBytes("/Users/yangxiying/Downloads/test.png");
        return bytes;
    }

    @GetMapping(value = "/pic4", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] getPic4() throws IOException {

        byte[] bytes = FileUtil.readBytes("/Users/yangxiying/Downloads/test.png");
        return bytes;
    }


    @RequestMapping(value = "/pic3",produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public BufferedImage getImage() throws IOException {
        return ImageIO.read(new FileInputStream(new File("/Users/yangxiying/Downloads/test.png")));
    }



    //    @Test
//    @GetMapping(value = "/pdf",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @GetMapping(value = "/pdf")
    public BufferedOutputStream getpdf() throws IOException {
        BufferedOutputStream outputStream = FileUtil.getOutputStream("/Users/yangxiying/Downloads/test.pdf");
        return outputStream;
    }


    /**
     *  测试可用
     *  1、如果MediaType.APPLICATION_PDF_VALUE，postman测试提示下载
     *  2、MediaType.APPLICATION_OCTET_STREAM_VALUE，postman测试显示地堆乱码
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/pdf2", produces = {MediaType.APPLICATION_PDF_VALUE})
    public byte[] getpdf2() throws IOException {
        byte[] bytes = FileUtil.readBytes("/Users/yangxiying/Downloads/test.pdf");
        return bytes;
    }
    @GetMapping(value = "/pdf2-2", produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE})
    public byte[] getpdf2_2() throws IOException {
        byte[] bytes = FileUtil.readBytes("/Users/yangxiying/Downloads/test.pdf");
        return bytes;
    }

    @GetMapping(value = "/hello")
    public String qryHello(String name) {

        return "hello " + name;
    }

    @GetMapping(value = "/he", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User qryHe(String name) {

        User user = new User();
        user.setName(name);
        user.setAge(18);
        user.setSex("未知");
        return user;
    }
}
