package com.software.deliver.dal.mapper;

import com.software.deliver.dal.vo.WorkFlowInstanceVO;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/30 20:18
 */
public interface WorkFlowInstanceDAO {

    int create(WorkFlowInstanceVO workFlowInstanceVO);

    WorkFlowInstanceVO get(Long instanceId);

    int update(WorkFlowInstanceVO workFlowInstanceVO);

}
