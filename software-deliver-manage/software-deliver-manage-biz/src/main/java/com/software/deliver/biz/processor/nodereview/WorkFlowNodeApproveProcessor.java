package com.software.deliver.biz.processor.nodereview;

import com.software.deliver.biz.WorkFlowProgressService;
import com.software.deliver.biz.dto.FlowNodeActionProcessParam;
import com.software.deliver.biz.dto.FlowNodeHandlerTypeProcessParam;
import com.software.deliver.biz.enums.*;
import com.software.deliver.biz.factory.FlowHandlerTypeProcessorFactory;
import com.software.deliver.biz.model.WorkFlowProgress;
import com.software.deliver.biz.acquirer.WorkFlowNodeHandlerUserAcquirerBase;
import com.software.deliver.dal.mapper.FlowInstanceSubFlowSummaryDao;
import com.software.deliver.dal.mapper.WorkFlowNodeDao;
import com.software.deliver.dal.mapper.WorkFlowNodeRelDao;
import com.software.deliver.dal.vo.FlowInstanceSubFlowSummaryVO;
import com.software.deliver.dal.vo.WorkFlowNodeRelVO;
import com.software.deliver.dal.vo.WorkFlowNodeVO;
import com.software.deliver.dal.vo.WorkFlowProgressRelVO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/19 22:16
 */
public class WorkFlowNodeApproveProcessor implements WorkFlowNodeActionProcessorBase{

    public static final int ONE = 1;

    @Override
    public boolean doProcess(FlowNodeActionProcessParam param) {
        WorkFlowNodeRelDao workFlowNodeRelDao = param.getWorkFlowNodeRelDao();
        Long workFlowId = param.getCurrentProgress().getWorkFlowId();
        String currentNodeCode = param.getCurrentProgress().getWorkFlowNodeCode();

        //判断当前节点是否为最终节点
        WorkFlowNodeDao workFlowNodeDao = param.getWorkFlowNodeDao();
        WorkFlowNodeVO currentFlowNode = workFlowNodeDao.getByNodeCode(workFlowId, currentNodeCode);
        if (WorkFlowNodeSeqTypeEnum.LAST_NODE.getType().equals(currentFlowNode.getNodeSeqType())) {
            //todo:更新instance状态

            return true;
        }

        List<WorkFlowNodeRelVO> nextNodeRels = workFlowNodeRelDao.getNextNodeRels(workFlowId, currentNodeCode);
        List<String> relNodeCodes = Optional.ofNullable(nextNodeRels).orElse(new ArrayList<>()).stream()
                .map(WorkFlowNodeRelVO::getRelWorkFlowNodeCode)
                .collect(Collectors.toList());

        List<WorkFlowNodeVO> nextWorkFlowNodes = workFlowNodeDao.batchGetByNodeCodes(workFlowId, relNodeCodes);
        nextWorkFlowNodes.forEach(nextWorkFlowNodeVO -> {
            //节点处理人ids
            List<Long> userIds = acquireFlowNodeUserIds(param, nextWorkFlowNodeVO);

            if (WorkFlowNodeTypeEnum.COMMON_NODE.getType().equals(nextWorkFlowNodeVO.getNodeType()) ||
                    WorkFlowNodeTypeEnum.BRANCH_NODE.getType().equals(nextWorkFlowNodeVO.getNodeType()) ) {
                createFlowProgress(param,userIds,WorkFlowProgressStatusEnum.NEED_PROCESS.getStatus());

            } else if (WorkFlowNodeTypeEnum.SUMMARY_NODE.getType().equals(nextWorkFlowNodeVO.getNodeType())) {
                //todo:wh加签的场景待考虑
                processSummaryNode(param, nextWorkFlowNodeVO, userIds);
            }
        });

        return true;
    }

