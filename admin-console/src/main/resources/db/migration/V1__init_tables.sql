DROP TABLE IF EXISTS `t_application`;
CREATE TABLE `t_application` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '应用id',
  `name` varchar(64) NOT NULL COMMENT '应用名称',
  `tenant_id` int(32) NOT NULL COMMENT '租户id',
  `status` int(4) NOT NULL DEFAULT '0' COMMENT '应用状态：0-正常，9-删除',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `t_application_key`;
CREATE TABLE `t_application_key` (
  `app_key` varchar(32) NOT NULL,
  `app_secret` varchar(64) NOT NULL,
  `app_id` int(32) NOT NULL COMMENT '应用id',
  `status` int(4) NOT NULL DEFAULT '0' COMMENT 'Key的状态：0-正常，9-删除',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`app_key`),
  UNIQUE KEY `app_key_UNIQUE` (`app_key`),
  UNIQUE KEY `app_secret_UNIQUE` (`app_secret`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `t_tenant`;
CREATE TABLE `t_tenant` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '租户id',
  `name` varchar(256) NOT NULL COMMENT '租户名称',
  `code` varchar(16) NOT NULL COMMENT '租户标识',
  `type` int(8) NOT NULL DEFAULT '0' COMMENT '租户类型：0-个人，1-公司',
  `status` int(4) NOT NULL DEFAULT '0' COMMENT '租户状态：0-正常，9-删除',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `code_UNIQUE` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `tenant_id` int(32) NOT NULL COMMENT '租户id',
  `login_name` varchar(64) DEFAULT NULL COMMENT '登录名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `email` varchar(128) DEFAULT NULL COMMENT '邮件',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机',
  `email_verified` int(2) NOT NULL DEFAULT '0' COMMENT '邮箱是否验证：0-未验证，1-已验证',
  `mobile_verified` int(2) NOT NULL DEFAULT '0',
  `status` int(4) NOT NULL DEFAULT '0' COMMENT '用户状态：0-未激活，1-已激活，9-删除',
  `group_id` int(32) NOT NULL COMMENT '用户组id',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name_UNIQUE` (`login_name`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `mobile_UNIQUE` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `t_user_refresh_token`;
CREATE TABLE `t_user_refresh_token` (
  `token` varchar(255) NOT NULL COMMENT '令牌',
  `user_id` int(32) NOT NULL COMMENT '用户id',
  `tenant_id` int(32) NOT NULL COMMENT '租户id',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `expired_at` datetime NOT NULL COMMENT '失效时间',
  PRIMARY KEY (`token`),
  UNIQUE KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `t_user_group`;
CREATE TABLE `t_user_group` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '用户组id',
  `type` int(4) NOT NULL DEFAULT '0' COMMENT '用户组类型：0-普通管理员，1-非管理员，9-系统管理员\n普通管理员有账户所有app、service、module的权限。\n系统管理员有所有账户app、service、module的权限。\n非管理员需要关联t_permission表查询权限。',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `group_id` INT(32) NOT NULL COMMENT '用户组id',
  `permissions` TEXT NOT NULL COMMENT '权限，用半角逗号分隔(,)',
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `t_config_server`;
CREATE TABLE `t_config_server` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '用户组id',
  `tenant_id` int(32) NOT NULL COMMENT '用户id',
  `server_name` varchar(64) NOT NULL COMMENT '服务名称',
  `server_type` int(32) NOT NULL COMMENT '服务类型 0: 区块链 1:企业征信 2:个人征信',
  `status` int(32) NOT NULL DEFAULT 0 COMMENT '服务状态.0:开启 1：关闭',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `t_user_captcha`;
CREATE TABLE `t_user_captcha` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `login_name` varchar(64) NOT NULL COMMENT '登录名',
  `times` int(32) DEFAULT 0 COMMENT '登录失败的次数',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_login_name` (`login_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
