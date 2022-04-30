package com.software.deliver.biz.factory;

import com.software.deliver.biz.Exception.BizException;
import com.software.deliver.biz.enums.BizExceptionEnum;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/30 21:27
 */
public class BizExceptionFactory {

    public static BizException build(BizExceptionEnum bizExceptionEnum) {
        return new BizException(bizExceptionEnum.getCode(), bizExceptionEnum.getMsg());
    }

}
