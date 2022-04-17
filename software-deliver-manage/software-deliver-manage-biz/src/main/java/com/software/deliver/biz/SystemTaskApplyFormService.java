package com.software.deliver.biz;

import com.software.deliver.biz.model.BizException;
import com.software.deliver.biz.model.SystemTaskApplyForm;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/17 11:56
 */
public interface SystemTaskApplyFormService {

    int create(SystemTaskApplyForm systemTaskApplyForm) throws BizException;
}
