package com.software.deliver.biz.Exception;

import lombok.Builder;
import lombok.Data;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/30 21:25
 */
@Builder
@Data
public class BizException extends Exception{

    private String errorCode;

    public BizException(String errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
    }


}
