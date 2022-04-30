package com.software.deliver.dal.vo;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/30 17:13
 */
@Data
@Builder
public class WorkFlowProgressVO {

    private Long id;

    private String flowCode;

    private String nodeCode;

    private Long flowInstanceId;

    /**
     * 审批批次，回退到申请人再次提交，term+1
     */
    private Integer term;

    /**
     * 回退到非提交人，backNo+1
     */
    private Integer backNo;

    /**
     * 1:已提交
     * 2:待处理
     * 3:同意
     * 4:退回
     * 5:驳回
     */
    private Integer executeType;

    private Long processUserId;

    private String processMsg;

    private Long progressOptRecordId;

    private Timestamp createdAt;

    private Timestamp updateAt;
}
