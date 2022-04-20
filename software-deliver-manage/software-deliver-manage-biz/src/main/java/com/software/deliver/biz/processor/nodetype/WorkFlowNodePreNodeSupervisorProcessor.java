package com.software.deliver.biz.processor.nodetype;

import com.software.deliver.biz.dto.FlowNodeHandlerTypeProcessParam;
import com.software.deliver.biz.enums.WorkFlowNodeHandleTypeEnum;

import java.util.ArrayList;
import java.util.List;

public class WorkFlowNodePreNodeSupervisorProcessor implements WorkFlowNodeHandlerTypeProcessorBase{
    @Override
    public List<Long> doProcess(FlowNodeHandlerTypeProcessParam param) {
        param.getCurrentFlowProgressUserId();

        //todo:wh 获取当前处理人的主管id，待实现
        return new ArrayList<>();
    }

    @Override
    public int nodeType() {
        return WorkFlowNodeHandleTypeEnum.PRE_NODE_SUPERVISOR.getType();
    }
}
