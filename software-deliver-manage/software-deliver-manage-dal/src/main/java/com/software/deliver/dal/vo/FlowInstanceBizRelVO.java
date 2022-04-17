package com.software.deliver.dal.vo;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/17 11:00
 */
@Data
@Builder
public class FlowInstanceBizRelVO {

    private Long id;

    private Long workFlowId;

    private Long workFlowInstanceId;

    /**
     * 关联的业务表id，可能来源不同的业务表
     */
    private Long bizId;

    private Timestamp createdAt;

    private Timestamp updateAt;

}
