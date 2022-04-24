CREATE TABLE `work_flow` (
`id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键自增id',
`code` varchar(20) NOT NULL default '' COMMENT '流程唯一编码',
`title` varchar(50) NOT NULL DEFAULT '' COMMENT '流程名称',
`is_enable` tinyint NOT NULL DEFAULT 0 COMMENT '是否启用，0：启用，1：禁用 默认0',
`status` tinyint NOT NULL default 0 COMMENT '状态，1：审批中，2：已完成，3：已退回 默认0',
`is_delete` tinyint DEFAULT 0 COMMENT '0:未删除 1:已删除',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
`update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`id`),
UNIQUE KEY(`code`),
INDEX title_index(title)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT '流程定义表';

CREATE TABLE `work_flow_node` (
`id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键自增id',
`code` varchar(20) NOT NULL default '' COMMENT '节点编码',
`node_title` varchar(50) NOT NULL DEFAULT '' COMMENT '节点名称',
`work_flow_id` bigint NOT NULL default 0 COMMENT '所属的流程id',
`work_flow_code` varchar(20) not null default '' COMMENT '所属的流程编码',
`handler_type` tinyint NOT NULL default 0 COMMENT '1:指定人处理 2:固定人处理 3:上一节点主管处理 4:指定角色处理 默认0',
`handler_user_id` bigint  NOT NULL default 0 COMMENT 'handler_type为2时的处理人id',
`var_name` varchar(50) NOT NULL default '' COMMENT 'handler_type为1时的变量名称',
`node_type` tinyint NOT NULL default 0 COMMENT '1:普通节点 2:分支节点，有多个子节点 3:汇总节点，有多个父节点',
`node_seq_type` tinyint DEFAULT 0 COMMENT '1:开始节点 2:中间节点 3:结束节点',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
`update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`id`),
UNIQUE KEY(`work_flow_id`,`code`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '流程节点表';

CREATE TABLE `work_flow_node_action` (
`id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键自增id',
`work_flow_id` bigint NOT NULL default 0 COMMENT '所属的流程id',
`work_flow_node_code` varchar(20) not null default '' COMMENT '所属的流程节点编码',
`review_type` tinyint NOT NULL default 0 COMMENT '1:同意 2:驳回 3:加签 4:转办 默认0',
`action_type` tinyint  NOT NULL default 0 COMMENT '1:同意，流转下一个节点 2:驳回，返回上一节点 3:驳回，返回提交人 4:加签，流转至指定人 5:转办，流转至指定人 默认0',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
`update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`id`),
UNIQUE KEY(`work_flow_id`,`work_flow_node_code`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '流程节点动作表';

CREATE TABLE `work_flow_node_relation` (
`id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键自增id',
`work_flow_id` bigint NOT NULL default 0 COMMENT '所属的流程id',
`work_flow_node_id` bigint NOT NULL default 0 COMMENT '流程节点id',
`work_flow_node_code` varchar(20) NOT NULL default '' COMMENT '节点编码',
`type` tinyint NOT NULL default 0 COMMENT '1:前置节点关系 2:后置节点关系 默认0',
`rel_work_flow_node_code` varchar(20) not null default '' COMMENT '关联的流程节点编码',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
`update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`id`),
UNIQUE KEY(`work_flow_id`,`work_flow_node_code`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '流程节点关系表';

CREATE TABLE `work_flow_instance` (
`id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键自增id',
`work_flow_id` bigint NOT NULL DEFAULT 0 COMMENT '关联的流程id',
`work_flow_code` varchar(20) NOT NULL default '' COMMENT '关联的流程编码',
`title` varchar(50) NOT NULL DEFAULT '' COMMENT '流程名称',
`submit_user_id` tinyint NOT NULL DEFAULT 0 COMMENT '提交人id',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
`update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`id`),
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT '流程实例表';

CREATE TABLE `work_flow_variable` (
`id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键自增id',
`work_flow_instance_id` bigint NOT NULL DEFAULT 0 COMMENT '关联的流程实例id',
`var_name` varchar(20) NOT NULL default '' COMMENT '变量名称',
`value` bigint NOT NULL DEFAULT 0 COMMENT '变量值，处理人id',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
`update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`id`),
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT '流程实例变量表';

CREATE TABLE `work_flow_instance_subflow_summary` (
`id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键自增id',
`work_flow_instance_id` bigint NOT NULL DEFAULT 0 COMMENT '关联的流程实例id',
`work_flow_id` bigint NOT NULL DEFAULT 0 COMMENT '关联的流程id',
`work_flow_node_code` VARCHAR NOT NULL DEFAULT '' COMMENT '关联的流程节点编码',
`pre_work_flow_node_code` VARCHAR NOT NULL DEFAULT '' COMMENT '关联的流程前置流程节点编码',
`value` INT NOT NULL DEFAULT 0 COMMENT '子流程节点处理累计值',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
`update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`id`),
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT '流程实例子流程汇总记录表';

CREATE TABLE `system_task` (
`id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键自增id',
`requirement_id` bigint NOT NULL DEFAULT 0 COMMENT '需求id',
`requirement_title` VARCHAR NOT NULL DEFAULT '' COMMENT '需求名称',
`type` TINYINT NOT NULL DEFAULT 0 COMMENT '1:设计任务 2:开发任务 3:测试任务 默认0',
`owner_user_id` bigint NOT NULL DEFAULT 0 COMMENT '系统任务归属人id',
`msg` VARCHAR NOT NULL DEFAULT '' COMMENT '归属人填的的msg信息',
`qa_user_id` bigint NOT NULL DEFAULT 0 COMMENT 'qa的用户id',
`created_by` bigint NOT NULL DEFAULT 0 COMMENT '创建人id',
`work_flow_instance_id` bigint NOT NULL DEFAULT 0 COMMENT '关联的流程实例id',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
`update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`id`),
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT '系统任务表';
