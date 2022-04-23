package com.software.deliver.biz.processor.nodereview;

import com.software.deliver.biz.WorkFlowProgressService;
import com.software.deliver.biz.converter.WorkFlowProgressConverter;
import com.software.deliver.biz.enums.WorkFlowNodeActionEnum;
import com.software.deliver.biz.dto.FlowNodeActionProcessParam;
import com.software.deliver.biz.enums.WorkFlowProgressStatusEnum;
import com.software.deliver.biz.model.WorkFlowProgress;
import com.software.deliver.dal.vo.WorkFlowNodeVO;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/19 22:19
 */
public class WorkFlowNodeRejectToSubmitProcessor implements WorkFlowNodeActionProcessorBase{
    @Override
    public boolean doProcess(FlowNodeActionProcessParam param) {
        //todo:wh退回到提交人，中间进度的状态是否需要变动？审批过的流程是否一直可以查看列表
        WorkFlowProgressService workFlowProgressService = param.getWorkFlowProgressService();
        Long workFlowInstanceId = param.getCurrentProgress().getWorkFlowInstanceId();

        WorkFlowProgress workFlowProgress = workFlowProgressService.getByFlowProgressId(param.getCurrentProgress().getId());

        WorkFlowNodeVO startNodeByFlowCode = param.getWorkFlowNodeDao().getStartNodeByFlowCode(param.getCurrentProgress().getWorkFlowCode());

        WorkFlowProgress submitWorkFlowProgress = WorkFlowProgress.builder()
                .workFlowId(workFlowProgress.getWorkFlowId())
                .workFlowCode(workFlowProgress.getWorkFlowCode())
                .workFlowNodeCode(startNodeByFlowCode.getCode())
                .workFlowInstanceId(workFlowInstanceId)
                .createdBy(workFlowProgress.getCreatedBy())
                .handlerUserId(workFlowProgress.getCreatedBy())
                .createdBy(param.getCurrentProgress().getCreatedBy())
                .status(WorkFlowProgressStatusEnum.RETURNED.getStatus())
                .build();
        workFlowProgressService.create(submitWorkFlowProgress);

        //todo:更新instance状态,如有子流程，关闭待处理的所有子流程

        return true;
    }

    @Override
    public int reviewType() {
        return WorkFlowNodeActionEnum.REJECT_TO_SUBMIT.getType();
    }
}
