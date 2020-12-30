package com.yxy.coding.springbootactiviti7demo.listen;


import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * <b>监听器使用范例</b>：销假后处理器
 * <p>
 * 设置销假时间
 * </p>
 * <p>
 * 使用Spring代理，可以注入Bean，管理事物
 * </p>
 *
 * event (required): 事件类型.。支持的类型有：
 *  create: 任务被创建，并且所有的属性都被设置好后。
 *  assignment: 任务被委派给某人后.。注意: 当流程执行到达一个userTask时，会先触发一个assignment事件，再触发create事件。
 *  complete:在任务完成后，且被从运行时数据（runtime data）中删除前触发。
 *  delete: 在任务将要被删除之前发生。注意，当任务通过completeTask完成任务时，它也会被执行。
 *
 * @author HenryYan
 */
@Component
@Transactional
public class CreateProcessor implements TaskListener {

    private static final long serialVersionUID = 1L;



    /*
     * (non-Javadoc)
     *
     * @see
     * org.activiti.engine.delegate.TaskListener#notify(org.activiti.engine.delegate
     * .DelegateTask)
     */
    public void notify(DelegateTask delegateTask) {

        System.out.println("CreateProcessor::::::tmpval==="+delegateTask.getVariable("tmpval"));
        System.out.println("CreateProcessor::::::tmpvald==="+delegateTask.getVariable("tmpvald"));
//        System.out.println(delegateTask.getVariable("tmpval"));

        delegateTask.getId();//task id  节点id
        delegateTask.getName();// task name 节点名称
        delegateTask.getTaskDefinitionKey(); //task key 节点key

        delegateTask.getExecution().getProcessInstanceId();
        delegateTask.getProcessInstanceId(); // 流程实例id

//        BizLeave leave = bizLeaveService.selectBizLeaveById(new Long(delegateTask.getExecution().getProcessInstanceBusinessKey()));
//        Object realityStartTime = delegateTask.getVariable("realityStartTime");
//        leave.setRealityStartTime((Date) realityStartTime);
//        Object realityEndTime = delegateTask.getVariable("realityEndTime");
//        leave.setRealityEndTime((Date) realityEndTime);
//        bizLeaveService.updateBizLeave(leave);
    }

}
