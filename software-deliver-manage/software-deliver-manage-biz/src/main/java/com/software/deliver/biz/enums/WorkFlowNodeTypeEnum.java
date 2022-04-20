package com.software.deliver.biz.enums;

import lombok.Getter;

@Getter
public enum WorkFlowNodeTypeEnum {

    COMMON_NODE(1,"普通节点"),
    BRANCH_NODE(2, "分支节点，多个子节点"),
    SUMMARY_NODE(3, "汇总节点，多个父节点"),

    ;
    private Integer type;

    private String msg;

    WorkFlowNodeTypeEnum(Integer type, String msg) {
        this.type = type;
        this.msg = msg;
    }
}
