package com.yxycoding.demo.copyActiviti.impl;/*
 * @author yangxy
 * @date 2020/12/2 15:17
 */

import com.yxycoding.demo.copyActiviti.query.QueryProperty;

import java.util.HashMap;
import java.util.Map;

public class TaskQueryProperty implements QueryProperty {
    private static final long serialVersionUID = 1L;

    private static final Map<String, TaskQueryProperty> properties = new HashMap<String, TaskQueryProperty>();

    public static final TaskQueryProperty TASK_ID = new TaskQueryProperty("RES.ID_");
    public static final TaskQueryProperty NAME = new TaskQueryProperty("RES.NAME_");
    public static final TaskQueryProperty DESCRIPTION = new TaskQueryProperty("RES.DESCRIPTION_");
    public static final TaskQueryProperty PRIORITY = new TaskQueryProperty("RES.PRIORITY_");

    private String name;

    public TaskQueryProperty(String name) {
        this.name = name;
        properties.put(name, this);
    }

    @Override
    public String getName() {
        return name;
    }

    public static TaskQueryProperty findByName(String propertyName) {
        return properties.get(propertyName);
    }

}
