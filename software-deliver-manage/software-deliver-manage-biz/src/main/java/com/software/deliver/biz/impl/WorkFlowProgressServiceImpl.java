package com.software.deliver.biz.impl;

import com.software.deliver.biz.WorkFlowProgressService;
import com.software.deliver.biz.converter.WorkFlowProgressConverter;
import com.software.deliver.biz.model.WorkFlowProgress;
import com.software.deliver.dal.mapper.WorkFlowProcessDao;
import com.software.deliver.dal.mapper.WorkFlowProgressRelDao;
import com.software.deliver.dal.vo.WorkFlowProgressRelVO;
import com.software.deliver.dal.vo.WorkFlowProgressVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/17 20:29
 */
@Service
public class WorkFlowProgressServiceImpl implements WorkFlowProgressService {

    @Autowired
    private WorkFlowProcessDao workFlowProcessDao;

    @Autowired
    private WorkFlowProgressRelDao workFlowProgressRelDao;

    @Override
    public int create(WorkFlowProgress workFlowProgress) {

        WorkFlowProgressVO workFlowProgressVO = WorkFlowProgressConverter.from(workFlowProgress);
        workFlowProcessDao.create(workFlowProgressVO);
        //回填主键id
        workFlowProgress.setId(workFlowProgressVO.getId());
        return 1;
    }

    @Override
    public WorkFlowProgress getByFlowProgressId(Long workFlowProgressId) {
        WorkFlowProgressVO workFlowProgressVO= workFlowProcessDao.getByFlowProgressId(workFlowProgressId);
        return WorkFlowProgressConverter.from(workFlowProgressVO);
    }

    @Override
    public int update(WorkFlowProgress workFlowProgress) {
        return workFlowProcessDao.update(WorkFlowProgressConverter.from(workFlowProgress));
    }

    @Override
    public int batchUpdate(List<WorkFlowProgress> workFlowProgresses) {
        return workFlowProcessDao.batchUpdate(WorkFlowProgressConverter.batchFrom(workFlowProgresses));
    }

    @Override
    public List<WorkFlowProgress> batchGetByNodeCode(Long workFlowId, List<String> workFlowNodeCodes) {
        List<WorkFlowProgressVO> workFlowProgressVOS = workFlowProcessDao.batchGetByNodeCode(workFlowId, workFlowNodeCodes);
        return WorkFlowProgressConverter.batchFromVO(workFlowProgressVOS);
    }

    @Override
    public int batchCreateFlowProgressRels(List<WorkFlowProgressRelVO> workFlowProgressRelVOS) {
        return workFlowProgressRelDao.batchCreate(workFlowProgressRelVOS);
    }

    @Override
    public List<WorkFlowProgressRelVO> batchGetFlowProgressRels(Long workFlowProgressId, Integer type) {
        return null;
    }

    @Override
    public List<WorkFlowProgress> batchGetFlowProgresses(List<Long> workFlowProgressIds) {
        return null;
    }
}
