package com.software.deliver.biz.factory;

import com.software.deliver.biz.enums.WorkFlowNodeHandleTypeEnum;
import com.software.deliver.biz.acquirer.WorkFlowNodeAssignerAcquirer;
import com.software.deliver.biz.acquirer.WorkFlowNodeFixedPersonAcquirer;
import com.software.deliver.biz.acquirer.WorkFlowNodeHandlerUserAcquirerBase;
import com.software.deliver.biz.acquirer.WorkFlowNodePreNodeSupervisorAcquirer;

public class FlowHandlerTypeProcessorFactory {

    public static WorkFlowNodeHandlerUserAcquirerBase build(Integer handlerType) {
        WorkFlowNodeHandlerUserAcquirerBase processorBase = null;
        if (WorkFlowNodeHandleTypeEnum.ASSIGNER.getType().equals(handlerType)) {
            processorBase = new WorkFlowNodeAssignerAcquirer();
        } else if (WorkFlowNodeHandleTypeEnum.FIXED_PERSON.getType().equals(handlerType)) {
            processorBase = new WorkFlowNodeFixedPersonAcquirer();
        } else if (WorkFlowNodeHandleTypeEnum.PRE_NODE_SUPERVISOR.getType().equals(handlerType)) {
            processorBase = new WorkFlowNodePreNodeSupervisorAcquirer();
        }
        return processorBase;
    }
}
