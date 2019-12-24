package com.yxycoding.mocker.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yxycoding.mocker.demo.entiy.User;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 使用jnuit5的，不用把类和方法定义为publc的
 * 没有@before，区分为@BeforeAll (只执行一次)和 @BeforeEach (每个方法之前都会执行一次)
 * @After 同理
 */
@SpringBootTest
class MockmvcTestTest {

    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @BeforeEach
    void initeach() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();//建议使用这种
    }

    /**
     *
     */
    @BeforeAll
    static void initall() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();//建议使用这种
    }


    @Test
    void doQry() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/welcome")
                .param("name", "yxycoding")
                .accept(MediaType.TEXT_HTML_VALUE))
                // .andExpect(MockMvcResultMatchers.status().isOk())             //等同于 assertEquals(200,status);
                // .andExpect(MockMvcResultMatchers.content().string("hello lvgang"))    //等同于 assertEquals("welcome : yxycoding",content);
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果

        assertEquals(200, status);                        //断言，判断返回代码是否正确
        assertEquals("welcome : yxycoding", content);            //断言，判断返回的值是否正确

    }

    @Test
    void dopost() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/welcome")
                .param("name", "yxycoding")
                .accept(MediaType.TEXT_HTML_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果

        assertEquals(200, status);                        //断言，判断返回代码是否正确
        assertEquals("welcome : yxycoding", content);            //断言，判断返回的值是否正确
    }

    @Test
    void dopostJson() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        User user = new User();
        user.setUserName("yxyCoding");
        user.setUserAddress("BeiJing");
        String jsonString = mapper.writeValueAsString(user);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/welcomejson")
//                .param("name","yxycoding")
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果

        System.out.println("返回值：" + content);

        assertEquals(200, status);                        //断言，判断返回代码是否正确
//        assertEquals("welcome : yxycoding",content);            //断言，判断返回的值是否正确

    }
}