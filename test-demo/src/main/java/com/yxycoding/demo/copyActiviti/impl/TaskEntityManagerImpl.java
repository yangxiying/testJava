package com.yxycoding.demo.copyActiviti.impl;/*
 * @author yangxy
 * @date 2020/12/2 15:42
 */

import com.yxycoding.demo.copyActiviti.Dao.Task;
import com.yxycoding.demo.copyActiviti.Dao.TaskEntity;
import com.yxycoding.demo.copyActiviti.Dao.TaskEntityManager;

import java.util.List;

public class TaskEntityManagerImpl implements TaskEntityManager {


    @Override
    public TaskEntity create() {
        return null;
    }

    @Override
    public TaskEntity findById(String entityId) {
        return null;
    }

    @Override
    public void insert(TaskEntity taskEntity) {

    }

    @Override
    public void insert(TaskEntity entity, boolean fireCreateEvent) {

    }

    @Override
    public TaskEntity update(TaskEntity entity) {
        return null;
    }

    @Override
    public TaskEntity update(TaskEntity entity, boolean fireUpdateEvent) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void delete(TaskEntity entity) {

    }

    @Override
    public void delete(TaskEntity entity, boolean fireDeleteEvent) {

    }

    @Override
    public List<Task> findTasksByQueryCriteria(TaskQueryImpl taskQuery) {
        return null;
    }

    @Override
    public long findTaskCountByQueryCriteria(TaskQueryImpl taskQuery) {
        return 0;
    }
}
