package com.yxycoding.demo.tree.dao;/*
 * @author yangxy
 * @date 2020/11/6 11:58
 */

import java.util.ArrayList;
import java.util.List;

public class Tdao {

    private Bdao bdao;

    private List<Tdao> childs;

    public Tdao(){

    }
    public Tdao(Bdao bdao){
        this.bdao = bdao;
    }

    public Bdao getBdao() {
        return bdao;
    }

    public void setBdao(Bdao bdao) {
        this.bdao = bdao;
    }

    public List<Tdao> getChilds() {
        if(this.childs==null){
            this.childs=new ArrayList<>();
        }
        return childs;
    }

    public void setChilds(List<Tdao> childs) {
        this.childs = childs;
    }


}
