package com.software.deliver.biz.converter;

import com.software.deliver.biz.model.WorkFlowProgress;
import com.software.deliver.dal.vo.WorkFlowProgressVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/17 20:30
 */
public class WorkFlowProgressConverter {

    public static WorkFlowProgressVO from(WorkFlowProgress workFlowProgress) {

        return WorkFlowProgressVO.builder()
                .id(workFlowProgress.getId())
                .workFlowId(workFlowProgress.getWorkFlowId())
                .workFlowCode(workFlowProgress.getWorkFlowCode())
                .workFlowInstanceId(workFlowProgress.getWorkFlowInstanceId())
                .flowNodeCode(workFlowProgress.getWorkFlowNodeCode())
                .handlerUserId(workFlowProgress.getHandlerUserId())
                .status(workFlowProgress.getStatus())
                .handlerMsg(workFlowProgress.getHandlerMsg())
                .createdAt(workFlowProgress.getCreatedAt())
                .updateAt(workFlowProgress.getUpdateAt())
                .build();
    }

    public static List<WorkFlowProgressVO> batchFrom(List<WorkFlowProgress> workFlowProgresses) {
        return Optional.ofNullable(workFlowProgresses).orElse(new ArrayList<>())
                .stream().map(WorkFlowProgressConverter::from).collect(Collectors.toList());
    }
    public static WorkFlowProgress from(WorkFlowProgressVO workFlowProgressVO) {
        return WorkFlowProgress.builder()
                .id(workFlowProgressVO.getId())
                .workFlowId(workFlowProgressVO.getWorkFlowId())
                .workFlowCode(workFlowProgressVO.getWorkFlowCode())
                .workFlowNodeCode(workFlowProgressVO.getFlowNodeCode())
                .workFlowInstanceId(workFlowProgressVO.getWorkFlowInstanceId())
                .status(workFlowProgressVO.getStatus())
                .handlerUserId(workFlowProgressVO.getHandlerUserId())
                .handlerMsg(workFlowProgressVO.getHandlerMsg())
                .createdAt(workFlowProgressVO.getCreatedAt())
                .updateAt(workFlowProgressVO.getUpdateAt())
                .build();
    }

    public static List<WorkFlowProgress> batchFromVO(List<WorkFlowProgressVO> workFlowProgressVOS) {
        return Optional.ofNullable(workFlowProgressVOS).orElse(new ArrayList<>())
                .stream().map(WorkFlowProgressConverter::from).collect(Collectors.toList());
    }

}
