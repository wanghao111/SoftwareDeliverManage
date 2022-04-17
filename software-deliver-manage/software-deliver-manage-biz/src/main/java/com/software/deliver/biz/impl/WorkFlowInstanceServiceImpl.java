package com.software.deliver.biz.impl;

import com.software.deliver.biz.WorkFlowInstanceService;
import com.software.deliver.biz.converter.WorkFlowInstanceConverter;
import com.software.deliver.biz.model.WorkFlowInstance;
import com.software.deliver.dal.mapper.WorkFlowInstanceDao;
import com.software.deliver.dal.vo.WorkFlowInstanceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/17 11:43
 */
@Service
public class WorkFlowInstanceServiceImpl implements WorkFlowInstanceService {

    @Autowired
    private WorkFlowInstanceDao workFlowInstanceDao;

    @Override
    public int create(WorkFlowInstance workFlowInstance) {
        WorkFlowInstanceVO workFlowInstanceVO = WorkFlowInstanceConverter.from(workFlowInstance);
        workFlowInstanceDao.create(workFlowInstanceVO);
        return 0;
    }
}
