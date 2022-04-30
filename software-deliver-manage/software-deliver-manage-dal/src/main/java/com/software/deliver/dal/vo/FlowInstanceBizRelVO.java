package com.software.deliver.dal.vo;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/30 11:08
 */
@Data
@Builder
public class FlowInstanceBizRelVO {

    private Long id;

    private Long flowInstanceId;

    private Long bizId;

    private Timestamp createdAt;

    private Timestamp updateAt;

}
