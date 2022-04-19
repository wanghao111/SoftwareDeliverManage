package com.software.deliver.biz.model;

import com.software.deliver.biz.WorkFlowProgressService;
import com.software.deliver.dal.mapper.WorkFlowNodeDao;
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

    private Long workFlowInstanceId;

    private String currentNodeCode;

    private WorkFlowNodeDao workFlowNodeDao;

    private WorkFlowProgressService workFlowProgressService;

}
