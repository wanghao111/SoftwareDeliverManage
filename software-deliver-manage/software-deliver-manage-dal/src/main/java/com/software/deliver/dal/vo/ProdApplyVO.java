package com.software.deliver.dal.vo;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/30 10:15
 */

@Data
@Builder
public class ProdApplyVO {

    private Long id;

    private String requirementNo;

    private String requirementTitle;

    /**
     * 开发人员id
     */
    private Long devUserId;

    /**
     * 模拟开发负责人上传文件
     */
    private String devMsg;

    /**
     * 测试人员id
     */
    private Long testUserId;

    /**
     * 模拟测试负责人上传文件
     */
    private String testMsg;

    /**
     * 验收人id
     */
    private Long acceptanceUserId;

    /**
     * 模拟验收负责人上传文件
     */
    private String acceptanceMsg;

    /**
     * 创建人id
     */
    private Long createBy;

    private Timestamp createdAt;

    private Timestamp updateAt;
}
