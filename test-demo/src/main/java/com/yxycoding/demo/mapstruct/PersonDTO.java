package com.yxycoding.demo.mapstruct;/*
 * @author yangxy
 * @date 2020/12/5 11:03
 */

import lombok.Data;

import java.util.Date;

@Data
public class PersonDTO {

    private String userName;

    private Integer age;

    private Date birthday;

    private Gender gender;
}
