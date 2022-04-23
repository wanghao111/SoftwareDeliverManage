package com.software.deliver.biz.factory;

import com.software.deliver.biz.WorkFlowProgressService;
import com.software.deliver.biz.dto.FlowNodeActionProcessParam;
import com.software.deliver.biz.model.WorkFlowProgress;
import com.software.deliver.dal.mapper.WorkFlowNodeDao;
import com.software.deliver.dal.mapper.WorkFlowNodeRelDao;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/19 22:47
 */
public class FlowNodeActionProcessParamFactory {

    public static FlowNodeActionProcessParam build(WorkFlowProgress currentProgress,
                                                   WorkFlowNodeDao workFlowNodeDao,
                                                   WorkFlowProgressService workFlowProgressService, WorkFlowNodeRelDao workFlowNodeRelDao) {
        return FlowNodeActionProcessParam.builder()
                .currentProgress(currentProgress)
                .workFlowNodeDao(workFlowNodeDao)
                .workFlowProgressService(workFlowProgressService)
                .workFlowNodeRelDao(workFlowNodeRelDao)
                .build();
    }
}
