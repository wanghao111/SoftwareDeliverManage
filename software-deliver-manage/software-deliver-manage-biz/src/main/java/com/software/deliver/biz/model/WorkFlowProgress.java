package com.software.deliver.biz.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/17 20:27
 */
@Data
@Builder
public class WorkFlowProgress {

    private Long id;

    private long workFlowId;

    private String workFlowCode;

    private String workFlowNodeCode;

    /**
     * 流程实例id
     */
    private Long workFlowInstanceId;

    /**
     * 当前处理人id
     */
    private long handlerUserId;


    /**
     * 当前处理状态
     * 0:无意义，默认
     * 1:已提交
     * 2:待处理
     * 3:同意
     * 4:驳回
     * 5:加签
     * 6:转办
     */
    private Integer status;


    /**
     * 处理意见
     */
    private String handlerMsg;


    private Timestamp createdAt;

    private Timestamp updateAt;


}
