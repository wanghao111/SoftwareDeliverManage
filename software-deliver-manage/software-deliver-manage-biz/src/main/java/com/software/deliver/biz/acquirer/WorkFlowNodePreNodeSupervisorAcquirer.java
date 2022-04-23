package com.software.deliver.biz.acquirer;

import com.software.deliver.biz.dto.FlowNodeHandlerTypeProcessParam;
import com.software.deliver.biz.enums.WorkFlowNodeHandleTypeEnum;

import java.util.ArrayList;
import java.util.List;

public class WorkFlowNodePreNodeSupervisorAcquirer implements WorkFlowNodeHandlerUserAcquirerBase {
    @Override
    public List<Long> acquire(FlowNodeHandlerTypeProcessParam param) {
        param.getCurrentFlowProgressUserId();

        //todo:wh 获取当前处理人的主管id，待实现
        return new ArrayList<>(1);
    }

    @Override
    public int nodeType() {
        return WorkFlowNodeHandleTypeEnum.PRE_NODE_SUPERVISOR.getType();
    }
}
