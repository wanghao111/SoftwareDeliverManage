package com.software.deliver.biz.dto;

import com.software.deliver.dal.mapper.WorkFlowVariableDao;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FlowNodeHandlerTypeProcessParam {

    private Long workFlowId;

    private String workFlowCode;

    /**
     * 下一个审批节点
     */
    private String workFlowNodeCode;

    /**
     * 当前审批的流程实例id
     */
    private Long workFlowInstanceId;

    /**
     * 流程当前处理进度实例id
     */
    private Long currentFlowProgressId;

    /**
     * 当前处理进度用户id
     */
    private Long currentFlowProgressUserId;

    private String varName;

    private Long handlerUserId;

    private Integer reviewType;

    private WorkFlowVariableDao workFlowVariableDao;





//    private Integer status;

}
