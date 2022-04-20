package com.software.deliver.biz.factory;

import com.software.deliver.biz.enums.WorkFlowNodeHandleTypeEnum;
import com.software.deliver.biz.processor.nodetype.WorkFlowNodeAssignerProcessor;
import com.software.deliver.biz.processor.nodetype.WorkFlowNodeFixedPersonProcessor;
import com.software.deliver.biz.processor.nodetype.WorkFlowNodeHandlerTypeProcessorBase;
import com.software.deliver.biz.processor.nodetype.WorkFlowNodePreNodeSupervisorProcessor;

public class FlowHandlerTypeProcessorFactory {

    public static WorkFlowNodeHandlerTypeProcessorBase build(Integer handlerType) {
        WorkFlowNodeHandlerTypeProcessorBase processorBase = null;
        if (WorkFlowNodeHandleTypeEnum.ASSIGNER.getType().equals(handlerType)) {
            processorBase = new WorkFlowNodeAssignerProcessor();
        } else if (WorkFlowNodeHandleTypeEnum.FIXED_PERSON.getType().equals(handlerType)) {
            processorBase = new WorkFlowNodeFixedPersonProcessor();
        } else if (WorkFlowNodeHandleTypeEnum.PRE_NODE_SUPERVISOR.getType().equals(handlerType)) {
            processorBase = new WorkFlowNodePreNodeSupervisorProcessor();
        }
        return processorBase;
    }
}
