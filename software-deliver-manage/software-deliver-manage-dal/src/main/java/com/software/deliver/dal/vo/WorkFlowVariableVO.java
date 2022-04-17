package com.software.deliver.dal.vo;

import lombok.Data;

import java.sql.Timestamp;
/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/16 10:28
 */
@Data
public class WorkFlowVariableVO {

    private long id;

    private Long workFlowInstanceId;

    private String varName;

    /**
     * 节点处理人id
     */
    private Long value;

    private Timestamp createdAt;

    private Timestamp updateAt;


}
