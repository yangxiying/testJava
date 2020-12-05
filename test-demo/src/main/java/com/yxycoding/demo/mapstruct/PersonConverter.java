package com.yxycoding.demo.mapstruct;/*
 * @author yangxy
 * @date 2020/12/5 11:09
 */

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonConverter {
    PersonConverter INSTANCE = Mappers.getMapper(PersonConverter.class);

    @Mappings(@Mapping(source = "name", target = "userName"))
    PersonDTO do2dto(PersonDO person);

}

