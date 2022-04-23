package com.software.deliver.biz.converter;

import com.software.deliver.biz.model.SystemTaskApplyForm;
import com.software.deliver.dal.vo.SystemTaskApplyFormVO;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/17 12:01
 */
public class SystemTaskApplyFormConverter {

    public static SystemTaskApplyFormVO from(SystemTaskApplyForm systemTaskApplyForm) {

        return SystemTaskApplyFormVO.builder()
                .id(systemTaskApplyForm.getId())
                .ownerUserId(systemTaskApplyForm.getOwnerUserId())
                .qaUserId(systemTaskApplyForm.getQaUserId())
                .requirementId(systemTaskApplyForm.getRequirementId())
                .requirementTitle(systemTaskApplyForm.getRequirementTitle())
                .type(systemTaskApplyForm.getType())
//                .title(systemTaskApplyForm.getTitle())
                .workFlowInstanceId(systemTaskApplyForm.getWorkFlowInstanceId())
                .createdBy(systemTaskApplyForm.getCreatedBy())
                .msg(systemTaskApplyForm.getMsg())
                .build();
    }
}