    private void processSummaryNode(FlowNodeActionProcessParam param, WorkFlowNodeVO nextWorkFlowNodeVO, List<Long> userIds) {
        Long workFlowId = param.getCurrentProgress().getWorkFlowId();
        String currentNodeCode = param.getCurrentProgress().getWorkFlowNodeCode();
        Long currentWorkFlowInstanceId = param.getCurrentProgress().getWorkFlowInstanceId();
        WorkFlowNodeRelDao workFlowNodeRelDao = param.getWorkFlowNodeRelDao();

        FlowInstanceSubFlowSummaryDao flowInstanceSubFlowSummaryDao = param.getFlowInstanceSubFlowSummaryDao();
        FlowInstanceSubFlowSummaryVO flowInstanceSubFlowSummaryVO = flowInstanceSubFlowSummaryDao
                .getWithPreNodeCode(currentWorkFlowInstanceId, nextWorkFlowNodeVO.getCode(), currentNodeCode);
        if (null == flowInstanceSubFlowSummaryVO || null == flowInstanceSubFlowSummaryVO.getId()) {
            FlowInstanceSubFlowSummaryVO flowSummaryVO = FlowInstanceSubFlowSummaryVO.builder()
                    .workFlowId(workFlowId)
                    .workFlowInstanceId(currentWorkFlowInstanceId)
                    .workFlowNodeCode(nextWorkFlowNodeVO.getCode())
                    .preWorkFlowNodeCode(currentNodeCode)
                    .value(ONE)
                    .build();
            flowInstanceSubFlowSummaryDao.create(flowSummaryVO);
        } else {
            flowInstanceSubFlowSummaryDao.increase(currentWorkFlowInstanceId, nextWorkFlowNodeVO.getCode(), currentNodeCode);

            List<FlowInstanceSubFlowSummaryVO> flowSummaryVOS = flowInstanceSubFlowSummaryDao.getByNodeCode(currentWorkFlowInstanceId,
                    nextWorkFlowNodeVO.getCode());

            int totalPreApproveCount = Optional.ofNullable(flowSummaryVOS).orElse(new ArrayList<>())
                    .stream().mapToInt(FlowInstanceSubFlowSummaryVO::getValue).sum();

            List<WorkFlowNodeRelVO> preNodeRels = workFlowNodeRelDao.grePreNodeRels(workFlowId, nextWorkFlowNodeVO.getCode());

            //所有父节点完成同意审批，暂不支持部分父节点同意即流程往下走等场景
            if (totalPreApproveCount == preNodeRels.size()) {
                createFlowProgress(param, userIds, WorkFlowProgressStatusEnum.NEED_PROCESS.getStatus());
            }
        }
    }

    /**
     * 创建flow progress流程处理进度
     * @param param
     * @param userIds
     * @param status
     */
    private void createFlowProgress(FlowNodeActionProcessParam param, List<Long> userIds, Integer status) {
        Optional.ofNullable(userIds).orElse(new ArrayList<>()).forEach(userId->{
            WorkFlowProgress workFlowProgress = WorkFlowProgress.builder()
                    .workFlowId(param.getCurrentProgress().getWorkFlowId())
                    .workFlowCode(param.getCurrentProgress().getWorkFlowCode())
                    .workFlowInstanceId(param.getCurrentProgress().getWorkFlowInstanceId())
                    .handlerUserId(userId)
                    .workFlowNodeCode(param.getCurrentProgress().getWorkFlowNodeCode())
                    .createdBy(param.getCurrentProgress().getCreatedBy())
                    .status(status)
                    .build();
            param.getWorkFlowProgressService().create(workFlowProgress);

            WorkFlowProgressRelVO nextFlowProgressRelVO = WorkFlowProgressRelVO.builder()
                    .workFlowInstanceId(param.getCurrentProgress().getWorkFlowInstanceId())
                    .workFlowProgressId(param.getCurrentProgress().getId())
                    .workFlowProgressRelId(workFlowProgress.getId())
                    .type(WorkFlowNodeRelTypeEnum.NEXT_NODE.getType())
                    .build();
            WorkFlowProgressRelVO preFlowProgressRelVO = WorkFlowProgressRelVO.builder()
                    .workFlowInstanceId(param.getCurrentProgress().getWorkFlowInstanceId())
                    .workFlowProgressId(workFlowProgress.getId())
                    .workFlowProgressRelId(param.getCurrentProgress().getId())
                    .type(WorkFlowNodeRelTypeEnum.PRE_NODE.getType())
                    .build();
            List<WorkFlowProgressRelVO> workFlowProgressRelVOS = Arrays.asList(nextFlowProgressRelVO, preFlowProgressRelVO);
            param.getWorkFlowProgressService().batchCreateFlowProgressRels(workFlowProgressRelVOS);
        });
    }

    /**
     * 获取节点的处理人用户id
     * @param param
     * @param worwwkFlowNodeVO
     * @return
     */
    private List<Long> acquireFlowNodeUserIds(FlowNodeActionProcessParam param, WorkFlowNodeVO worwwkFlowNodeVO) {
        WorkFlowNodeHandlerUserAcquirerBase processorBase = FlowHandlerTypeProcessorFactory.build(worwwkFlowNodeVO.getHandlerType());
        FlowNodeHandlerTypeProcessParam typeProcessParam = FlowNodeHandlerTypeProcessParam.builder()
                .workFlowCode(param.getCurrentProgress().getWorkFlowCode())
                .workFlowId(param.getCurrentProgress().getWorkFlowId())
                .workFlowInstanceId(param.getCurrentProgress().getWorkFlowInstanceId())
                .workFlowNodeCode(worwwkFlowNodeVO.getWorkFlowCode())
                .reviewType(WorkFlowNodeReviewTypeEnum.APPROVE.getType())
                .varName(worwwkFlowNodeVO.getVarName())
                .handlerUserId(worwwkFlowNodeVO.getHandlerUserId())
                .currentFlowProgressUserId(param.getCurrentProgress().getHandlerUserId())
                .currentFlowProgressId(param.getCurrentProgress().getId())
                .build();
        return processorBase.acquire(typeProcessParam);
    }

    @Override
    public int reviewType() {
        return WorkFlowNodeActionEnum.APPROVE.getType();
    }
}
