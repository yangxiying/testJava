package com.yxy.demo.guanchazhe;

import java.util.ArrayList;
import java.util.List;

public class XiaoMei {
    private List<Person> personList = new ArrayList<>();

    public XiaoMei() {

    }

    public void addPerson(Person person) {
        personList.add(person);
    }

    public void notifyPerson() {
        for (Person item : personList) {
            item.getMessage("你们过来吧，谁先过来，我和谁约会！！");
        }
    }
}
