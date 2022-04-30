package com.software.deliver.dal.vo;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/30 19:48
 */
@Data
@Builder
public class SystemTaskVO {

    private Long id;

    private String title;

    /**
     * 1:设计任务
     * 2:开发任务
     * 3:测试
     */
    private Integer taskType;

    /**
     * 模拟上次文件
     */
    private String msg;

    private Timestamp createdAt;

    private Timestamp updateAt;



}
