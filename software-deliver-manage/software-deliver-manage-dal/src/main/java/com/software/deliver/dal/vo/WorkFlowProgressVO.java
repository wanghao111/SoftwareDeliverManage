package com.software.deliver.dal.vo;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/16 10:28
 * 流程处理进度表
 */
@Data
@Builder
public class WorkFlowProgressVO {


    private long id;

    private long workFlowId;

    private String workFlowCode;

//    private long flowNodeId;

    private String flowNodeCode;

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

//    /**
//     * 前一个流程进度id
//     */
//    private Long preFlowProgressId;
//
//    /**
//     * 下一个流程进度id
//     */
//    private Long newFlowProgressId;


    private Timestamp createdAt;

    private Timestamp updateAt;
}
