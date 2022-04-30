package com.software.deliver.dal.vo;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/30 21:33
 */
@Data
@Builder
public class WorkFlowInstanceVarVO {

    private Long id;

    private Long flowInstanceId;

    private String workFlowCode;

    private String nodeCode;

    private String varName;

    private String value;

    private Timestamp createdAt;

    private Timestamp updateAt;
}
