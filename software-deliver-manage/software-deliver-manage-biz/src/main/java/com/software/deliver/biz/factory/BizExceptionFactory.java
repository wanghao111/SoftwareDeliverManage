package com.software.deliver.biz.factory;

import com.software.deliver.biz.enums.BizExceptionEnum;
import com.software.deliver.biz.model.BizException;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/17 12:25
 */
public class BizExceptionFactory {

    public static BizException build(int errorCode, String errorMsg) {
        BizException bizexception = new BizException(errorMsg);
        bizexception.setErrorCode(errorCode);
        return bizexception;
    }

    public static BizException build(BizExceptionEnum bizExceptionEnum) {
        BizException bizException = new BizException(bizExceptionEnum.getErrorMsg());
        bizException.setErrorCode(bizExceptionEnum.getErrorCode());
        return bizException;
    }
}
