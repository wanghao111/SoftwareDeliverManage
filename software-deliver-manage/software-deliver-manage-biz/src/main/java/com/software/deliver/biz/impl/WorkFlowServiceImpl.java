package com.software.deliver.biz.impl;

import com.software.deliver.biz.WorkFlowService;
import com.software.deliver.biz.converter.WorkFlowConverter;
import com.software.deliver.biz.converter.WorkFlowNodeConverter;
import com.software.deliver.biz.enums.WorkFlowEnableEnum;
import com.software.deliver.biz.model.WorkFlow;
import com.software.deliver.biz.model.WorkFlowNode;
import com.software.deliver.dal.mapper.WorkFlowDao;
import com.software.deliver.dal.mapper.WorkFlowNodeActionDao;
import com.software.deliver.dal.mapper.WorkFlowNodeDao;
import com.software.deliver.dal.mapper.WorkFlowNodeRelDao;
import com.software.deliver.dal.vo.WorkFlowNodeActionVO;
import com.software.deliver.dal.vo.WorkFlowNodeRelVO;
import com.software.deliver.dal.vo.WorkFlowNodeVO;
import com.software.deliver.dal.vo.WorkFlowVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/16 17:47
 */
@Service
public class WorkFlowServiceImpl implements WorkFlowService {

    @Autowired
    private WorkFlowDao workFlowDao;

    @Autowired
    private WorkFlowNodeDao workFlowNodeDao;

    @Autowired
    private WorkFlowNodeRelDao workFlowNodeRelDao;

    @Autowired
    private WorkFlowNodeActionDao workFlowNodeActionDao;

    @Override
    @Transactional
    public int create(WorkFlow workFlow) {

        WorkFlowVO workFlowVO = WorkFlowConverter.fromWorkFlow(workFlow);
        workFlowVO.setIsEnable(WorkFlowEnableEnum.ENABLE.getType());
        workFlowDao.create(workFlowVO);

        List<WorkFlowNode> workFlowNodes = workFlow.getWorkFlowNodes();

        List<WorkFlowNodeVO> workFlowNodeVOS = new ArrayList<>();
        List<WorkFlowNodeRelVO> workFlowNodeRelVOS = new ArrayList<>();
        List<WorkFlowNodeActionVO> workFlowNodeActionVOS = new ArrayList<>();
        WorkFlowNodeConverter.flowNodes2NodeVOs(workFlowNodes, workFlowNodeVOS, workFlowNodeRelVOS,workFlowNodeActionVOS);

        workFlowNodeDao.batchInsert(workFlowNodeVOS);

        workFlowNodeRelDao.batchInsert(workFlowNodeRelVOS);

        workFlowNodeActionDao.batchInsert(workFlowNodeActionVOS);

        return 1;
    }

    @Override
    public int update(WorkFlow workFlow) {
        WorkFlowVO workFlowVO = WorkFlowConverter.fromWorkFlow(workFlow);
        workFlowDao.update(workFlowVO);

        //先删除原工作流节点和节点关系
        workFlowNodeDao.deleteByWorkFlowId(workFlow.getId());

        workFlowNodeRelDao.deleteByWorkFlowId(workFlow.getId());

        workFlowNodeActionDao.deleteByWorkFlowId(workFlow.getId());

        List<WorkFlowNode> workFlowNodes = workFlow.getWorkFlowNodes();

        List<WorkFlowNodeVO> workFlowNodeVOS = new ArrayList<>();
        List<WorkFlowNodeRelVO> workFlowNodeRelVOS = new ArrayList<>();
        List<WorkFlowNodeActionVO> workFlowNodeActionVOS = new ArrayList<>();
        WorkFlowNodeConverter.flowNodes2NodeVOs(workFlowNodes, workFlowNodeVOS, workFlowNodeRelVOS,workFlowNodeActionVOS);

        workFlowNodeDao.batchInsert(workFlowNodeVOS);

        workFlowNodeRelDao.batchInsert(workFlowNodeRelVOS);

        workFlowNodeActionDao.batchInsert(workFlowNodeActionVOS);

        return 1;
    }


    @Override
    public int delete(Long workFlowId) {

        workFlowDao.delete(workFlowId);

        workFlowNodeDao.deleteByWorkFlowId(workFlowId);

        workFlowNodeRelDao.deleteByWorkFlowId(workFlowId);

        workFlowNodeActionDao.deleteByWorkFlowId(workFlowId);

        return 1;
    }

    @Override
    public WorkFlow getBriefByCode(String workFlowCode) {
        WorkFlowVO workFlowVO = workFlowDao.getByCode(workFlowCode);
        return WorkFlowConverter.fromWorkFlowVO(workFlowVO);
    }
}
