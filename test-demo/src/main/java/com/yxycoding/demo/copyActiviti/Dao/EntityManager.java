package com.yxycoding.demo.copyActiviti.Dao;/*
 * @author yangxy
 * @date 2020/12/2 15:24
 */

public interface EntityManager<EntityImpl extends Entity> {

    EntityImpl create();

    EntityImpl findById(String entityId);

    void insert(EntityImpl entity);

    void insert(EntityImpl entity, boolean fireCreateEvent);

    EntityImpl update(EntityImpl entity);

    EntityImpl update(EntityImpl entity, boolean fireUpdateEvent);

    void delete(String id);

    void delete(EntityImpl entity);

    void delete(EntityImpl entity, boolean fireDeleteEvent);

}
