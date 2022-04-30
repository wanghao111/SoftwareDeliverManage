package com.software.deliver.biz.enums;

import lombok.Getter;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/25 21:40
 */
@Getter
public enum WorkFlowNodeTypeEnum {

    COMMON_NODE(1,"普通节点"),
    ASSIGN_NODE(2, "加签节点"),
    TRANSFER_NODE(3, "转办节点"),

    ;
    private Integer type;

    private String msg;

    WorkFlowNodeTypeEnum(Integer type, String msg) {
        this.type = type;
        this.msg = msg;
    }


}
