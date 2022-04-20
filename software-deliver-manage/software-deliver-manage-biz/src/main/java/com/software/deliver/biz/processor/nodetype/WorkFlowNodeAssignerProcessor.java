package com.software.deliver.biz.processor.nodetype;

import com.software.deliver.biz.dto.FlowNodeHandlerTypeProcessParam;
import com.software.deliver.biz.enums.WorkFlowNodeHandleTypeEnum;
import com.software.deliver.biz.enums.WorkFlowNodeReviewTypeEnum;
import com.software.deliver.dal.vo.WorkFlowVariableVO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class WorkFlowNodeAssignerProcessor implements WorkFlowNodeHandlerTypeProcessorBase{
    @Override
    public List<Long> doProcess(FlowNodeHandlerTypeProcessParam param) {
        if (Objects.equals(param.getReviewType(), WorkFlowNodeReviewTypeEnum.APPROVE.getType())) {
            WorkFlowVariableVO workFlowVariableVO = param.getWorkFlowVariableDao().getByVarName(param.getVarName());
            return Arrays.asList(workFlowVariableVO.getValue());
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public int nodeType() {
        return WorkFlowNodeHandleTypeEnum.ASSIGNER.getType();
    }
}
