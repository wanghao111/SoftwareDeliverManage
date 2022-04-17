package com.software.deliver.dal.mapper;

import com.software.deliver.dal.vo.WorkFlowInstanceVO;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/16 16:59
 */
public interface WorkFlowInstanceDao {

    //todo:wh instancedao.create需要设置返回主键
    int create(WorkFlowInstanceVO workFlowInstanceVO);


}
