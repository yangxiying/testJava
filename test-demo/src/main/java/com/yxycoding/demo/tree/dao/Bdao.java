package com.yxycoding.demo.tree.dao;/*
 * @author yangxy
 * @date 2020/11/6 11:57
 */

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Bdao {

    private int id;
    private int pid;
    private String name;

    private List<Bdao> childs;
}
