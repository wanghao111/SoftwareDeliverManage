package com.software.deliver.dal.mapper;

import com.software.deliver.dal.vo.WorkFlowNodeActionVO;

import java.util.List;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/16 17:00
 */
public interface WorkFlowNodeActionDao {

//    int create(WorkFlowNodeActionVO workFlowNodeActionVO);

    int batchInsert(List<WorkFlowNodeActionVO> workFlowNodeActionVOS);

    int deleteByWorkFlowId(Long workFlowId);

}
