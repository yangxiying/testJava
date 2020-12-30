package com.yxy.coding.springbootactiviti7demo.listen;/*
 * @author yangxy
 * @date 2020/11/27 14:09
 */

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class TaskUserDynamicProcessor implements TaskListener {

    private static final long serialVersionUID = 1L;
    @Override
    public void notify(DelegateTask delegateTask) {

        //设置任务的执行人
//        delegateTask.setAssignee("rensm");
        //设置任务的候选人
//        delegateTask.addCandidateUser("axianlu");
//        delegateTask.addCandidateUser("test02");
        //
//        delegateTask.addCandidateGroup("user");
//        delegateTask.addCandidateGroup("se");
    }
}
