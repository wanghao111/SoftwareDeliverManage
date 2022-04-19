package com.software.deliver.biz.factory;

import com.software.deliver.biz.enums.WorkFlowNodeActionEnum;
import com.software.deliver.biz.processor.*;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/19 22:30
 */
public class WorkFlowNodeActionProcessorFactory {

    public static WorkFlowNodeActionProcessorBase build(int reviewType) {
        WorkFlowNodeActionProcessorBase processorBase = null;
        if (WorkFlowNodeActionEnum.APPROVE.getType().equals(reviewType)) {
            processorBase = new WorkFlowNodeApproveProcessor();
        } else if (WorkFlowNodeActionEnum.REJECT_TO_PRE.getType().equals(reviewType)) {
            processorBase = new WorkFlowNodeRejectToPreProcessor();
        } else if (WorkFlowNodeActionEnum.REJECT_TO_SUBMIT.getType().equals(reviewType)) {
            processorBase = new WorkFlowNodeRejectToSubmitProcessor();
        } else if (WorkFlowNodeActionEnum.ADD_SIGN.getType().equals(reviewType)) {
            processorBase = new WorkFlowNodeAddSignProcessor();
        } else if (WorkFlowNodeActionEnum.TRANSFER.getType().equals(reviewType)) {
            processorBase = new WorkFlowNodeTransferProcessor();
        }
        return processorBase;
    }
}
