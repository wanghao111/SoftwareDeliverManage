package com.software.deliver.dal.vo;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/23 11:11
 */
@Data
@Builder
public class FlowInstanceSubFlowSummaryVO {

    private Long id;

    private Long workFlowInstanceId;

    private Long workFlowId;

    private String workFlowNodeCode;

    /**
     * 前置节点，确定哪个前置节点同意的数量
     */
    private String preWorkFlowNodeCode;

    /**
     * 子流程处理完成的数量
     */
    private Integer value;

    private Timestamp createdAt;

    private Timestamp updateAt;

}
