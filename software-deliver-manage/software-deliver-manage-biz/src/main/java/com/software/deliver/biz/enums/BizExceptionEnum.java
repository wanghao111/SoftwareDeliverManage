package com.software.deliver.biz.enums;

import lombok.Getter;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/30 21:29
 */
@Getter
public enum BizExceptionEnum {

    NODE_HANDLER_TYPE_NOT_SUPPORT("100001", "节点处理人类型不支持"),

    ;

    private String code;

    private String msg;

    BizExceptionEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
