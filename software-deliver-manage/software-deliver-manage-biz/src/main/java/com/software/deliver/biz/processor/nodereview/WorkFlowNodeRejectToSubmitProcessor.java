package com.software.deliver.biz.processor.nodereview;

import com.software.deliver.biz.enums.WorkFlowNodeActionEnum;
import com.software.deliver.biz.dto.FlowNodeActionProcessParam;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/19 22:19
 */
public class WorkFlowNodeRejectToSubmitProcessor implements WorkFlowNodeActionProcessorBase{
    @Override
    public boolean doProcess(FlowNodeActionProcessParam param) {
        //todo:wh待实现
        return false;
    }

    @Override
    public int reviewType() {
        return WorkFlowNodeActionEnum.REJECT_TO_SUBMIT.getType();
    }
}
