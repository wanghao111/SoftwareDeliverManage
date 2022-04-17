package com.software.deliver.biz.converter;

import com.software.deliver.biz.model.WorkFlow;
import com.software.deliver.dal.vo.WorkFlowVO;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/16 17:52
 */
public class WorkFlowConverter {

    public static WorkFlowVO fromWorkFlow(WorkFlow workFlow) {
        WorkFlowVO workFlowVO = WorkFlowVO.builder().id(workFlow.getId())
                .code(workFlow.getCode())
                .title(workFlow.getTitle())
                .isEnable(workFlow.getIsEnable())
                .build();

        return workFlowVO;
    }

    public static WorkFlow fromWorkFlowVO(WorkFlowVO workFlowVO) {
        return WorkFlow.builder()
                .id(workFlowVO.getId())
                .code(workFlowVO.getCode())
                .title(workFlowVO.getTitle())
                .isEnable(workFlowVO.getIsEnable())
                .build();
    }
}
