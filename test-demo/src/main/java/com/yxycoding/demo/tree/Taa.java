package com.yxycoding.demo.tree;/*
 * @author yangxy
 * @date 2020/11/6 11:57
 */

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.yxycoding.demo.tree.dao.Bdao;
import com.yxycoding.demo.tree.dao.Tdao;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Taa {


    public static List<Bdao> buildTree(List<Bdao> nodes) {
        Map<Integer, List<Bdao>> sub = nodes.stream().filter(node -> node.getPid() != 0).collect(Collectors.groupingBy(node -> node.getPid()));
        nodes.forEach(node -> node.setChilds(sub.get(node.getId())));
        return nodes.stream().filter(node -> node.getPid() == 0).collect(Collectors.toList());
    }

    public static void main(String[] args) {

        List<Bdao> bdaos = new ArrayList<>();
        bdaos.add(Bdao.builder().id(100).name("一级-1").pid(0).build());

        bdaos.add(Bdao.builder().id(110).name("二级-1-1").pid(100).build());
        bdaos.add(Bdao.builder().id(111).name("三级-1-1-1").pid(110).build());
        bdaos.add(Bdao.builder().id(112).name("三级-1-1-2").pid(110).build());

        bdaos.add(Bdao.builder().id(120).name("二级-1-2").pid(100).build());
        bdaos.add(Bdao.builder().id(121).name("三级-1-2-1").pid(120).build());
        bdaos.add(Bdao.builder().id(122).name("三级-1-2-2").pid(120).build());

        bdaos.add(Bdao.builder().id(130).name("二级-1-3").pid(100).build());
        bdaos.add(Bdao.builder().id(131).name("三级-1-3-1").pid(130).build());
        bdaos.add(Bdao.builder().id(132).name("三级-1-3-2").pid(130).build());




        bdaos.add(Bdao.builder().id(200).name("一级-2").pid(0).build());

        bdaos.add(Bdao.builder().id(210).name("二级-2-1").pid(200).build());
        bdaos.add(Bdao.builder().id(211).name("三级-2-1-1").pid(210).build());
        bdaos.add(Bdao.builder().id(212).name("三级-2-1-2").pid(210).build());

        bdaos.add(Bdao.builder().id(220).name("二级-2-2").pid(200).build());
        bdaos.add(Bdao.builder().id(221).name("三级-2-2-1").pid(220).build());
        bdaos.add(Bdao.builder().id(222).name("三级-2-2-2").pid(220).build());

        bdaos.add(Bdao.builder().id(230).name("二级-2-3").pid(200).build());



        bdaos.add(Bdao.builder().id(300).name("一级-3").pid(0).build());

        List<Bdao> bdaos1 = buildTree(bdaos);
        System.out.println(JSONUtil.toJsonStr(bdaos1));


        List<String> tmpList = new ArrayList<>();
        tmpList.add("1");
        tmpList.add("2");
        tmpList.add("3");
        tmpList.add("4");
        tmpList.add("5");

        for (int i = 0; i < tmpList.size(); i++) {
            System.out.println(tmpList.get(i));
            if(tmpList.get(i).equals("4")){
                tmpList.remove(i);
                i--;
            }
        }

        System.out.println("tmpList.size()=="+tmpList.size());
        System.out.println("tmpList.size()=="+tmpList.toString());





    }

    private void aaaaa(List<Tdao> tdaos,Bdao bdao){
        boolean isroot=false;
        boolean isleaf=false;
        boolean isParent=false;

        boolean isDo =false;
        for (Tdao tdaoItem : tdaos) {
            if(null != tdaoItem.getBdao()){
                if(tdaoItem.getBdao().getId()==bdao.getId()){
                    isDo=true;
                    break;
                }

                if(tdaoItem.getBdao().getId()==bdao.getPid()){
                    tdaoItem.getChilds().add(new Tdao(bdao));
                    isDo=true;
                }

                if(tdaoItem.getBdao().getPid()==bdao.getId()){
                    isParent=true;

                }
            }
        }
//        if(!isroot){
//            tdaos.add(new Tdao(bdao));
//            return;
//        }
    }

    private void ss(){

    }
}
