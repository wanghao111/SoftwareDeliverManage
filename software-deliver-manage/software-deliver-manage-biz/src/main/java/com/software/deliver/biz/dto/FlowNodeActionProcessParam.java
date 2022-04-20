package com.software.deliver.biz.dto;

import com.software.deliver.biz.WorkFlowProgressService;
import com.software.deliver.dal.mapper.WorkFlowNodeDao;
import com.software.deliver.dal.mapper.WorkFlowNodeRelDao;
import lombok.Builder;
import lombok.Data;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/19 22:43
 */
@Data
@Builder
public class FlowNodeActionProcessParam {

    private Long workFlowId;

    private String workFlowCode;

    /**
     * 审批的instanceid
     */
    private Long workFlowInstanceId;

    /**
     * 当前流程处理进度实例id
     */
    private Long currentFlowProgressId;

    /**
     * 当前流程进度处理人id
     */
    private Long currentFlowProgressUserId;

    /**
     * 审批的node code
     */
    private String workFlowNodeCode;

    private WorkFlowNodeDao workFlowNodeDao;

    private WorkFlowProgressService workFlowProgressService;

    private WorkFlowNodeRelDao workFlowNodeRelDao;

}
