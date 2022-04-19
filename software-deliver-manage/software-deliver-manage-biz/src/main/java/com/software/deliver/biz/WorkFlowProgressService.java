package com.software.deliver.biz;

import com.software.deliver.biz.model.WorkFlowProgress;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/17 20:29
 */
public interface WorkFlowProgressService {

    int create(WorkFlowProgress workFlowProgress);

    WorkFlowProgress getByFlowProgressId(Long workFlowProgressId);

    int update(WorkFlowProgress workFlowProgress);
}
