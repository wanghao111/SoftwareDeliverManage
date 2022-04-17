package com.software.deliver.biz.impl;

import com.software.deliver.biz.WorkFlowProgressService;
import com.software.deliver.biz.converter.WorkFlowProgressConverter;
import com.software.deliver.biz.model.WorkFlowProgress;
import com.software.deliver.dal.mapper.WorkFlowProcessDao;
import com.software.deliver.dal.vo.WorkFlowProgressVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/17 20:29
 */
@Service
public class WorkFlowProgressServiceImpl implements WorkFlowProgressService {

    @Autowired
    private WorkFlowProcessDao workFlowProcessDao;

    @Override
    public int create(WorkFlowProgress workFlowProgress) {

        WorkFlowProgressVO workFlowProgressVO = WorkFlowProgressConverter.from(workFlowProgress);
        workFlowProcessDao.create(workFlowProgressVO);

        //回填主键id
        workFlowProgress.setId(workFlowProgressVO.getId());
        return 1;
    }
}
