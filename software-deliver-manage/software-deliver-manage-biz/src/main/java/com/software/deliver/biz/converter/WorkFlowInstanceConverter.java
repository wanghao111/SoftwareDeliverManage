package com.software.deliver.biz.converter;

import com.software.deliver.biz.model.SystemTaskApplyForm;
import com.software.deliver.biz.model.WorkFlowInstance;
import com.software.deliver.dal.vo.WorkFlowInstanceVO;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/17 11:44
 */
public class WorkFlowInstanceConverter {

    public static WorkFlowInstanceVO from(WorkFlowInstance workFlowInstance) {
        return WorkFlowInstanceVO.builder()
                .id(workFlowInstance.getId())
                .workFlowCode(workFlowInstance.getWorkFlowCode())
                .workFlowId(workFlowInstance.getWorkFlowId())
                .submitUserId(workFlowInstance.getSubmitUserId())
                .title(workFlowInstance.getTitle())
                .build();
    }


}
