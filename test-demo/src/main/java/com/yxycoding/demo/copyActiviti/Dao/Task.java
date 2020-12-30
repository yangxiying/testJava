package com.yxycoding.demo.copyActiviti.Dao;/*
 * @author yangxy
 * @date 2020/12/2 14:58
 */

public interface Task extends TaskInfo {

    void setName(String name);

    void setDescription(String description);

    void setPriority(int priority);
}
