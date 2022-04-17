package com.software.deliver.dal.mapper;

import com.software.deliver.dal.vo.WorkFlowVO;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/16 16:52
 */
public interface WorkFlowDao {


    int create(WorkFlowVO workFlowVO);


    int delete(Long workFlowId);


    int update(WorkFlowVO workFlowVO);


    WorkFlowVO getByCode(String workFlowCode);
}
