package com.software.deliver.dal.mapper;

import com.software.deliver.dal.vo.WorkFlowProgressVO;

import java.util.List;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/30 20:20
 */
public interface WorkFlowProgressDAO {

    int create(WorkFlowProgressVO workFlowProgressVO);

    int update(WorkFlowProgressVO workFlowProgressVO);


    WorkFlowProgressVO get(Long progressId);

    List<WorkFlowProgressVO> batchGet(Long instanceId, List<String> nodeCodes);
}
