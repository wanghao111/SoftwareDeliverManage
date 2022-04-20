package com.software.deliver.biz.processor.nodereview;

import com.software.deliver.biz.WorkFlowProgressService;
import com.software.deliver.biz.enums.WorkFlowNodeActionEnum;
import com.software.deliver.biz.dto.FlowNodeActionProcessParam;
import com.software.deliver.biz.enums.WorkFlowProgressStatusEnum;
import com.software.deliver.biz.model.WorkFlowProgress;

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
        Long workFlowInstanceId = param.getWorkFlowInstanceId();

        WorkFlowProgress submitWorkFlowProgress = workFlowProgressService.getSubmitWorkFlowProgress(workFlowInstanceId);

        submitWorkFlowProgress.setStatus(WorkFlowProgressStatusEnum.NEED_PROCESS.getStatus());
        workFlowProgressService.update(submitWorkFlowProgress);

        return true;
    }

    @Override
    public int reviewType() {
        return WorkFlowNodeActionEnum.REJECT_TO_SUBMIT.getType();
    }
}
