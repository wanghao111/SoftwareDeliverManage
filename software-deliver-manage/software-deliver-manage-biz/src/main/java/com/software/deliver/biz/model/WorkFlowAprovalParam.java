package com.software.deliver.biz.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/30 20:27
 */
@Data
@Builder
public class WorkFlowAprovalParam implements Serializable {

    private String workFlowCode;

    private Long flowInstanceId;

    private String flowNodeCode;

    /**
     * 当前处理的进度id
     */
    private Long progressId;

    /**
     * 处理意见
     */
    private String processMsg;

    /**
     * 当前节点审批人
     */
    private Long processUserId;

    /**
     * 下一节点编码，在有多个下一个节点时需要指定
     */
    private String nextNodeCode;

}
