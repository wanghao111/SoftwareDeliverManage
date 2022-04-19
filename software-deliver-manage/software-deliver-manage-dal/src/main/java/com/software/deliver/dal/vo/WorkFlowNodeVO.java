package com.software.deliver.dal.vo;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/16 10:28
 */
@Data
@Builder
public class WorkFlowNodeVO {

    private long id;

    /**
     * 节点code
     */
    private String code;

    /**
     * 节点标题
     */
    private String nodeTitle;

    private long workFlowId;

    private String workFlowCode;

    /**
     * 流程节点处理人类型
     * 0:无意义，默认
     * 1:指定人处理
     * 2:固定人处理
     * 3:上一节点主管处理
     * 4:指定角色处理
     *
     */
    private Integer handlerType;

    /**
     * handlerType=1，必须指定handlerUserId
     */
//    private Long handlerUserId;

    /**
     * 流程节点处理人变量名称
     * handlerType=1时需要查询变量表
     */
    private String varName;

    /**
     * 0:无意义，默认
     * 1:普通节点
     * 2:分支节点，有多个子节点
     * 3:汇总节点，有多个父节点
     */
    private Integer nodeType;

    /**
     * nodeType=3汇总节点时，该参数有效
     * 0:无意义，默认
     * 1:任意一个父节点通过即可流转至当前汇总节点
     * 2:所有父节点通过后流转至当前汇总节点
     */
    private Integer summaryType;

//    /**
//     * 是否开始节点
//     * 0:无意义，默认
//     * 1:是
//     * 2:否
//     */
//    private Integer isStartNode;
//
//    /**
//     * 是否最后节点
//     * 0:无意义，默认
//     * 1:是
//     * 2:否
//     */
//    private Integer isLastNode;
//
//    /**
//     * 是否中间节点
//     * 0:无意义，默认
//     * 1:是
//     * 2:否
//     */
//    private Integer isMiddleNode;

    /**
     * 节点顺序类型
     * 0:无意义，默认
     * 1:开始节点
     * 2:中间节点
     * 3:结束节点
     */
    private Integer nodeSeqType;

    private Timestamp createdAt;

    private Timestamp updateAt;

}
