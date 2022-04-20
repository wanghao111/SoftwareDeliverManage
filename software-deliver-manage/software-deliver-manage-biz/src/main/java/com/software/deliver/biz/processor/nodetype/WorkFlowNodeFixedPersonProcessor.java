package com.software.deliver.biz.processor.nodetype;

import com.software.deliver.biz.dto.FlowNodeHandlerTypeProcessParam;
import com.software.deliver.biz.enums.WorkFlowNodeHandleTypeEnum;

import java.util.Arrays;
import java.util.List;

public class WorkFlowNodeFixedPersonProcessor implements WorkFlowNodeHandlerTypeProcessorBase{


    @Override
    public List<Long> doProcess(FlowNodeHandlerTypeProcessParam param) {
        return Arrays.asList(param.getHandlerUserId());
    }

    @Override
    public int nodeType() {
        return WorkFlowNodeHandleTypeEnum.FIXED_PERSON.getType();
    }
}
