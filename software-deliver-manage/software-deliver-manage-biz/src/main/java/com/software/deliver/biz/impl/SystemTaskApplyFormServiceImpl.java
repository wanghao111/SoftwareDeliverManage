package com.software.deliver.biz.impl;

import com.software.deliver.biz.SystemTaskApplyFormService;
import com.software.deliver.biz.WorkFlowInstanceService;
import com.software.deliver.biz.WorkFlowService;
import com.software.deliver.biz.constants.SoftwareBizConstants;
import com.software.deliver.biz.converter.SystemTaskApplyFormConverter;
import com.software.deliver.biz.enums.BizExceptionEnum;
import com.software.deliver.biz.enums.WorkFlowEnableEnum;
import com.software.deliver.biz.factory.BizExceptionFactory;
import com.software.deliver.biz.model.BizException;
import com.software.deliver.biz.model.SystemTaskApplyForm;
import com.software.deliver.biz.model.WorkFlow;
import com.software.deliver.biz.model.WorkFlowInstance;
import com.software.deliver.dal.mapper.SystemTaskApplyFormDao;
import com.software.deliver.dal.vo.SystemTaskApplyFormVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

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


        //第一个审批人


        return 1;
    }
}
