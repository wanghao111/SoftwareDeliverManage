package com.software.deliver.dal.vo;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/16 10:28
 */
@Data
@Builder
public class SystemTaskApplyFormVO {

    private long id;

//    private String title;

    /**
     * 需求号
     */
    private Long requirementId;

    /**
     * 需求名称
     */
    private String requirementTitle;

    /**
     * 0:无意义，默认
     * 1:设计任务
     * 2:开发任务
     * 3:测试任务
     */
    private Integer type;

    /**
     * 该系统任务负责人id
     */
    private Long ownerUserId;

    /**
     * 审批人可以编辑，模拟上传文件
     */
    private String msg;

    /**
     * qa用户id
     */
    private Long qaUserId;

    /**
     * 创建人userId
     */
    private Long createdBy;

    /**
     * 关联的流程实例id
     */
    private Long workFlowInstanceId;

    private Timestamp createdAt;

    private Timestamp updateAt;

}
