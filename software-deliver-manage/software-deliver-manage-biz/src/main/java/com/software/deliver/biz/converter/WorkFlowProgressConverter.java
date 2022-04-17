package com.software.deliver.biz.converter;

import com.software.deliver.biz.model.WorkFlowProgress;
import com.software.deliver.dal.vo.WorkFlowProgressVO;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/17 20:30
 */
public class WorkFlowProgressConverter {

    public static WorkFlowProgressVO from(WorkFlowProgress workFlowProgress) {

        return WorkFlowProgressVO.builder()
                .id(workFlowProgress.getId())
                .workFlowId(workFlowProgress.getWorkFlowId())
                .flowCode(workFlowProgress.getFlowCode())
                .flowInstanceId(workFlowProgress.getFlowInstanceId())
                .flowNodeId(workFlowProgress.getFlowNodeId())
                .handlerUserId(workFlowProgress.getHandlerUserId())
                .status(workFlowProgress.getStatus())
                .handlerMsg(workFlowProgress.getHandlerMsg())
                .build();
    }

}
