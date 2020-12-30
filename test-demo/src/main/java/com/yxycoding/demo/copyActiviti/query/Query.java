package com.yxycoding.demo.copyActiviti.query;/*
 * @author yangxy
 * @date 2020/12/2 10:32
 */

import java.util.List;

public interface Query<T extends Query<?,?>,U extends Object> {

    T asc();

    T desc();

    long count();

    U singleResult();

    List<U> list();

    List<U> listPage(int firstResult,int maxResults);
}
