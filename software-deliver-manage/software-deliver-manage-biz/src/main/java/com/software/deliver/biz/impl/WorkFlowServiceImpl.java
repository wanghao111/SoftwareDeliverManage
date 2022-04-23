package com.software.deliver.biz.impl;

import com.software.deliver.biz.WorkFlowProgressService;
import com.software.deliver.biz.WorkFlowService;
import com.software.deliver.biz.converter.WorkFlowConverter;
import com.software.deliver.biz.converter.WorkFlowNodeConverter;
import com.software.deliver.biz.dto.WorkFlowReviewDTO;
import com.software.deliver.biz.enums.*;
import com.software.deliver.biz.factory.BizExceptionFactory;
import com.software.deliver.biz.factory.FlowNodeActionProcessParamFactory;
import com.software.deliver.biz.factory.WorkFlowNodeActionProcessorFactory;
import com.software.deliver.biz.dto.FlowNodeActionProcessParam;
import com.software.deliver.biz.model.WorkFlow;
import com.software.deliver.biz.model.WorkFlowNode;
import com.software.deliver.biz.model.WorkFlowProgress;
import com.software.deliver.biz.processor.nodereview.WorkFlowNodeActionProcessorBase;
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
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private WorkFlowProgressService workFlowProgressService;

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

    @Override
    public WorkFlowNode getBriefStartNode(String workFlowCode) {
        WorkFlowNodeVO startNode = workFlowNodeDao.getStartNodeByFlowCode(workFlowCode);
        return WorkFlowNodeConverter.WorkFlowNodeVO2WorkFlowNode(startNode);
    }

    @Override
    public List<WorkFlowNode> getBriefNextNode(Long workFlowId, String workFlowNodeCode) {
        List<WorkFlowNodeRelVO> nextNodeRels = workFlowNodeRelDao.getNextNodeRels(workFlowId, workFlowNodeCode);
        List<String> nextNodeRelCodes = nextNodeRels.stream().map(WorkFlowNodeRelVO::getRelWorkFlowNodeCode).collect(Collectors.toList());
        List<WorkFlowNodeVO> workFlowNodeVOS = workFlowNodeDao.batchGetByNodeCodes(workFlowId, nextNodeRelCodes);
        return WorkFlowNodeConverter.WorkFlowNodeVOs2WorkFlowNodes(workFlowNodeVOS);
    }

    @Override
    public boolean reviewFlow(WorkFlowReviewDTO workFlowReviewDTO) throws Exception {
        Long flowInstanceId = workFlowReviewDTO.getWorkFlowInstanceId();
        String flowNodeCode = workFlowReviewDTO.getWorkFlowNodeCode();
        Integer reviewType = workFlowReviewDTO.getReviewType();
        Long workFlowProgressId = workFlowReviewDTO.getWorkFlowProgressId();

        WorkFlowNodeActionVO workFlowNodeActionVO = workFlowNodeActionDao.get(flowNodeCode, reviewType);
        Integer actionType = workFlowNodeActionVO.getActionType();

        //更新当前审批节点状态
        WorkFlowProgress currentProgress = workFlowProgressService.getByFlowProgressId(workFlowProgressId);
        Integer progressStatus;
        if (WorkFlowNodeReviewTypeEnum.APPROVE.getType().equals(reviewType)) {
            progressStatus = WorkFlowProgressStatusEnum.AGREE.getStatus();
        } else if (WorkFlowNodeReviewTypeEnum.REJECT.getType().equals(reviewType)) {
            progressStatus = WorkFlowProgressStatusEnum.REJECT.getStatus();
        } else if (WorkFlowNodeReviewTypeEnum.ADD_SIGN.getType().equals(reviewType)) {
            progressStatus = WorkFlowProgressStatusEnum.ADD_SIGN.getStatus();
        } else if (WorkFlowNodeReviewTypeEnum.transfer.getType().equals(reviewType)) {
            progressStatus = WorkFlowProgressStatusEnum.TRANSFER.getStatus();
        } else {
            throw BizExceptionFactory.build(BizExceptionEnum.WORK_FLOW_REVIEW_TYPE_ILLEGAL);
        }

        currentProgress.setStatus(progressStatus);
        workFlowProgressService.update(currentProgress);

        //更新flowinstance状态
        //todo:wh待实现

        //新增下一个节点审批进度
        //判断是否为最后一个节点
        WorkFlowNodeActionProcessorBase processorBase = WorkFlowNodeActionProcessorFactory.build(actionType);
        FlowNodeActionProcessParam param = FlowNodeActionProcessParamFactory.build(currentProgress,
                                  workFlowNodeDao, workFlowProgressService, workFlowNodeRelDao);
        return processorBase.doProcess(param);
    }
}
