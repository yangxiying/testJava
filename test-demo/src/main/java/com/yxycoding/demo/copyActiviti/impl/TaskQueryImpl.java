package com.yxycoding.demo.copyActiviti.impl;/*
 * @author yangxy
 * @date 2020/12/2 15:02
 */

import com.yxycoding.demo.copyActiviti.Dao.Task;
import com.yxycoding.demo.copyActiviti.Dao.TaskQuery;
import com.yxycoding.demo.copyActiviti.impl.interceptor.CommandContext;

import java.util.List;

public class TaskQueryImpl extends AbstractQuery<TaskQuery, Task> implements TaskQuery {

    protected String taskId;
    protected String name;
    protected String nameLike;
    protected List<String> nameList;
    protected String description;
    protected String descriptionLike;
    protected Integer priority;
    protected Integer minPriority;
    protected Integer maxPriority;

    @Override
    public TaskQuery taskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    @Override
    public TaskQuery taskName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public TaskQuery taskNameIn(List<String> nameList) {
        if (nameList == null) {
            throw new RuntimeException("Task name list is null");
        }
        if (nameList.isEmpty()) {
            throw new RuntimeException("Task name list is empty");
        }
        for (String name : nameList) {
            if (name == null) {
                throw new RuntimeException("None of the given task names can be null");
            }
        }
        if (name != null) {
            throw new RuntimeException("Invalid query usage: cannot set both taskNameIn and name");
        }
        if (nameLike != null) {
            throw new RuntimeException("Invalid query usage: cannot set both taskNameIn and nameLike");
        }

        this.nameList = nameList;
        return this;
    }

    @Override
    public TaskQuery taskNameLike(String nameLike) {
        this.nameLike = nameLike;
        return this;
    }

    @Override
    public TaskQuery taskDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public TaskQuery taskDescriptionLike(String descriptionLike) {
        this.descriptionLike = descriptionLike;
        return this;
    }

    @Override
    public TaskQuery taskPriority(Integer priority) {
        this.priority = priority;
        return this;
    }

    @Override
    public TaskQuery taskMaxPriority(Integer maxPriority) {
        this.minPriority = minPriority;
        return this;
    }

    @Override
    public TaskQuery taskMinPriority(Integer minPriority) {
        this.maxPriority = maxPriority;
        return this;
    }


    public TaskQuery orderByTaskId() {
        return orderBy(TaskQueryProperty.TASK_ID);
    }



    @Override
    public TaskQuery asc() {
        return this;
    }

    @Override
    public TaskQuery desc() {
        return this;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public long executeCount(CommandContext commandContext) {
        return 0;
    }

    @Override
    public List<Task> executeList(CommandContext commandContext, Page page) {
        List<Task> tasks = commandContext.getTaskEntityManager().findTasksByQueryCriteria(this);

        return tasks;
    }

    @Override
    public Task singleResult() {
        return null;
    }

    @Override
    public List<Task> list() {
        return null;
    }

    @Override
    public List<Task> listPage(int firstResult, int maxResults) {
        return null;
    }




}
