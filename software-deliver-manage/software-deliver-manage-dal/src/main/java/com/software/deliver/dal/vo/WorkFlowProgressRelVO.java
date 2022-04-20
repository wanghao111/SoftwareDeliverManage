package com.software.deliver.dal.vo;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/20 22:12
 */
@Data
@Builder
public class WorkFlowProgressRelVO {

    private Long id;

    private Long workFlowInstanceId;

    private Long workFlowProgressId;


    private Long workFlowProgressRelId;

    /**
     * 0:无意义，默认
     * 1:前置节点关系
     * 2:后置节点关系
     */
    private Integer type;


    private Timestamp createdAt;

    private Timestamp updateAt;



}
