package com.software.deliver.biz.processor;

import com.software.deliver.biz.WorkFlowProgressService;
import com.software.deliver.biz.enums.WorkFlowNodeActionEnum;
import com.software.deliver.dal.mapper.WorkFlowNodeDao;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/19 22:19
 */
public class WorkFlowNodeRejectToSubmitProcessor implements WorkFlowNodeActionProcessorBase{
    @Override
    public boolean doProcess(Long workFlowId, Long workFlowInstanceId, String currentNodeCode, WorkFlowNodeDao workFlowNodeDao, WorkFlowProgressService workFlowProgressService) {
        //todo:wh待实现
        return false;
    }

    @Override
    public int reviewType() {
        return WorkFlowNodeActionEnum.REJECT_TO_SUBMIT.getType();
    }
}