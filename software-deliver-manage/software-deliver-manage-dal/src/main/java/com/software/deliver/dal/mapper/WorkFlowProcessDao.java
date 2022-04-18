package com.software.deliver.dal.mapper;

import com.software.deliver.dal.vo.WorkFlowProgressVO;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/16 17:00
 */
public interface WorkFlowProcessDao {

    int create(WorkFlowProgressVO workFlowProgressVO);

    WorkFlowProgressVO getByFlowProgressId(Long workFlowProgressId);
}
