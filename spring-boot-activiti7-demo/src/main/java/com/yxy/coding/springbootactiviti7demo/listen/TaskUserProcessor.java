package com.yxy.coding.springbootactiviti7demo.listen;/*
 * @author yangxy
 * @date 2020/11/27 11:49
 */

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

// 流程实例start、end、take的时候调用。take是监控连线的时候使用的

public class TaskUserProcessor implements ExecutionListener {

    @Override
    public void notify(DelegateExecution execution) {
//        execution.

    }
}
