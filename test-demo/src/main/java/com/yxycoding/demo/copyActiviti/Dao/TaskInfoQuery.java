package com.yxycoding.demo.copyActiviti.Dao;/*
 * @author yangxy
 * @date 2020/12/2 14:54
 */

import com.yxycoding.demo.copyActiviti.query.Query;

import java.util.List;

public interface TaskInfoQuery<T extends TaskInfoQuery<?,?>,V extends TaskInfo>  extends Query<T,V> {

    T taskId(String taskId);

    T taskName(String name);

    T taskNameIn(List<String> nameList);

    T taskNameLike(String nameLike);

    T taskDescription(String description);

    T taskDescriptionLike(String descriptionLike);

    T taskPriority(Integer priority);

    T taskMaxPriority(Integer maxPriority);

    T taskMinPriority(Integer minPriority);

    T orderByTaskId();

}
