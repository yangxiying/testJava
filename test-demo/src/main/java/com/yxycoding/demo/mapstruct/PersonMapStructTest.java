package com.yxycoding.demo.mapstruct;/*
 * @author yangxy
 * @date 2020/12/5 11:10
 */

import java.util.Date;

public class PersonMapStructTest {
    public static void main(String[] args) {
        PersonDO personDO = new PersonDO();

        personDO.setName("Hollis");

        personDO.setAge(26);

        personDO.setBirthday(new Date());

        personDO.setId(1);

        personDO.setGender(Gender.NAN.name());

        PersonDTO personDTO = PersonConverter.INSTANCE.do2dto(personDO);

        System.out.println(personDTO);
    }
}
