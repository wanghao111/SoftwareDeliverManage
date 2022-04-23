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
public class WorkFlowVO {

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

    /**
     * 状态
     * 1:审批中
     * 2:已完成
     * 3:已退回
     */
    private Integer status;//todo:wh赋值、逻辑处理待补充

    private Timestamp createdAt;

    private Timestamp updateAt;

}
