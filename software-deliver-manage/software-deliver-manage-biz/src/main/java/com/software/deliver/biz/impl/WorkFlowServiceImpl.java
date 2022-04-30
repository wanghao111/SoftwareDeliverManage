package com.software.deliver.biz.impl;

import com.software.deliver.biz.WorkFlowService;
import com.software.deliver.biz.achiever.NodeProcessUserAchiever;
import com.software.deliver.biz.enums.FlowInstanceStatusTypeEnum;
import com.software.deliver.biz.enums.FlowProgressExecuteTypeEnum;
import com.software.deliver.biz.enums.NodeActionTypeEnum;
import com.software.deliver.biz.enums.NodeSeqTypeEnum;
import com.software.deliver.biz.factory.WorkFlowProgressFactory;
import com.software.deliver.biz.factory.WorkFlowProgressRecordFactory;
import com.software.deliver.biz.model.WorkFlowAprovalParam;
import com.software.deliver.dal.mapper.*;
import com.software.deliver.dal.vo.*;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/30 20:33
 */
@Service
@Slf4j
public class WorkFlowServiceImpl implements WorkFlowService {

    @Autowired
    private WorkFlowNodeDAO workFlowNodeDAO;

    @Autowired
    private  WorkFlowNodeActionDAO workFlowNodeActionDAO;

    @Autowired
    private WorkFlowInstanceDAO workFlowInstanceDAO;

    @Autowired
    private WorkFlowProgressDAO workFlowProgressDAO;

    @Autowired
    private WorkFlowProgressOperateRecordDAO workFlowProgressOperateRecordDAO;

    @Autowired
    private NodeProcessUserAchiever nodeProcessUserAchiever;




    @Override
    public boolean agree(WorkFlowAprovalParam workFlowAprovalParam) {
        String workFlowCode = workFlowAprovalParam.getWorkFlowCode();
        Long flowInstanceId = workFlowAprovalParam.getFlowInstanceId();
        String flowNodeCode = workFlowAprovalParam.getFlowNodeCode();

        Long processUserId = workFlowAprovalParam.getProcessUserId();

        //判断当前是否为开始节点或最后节点,和修改当前节点待办为已同意状态
        WorkFlowProgressVO workFlowProgressVO = workFlowProgressDAO.get(workFlowAprovalParam.getProgressId());

        //更新progress
        workFlowProgressVO.setExecuteType(FlowProgressExecuteTypeEnum.AGREE.getCode());
        workFlowProgressVO.setProcessMsg(workFlowAprovalParam.getProcessMsg());
        workFlowProgressDAO.update(workFlowProgressVO);

//        //更新progressRecord记录
        WorkFlowProgressOperateRecordVO recordVO = workFlowProgressOperateRecordDAO.get(workFlowProgressVO.getProgressOptRecordId());
        recordVO.setExecuteTime(new Timestamp(new Date().getTime()));
        recordVO.setExecuteType(FlowProgressExecuteTypeEnum.AGREE.getCode());
        recordVO.setProcessMsg(workFlowAprovalParam.getProcessMsg());
        recordVO.setIsViewed(1);
        recordVO.setViewTime(recordVO.getIsViewed() == 0 ? recordVO.getExecuteTime() : null);

        List<WorkFlowNodeVO> currentNode = workFlowNodeDAO.batchGetByNodeCodes(workFlowCode, Arrays.asList(flowNodeCode));
        if (currentNode.get(0).getNodeSeqType().equals(NodeSeqTypeEnum.END_NODE.getCode())) {
            //结束节点，流程结束

            workFlowProgressOperateRecordDAO.update(recordVO);

            WorkFlowInstanceVO workFlowInstanceVO = workFlowInstanceDAO.get(flowInstanceId);
            workFlowInstanceVO.setStatus(FlowInstanceStatusTypeEnum.DONE.getCode());
            workFlowInstanceDAO.update(workFlowInstanceVO);
            return true;
        }

        //todo:wh当前默认支持一个节点只有一个处理人
        List<WorkFlowNodeActionVO> nodeActionVOS = workFlowNodeActionDAO.getActionType(workFlowCode, flowNodeCode,
                NodeActionTypeEnum.AGREE.getCode());

        List<String> nextNodeCodes = Optional.ofNullable(nodeActionVOS).orElse(new ArrayList<>())
                .stream().map(WorkFlowNodeActionVO::getNextNodeCode).collect(Collectors.toList());


        List<WorkFlowProgressVO> workFlowProgressVOS = workFlowProgressDAO.batchGet(flowInstanceId, nextNodeCodes);
        if (!CollectionUtils.isEmpty(workFlowProgressVOS)) {
            //回退流程中的节点
            workFlowProgressVOS.forEach(workFlowProgressVO1 -> {
               workFlowProgressVO1.setExecuteType(FlowProgressExecuteTypeEnum.NEED_PROCESS.getCode());
               workFlowProgressVO1.setProcessMsg("");

                WorkFlowProgressOperateRecordVO workFlowProgressOperateRecordVO = WorkFlowProgressRecordFactory.create(workFlowCode,
                        workFlowProgressVO1.getNodeCode(), flowInstanceId, FlowProgressExecuteTypeEnum.NEED_PROCESS.getCode(),
                        new Timestamp(new Date().getTime()),
                        workFlowProgressVO1.getProcessUserId(), null,
                        0,null,null, null);
                workFlowProgressOperateRecordDAO.create(workFlowProgressOperateRecordVO);

                workFlowProgressVO1.setProgressOptRecordId(workFlowProgressOperateRecordVO.getId());
                workFlowProgressDAO.update(workFlowProgressVO1);

                //todo:wh目前仅支持一个节点一个处理人
                recordVO.setNextNodeCode(workFlowProgressVO1.getNodeCode());
                recordVO.setNextNodeProcessUserIds(workFlowProgressVO1.getProcessUserId().toString());
                workFlowProgressOperateRecordDAO.update(recordVO);
            });
            return true;
        }

        //非回退流程中的节点
        List<WorkFlowNodeVO> workFlowNodeVOS = workFlowNodeDAO.batchGetByNodeCodes(workFlowCode, nextNodeCodes);

        //获取下一批节点的审批人id列表
        Map<String, List<Long>> nodeCodeUserIds = achieveUserIds(flowInstanceId, processUserId, workFlowNodeVOS);

        Optional.ofNullable(workFlowNodeVOS).orElse(new ArrayList<>()).forEach(nextNodeCode -> {
            List<Long> userIds = nodeCodeUserIds.get(nextNodeCode.getCode());
            if (CollectionUtils.isEmpty(userIds)) {
                return;
            }

//            // todo:wh 仅支持一个节点一个审批人
//            //新增progress record待办
            WorkFlowProgressOperateRecordVO workFlowProgressOperateRecordVO = WorkFlowProgressRecordFactory.create(workFlowCode,
                    nextNodeCode.getCode(), flowInstanceId, FlowProgressExecuteTypeEnum.NEED_PROCESS.getCode(),
                    new Timestamp(new Date().getTime()),
                    userIds.get(0), null,
                    0,null,null, null);
            workFlowProgressOperateRecordDAO.create(workFlowProgressOperateRecordVO);

            //新增progress待办
            // todo:wh 仅支持一个节点一个审批人
            WorkFlowProgressVO nextWorkFlowProgressVO = WorkFlowProgressFactory.create(workFlowCode, flowInstanceId,
                    nextNodeCode.getCode(),userIds.get(0),null,null,
                    FlowProgressExecuteTypeEnum.NEED_PROCESS.getCode(),
                    workFlowProgressVO.getTerm(),workFlowProgressVO.getBackNo()
                    );
            workFlowProgressDAO.create(nextWorkFlowProgressVO);


//            //更新当前审批节点的审批记录
            //todo:wh多个子节点时待考虑
            recordVO.setNextNodeCode(nextNodeCode.getCode());
            recordVO.setNextNodeProcessUserIds(userIds.toString());
            workFlowProgressOperateRecordDAO.update(recordVO);
        });

        return true;
    }


