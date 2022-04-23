package com.software.deliver.biz.acquirer;

import com.software.deliver.biz.dto.FlowNodeHandlerTypeProcessParam;
import com.software.deliver.biz.enums.WorkFlowNodeHandleTypeEnum;

import java.util.Arrays;
import java.util.List;

public class WorkFlowNodeFixedPersonAcquirer implements WorkFlowNodeHandlerUserAcquirerBase {


    @Override
    public List<Long> acquire(FlowNodeHandlerTypeProcessParam param) {
        return Arrays.asList(param.getHandlerUserId());
    }

    @Override
    public int nodeType() {
        return WorkFlowNodeHandleTypeEnum.FIXED_PERSON.getType();
    }
}
