package com.software.deliver.biz.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/17 10:54
 */
@Data
@Builder
public class WorkFlowInstance {

    private long id;

    private Long workFlowId;

    private String workFlowCode;

    private String title;

    private Long submitUserId;

    private Timestamp createdAt;

    private Timestamp updateAt;

}
