package com.software.deliver.dal.mapper;

import com.software.deliver.dal.vo.WorkFlowProgressRelVO;

import java.util.List;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/20 22:14
 */
public interface WorkFlowProgressRelDao {


    int create(WorkFlowProgressRelVO workFlowProgressRelVO);


    int batchCreate(List<WorkFlowProgressRelVO> workFlowProgressRelVOS);

    List<WorkFlowProgressRelVO> batchGetByProgressId(Long workFlowProgressId, Integer type);

}
