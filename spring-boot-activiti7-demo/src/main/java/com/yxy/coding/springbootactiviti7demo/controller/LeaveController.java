package com.yxy.coding.springbootactiviti7demo.controller;/*
 * @author yangxy
 * @date 2020/12/1 09:47
 */

import com.yxy.coding.springbootactiviti7demo.activiti.ActivitiUtils;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/leave")
public class LeaveController {
    @Autowired
    private RuntimeService runtimeService;//流程相关

    //org.activiti.engine.TaskService
    @Autowired
    private TaskService taskService;//任务相关

    //org.activiti.engine.HistoryService
    @Autowired
    private HistoryService historyService;//历史记录相关

    @Autowired
    private RepositoryService repositoryService;

    /**
     * 开始流程
     * @param jobNumber
     * @author
     * @return
     * http://127.0.0.1:8080/leave/start?jobNumber=A1001
     * 这里我们假设员工的工号为A1001进行发起了请假申请，访问地址成功的话，返回一个流程实例ID
     */
    @RequestMapping(value="/start")
    public String start(String jobNumber) {
        //设置流程的发起者
        Authentication.setAuthenticatedUserId(jobNumber);

        // activiti6的版本使用
//        identityService.setAuthenticatedUserId(jobNumber);

        // bpmn中定义process的id。
        String instanceKey = "leaveProcess";
        System.out.println("开启请假流程...");

        // 设置流程参数，开启流程
        Map<String,Object> variables = new HashMap<String,Object>();
        //设置参数，这里的key就是上面配置的assignee的${jobNumber}，会进行赋值。
        variables.put("jobNumber",jobNumber);

        //使用流程定义的key启动流程实例，key对应helloworld.bpmn文件中id的属性值，使用key值启动，默认是按照最新版本的流程定义启动
        // 流程开启成功之后，获取到ProcessInstance信息。
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(instanceKey, variables);
        //busiKey 为业务数据，可以和具体的业务关联
//        runtimeService.startProcessInstanceByKey(instanceKey,"busiKey",variables);
        System.out.println("流程实例ID:"+instance.getId());
        System.out.println("流程实例ID:"+instance.getProcessInstanceId());
        System.out.println("流程定义ID:"+instance.getProcessDefinitionId());

        //
        TaskQuery taskQuery = taskService.createTaskQuery();
        List<Task> list = taskQuery.processInstanceId(instance.getProcessInstanceId()).list();
        System.out.println("根据流程ID查询任务："+list.size());

        //验证是否启动成功
        //通过查询正在运行的流程实例来判断
        ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
        //根据流程实例ID来查询
        List<ProcessInstance> runningList = processInstanceQuery.processInstanceId(instance.getProcessInstanceId()).list();
        System.out.println("根据流程ID查询条数:"+runningList.size());

        // 返回流程ID
        return instance.getId();
    }


    /**
     * <p>查看任务</p>
     * @author
     *
     * http://127.0.0.1:8080/leave/showTask?jobNumber=A1001
     */
    @RequestMapping(value="/showTask")
    public List<Map<String, String>> showTask(String jobNumber) {
        /*
         * 获取请求参数
         */
        TaskQuery taskQuery = taskService.createTaskQuery();

        List<Task> taskList = null;
        if(jobNumber == null){
            //获取所有人的所有任务.
            taskList = taskQuery.list();
        }else{
            //获取分配人的任务.
            taskList = taskQuery.taskAssignee(jobNumber).list();
        }

        if(taskList == null || taskList.size() == 0) {
            System.out.println("查询任务列表为空！");
            return null;
        }


        /*
         * 查询所有任务，并封装
         */
        List<Map<String, String>> resultList = new ArrayList<>();
        for(Task task : taskList) {
            Map<String, String> map = new HashMap<>();
            map.put("taskId", task.getId());
            map.put("name", task.getName());
            map.put("createTime", task.getCreateTime().toString());
            map.put("assignee", task.getAssignee());
            map.put("instanceId", task.getProcessInstanceId());
            map.put("executionId", task.getExecutionId());
            map.put("definitionId", task.getProcessDefinitionId());
            resultList.add(map);
        }


        /*
         * 返回结果
         */
        return resultList;
    }


    /**
     * 员工提交申请
     * @author
     * @return String 申请受理结果
     *
     * 这里有一个参数很重要，在这里特别说明下，就是任务的下一个节点：
     * deptJobNumber的分配人，这里我们使用前端进行传递的方式，等同于前端有一个地方可以进行勾选人员进行处理，
     * 那么在实际项目中，这个参数可以后端调用一个该员工的部门经理的工号进行设置即可
     *        访问测试下：
     * http://127.0.0.1:8080/leave/employeeApply?taskId=451c14fa-e9d7-11ea-9e4c-9ab0362195b5&deptJobNumber=A1002&leaveDays=2&leaveReason=家里有事
     *        这里的taskId就是上面查询出来的taskId。
     */
    @RequestMapping(value="/employeeApply")
    public String employeeApply(HttpServletRequest request){
        System.out.println("--> 提交申请单信息");
        /*
         * 获取请求参数
         */
        String taskId = request.getParameter("taskId"); // 任务ID
        //String jobNumber = request.getParameter("jobNumber"); // 工号
        String deptJobNumber = request.getParameter("deptJobNumber"); //上级
        String leaveDays = request.getParameter("leaveDays"); // 请假天数
        String leaveReason = request.getParameter("leaveReason"); // 请假原因


        /*
         *  查询任务
         */
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if(task == null) {
            System.out.println("任务ID:"+taskId+"查询到任务为空！");
            return "fail";
        }


        /*
         * 参数传递并提交申请
         */
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("days", leaveDays);
        variables.put("date", new Date());
        variables.put("reason", leaveReason);
        variables.put("deptJobNumber", deptJobNumber);
        taskService.complete(task.getId(), variables);
        System.out.println("执行【员工申请】环节，流程推动到【部门审核】环节");

        /*
         * 返回成功
         */
        return "success";
    }

