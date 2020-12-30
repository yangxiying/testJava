package com.yxycoding.demo.copyActiviti.db;/*
 * @author yangxy
 * @date 2020/12/2 10:39
 */

public class ListQueryParameterObject {

    protected int maxResults = Integer.MAX_VALUE;
    protected int firstResult;

    public int getMaxResults() {
        return maxResults;
    }
    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    public void setFirstResult(int firstResult) {
        this.firstResult = firstResult;
    }
    public int getFirstResult() {
        return firstResult;
    }

    public int getFirstRow() {
        return firstResult + 1;
    }



    public String getOrderBy() {
        // the default order column
        return "RES.ID_ asc";
    }

    public String getOrderByColumns() {
        return getOrderBy();
    }
}
