CREATE TABLE `work_flow` (
`id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键自增id',
`code` varchar(20) NOT NULL default '' COMMENT '流程唯一编码',
`title` varchar(50) NOT NULL DEFAULT '' COMMENT '流程名称',
`is_enable` tinyint NOT NULL DEFAULT 0 COMMENT '是否启用，0：启用，1：禁用',
`status` tinyint NOT NULL default 1 COMMENT '状态，1：审批中，2：已完成，3：已退回',
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
`handler_user_id` bigint  NOT NULL default '' COMMENT 'handler_type为2时的处理人id',
`var_name` varchar(50) NOT NULL default 0 COMMENT 'handler_type为1时的变量名称',
`node_type` tinyint NOT NULL default 0 COMMENT '1:普通节点 2:分支节点，有多个子节点 3:汇总节点，有多个父节点',
`node_seq_type` tinyint DEFAULT 0 COMMENT '1:开始节点 2:中间节点 3:结束节点',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
`update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`id`),
UNIQUE KEY(`work_flow_id`,`code`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '流程节点表';

