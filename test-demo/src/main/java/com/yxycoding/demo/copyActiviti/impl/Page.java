package com.yxycoding.demo.copyActiviti.impl;/*
 * @author yangxy
 * @date 2020/12/2 11:18
 */

public class Page {

    protected int firstResult;
    protected int maxResults;

    public Page(int firstResult, int maxResults) {
        this.firstResult = firstResult;
        this.maxResults = maxResults;
    }

    public int getFirstResult() {
        return firstResult;
    }

    public int getMaxResults() {
        return maxResults;
    }

}
