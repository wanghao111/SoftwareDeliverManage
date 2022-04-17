package com.software.deliver.biz.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/17 12:23
 */
@Data
@Builder
public class BizException extends Exception{

    public BizException(String msg) {
        super(msg);
    }
    private int errorCode;


}
