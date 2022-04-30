package com.software.deliver.dal.vo;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/30 10:23
 */
@Data
@Builder
public class WorkFlowInstanceVO {

    private long id;

    private String workFlowCode;

    private String flowName;

    private String flowInstanceTitle;

    private Long submitUserId;

    /**
     * 流程状态
     * 1:审批中
     * 2:已完成
     * 3:已关闭
     */
    private Integer status;


    private Timestamp createdAt;

    private Timestamp updateAt;


}
