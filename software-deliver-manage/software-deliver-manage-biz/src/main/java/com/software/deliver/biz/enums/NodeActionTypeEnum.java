package com.software.deliver.biz.enums;

import lombok.Getter;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/30 20:45
 */
@Getter
public enum NodeActionTypeEnum {

    AGREE(1, "同意"),
    GOBCK(2, "回退"),
    ADDSIGNATURE(3, "加签"),
    TRANSFER(4, "转办"),
    ;

    private Integer code;

    private String msg;

    NodeActionTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
