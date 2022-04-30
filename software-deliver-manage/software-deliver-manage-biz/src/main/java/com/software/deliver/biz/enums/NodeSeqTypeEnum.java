package com.software.deliver.biz.enums;

import lombok.Data;
import lombok.Getter;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/30 23:41
 */
@Getter
public enum NodeSeqTypeEnum {

    START_NODE(1, "开始节点，提交人节点"),
    MIDDLE_NODE(2, "中间节点"),
    END_NODE(3, "结束节点"),
    ;

    private Integer code;

    private String msg;

    NodeSeqTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
