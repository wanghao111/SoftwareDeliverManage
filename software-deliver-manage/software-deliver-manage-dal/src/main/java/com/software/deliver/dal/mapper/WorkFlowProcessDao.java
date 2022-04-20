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


    List<WorkFlowProgressVO> batchGetByNodeCode(Long workFlowId, List<String> workFlowNodeCodes);

    int batchUpdate(List<WorkFlowProgressVO> workFlowProgressVOS);
}
