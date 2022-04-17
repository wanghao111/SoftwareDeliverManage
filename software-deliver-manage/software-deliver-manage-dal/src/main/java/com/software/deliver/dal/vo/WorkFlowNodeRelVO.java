package com.software.deliver.dal.vo;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/16 11:06
 */
@Data
@Builder
public class WorkFlowNodeRelVO {


    private Long id;

    private Long workFlowId;

    private Long workFlowNodeId;

    /**
     * 0:无意义，默认
     * 1:前置节点关系
     * 2:后置节点关系
     */
    private Integer type;

    /**
     * 关联节点的code
     */
    private String relWorkFlowNodeCode;

    private Timestamp createdAt;

    private Timestamp updateAt;


}
