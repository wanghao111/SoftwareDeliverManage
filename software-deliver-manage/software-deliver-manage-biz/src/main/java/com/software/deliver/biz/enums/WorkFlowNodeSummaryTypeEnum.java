package com.software.deliver.biz.enums;

import lombok.Getter;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/20 21:53
 */
@Getter
public enum WorkFlowNodeSummaryTypeEnum {

    //目前仅支持ALL_PRE_APPROVE
    ONE_PRE_APPROVE(1,"任意一个父节点通过即可流转至当前汇总节点"),
    ALL_PRE_APPROVE(2, "所有父节点通过后流转至当前汇总节点"),

    ;
    private Integer type;

    private String msg;

    WorkFlowNodeSummaryTypeEnum(Integer type, String msg) {
        this.type = type;
        this.msg = msg;
    }
}
