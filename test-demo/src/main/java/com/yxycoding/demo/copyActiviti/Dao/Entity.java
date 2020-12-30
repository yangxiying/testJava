package com.yxycoding.demo.copyActiviti.Dao;/*
 * @author yangxy
 * @date 2020/12/2 15:25
 */

public interface Entity {

    String getId();

    void setId(String id);

    boolean isInserted();

    void setInserted(boolean inserted);

    boolean isUpdated();

    void setUpdated(boolean updated);

    boolean isDeleted();

    void setDeleted(boolean deleted);

}