    @Override
    public boolean goBack(WorkFlowAprovalParam workFlowAprovalParam) {
        String workFlowCode = workFlowAprovalParam.getWorkFlowCode();
        Long flowInstanceId = workFlowAprovalParam.getFlowInstanceId();
        String flowNodeCode = workFlowAprovalParam.getFlowNodeCode();

        Long processUserId = workFlowAprovalParam.getProcessUserId();

        //判断当前是否为开始节点或最后节点,和修改当前节点待办为已同意状态
        WorkFlowProgressVO workFlowProgressVO = workFlowProgressDAO.get(workFlowAprovalParam.getProgressId());

        //更新progress
        workFlowProgressVO.setExecuteType(FlowProgressExecuteTypeEnum.GO_BACK.getCode());
        workFlowProgressVO.setProcessMsg(workFlowAprovalParam.getProcessMsg());
        workFlowProgressDAO.update(workFlowProgressVO);

        //更新progressRecord记录
        WorkFlowProgressOperateRecordVO recordVO = workFlowProgressOperateRecordDAO.get(workFlowProgressVO.getProgressOptRecordId());
        recordVO.setExecuteTime(new Timestamp(new Date().getTime()));
        recordVO.setExecuteType(FlowProgressExecuteTypeEnum.GO_BACK.getCode());
        recordVO.setProcessMsg(workFlowAprovalParam.getProcessMsg());
        recordVO.setIsViewed(1);
        recordVO.setViewTime(recordVO.getIsViewed() == 0 ? recordVO.getExecuteTime() : null);

        if (!StringUtils.isEmpty(workFlowAprovalParam.getNextNodeCode())) {
            //回退到指定节点
            List<WorkFlowProgressVO> nextWorkFlowProgressVOS = workFlowProgressDAO.batchGet(flowInstanceId,
                    Arrays.asList(workFlowAprovalParam.getNextNodeCode()));

            if (!CollectionUtils.isEmpty(nextWorkFlowProgressVOS)) {
                for (WorkFlowProgressVO nextWorkFlowProgressVO : nextWorkFlowProgressVOS) {
                    nextWorkFlowProgressVO.setProcessMsg("");
                    nextWorkFlowProgressVO.setExecuteType(FlowProgressExecuteTypeEnum.NEED_PROCESS.getCode());

                    WorkFlowProgressOperateRecordVO workFlowProgressOperateRecordVO = WorkFlowProgressRecordFactory.create(workFlowCode,
                            nextWorkFlowProgressVO.getNodeCode(), flowInstanceId, FlowProgressExecuteTypeEnum.NEED_PROCESS.getCode(),
                            new Timestamp(new Date().getTime()),
                            nextWorkFlowProgressVO.getProcessUserId(), null,
                            0,null,null, null);
                    workFlowProgressOperateRecordDAO.create(workFlowProgressOperateRecordVO);

                    nextWorkFlowProgressVO.setProgressOptRecordId(workFlowProgressOperateRecordVO.getId());
                    workFlowProgressDAO.update(nextWorkFlowProgressVO);

                    //todo:wh目前仅支持一个节点一个处理人
                    recordVO.setNextNodeCode(nextWorkFlowProgressVO.getNodeCode());
                    recordVO.setNextNodeProcessUserIds(nextWorkFlowProgressVO.getProcessUserId().toString());
                    workFlowProgressOperateRecordDAO.update(recordVO);
                }
                return true;
            }
        }
        List<WorkFlowNodeActionVO> nodeActionVOS = workFlowNodeActionDAO.getActionType(workFlowCode, flowNodeCode,
                NodeActionTypeEnum.GOBCK.getCode());

        List<String> nextNodeCodes = Optional.ofNullable(nodeActionVOS).orElse(new ArrayList<>())
                .stream().map(WorkFlowNodeActionVO::getNextNodeCode).collect(Collectors.toList());
        List<WorkFlowNodeVO> workFlowNodeVOS = workFlowNodeDAO.batchGetByNodeCodes(workFlowCode, nextNodeCodes);
        //获取下一批节点的审批人id列表
        Map<String, List<Long>> nodeCodeUserIds = achieveUserIds(flowInstanceId, processUserId, workFlowNodeVOS);

        Optional.ofNullable(workFlowNodeVOS).orElse(new ArrayList<>()).forEach(nextNodeCode -> {
            List<Long> userIds = nodeCodeUserIds.get(nextNodeCode.getCode());
            if (CollectionUtils.isEmpty(userIds)) {
                return;
            }

//            // todo:wh 仅支持一个节点一个审批人
//            //新增progress record待办
            WorkFlowProgressOperateRecordVO workFlowProgressOperateRecordVO = WorkFlowProgressRecordFactory.create(workFlowCode,
                    nextNodeCode.getCode(), flowInstanceId, FlowProgressExecuteTypeEnum.NEED_PROCESS.getCode(),
                    new Timestamp(new Date().getTime()),
                    userIds.get(0), null,
                    0,null,null, null);
            workFlowProgressOperateRecordDAO.create(workFlowProgressOperateRecordVO);

            //新增progress待办
            // todo:wh 仅支持一个节点一个审批人
            WorkFlowProgressVO nextWorkFlowProgressVO = WorkFlowProgressFactory.create(workFlowCode, flowInstanceId,
                    nextNodeCode.getCode(),userIds.get(0),null,null,
                    FlowProgressExecuteTypeEnum.NEED_PROCESS.getCode(),
                    workFlowProgressVO.getTerm(),workFlowProgressVO.getBackNo()
            );
            workFlowProgressDAO.create(nextWorkFlowProgressVO);

//            //更新当前审批节点的审批记录
            //todo:wh多个子节点时待考虑
            recordVO.setNextNodeCode(nextNodeCode.getCode());
            recordVO.setNextNodeProcessUserIds(userIds.toString());
            workFlowProgressOperateRecordDAO.update(recordVO);
        });

        return true;
    }

    @Override
    public WorkFlowProgressVO getDetail(Long progressId) {
        return workFlowProgressDAO.get(progressId);
    }

    @Override
    public List<WorkFlowProgressVO> page() {
        //todo:wh待实现
        return null;
    }


    private Map<String, List<Long>> achieveUserIds(Long flowInstanceId, Long processUserId, List<WorkFlowNodeVO> workFlowNodeVOS) {
        Map<String, List<Long>> nodeCodeUserIds = new HashMap<>();
        Optional.ofNullable(workFlowNodeVOS).orElse(new ArrayList<>()).forEach(nextWorkFlowNodeVO -> {
            try {
                List<Long> userIds = nodeProcessUserAchiever.doAchieve(flowInstanceId, processUserId, nextWorkFlowNodeVO);
                nodeCodeUserIds.put(nextWorkFlowNodeVO.getCode(), userIds);
            } catch (Exception e) {
                log.error("[WorkFlowServiceImpl][agree]: doAchieve exception,e=", e);
            }
        });
        return nodeCodeUserIds;
    }

}
