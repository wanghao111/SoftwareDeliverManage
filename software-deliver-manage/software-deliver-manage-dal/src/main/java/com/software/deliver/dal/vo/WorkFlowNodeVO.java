package com.software.deliver.dal.vo;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/30 10:29
 */
@Data
@Builder
public class WorkFlowNodeVO {

    private Long id;

    /**
     * 所属的workflow code
     */
    private String flowCode;

    /**
     * 每个workflow里nodecode唯一
     */
    private String code;

    /**
     * 节点标题
     */
    private String nodeTitle;


    /**
     * 1:串行，默认
     * 2:并行
     */
    private Integer executeType;

    //并行节点暂时仅支持全部同意后方可进入一下节点

//    /**
//     * 1:普通节点
//     * 2:加签节点
//     * 3:转办节点
//     * 4:会签节点
//     */
//    private Integer nodeType;

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
     * handlerType=2，必须指定handlerUserIds
     * id1,id2,id3
     */
    private String handlerUserIds;

    /**
     * 流程节点处理人变量名称
     * handlerType=1时需要查询变量表
     */
    private String handlerVarName;

    /**
     * 节点顺序类型
     * 0:无意义，默认
     * 1:开始节点，提交人节点
     * 2:中间节点
     * 3:结束节点
     */
    private Integer nodeSeqType;


    private Timestamp createdAt;

    private Timestamp updateAt;
}
