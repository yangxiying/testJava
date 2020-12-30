package com.yxycoding.demo.graphql.demo;/*
 * @author yangxy
 * @date 2020/12/24 08:51
 */

import lombok.Data;

@Data
public class Author {
    private int id;//作者的ID.
    private String name;//作者名称.
    private String photo;//照片.
}
