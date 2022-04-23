package com.software.deliver.dal.mapper;

import com.software.deliver.dal.vo.FlowInstanceSubFlowSummaryVO;

import java.util.List;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/23 11:16
 */
public interface FlowInstanceSubFlowSummaryDao {

    int create(FlowInstanceSubFlowSummaryVO flowInstanceSubFlowSummaryVO);

    int increase(Long workFlowId, Long InstanceId, String workFlowNodeCode, String preWorkFlowNodeCode);

    FlowInstanceSubFlowSummaryVO getWithPreNodeCode(Long workFlowId, Long InstanceId, String workFlowNodeCode, String preWorkFlowNode);

    List<FlowInstanceSubFlowSummaryVO> getByNodeCode(Long workFlowId, Long InstanceId, String workFlowNodeCode);
}