    /**
     * <p>输出图像</p>
     * @param response 响应实体
     * @param bpmnModel 图像对象
     * @param flowIds 已执行的线集合
     * @param executedActivityIdList void 已执行的节点ID集合
     * @author 悟纤【公众号SpringBoot】
     */
    private void outputImg(HttpServletResponse response, BpmnModel bpmnModel, List<String> flowIds, List<String> executedActivityIdList) {
        InputStream imageStream = null;
        ProcessDiagramGenerator processDiagramGenerator = new DefaultProcessDiagramGenerator();
        try {
            if(CollectionUtils.isEmpty(flowIds)) flowIds = Arrays.asList();
            if(CollectionUtils.isEmpty(executedActivityIdList)) executedActivityIdList = Arrays.asList();
            imageStream = processDiagramGenerator.generateDiagram(bpmnModel, executedActivityIdList, flowIds, "宋体", "微软雅黑", "黑体", true, "png");
            // 输出资源内容到相应对象
            byte[] b = new byte[1024];
            int len;
            while ((len = imageStream.read(b, 0, 1024)) != -1) {
                response.getOutputStream().write(b, 0, len);
            }
            response.getOutputStream().flush();
        }catch(Exception e) {
            e.printStackTrace();
        } finally { // 流关闭
            if(imageStream != null){
                try {
                    imageStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *
     * @param modekey
     * @param response
     * http://127.0.0.1:8080/leave/showDeImg?modekey=leaveProcess
     */
    @ResponseBody
    @RequestMapping(value="/showDeImg")
    public void showDeImg(String modekey, HttpServletResponse response){
        ProcessDefinitionQuery pdq = repositoryService.createProcessDefinitionQuery();
//        List<org.activiti.engine.repository.ProcessDefinition> list = pdq.processDefinitionKey(modekey).asc().list();
//        if(CollectionUtils.isEmpty(list)){
//            return;
//        }
//
//        ProcessDefinition pd = list.get(0);
//        pd.getId();
        ProcessDefinition pd = pdq.processDefinitionKey(modekey).latestVersion().singleResult();

//        repositoryService.getModel()
        BpmnModel bpmnModel = repositoryService.getBpmnModel(pd.getId());
        outputImg(response, bpmnModel, null, null);
//        outputImg(response, bpmnModel, Arrays.asList(), Arrays.asList());

    }
    /**
     * <p>查看当前流程图</p>
     * @param instanceId 流程实例
     * @param response void 响应
     * @author 悟纤【公众号SpringBoot】
     * http://127.0.0.1:8080/leave/showImg?instanceId=2b96782e-337a-11eb-a3e3-d2e29885766c
     */
    @ResponseBody
    @RequestMapping(value="/showImg")
    public void showImg(String instanceId, HttpServletResponse response) {
        /*
         * 参数校验
         */
        System.out.println("查看完整流程图！流程实例ID:"+instanceId);
        if(StringUtils.isBlank(instanceId)) return;


        /*
         *  获取流程实例
         */
        HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(instanceId).singleResult();
        if(processInstance == null) {

//            repositoryService.createModelQuery().modelKey("")
            System.out.println("流程实例ID:"+instanceId+"没查询到流程实例！");
            return;
        }

        // 根据流程对象获取流程对象模型
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());


        /*
         *  查看已执行的节点集合
         *  获取流程历史中已执行节点，并按照节点在流程中执行先后顺序排序
         */
        // 构造历史流程查询
        HistoricActivityInstanceQuery historyInstanceQuery = historyService.createHistoricActivityInstanceQuery().processInstanceId(instanceId);
        // 查询历史节点
        List<HistoricActivityInstance> historicActivityInstanceList = historyInstanceQuery.orderByHistoricActivityInstanceStartTime().asc().list();
        if(historicActivityInstanceList == null || historicActivityInstanceList.size() == 0) {
            System.out.println("流程实例ID:"+instanceId+"没有历史节点信息！");
            outputImg(response, bpmnModel, null, null);
            return;
        }
        // 已执行的节点ID集合(将historicActivityInstanceList中元素的activityId字段取出封装到executedActivityIdList)
        List<String> executedActivityIdList = historicActivityInstanceList.stream().map(item -> item.getActivityId()).collect(Collectors.toList());

        /*
         *  获取流程走过的线
         */
        // 获取流程定义
        ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(processInstance.getProcessDefinitionId());
        List<String> flowIds = ActivitiUtils.getHighLightedFlows(bpmnModel, processDefinition, historicActivityInstanceList);


        /*
         * 输出图像，并设置高亮
         */
        outputImg(response, bpmnModel, flowIds, executedActivityIdList);
    }

}
