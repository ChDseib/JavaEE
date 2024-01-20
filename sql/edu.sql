CREATE TABLE `edu_student` (
  `student_id` bigint NOT NULL AUTO_INCREMENT COMMENT '学生ID',
  `student_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '姓名',
  `student_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '学号',
  `class_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '班级',
  `dept_id` bigint DEFAULT NULL COMMENT '部门ID',
  `create_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='学生表';

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生管理', '3', '1', 'student', 'edu/student/index', 1, 0, 'C', '0', '0', 'edu:student:list', '#', 'admin', sysdate(), '', null, '学生菜单');
-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();
-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'edu:student:query',        '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'edu:student:add',          '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'edu:student:edit',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'edu:student:remove',       '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'edu:student:export',       '#', 'admin', sysdate(), '', null, '');

CREATE TABLE `edu_authority` (
  `authority_id` bigint NOT NULL AUTO_INCREMENT COMMENT '机构ID',
  `authority_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '机构名称',
  PRIMARY KEY (`authority_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='认证机构表';

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('认证机构', '2007', '1', 'authority', 'edu/authority/index', 1, 0, 'C', '0', '0', 'edu:authority:list', '#', 'admin', sysdate(), '', null, '认证机构菜单');
-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();
-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('认证机构查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'edu:authority:query',        '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('认证机构新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'edu:authority:add',          '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('认证机构修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'edu:authority:edit',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('认证机构删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'edu:authority:remove',       '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('认证机构导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'edu:authority:export',       '#', 'admin', sysdate(), '', null, '');

CREATE TABLE `edu_certificate` (
  `certificate_id` bigint NOT NULL AUTO_INCREMENT COMMENT '学生证书ID',
  `student_id` bigint DEFAULT NULL COMMENT '学生ID',
  `authority_id` bigint DEFAULT NULL COMMENT '认证机构ID',
  `certificate_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '证书名称',
  `issue_time` datetime DEFAULT NULL COMMENT '认证时间',
  `file_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '附件',
  `create_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`certificate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='学生证书表';

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生证书', '2007', '1', 'certificate', 'edu/certificate/index', 1, 0, 'C', '0', '0', 'edu:certificate:list', '#', 'admin', sysdate(), '', null, '学生证书菜单');
-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();
-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生证书查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'edu:certificate:query',        '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生证书新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'edu:certificate:add',          '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生证书修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'edu:certificate:edit',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生证书删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'edu:certificate:remove',       '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生证书导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'edu:certificate:export',       '#', 'admin', sysdate(), '', null, '');

CREATE TABLE `edu_contest` (
  `contest_id` bigint NOT NULL AUTO_INCREMENT COMMENT '竞赛ID',
  `contest_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '竞赛名称',
  `level` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '级别',
  `organizer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '组织者',
  PRIMARY KEY (`contest_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='竞赛表';

INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(2020, '学科竞赛', 2007, 1, 'contest', 'edu/contest/index', NULL, 1, 0, 'C', '0', '0', 'edu:contest:list', 'row', 'admin', '2024-01-17 11:37:12', 'admin', '2024-01-17 12:22:46', '竞赛管理菜单'),
(2021, '竞赛查询', 2020, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:contest:query', '#', 'admin', '2024-01-17 11:37:12', 'admin', '2024-01-17 12:23:01', ''),
(2022, '竞赛新增', 2020, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:contest:add', '#', 'admin', '2024-01-17 11:37:12', 'admin', '2024-01-17 12:23:07', ''),
(2023, '竞赛修改', 2020, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:contest:edit', '#', 'admin', '2024-01-17 11:37:12', 'admin', '2024-01-17 12:23:12', ''),
(2024, '竞赛删除', 2020, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:contest:remove', '#', 'admin', '2024-01-17 11:37:12', 'admin', '2024-01-17 12:23:17', ''),
(2025, '竞赛导出', 2020, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:contest:export', '#', 'admin', '2024-01-17 11:37:12', 'admin', '2024-01-17 12:23:22', '');

INSERT INTO `sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(100, '竞赛级别', 'edu_contest_level', '0', 'admin', '2024-01-17 11:10:38', '', NULL, '竞赛级别列表');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(100, 1, '国家级', '1', 'edu_contest_level', NULL, 'default', 'N', '0', 'admin', '2024-01-17 11:12:53', '', NULL, NULL),
(101, 2, '省市级', '2', 'edu_contest_level', NULL, 'default', 'N', '0', 'admin', '2024-01-17 11:13:07', '', NULL, NULL),
(102, 3, '校级', '3', 'edu_contest_level', NULL, 'default', 'N', '0', 'admin', '2024-01-17 11:13:18', '', NULL, NULL),
(103, 9, '其他', '9', 'edu_contest_level', NULL, 'default', 'N', '0', 'admin', '2024-01-17 11:13:31', '', NULL, NULL);

CREATE TABLE `edu_award` (
  `award_id` bigint NOT NULL AUTO_INCREMENT COMMENT '奖项ID',
  `student_id` bigint DEFAULT NULL COMMENT '学生ID',
  `contest_id` bigint DEFAULT NULL COMMENT '竞赛ID',
  `grade` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '等级',
  `issue_time` datetime DEFAULT NULL COMMENT '获奖时间',
  `teacher_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '指导老师',
  `file_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '附件',
  `team_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '团队名称',
  `create_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`award_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='奖项表';

INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(2026, '学生奖项', 2007, 1, 'award', 'edu/award/index', NULL, 1, 0, 'C', '0', '0', 'edu:award:list', 'rate', 'admin', '2024-01-17 13:59:42', 'admin', '2024-01-17 14:01:22', '奖项菜单'),
(2027, '奖项查询', 2026, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:award:query', '#', 'admin', '2024-01-17 13:59:42', '', NULL, ''),
(2028, '奖项新增', 2026, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:award:add', '#', 'admin', '2024-01-17 13:59:42', '', NULL, ''),
(2029, '奖项修改', 2026, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:award:edit', '#', 'admin', '2024-01-17 13:59:42', '', NULL, ''),
(2030, '奖项删除', 2026, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:award:remove', '#', 'admin', '2024-01-17 13:59:42', '', NULL, ''),
(2031, '奖项导出', 2026, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:award:export', '#', 'admin', '2024-01-17 13:59:42', '', NULL, '');

INSERT INTO `sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(101, '奖项等级', 'edu_award_grade', '0', 'admin', '2024-01-17 13:48:49', '', NULL, '奖项等级列表');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(104, 0, '特等奖', '0', 'edu_award_grade', NULL, 'default', 'N', '0', 'admin', '2024-01-17 13:49:14', '', NULL, NULL),
(105, 1, '一等奖', '1', 'edu_award_grade', NULL, 'default', 'N', '0', 'admin', '2024-01-17 13:49:23', '', NULL, NULL),
(106, 2, '二等奖', '2', 'edu_award_grade', NULL, 'default', 'N', '0', 'admin', '2024-01-17 13:49:32', '', NULL, NULL),
(107, 3, '三等奖', '3', 'edu_award_grade', NULL, 'default', 'N', '0', 'admin', '2024-01-17 13:49:41', '', NULL, NULL),
(108, 9, '优胜奖', '9', 'edu_award_grade', NULL, 'default', 'N', '0', 'admin', '2024-01-17 13:49:54', '', NULL, NULL);

CREATE TABLE `edu_teacher` (
  `teacher_id` bigint NOT NULL AUTO_INCREMENT COMMENT '教师ID',
  `teacher_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '姓名',
  `teacher_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '工号',
  `dept_id` bigint DEFAULT NULL COMMENT '部门ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='教师表';

INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(2032, '教师管理', 2007, 1, 'teacher', 'edu/teacher/index', NULL, 1, 0, 'C', '0', '0', 'edu:teacher:list', 'user', 'admin', '2024-01-20 21:44:21', 'admin', '2024-01-20 21:46:55', '教师菜单'),
(2033, '教师查询', 2032, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:teacher:query', '#', 'admin', '2024-01-20 21:44:21', '', NULL, ''),
(2034, '教师新增', 2032, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:teacher:add', '#', 'admin', '2024-01-20 21:44:21', '', NULL, ''),
(2035, '教师修改', 2032, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:teacher:edit', '#', 'admin', '2024-01-20 21:44:21', '', NULL, ''),
(2036, '教师删除', 2032, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:teacher:remove', '#', 'admin', '2024-01-20 21:44:22', '', NULL, ''),
(2037, '教师导出', 2032, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:teacher:export', '#', 'admin', '2024-01-20 21:44:22', '', NULL, '');

CREATE TABLE `edu_training` (
  `training_id` bigint NOT NULL AUTO_INCREMENT COMMENT '教师培训ID',
  `teacher_id` bigint DEFAULT NULL COMMENT '教师ID',
  `authority_id` bigint DEFAULT NULL COMMENT '培训机构ID',
  `training_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '培训名称',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `class_hour` int DEFAULT NULL COMMENT '培训学时',
  `file_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '证书附件',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`training_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='教师培训表';

INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(2038, '教师培训', 2007, 1, 'training', 'edu/training/index', NULL, 1, 0, 'C', '0', '0', 'edu:training:list', 'date', 'admin', '2024-01-20 22:24:28', 'admin', '2024-01-20 22:27:56', '教师培训菜单'),
(2039, '教师培训查询', 2038, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:training:query', '#', 'admin', '2024-01-20 22:24:28', '', NULL, ''),
(2040, '教师培训新增', 2038, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:training:add', '#', 'admin', '2024-01-20 22:24:28', '', NULL, ''),
(2041, '教师培训修改', 2038, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:training:edit', '#', 'admin', '2024-01-20 22:24:28', '', NULL, ''),
(2042, '教师培训删除', 2038, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:training:remove', '#', 'admin', '2024-01-20 22:24:28', '', NULL, ''),
(2043, '教师培训导出', 2038, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:training:export', '#', 'admin', '2024-01-20 22:24:28', '', NULL, '');
