package com.software.deliver.biz.enums;

import lombok.Getter;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/17 21:03
 */
@Getter
public enum WorkFlowNodeSeqTypeEnum {

    START_NODE(1,"开始节点"),
    MIDDLE_NODE(2, "中间节点"),
    LAST_NODE(3, "结束节点"),

    ;
    private Integer type;

    private String msg;

    private WorkFlowNodeSeqTypeEnum(Integer type, String msg) {
        this.type = type;
        this.msg = msg;
    }
}
