package com.software.deliver.dal.mapper;

import com.software.deliver.dal.vo.WorkFlowProgressVO;

import java.util.List;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/16 17:00
 */
public interface WorkFlowProcessDao {

    int create(WorkFlowProgressVO workFlowProgressVO);

    WorkFlowProgressVO getByFlowProgressId(Long workFlowProgressId);

    int update(WorkFlowProgressVO workFlowProgressVO);
    

    List<WorkFlowProgressVO> batchGetByNodeIds(List<Long> workFlowNodeIds);

    int batchUpdate(List<WorkFlowProgressVO> workFlowProgressVOS);

    int batchCreate(List<WorkFlowProgressVO> workFlowProgressVOS);
}
