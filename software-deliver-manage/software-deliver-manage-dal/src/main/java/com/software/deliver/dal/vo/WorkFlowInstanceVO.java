package com.software.deliver.dal.vo;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/16 10:28
 */
@Data
@Builder
public class WorkFlowInstanceVO {

    private long id;

    private Long workFlowId;

    private String workFlowCode;

    private String title;

    private Long submitUserId;

    private Timestamp createdAt;

    private Timestamp updateAt;


}
