package com.yxycoding.demo.tree.dao;/*
 * @author yangxy
 * @date 2020/11/6 14:49
 */

import lombok.Data;

import java.util.List;

public class ExtBdao extends Bdao {


    ExtBdao(int id, int pid, String name, List<Bdao> childs) {
        super(id, pid, name, childs);
    }
}
