package com.software.deliver.biz.acquirer;

import com.software.deliver.biz.dto.FlowNodeHandlerTypeProcessParam;
import com.software.deliver.biz.enums.WorkFlowNodeHandleTypeEnum;
import com.software.deliver.biz.enums.WorkFlowNodeReviewTypeEnum;
import com.software.deliver.dal.vo.WorkFlowVariableVO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class WorkFlowNodeAssignerAcquirer implements WorkFlowNodeHandlerUserAcquirerBase {
    @Override
    public List<Long> acquire(FlowNodeHandlerTypeProcessParam param) {
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
