package com.yxycoding.demo.copyActiviti.impl;/*
 * @author yangxy
 * @date 2020/12/2 10:40
 */

import com.yxycoding.demo.copyActiviti.db.ListQueryParameterObject;
import com.yxycoding.demo.copyActiviti.impl.context.Context;
import com.yxycoding.demo.copyActiviti.impl.interceptor.Command;
import com.yxycoding.demo.copyActiviti.impl.interceptor.CommandContext;
import com.yxycoding.demo.copyActiviti.impl.interceptor.CommandExecutor;
import com.yxycoding.demo.copyActiviti.query.Query;
import com.yxycoding.demo.copyActiviti.query.QueryProperty;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractQuery<T extends Query<?,?>,U> extends ListQueryParameterObject implements Command<Object>,Query<T,U>, Serializable {

    private static final long serialVersionUID = 1L;

    protected String orderBy;

    public static enum NullHandlingOnOrder {
        NULLS_FIRST, NULLS_LAST
    }

    protected NullHandlingOnOrder nullHandlingOnOrder;

    protected QueryProperty orderProperty;

//    不需要序列化的属性前添加关键字transient，序列化对象的时候，这个属性就不会序列化到指定的目的地中。
    protected transient CommandExecutor commandExecutor;

    private static enum ResultType {
        LIST, LIST_PAGE, SINGLE_RESULT, COUNT
    }
    protected ResultType resultType;

    @SuppressWarnings("unchecked")
    public T orderBy(QueryProperty property) {
        this.orderProperty = property;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T orderBy(QueryProperty property, NullHandlingOnOrder nullHandlingOnOrder) {
        orderBy(property);
        this.nullHandlingOnOrder = nullHandlingOnOrder;
        return (T) this;
    }

    @Override
    public T asc() {
        return direction(Direction.ASCENDING);
    }

    @Override
    public T desc() {
        return direction(Direction.DESCENDING);
    }

    @SuppressWarnings("unchecked")
    public T direction(Direction direction) {
        if (orderProperty == null) {
            throw new RuntimeException("You should call any of the orderBy methods first before specifying a direction");
        }
        addOrder(orderProperty.getName(), direction.getName(), nullHandlingOnOrder);
        orderProperty = null;
        nullHandlingOnOrder = null;
        return (T) this;
    }

    protected void addOrder(String column, String sortOrder, NullHandlingOnOrder nullHandlingOnOrder) {
        if(orderBy ==null){
            orderBy = "";
        }else{
            orderBy = orderBy + ", ";
        }

        String defaultOrderByClause = column + " " + sortOrder; // id asc

        if (nullHandlingOnOrder != null) { //如果字段为null时，排序是放在后面，还是前面
            if (nullHandlingOnOrder.equals(NullHandlingOnOrder.NULLS_FIRST)) {

                //这是MySQL的对null的排序处理，需要根据不同的数据库进行不同的处理
                orderBy = orderBy + "isnull(" + column + ") desc," + defaultOrderByClause;
            }else if (nullHandlingOnOrder.equals(NullHandlingOnOrder.NULLS_LAST)) {
                orderBy = orderBy + "isnull(" + column + ") asc," + defaultOrderByClause;
            }
        }else{
            orderBy = orderBy + defaultOrderByClause;
        }

    }

    public String getOrderBy() {
        if (orderBy == null) {
            return super.getOrderBy();
        } else {
            return orderBy;
        }
    }

    public String getOrderByColumns() {
        return getOrderBy();
    }

    @Override
    public long count() {
        this.resultType = ResultType.COUNT;
        if (commandExecutor != null) {
            return (Long) commandExecutor.execute(this);
        }
        return executeCount(Context.getCommandContext());
    }

    public abstract long executeCount(CommandContext commandContext);

    public abstract List<U> executeList(CommandContext commandContext, Page page);

    @Override
    public U singleResult() {
        this.resultType = ResultType.SINGLE_RESULT;
        if (commandExecutor != null) {
            return (U) commandExecutor.execute(this);
        }
        return executeSingleResult(Context.getCommandContext());
    }

    public U executeSingleResult(CommandContext commandContext) {
        List<U> results = executeList(commandContext, null);
        if (results.size() == 1) {
            return results.get(0);
        } else if (results.size() > 1) {
            throw new RuntimeException("Query return " + results.size() + " results instead of max 1");
        }
        return null;
    }

    @Override
    public List list() {
        this.resultType = ResultType.LIST;
        if (commandExecutor != null) {
            return (List<U>) commandExecutor.execute(this);
        }
        return executeList(Context.getCommandContext(), null);
    }

    @Override
    public List listPage(int firstResult, int maxResults) {
        this.firstResult = firstResult;
        this.maxResults = maxResults;
        this.resultType = ResultType.LIST_PAGE;
        if (commandExecutor != null) {
            return (List<U>) commandExecutor.execute(this);
        }
        return executeList(Context.getCommandContext(), new Page(firstResult, maxResults));
    }

    @Override
    public Object execute(CommandContext commandContext) {
        if (resultType == ResultType.LIST) {
            return executeList(commandContext, null);
        } else if (resultType == ResultType.SINGLE_RESULT) {
            return executeSingleResult(commandContext);
        } else if (resultType == ResultType.LIST_PAGE) {
            return executeList(commandContext, null);
        } else {
            return executeCount(commandContext);
        }
    }
}
