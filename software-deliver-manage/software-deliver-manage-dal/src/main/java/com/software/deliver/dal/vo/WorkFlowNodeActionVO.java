package com.software.deliver.dal.vo;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/30 10:43
 */
@Data
@Builder
public class WorkFlowNodeActionVO {

    private Long id;

    private String flowCode;

    private String nodeCode;

    /**
     * 1:同意
     * 2:退回
     * 3:加签
     * 4:转办
     */
    private Integer actionType;

    /**
     * actionType对应的下一个节点编码
     */
    private String nextNodeCode;

    private Timestamp createdAt;

    private Timestamp updateAt;
}
