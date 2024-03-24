INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(5, '获奖信息', 0, 4, 'edu', NULL, NULL, 1, 0, 'M', '0', '0', '', 'validCode', 'admin', '2024-01-14 18:30:36', 'admin', '2024-01-17 19:00:28', '');

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

INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(2000, '学生管理', 5, 1, 'student', 'edu/student/index', NULL, 1, 0, 'C', '0', '0', 'edu:student:list', 'people', 'admin', '2024-01-13 16:43:16', 'admin', '2024-01-14 19:10:49', '学生菜单'),
(2001, '学生查询', 2000, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:student:query', '#', 'admin', '2024-01-13 16:43:16', 'admin', '2024-01-14 18:13:35', ''),
(2002, '学生新增', 2000, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:student:add', '#', 'admin', '2024-01-13 16:43:16', 'admin', '2024-01-14 18:13:40', ''),
(2003, '学生修改', 2000, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:student:edit', '#', 'admin', '2024-01-13 16:43:17', 'admin', '2024-01-14 18:13:45', ''),
(2004, '学生删除', 2000, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:student:remove', '#', 'admin', '2024-01-13 16:43:17', 'admin', '2024-01-14 18:13:48', ''),
(2005, '学生导出', 2000, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:student:export', '#', 'admin', '2024-01-13 16:43:17', 'admin', '2024-01-14 18:13:53', '');

CREATE TABLE `edu_authority` (
  `authority_id` bigint NOT NULL AUTO_INCREMENT COMMENT '机构ID',
  `authority_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '机构名称',
  PRIMARY KEY (`authority_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='认证机构表';

INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(2008, '认证机构', 5, 1, 'authority', 'edu/authority/index', NULL, 1, 0, 'C', '0', '0', 'edu:authority:list', 'example', 'admin', '2024-01-14 19:27:03', 'admin', '2024-01-17 19:02:49', '认证机构菜单'),
(2009, '认证机构查询', 2008, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:authority:query', '#', 'admin', '2024-01-14 19:27:03', '', NULL, ''),
(2010, '认证机构新增', 2008, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:authority:add', '#', 'admin', '2024-01-14 19:27:03', '', NULL, ''),
(2011, '认证机构修改', 2008, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:authority:edit', '#', 'admin', '2024-01-14 19:27:03', '', NULL, ''),
(2012, '认证机构删除', 2008, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:authority:remove', '#', 'admin', '2024-01-14 19:27:03', '', NULL, ''),
(2013, '认证机构导出', 2008, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:authority:export', '#', 'admin', '2024-01-14 19:27:03', '', NULL, '');

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

INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(2014, '学生证书', 5, 1, 'certificate', 'edu/certificate/index', NULL, 1, 0, 'C', '0', '0', 'edu:certificate:list', 'skill', 'admin', '2024-01-15 08:23:10', 'admin', '2024-01-15 10:06:29', '学生证书菜单'),
(2015, '学生证书查询', 2014, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:certificate:query', '#', 'admin', '2024-01-15 08:23:10', '', NULL, ''),
(2016, '学生证书新增', 2014, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:certificate:add', '#', 'admin', '2024-01-15 08:23:10', '', NULL, ''),
(2017, '学生证书修改', 2014, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:certificate:edit', '#', 'admin', '2024-01-15 08:23:10', '', NULL, ''),
(2018, '学生证书删除', 2014, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:certificate:remove', '#', 'admin', '2024-01-15 08:23:10', '', NULL, ''),
(2019, '学生证书导出', 2014, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:certificate:export', '#', 'admin', '2024-01-15 08:23:10', '', NULL, '');

CREATE TABLE `edu_contest` (
  `contest_id` bigint NOT NULL AUTO_INCREMENT COMMENT '竞赛ID',
  `contest_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '竞赛名称',
  `level` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '级别',
  `organizer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '组织者',
  PRIMARY KEY (`contest_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='竞赛表';

INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(2020, '学科竞赛', 5, 1, 'contest', 'edu/contest/index', NULL, 1, 0, 'C', '0', '0', 'edu:contest:list', 'row', 'admin', '2024-01-17 11:37:12', 'admin', '2024-01-17 12:22:46', '竞赛管理菜单'),
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
(2026, '学生奖项', 5, 1, 'award', 'edu/award/index', NULL, 1, 0, 'C', '0', '0', 'edu:award:list', 'rate', 'admin', '2024-01-17 13:59:42', 'admin', '2024-01-17 14:01:22', '奖项菜单'),
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
(2032, '教师管理', 5, 1, 'teacher', 'edu/teacher/index', NULL, 1, 0, 'C', '0', '0', 'edu:teacher:list', 'user', 'admin', '2024-01-20 21:44:21', 'admin', '2024-01-20 21:46:55', '教师菜单'),
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
(2038, '教师培训', 5, 1, 'training', 'edu/training/index', NULL, 1, 0, 'C', '0', '0', 'edu:training:list', 'date', 'admin', '2024-01-20 22:24:28', 'admin', '2024-01-20 22:27:56', '教师培训菜单'),
(2039, '教师培训查询', 2038, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:training:query', '#', 'admin', '2024-01-20 22:24:28', '', NULL, ''),
(2040, '教师培训新增', 2038, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:training:add', '#', 'admin', '2024-01-20 22:24:28', '', NULL, ''),
(2041, '教师培训修改', 2038, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:training:edit', '#', 'admin', '2024-01-20 22:24:28', '', NULL, ''),
(2042, '教师培训删除', 2038, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:training:remove', '#', 'admin', '2024-01-20 22:24:28', '', NULL, ''),
(2043, '教师培训导出', 2038, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:training:export', '#', 'admin', '2024-01-20 22:24:28', '', NULL, '');

CREATE TABLE `edu_parttime` (
  `parttime_id` bigint NOT NULL AUTO_INCREMENT COMMENT '社会兼职ID',
  `teacher_id` bigint DEFAULT NULL COMMENT '教师ID',
  `category` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '类别',
  `employer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '兼职单位',
  `position` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '兼职职务',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`parttime_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='社会兼职表';

INSERT INTO `sys_dict_type`(`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (102, '兼职类别', 'edu_parttime_category', '0', 'admin', '2024-02-06 16:18:59', '', NULL, '兼职类别列表');
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (109, 0, '行业指导教师', '0', 'edu_parttime_category', NULL, 'default', 'N', '0', 'admin', '2024-02-06 16:34:00', '', NULL, NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (110, 1, '省、市学术组织任职', '1', 'edu_parttime_category', NULL, 'default', 'N', '0', 'admin', '2024-02-06 16:34:18', 'admin', '2024-02-06 16:34:35', NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (111, 2, '国家、省市教指委或认证机构任职', '2', 'edu_parttime_category', NULL, 'default', 'N', '0', 'admin', '2024-02-06 16:34:31', '', NULL, NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (112, 3, '全国性学术组织任职', '3', 'edu_parttime_category', NULL, 'default', 'N', '0', 'admin', '2024-02-06 16:34:46', '', NULL, NULL);

INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(2050, '社会兼职', 5, 1, 'parttime', 'edu/parttime/index', NULL, 1, 0, 'C', '0', '0', 'edu:parttime:list', 'post', 'admin', '2024-02-17 10:45:32', 'admin', '2024-02-17 10:47:40', '社会兼职菜单'),
(2051, '社会兼职查询', 2050, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:parttime:query', '#', 'admin', '2024-02-17 10:45:32', '', NULL, ''),
(2052, '社会兼职新增', 2050, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:parttime:add', '#', 'admin', '2024-02-17 10:45:32', '', NULL, ''),
(2053, '社会兼职修改', 2050, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:parttime:edit', '#', 'admin', '2024-02-17 10:45:32', '', NULL, ''),
(2054, '社会兼职删除', 2050, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:parttime:remove', '#', 'admin', '2024-02-17 10:45:32', '', NULL, ''),
(2055, '社会兼职导出', 2050, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:parttime:export', '#', 'admin', '2024-02-17 10:45:32', '', NULL, '');

CREATE TABLE `edu_honor` (
  `honor_id` bigint NOT NULL AUTO_INCREMENT COMMENT '奖项ID',
  `teacher_id` bigint DEFAULT NULL COMMENT '教师ID',
  `honor_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '奖项',
  `level` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '级别',
  `issue_time` datetime DEFAULT NULL COMMENT '获奖时间',
  `file_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '附件',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`honor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='教师奖项表';

INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(2044, '教师奖项', 5, 1, 'honor', 'edu/honor/index', NULL, 1, 0, 'C', '0', '0', 'edu:honor:list', 'star', 'admin', '2024-02-03 15:03:30', 'admin', '2024-03-08 12:46:14', '教师奖项菜单'),
(2045, '教师奖项查询', 2044, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:honor:query', '#', 'admin', '2024-02-03 15:03:30', '', NULL, ''),
(2046, '教师奖项新增', 2044, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:honor:add', '#', 'admin', '2024-02-03 15:03:30', '', NULL, ''),
(2047, '教师奖项修改', 2044, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:honor:edit', '#', 'admin', '2024-02-03 15:03:30', '', NULL, ''),
(2048, '教师奖项删除', 2044, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:honor:remove', '#', 'admin', '2024-02-03 15:03:30', '', NULL, ''),
(2049, '教师奖项导出', 2044, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:honor:export', '#', 'admin', '2024-02-03 15:03:30', '', NULL, '');

CREATE TABLE `edu_course` (
  `course_id` bigint NOT NULL AUTO_INCREMENT COMMENT '课程ID',
  `teacher_id` bigint DEFAULT NULL COMMENT '负责人',
  `course_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '课程名称',
  `level` char(1) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '级别',
  `course_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '类别',
  `issue_time` datetime DEFAULT NULL COMMENT '认定时间',
  `external_amount` double DEFAULT NULL COMMENT '上级拨款',
  `internal_amount` double DEFAULT NULL COMMENT '校内配套',
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='课程表';

INSERT INTO `sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(103, '课程类别', 'edu_course_type', '0', 'admin', '2024-03-18 15:31:34', 'admin', '2024-03-18 15:47:14', '课程类别列表');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(113, 1, '线下课程', '1', 'edu_course_type', NULL, 'default', 'N', '0', 'admin', '2024-03-18 15:32:23', '', NULL, NULL),
(114, 2, '课程思政示范课程', '2', 'edu_course_type', NULL, 'default', 'N', '0', 'admin', '2024-03-18 15:32:37', '', NULL, NULL),
(115, 3, '线上线下混合一流课程', '3', 'edu_course_type', NULL, 'default', 'N', '0', 'admin', '2024-03-18 15:32:56', '', NULL, NULL),
(116, 4, '虚拟仿真实验教学课程', '4', 'edu_course_type', NULL, 'default', 'N', '0', 'admin', '2024-03-18 15:33:23', '', NULL, NULL),
(117, 5, '重点课程', '5', 'edu_course_type', NULL, 'default', 'N', '0', 'admin', '2024-03-18 15:33:50', '', NULL, NULL);

INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(2062, '课程建设', 5, 1, 'course', 'edu/course/index', NULL, 1, 0, 'C', '0', '0', 'edu:course:list', 'clipboard', 'admin', '2024-03-18 15:23:21', 'admin', '2024-03-18 15:40:13', '课程菜单'),
(2063, '课程查询', 2062, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:course:query', '#', 'admin', '2024-03-18 15:23:21', '', NULL, ''),
(2064, '课程新增', 2062, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:course:add', '#', 'admin', '2024-03-18 15:23:21', '', NULL, ''),
(2065, '课程修改', 2062, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:course:edit', '#', 'admin', '2024-03-18 15:23:21', '', NULL, ''),
(2066, '课程删除', 2062, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:course:remove', '#', 'admin', '2024-03-18 15:23:21', '', NULL, ''),
(2067, '课程导出', 2062, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:course:export', '#', 'admin', '2024-03-18 15:23:21', '', NULL, '');

CREATE TABLE `edu_achievement` (
  `achievement_id` bigint NOT NULL AUTO_INCREMENT COMMENT '教学成果ID',
  `achievement_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名称',
  `level` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '级别',
  `grade` char(1) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '等级',
  `issue_time` datetime DEFAULT NULL COMMENT '获奖时间',
  PRIMARY KEY (`achievement_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='教学成果表';

INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(2068, '教学成果', 5, 1, 'achievement', 'edu/achievement/index', NULL, 1, 0, 'C', '0', '0', 'edu:achievement:list', 'chart', 'admin', '2024-03-24 09:51:23', 'admin', '2024-03-24 09:52:50', '教学成果菜单'),
(2069, '教学成果查询', 2068, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:achievement:query', '#', 'admin', '2024-03-24 09:51:23', '', NULL, ''),
(2070, '教学成果新增', 2068, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:achievement:add', '#', 'admin', '2024-03-24 09:51:23', '', NULL, ''),
(2071, '教学成果修改', 2068, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:achievement:edit', '#', 'admin', '2024-03-24 09:51:23', '', NULL, ''),
(2072, '教学成果删除', 2068, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:achievement:remove', '#', 'admin', '2024-03-24 09:51:23', '', NULL, ''),
(2073, '教学成果导出', 2068, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'edu:achievement:export', '#', 'admin', '2024-03-24 09:51:23', '', NULL, '');
