package com.software.deliver.dal.vo;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/24 22:20
 */
@Data
@Builder
public class WorkFlowInstanceVariableVO {

    private Long id;

    private Long workFlowInstanceId;

    /**
     * 0:无意义，默认
     * 1:节点处理人变量
     * 2:节点流入判断变量
     * 3:节点的前置子流程处理汇总变量 key:flowNodeCode_preFlowNodeCode
     * 4:节点后置的子流程处理汇总变量 key:flowNodeCode_nextFlowNodeCode
     *
     */
    private Integer varType;

    /**
     * key
     */
    private String varName;

    private String value;

    private Timestamp createdAt;

    private Timestamp updateAt;



}
