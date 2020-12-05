package com.yxycoding.demo.mapstruct;/*
 * @author yangxy
 * @date 2020/12/5 11:02
 */

import lombok.Data;

import java.util.Date;

@Data
public class PersonDO {

    private Integer id;

    private String name;

    private int age;

    private Date birthday;

    private String gender;
}
