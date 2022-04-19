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
public class WorkFlowNodeActionVO {

    private long id;

    private Long workFlowId;
    /**
     * 流程节点
     */
    private String workFlowNodeCode;

    /**
     * 0:无意义，默认
     * 1:同意
     * 2:驳回
     * 3:加签
     * 4:转办
     *
     */

    private Integer reviewType;
    /**
     * 0:无意义，默认
     * 1:同意，流转至下一个节点
     * 2:驳回，返回上一节点
     * 3:驳回，返回提交人
     * 4:加签，流转至指定人
     * 5:转办，流转至指定人
     */
    private Integer actionType;

    private Timestamp createdAt;

    private Timestamp updateAt;


}
