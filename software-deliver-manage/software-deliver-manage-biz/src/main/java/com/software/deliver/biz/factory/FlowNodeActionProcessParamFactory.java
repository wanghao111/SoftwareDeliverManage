package com.software.deliver.biz.factory;

import com.software.deliver.biz.WorkFlowProgressService;
import com.software.deliver.biz.model.FlowNodeActionProcessParam;
import com.software.deliver.dal.mapper.WorkFlowNodeDao;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/19 22:47
 */
public class FlowNodeActionProcessParamFactory {

    public static FlowNodeActionProcessParam build(Long workFlowId, Long workFlowInstanceId,
                                                   String currentNodeCode, WorkFlowNodeDao workFlowNodeDao,
                                                   WorkFlowProgressService workFlowProgressService) {
        return FlowNodeActionProcessParam.builder()
                .workFlowId(workFlowId)
                .workFlowInstanceId(workFlowInstanceId)
                .currentNodeCode(currentNodeCode)
                .workFlowNodeDao(workFlowNodeDao)
                .workFlowProgressService(workFlowProgressService)
                .build();
    }
}
