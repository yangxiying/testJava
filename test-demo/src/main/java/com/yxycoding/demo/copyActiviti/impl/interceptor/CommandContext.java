package com.yxycoding.demo.copyActiviti.impl.interceptor;/*
 * @author yangxy
 * @date 2020/12/2 11:09
 */


import com.yxycoding.demo.copyActiviti.Dao.TaskEntityManager;
import com.yxycoding.demo.copyActiviti.impl.TaskEntityManagerImpl;

// todo 这个具体是干什么的
public class CommandContext {


    public TaskEntityManager getTaskEntityManager() {
//        return new TaskEntityManagerImpl(this, taskDataManager);
        return null;
    }

}
