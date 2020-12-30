package com.yxycoding.demo.copyActiviti.Dao;/*
 * @author yangxy
 * @date 2020/12/2 15:23
 */

import com.yxycoding.demo.copyActiviti.impl.TaskQueryImpl;

import java.util.List;

public interface TaskEntityManager extends EntityManager<TaskEntity> {

    void insert(TaskEntity taskEntity );

    List<Task> findTasksByQueryCriteria(TaskQueryImpl taskQuery);


    long findTaskCountByQueryCriteria(TaskQueryImpl taskQuery);


}
