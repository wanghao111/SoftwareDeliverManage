package com.software.deliver.biz.processor;

import com.software.deliver.biz.WorkFlowProgressService;
import com.software.deliver.dal.mapper.WorkFlowNodeDao;
import com.software.deliver.dal.vo.WorkFlowNodeRelVO;

import java.util.List;

public interface WorkFlowNodeActionProcessorBase {

    /**
     * 审批
     * @param workFlowId
     * @param workFlowInstanceId
     * @param currentNodeCode
     * @param workFlowProgressService
     * @return
     */
    boolean doProcess(Long workFlowId, Long workFlowInstanceId, String currentNodeCode, WorkFlowNodeDao workFlowNodeDao, WorkFlowProgressService workFlowProgressService);


    /**
     * 处理审批动作类型
      * @return
     */
    int reviewType();

}
