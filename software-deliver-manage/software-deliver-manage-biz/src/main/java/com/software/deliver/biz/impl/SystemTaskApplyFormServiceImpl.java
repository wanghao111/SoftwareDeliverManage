package com.software.deliver.biz.impl;

import com.software.deliver.biz.SystemTaskApplyFormService;
import com.software.deliver.biz.WorkFlowInstanceService;
import com.software.deliver.biz.WorkFlowProgressService;
import com.software.deliver.biz.WorkFlowService;
import com.software.deliver.biz.constants.SoftwareBizConstants;
import com.software.deliver.biz.converter.SystemTaskApplyFormConverter;
import com.software.deliver.biz.enums.BizExceptionEnum;
import com.software.deliver.biz.enums.WorkFlowEnableEnum;
import com.software.deliver.biz.enums.WorkFlowProgressStatusEnum;
import com.software.deliver.biz.factory.BizExceptionFactory;
import com.software.deliver.biz.model.*;
import com.software.deliver.dal.mapper.SystemTaskApplyFormDao;
import com.software.deliver.dal.vo.SystemTaskApplyFormVO;
import com.software.deliver.dal.vo.WorkFlowVariableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/17 12:00
 */
@Service
public class SystemTaskApplyFormServiceImpl implements SystemTaskApplyFormService {

    @Autowired
    private SystemTaskApplyFormDao systemTaskApplyFormDao;

    @Autowired
    private WorkFlowInstanceService workFlowInstanceService;

    @Autowired
    private WorkFlowService workFlowService;

    @Autowired
    private WorkFlowProgressService workFlowProgressService;


    @Override
    public int create(SystemTaskApplyForm systemTaskApplyForm) throws BizException {

        SystemTaskApplyFormVO systemTaskApplyFormVO = SystemTaskApplyFormConverter.from(systemTaskApplyForm);
        systemTaskApplyFormDao.create(systemTaskApplyFormVO);

        //创建审批工作流
        //todo:wh
        WorkFlow workFlow = workFlowService.getBriefByCode(SoftwareBizConstants.SYSTEM_TASK_APPLY_FORM_FLOW_CODE);
        if (null == workFlow || WorkFlowEnableEnum.UNABLE.getType().equals(workFlow.getIsEnable())) {
            throw BizExceptionFactory.build(BizExceptionEnum.WORK_FLOW_NOT_EXIT_OR_UNABLE);
        }

        //创建工作流实例
        WorkFlowInstance workFlowInstance = WorkFlowInstance.builder()
                .submitUserId(systemTaskApplyForm.getCreatedBy())
                .workFlowId(workFlow.getId())
                .workFlowCode(workFlow.getCode())
                .title(workFlow.getTitle())
                .build();
        workFlowInstanceService.create(workFlowInstance);

        //创建审批处理任务
        //提交人
        WorkFlowProgress workFlowProgress = WorkFlowProgress.builder()
                .workFlowId(workFlow.getId())
                .workFlowCode(workFlow.getCode())
                .workFlowInstanceId(workFlowInstance.getId())
//                .flowNodeId()
                .handlerUserId(systemTaskApplyForm.getCreatedBy())
                .status(WorkFlowProgressStatusEnum.SUBMIT.getStatus())
                .build();
        WorkFlowNode briefStartNode = workFlowService.getBriefStartNode(workFlow.getCode());
        workFlowProgress.setWorkFlowNodeCode(briefStartNode.getWorkFlowNodeCode());
        workFlowProgressService.create(workFlowProgress);

        //第一个审批人
        //只有一个子节点
        List<WorkFlowNode> briefNextNodes = workFlowService.getBriefNextNode(workFlow.getId(), briefStartNode.getWorkFlowNodeCode());
        workFlowProgress.setWorkFlowNodeCode(briefNextNodes.get(0).getWorkFlowNodeCode());
        workFlowProgress.setStatus(WorkFlowProgressStatusEnum.NEED_PROCESS.getStatus());
        workFlowProgress.setId(null);
        workFlowProgress.setHandlerUserId(systemTaskApplyForm.getOwnerUserId());
        workFlowProgressService.create(workFlowProgress);

        WorkFlowVariableVO workFlowVariableVO = WorkFlowVariableVO.builder()
                .workFlowInstanceId(workFlowInstance.getId())
                .varName(SoftwareBizConstants.WORK_FLOW_NODE_QA_VAR_NAME)
                .value(systemTaskApplyForm.getQaUserId())
                .build();
        workFlowInstanceService.createWorkFlowVariable(workFlowVariableVO);

        return 1;
    }
}
