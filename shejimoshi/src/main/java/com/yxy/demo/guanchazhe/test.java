package com.yxy.demo.guanchazhe;

public class test {

    public static void main(String[] args) {
        XiaoMei xiaoMei = new XiaoMei();
        LaoLi laoLi = new LaoLi();
        LaoWang laoWang = new LaoWang();

        xiaoMei.addPerson(laoLi);
        xiaoMei.addPerson(laoWang);


        xiaoMei.notifyPerson();
    }
}
