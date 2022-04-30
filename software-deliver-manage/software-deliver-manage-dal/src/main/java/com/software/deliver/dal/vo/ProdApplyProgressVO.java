package com.software.deliver.dal.vo;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/30 12:44
 */
@Data
@Builder
public class ProdApplyProgressVO {

    private Long id;

    private Long flowInstanceId;

    private Long bizId;

    private String requirementNo;

    private String requirementTitle;

    private String flowCode;

    private String nodeCode;

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

    private String processMsg;

    /**
     * 模拟使用开发人员id进行模糊搜索
     */
    private Long devUserId;

    /**
     * 创建人id
     */
    private Long createBy;

    //同一个nodeCode，多个处理人，多条记录
    private Long currentProcessUserId;

    /**
     * 1:审批中
     * 2:已完成
     * 3:已关闭
     */
    private Integer status;

    private Timestamp createdAt;

    private Timestamp updateAt;

}
