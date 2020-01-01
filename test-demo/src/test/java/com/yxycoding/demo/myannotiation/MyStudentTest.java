package com.yxycoding.demo.myannotiation;

import com.yxycoding.demo.myannotiation.aop.MyStudentTwo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class MyStudentTest {

    @Autowired
    MyStudentTwo myStudentTwo;
    @Test
    void study() {
//        MyStudent myStudent = new MyStudent();
//        myStudent.study(1);
log.info("==================================");
        myStudentTwo.study(2);
    }
}