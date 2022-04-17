package com.software.deliver.biz.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/16 17:04
 */

@Data
@Builder
public class WorkFlow {

    private long id;

    private String code;

    private String title;

    /**
     * 是否启用
     * 0:无意义
     * 1:启用
     * 2:禁用
     */
    private Integer isEnable;

    private List<WorkFlowNode> workFlowNodes;

    private Timestamp createdAt;

    private Timestamp updateAt;

}
