-- =============================================
-- CMS Scaffold 数据库初始化脚本
-- 基于若依架构, 适配BeetlSQL
-- =============================================

CREATE DATABASE IF NOT EXISTS cms_scaffold DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE cms_scaffold;

-- ----------------------------
-- 1. 部门表
-- ----------------------------
DROP TABLE IF EXISTS sys_dept;
CREATE TABLE sys_dept (
  dept_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  parent_id bigint(20) DEFAULT 0 COMMENT '父部门ID',
  ancestors varchar(500) DEFAULT '' COMMENT '祖级列表',
  dept_name varchar(30) DEFAULT '' COMMENT '部门名称',
  order_num int(4) DEFAULT 0 COMMENT '显示顺序',
  leader varchar(20) DEFAULT NULL COMMENT '负责人',
  phone varchar(20) DEFAULT NULL COMMENT '联系电话',
  email varchar(50) DEFAULT NULL COMMENT '邮箱',
  status char(1) DEFAULT '0' COMMENT '状态(0正常 1停用)',
  del_flag char(1) DEFAULT '0' COMMENT '删除标志(0存在 2删除)',
  create_by varchar(64) DEFAULT '' COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT '' COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (dept_id)
) ENGINE=InnoDB AUTO_INCREMENT=200 DEFAULT CHARSET=utf8mb4 COMMENT='部门表';

-- ----------------------------
-- 2. 用户表
-- ----------------------------
DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user (
  user_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  dept_id bigint(20) DEFAULT NULL COMMENT '部门ID',
  user_name varchar(30) NOT NULL COMMENT '登录账号',
  nick_name varchar(30) DEFAULT '' COMMENT '用户昵称',
  email varchar(50) DEFAULT '' COMMENT '邮箱',
  phone_number varchar(20) DEFAULT '' COMMENT '手机号',
  sex char(1) DEFAULT '0' COMMENT '性别(0男 1女 2未知)',
  avatar varchar(200) DEFAULT '' COMMENT '头像路径',
  password varchar(200) DEFAULT '' COMMENT '密码',
  status char(1) DEFAULT '0' COMMENT '状态(0正常 1停用)',
  del_flag char(1) DEFAULT '0' COMMENT '删除标志(0存在 2删除)',
  login_ip varchar(128) DEFAULT '' COMMENT '最后登录IP',
  login_date datetime DEFAULT NULL COMMENT '最后登录时间',
  create_by varchar(64) DEFAULT '' COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT '' COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (user_id)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- 3. 角色表
-- ----------------------------
DROP TABLE IF EXISTS sys_role;
CREATE TABLE sys_role (
  role_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  role_name varchar(30) NOT NULL COMMENT '角色名称',
  role_key varchar(100) NOT NULL COMMENT '角色权限字符串',
  role_sort int(4) NOT NULL COMMENT '显示顺序',
  data_scope char(1) DEFAULT '1' COMMENT '数据范围(1全部 2自定义 3本部门 4本部门及以下 5仅本人)',
  menu_check_strictly tinyint(1) DEFAULT 1 COMMENT '菜单树选择项是否关联显示',
  dept_check_strictly tinyint(1) DEFAULT 1 COMMENT '部门树选择项是否关联显示',
  status char(1) DEFAULT '0' COMMENT '状态(0正常 1停用)',
  del_flag char(1) DEFAULT '0' COMMENT '删除标志(0存在 2删除)',
  create_by varchar(64) DEFAULT '' COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT '' COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (role_id)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- ----------------------------
-- 4. 菜单表
-- ----------------------------
DROP TABLE IF EXISTS sys_menu;
CREATE TABLE sys_menu (
  menu_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  menu_name varchar(50) NOT NULL COMMENT '菜单名称',
  parent_id bigint(20) DEFAULT 0 COMMENT '父菜单ID',
  order_num int(4) DEFAULT 0 COMMENT '显示顺序',
  path varchar(200) DEFAULT '' COMMENT '路由地址',
  component varchar(255) DEFAULT NULL COMMENT '组件路径',
  query varchar(255) DEFAULT NULL COMMENT '路由参数',
  route_name varchar(50) DEFAULT '' COMMENT '路由名称',
  is_frame char(1) DEFAULT '1' COMMENT '是否为外链(0是 1否)',
  is_cache char(1) DEFAULT '0' COMMENT '是否缓存(0缓存 1不缓存)',
  menu_type char(1) DEFAULT '' COMMENT '菜单类型(M目录 C菜单 F按钮)',
  visible char(1) DEFAULT '0' COMMENT '菜单状态(0显示 1隐藏)',
  status char(1) DEFAULT '0' COMMENT '菜单状态(0正常 1停用)',
  perms varchar(100) DEFAULT NULL COMMENT '权限标识',
  icon varchar(100) DEFAULT '#' COMMENT '菜单图标',
  create_by varchar(64) DEFAULT '' COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT '' COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (menu_id)
) ENGINE=InnoDB AUTO_INCREMENT=2000 DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';

-- ----------------------------
-- 5. 用户角色关联表
-- ----------------------------
DROP TABLE IF EXISTS sys_user_role;
CREATE TABLE sys_user_role (
  user_id bigint(20) NOT NULL COMMENT '用户ID',
  role_id bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (user_id, role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联表';

-- ----------------------------
-- 6. 角色菜单关联表
-- ----------------------------
DROP TABLE IF EXISTS sys_role_menu;
CREATE TABLE sys_role_menu (
  role_id bigint(20) NOT NULL COMMENT '角色ID',
  menu_id bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (role_id, menu_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色菜单关联表';

-- ----------------------------
-- 7. 岗位表
-- ----------------------------
DROP TABLE IF EXISTS sys_post;
CREATE TABLE sys_post (
  post_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  post_code varchar(64) NOT NULL COMMENT '岗位编码',
  post_name varchar(50) NOT NULL COMMENT '岗位名称',
  post_sort int(4) NOT NULL COMMENT '显示顺序',
  status char(1) DEFAULT '0' COMMENT '状态(0正常 1停用)',
  create_by varchar(64) DEFAULT '' COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT '' COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (post_id)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='岗位表';

-- ----------------------------
-- 8. 用户岗位关联表
-- ----------------------------
DROP TABLE IF EXISTS sys_user_post;
CREATE TABLE sys_user_post (
  user_id bigint(20) NOT NULL COMMENT '用户ID',
  post_id bigint(20) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (user_id, post_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户岗位关联表';

-- ----------------------------
-- 9. 字典类型表
-- 8→9 after sys_post/sys_user_post insertion
-- ----------------------------
DROP TABLE IF EXISTS sys_dict_type;
CREATE TABLE sys_dict_type (
  dict_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  dict_name varchar(100) DEFAULT '' COMMENT '字典名称',
  dict_type varchar(100) DEFAULT '' COMMENT '字典类型',
  status char(1) DEFAULT '0' COMMENT '状态(0正常 1停用)',
  create_by varchar(64) DEFAULT '' COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT '' COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (dict_id),
  UNIQUE KEY dict_type (dict_type)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COMMENT='字典类型表';

-- ----------------------------
-- 8. 字典数据表
-- ----------------------------
DROP TABLE IF EXISTS sys_dict_data;
CREATE TABLE sys_dict_data (
  dict_code bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  dict_sort int(4) DEFAULT 0 COMMENT '字典排序',
  dict_label varchar(100) DEFAULT '' COMMENT '字典标签',
  dict_value varchar(100) DEFAULT '' COMMENT '字典键值',
  dict_type varchar(100) DEFAULT '' COMMENT '字典类型',
  css_class varchar(100) DEFAULT NULL COMMENT '样式属性',
  list_class varchar(100) DEFAULT NULL COMMENT '表格回显样式',
  is_default char(1) DEFAULT 'N' COMMENT '是否默认(Y是 N否)',
  status char(1) DEFAULT '0' COMMENT '状态(0正常 1停用)',
  create_by varchar(64) DEFAULT '' COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT '' COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (dict_code)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COMMENT='字典数据表';

-- ----------------------------
-- 9. 系统配置表
-- ----------------------------
DROP TABLE IF EXISTS sys_config;
CREATE TABLE sys_config (
  config_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  config_name varchar(100) DEFAULT '' COMMENT '参数名称',
  config_key varchar(100) DEFAULT '' COMMENT '参数键名',
  config_value varchar(500) DEFAULT '' COMMENT '参数键值',
  config_type char(1) DEFAULT 'N' COMMENT '系统内置(Y是 N否)',
  create_by varchar(64) DEFAULT '' COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT '' COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (config_id)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COMMENT='系统配置表';

-- ----------------------------
-- 10. 通知公告表
-- ----------------------------
DROP TABLE IF EXISTS sys_notice;
CREATE TABLE sys_notice (
  notice_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  notice_title varchar(50) NOT NULL COMMENT '公告标题',
  notice_type char(1) NOT NULL COMMENT '公告类型(1通知 2公告)',
  notice_content longblob COMMENT '公告内容',
  status char(1) DEFAULT '0' COMMENT '状态(0正常 1关闭)',
  create_by varchar(64) DEFAULT '' COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT '' COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (notice_id)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='通知公告表';

-- ----------------------------
-- 11. 登录日志表
-- ----------------------------
DROP TABLE IF EXISTS sys_logininfor;
CREATE TABLE sys_logininfor (
  info_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  user_name varchar(50) DEFAULT '' COMMENT '用户账号',
  ipaddr varchar(128) DEFAULT '' COMMENT '登录IP',
  login_location varchar(255) DEFAULT '' COMMENT '登录地点',
  browser varchar(50) DEFAULT '' COMMENT '浏览器',
  os varchar(50) DEFAULT '' COMMENT '操作系统',
  status char(1) DEFAULT '0' COMMENT '登录状态(0成功 1失败)',
  msg varchar(255) DEFAULT '' COMMENT '提示信息',
  login_time datetime DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (info_id)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COMMENT='登录日志表';

-- ----------------------------
-- 12. 操作日志表
-- ----------------------------
DROP TABLE IF EXISTS sys_oper_log;
CREATE TABLE sys_oper_log (
  oper_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  title varchar(50) DEFAULT '' COMMENT '模块标题',
  business_type int(2) DEFAULT 0 COMMENT '业务类型(0其它 1新增 2修改 3删除)',
  method varchar(100) DEFAULT '' COMMENT '方法名称',
  request_method varchar(10) DEFAULT '' COMMENT '请求方式',
  oper_name varchar(50) DEFAULT '' COMMENT '操作人员',
  oper_url varchar(255) DEFAULT '' COMMENT '请求URL',
  oper_ip varchar(128) DEFAULT '' COMMENT '操作IP',
  oper_param varchar(2000) DEFAULT '' COMMENT '请求参数',
  json_result varchar(2000) DEFAULT '' COMMENT '返回参数',
  status int(1) DEFAULT 0 COMMENT '操作状态(0正常 1异常)',
  error_msg varchar(2000) DEFAULT '' COMMENT '错误信息',
  oper_time datetime DEFAULT NULL COMMENT '操作时间',
  cost_time bigint(20) DEFAULT 0 COMMENT '消耗时间(ms)',
  PRIMARY KEY (oper_id)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COMMENT='操作日志表';


-- =============================================
-- 演示数据
-- =============================================

-- ----------------------------
-- 部门数据
-- ----------------------------
INSERT INTO sys_dept VALUES (100, 0,   '0',          'XX科技有限公司', 0, '张总', '13800000001', 'ceo@example.com', '0', '0', 'admin', '2024-01-01 00:00:00', '', NULL);
INSERT INTO sys_dept VALUES (101, 100, '0,100',       '研发部',         1, '李经理', '13800000002', 'dev@example.com', '0', '0', 'admin', '2024-01-01 00:00:00', '', NULL);
INSERT INTO sys_dept VALUES (102, 100, '0,100',       '市场部',         2, '王经理', '13800000003', 'mkt@example.com', '0', '0', 'admin', '2024-01-01 00:00:00', '', NULL);
INSERT INTO sys_dept VALUES (103, 101, '0,100,101',   '前端开发组',     1, '赵组长', '13800000004', 'frontend@example.com', '0', '0', 'admin', '2024-01-01 00:00:00', '', NULL);
INSERT INTO sys_dept VALUES (104, 101, '0,100,101',   '后端开发组',     2, '钱组长', '13800000005', 'backend@example.com', '0', '0', 'admin', '2024-01-01 00:00:00', '', NULL);
INSERT INTO sys_dept VALUES (105, 100, '0,100',       '人事行政部',     3, '孙经理', '13800000006', 'hr@example.com', '0', '0', 'admin', '2024-01-01 00:00:00', '', NULL);

-- ----------------------------
-- 用户数据 (密码均为对应账号的MD5, 如admin123对应md5)
-- ----------------------------
-- admin/admin123 -> MD5: 0192023a7bbd73250516f069df18b500 (实际是admin123的MD5)
INSERT INTO sys_user VALUES (1,  103, 'admin',  '超级管理员', 'admin@example.com', '13800000000', '0', '',  '0192023a7bbd73250516f069df18b500', '0', '0', '127.0.0.1', '2024-01-01 00:00:00', 'admin', '2024-01-01 00:00:00', '', '管理员');
INSERT INTO sys_user VALUES (2,  104, 'zhangsan', '张三', 'zhangsan@example.com', '13800000010', '0', '', '0192023a7bbd73250516f069df18b500', '0', '0', '', NULL, 'admin', '2024-01-01 00:00:00', '', '研发工程师');
INSERT INTO sys_user VALUES (3,  102, 'lisi',    '李四', 'lisi@example.com', '13800000011', '0', '',    '0192023a7bbd73250516f069df18b500', '0', '0', '', NULL, 'admin', '2024-01-01 00:00:00', '', '市场专员');
INSERT INTO sys_user VALUES (4,  105, 'wangwu',  '王五', 'wangwu@example.com', '13800000012', '0', '',  '0192023a7bbd73250516f069df18b500', '0', '0', '', NULL, 'admin', '2024-01-01 00:00:00', '', 'HR主管');
INSERT INTO sys_user VALUES (5,  101, 'test',    '测试用户', 'test@example.com', '13800000013', '0', '',  '0192023a7bbd73250516f069df18b500', '0', '0', '', NULL, 'admin', '2024-01-01 00:00:00', '', '测试账号');

-- ----------------------------
-- 岗位数据
-- ----------------------------
INSERT INTO sys_post VALUES (1, 'ceo',     '董事长',   1, '0', 'admin', '2024-01-01 00:00:00', '', NULL, '');
INSERT INTO sys_post VALUES (2, 'cto',     '技术总监', 2, '0', 'admin', '2024-01-01 00:00:00', '', NULL, '');
INSERT INTO sys_post VALUES (3, 'se',      '软件工程师', 3, '0', 'admin', '2024-01-01 00:00:00', '', NULL, '');
INSERT INTO sys_post VALUES (4, 'pm',      '产品经理', 4, '0', 'admin', '2024-01-01 00:00:00', '', NULL, '');
INSERT INTO sys_post VALUES (5, 'hr',      '人事专员', 5, '0', 'admin', '2024-01-01 00:00:00', '', NULL, '');
INSERT INTO sys_post VALUES (6, 'sales',   '销售经理', 6, '0', 'admin', '2024-01-01 00:00:00', '', NULL, '');

-- 用户岗位关联
INSERT INTO sys_user_post VALUES (1, 1);
INSERT INTO sys_user_post VALUES (2, 3);
INSERT INTO sys_user_post VALUES (3, 6);
INSERT INTO sys_user_post VALUES (4, 5);
INSERT INTO sys_user_post VALUES (5, 4);

-- ----------------------------
-- 角色数据
-- ----------------------------
INSERT INTO sys_role VALUES (1, '超级管理员', 'admin', 1, '1', 1, 1, '0', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '拥有所有权限');
INSERT INTO sys_role VALUES (2, '普通角色',   'common', 2, '5', 1, 1, '0', '0', 'admin', '2024-01-01 00:00:00', '', NULL, '普通用户角色');

-- ----------------------------
-- 用户角色关联
-- ----------------------------
INSERT INTO sys_user_role VALUES (1, 1);
INSERT INTO sys_user_role VALUES (2, 2);
INSERT INTO sys_user_role VALUES (5, 2);

-- ----------------------------
-- 菜单数据
-- ----------------------------
-- 一级目录
INSERT INTO sys_menu VALUES (1,   '系统管理', 0,  1, 'system',           NULL,  '', '', 1, 0, 'M', '0', '0', '', 'mdi mdi-cog-outline',      'admin', '2024-01-01 00:00:00', '', '系统管理目录');
INSERT INTO sys_menu VALUES (2,   '系统监控', 0,  2, 'monitor',          NULL,  '', '', 1, 0, 'M', '0', '0', '', 'mdi mdi-monitor-dashboard', 'admin', '2024-01-01 00:00:00', '', '系统监控目录');
INSERT INTO sys_menu VALUES (3,   '系统工具', 0,  3, 'tool',             NULL,  '', '', 1, 0, 'M', '0', '0', '', 'mdi mdi-tools',             'admin', '2024-01-01 00:00:00', '', '系统工具目录');
INSERT INTO sys_menu VALUES (100, 'CMS管理',  0,  4, 'cms',              NULL,  '', '', 1, 0, 'M', '0', '0', '', 'mdi mdi-web',               'admin', '2024-01-01 00:00:00', '', 'CMS内容管理');

-- 系统管理子菜单
INSERT INTO sys_menu VALUES (10,  '用户管理', 1, 1, 'user',       'page/user.html',       '', '', 1, 0, 'C', '0', '0', 'system:user:list',     'mdi mdi-account-multiple',   'admin', '2024-01-01 00:00:00', '', '用户管理菜单');
INSERT INTO sys_menu VALUES (11,  '角色管理', 1, 2, 'role',       'page/role.html',       '', '', 1, 0, 'C', '0', '0', 'system:role:list',     'mdi mdi-account-supervisor', 'admin', '2024-01-01 00:00:00', '', '角色管理菜单');
INSERT INTO sys_menu VALUES (12,  '菜单管理', 1, 3, 'menu',       'page/menu.html',       '', '', 1, 0, 'C', '0', '0', 'system:menu:list',     'mdi mdi-menu',               'admin', '2024-01-01 00:00:00', '', '菜单管理菜单');
INSERT INTO sys_menu VALUES (13,  '部门管理', 1, 4, 'dept',       'page/dept.html',       '', '', 1, 0, 'C', '0', '0', 'system:dept:list',     'mdi mdi-file-tree',          'admin', '2024-01-01 00:00:00', '', '部门管理菜单');
INSERT INTO sys_menu VALUES (14,  '字典管理', 1, 5, 'dict',       'page/dict.html',       '', '', 1, 0, 'C', '0', '0', 'system:dict:list',     'mdi mdi-book-open-page-variant', 'admin', '2024-01-01 00:00:00', '', '字典管理菜单');
INSERT INTO sys_menu VALUES (15,  '参数设置', 1, 6, 'config',     'page/config.html',     '', '', 1, 0, 'C', '0', '0', 'system:config:list',   'mdi mdi-cog',                'admin', '2024-01-01 00:00:00', '', '参数设置菜单');
INSERT INTO sys_menu VALUES (16,  '通知公告', 1, 7, 'notice',     'page/notice.html',     '', '', 1, 0, 'C', '0', '0', 'system:notice:list',   'mdi mdi-bell-outline',       'admin', '2024-01-01 00:00:00', '', '通知公告菜单');
INSERT INTO sys_menu VALUES (17,  '岗位管理', 1, 8, 'post',       'page/post.html',       '', '', 1, 0, 'C', '0', '0', 'system:post:list',     'mdi mdi-badge-account-horizontal', 'admin', '2024-01-01 00:00:00', '', '岗位管理菜单');

-- 系统监控子菜单
INSERT INTO sys_menu VALUES (20,  '操作日志', 2, 1, 'operlog',    'page/operlog.html',    '', '', 1, 0, 'C', '0', '0', 'monitor:operlog:list', 'mdi mdi-file-document-outline', 'admin', '2024-01-01 00:00:00', '', '操作日志菜单');
INSERT INTO sys_menu VALUES (21,  '登录日志', 2, 2, 'logininfor', 'page/logininfor.html', '', '', 1, 0, 'C', '0', '0', 'monitor:logininfor:list', 'mdi mdi-login-variant',    'admin', '2024-01-01 00:00:00', '', '登录日志菜单');
INSERT INTO sys_menu VALUES (22,  '在线用户', 2, 3, 'online',     'page/online.html',     '', '', 1, 0, 'C', '0', '0', 'monitor:online:list',   'mdi mdi-account-check',      'admin', '2024-01-01 00:00:00', '', '在线用户菜单');
INSERT INTO sys_menu VALUES (23,  '服务监控', 2, 4, 'server',     'page/server.html',     '', '', 1, 0, 'C', '0', '0', 'monitor:server:list',   'mdi mdi-server',             'admin', '2024-01-01 00:00:00', '', '服务监控菜单');

-- 用户管理按钮权限
INSERT INTO sys_menu VALUES (1001, '用户查询', 10, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:query',  '#', 'admin', '2024-01-01 00:00:00', '', '');
INSERT INTO sys_menu VALUES (1002, '用户新增', 10, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:add',    '#', 'admin', '2024-01-01 00:00:00', '', '');
INSERT INTO sys_menu VALUES (1003, '用户修改', 10, 3, '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:edit',   '#', 'admin', '2024-01-01 00:00:00', '', '');
INSERT INTO sys_menu VALUES (1004, '用户删除', 10, 4, '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:remove', '#', 'admin', '2024-01-01 00:00:00', '', '');
INSERT INTO sys_menu VALUES (1005, '用户导出', 10, 5, '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:export', '#', 'admin', '2024-01-01 00:00:00', '', '');

-- 角色管理按钮权限
INSERT INTO sys_menu VALUES (1011, '角色查询', 11, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'system:role:query',  '#', 'admin', '2024-01-01 00:00:00', '', '');
INSERT INTO sys_menu VALUES (1012, '角色新增', 11, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'system:role:add',    '#', 'admin', '2024-01-01 00:00:00', '', '');
INSERT INTO sys_menu VALUES (1013, '角色修改', 11, 3, '', '', '', '', 1, 0, 'F', '0', '0', 'system:role:edit',   '#', 'admin', '2024-01-01 00:00:00', '', '');
INSERT INTO sys_menu VALUES (1014, '角色删除', 11, 4, '', '', '', '', 1, 0, 'F', '0', '0', 'system:role:remove', '#', 'admin', '2024-01-01 00:00:00', '', '');

-- CMS菜单 (为将来预留)
INSERT INTO sys_menu VALUES (110, '栏目管理', 100, 1, 'category',    'page/category.html',     '', '', 1, 0, 'C', '0', '0', 'cms:category:list',  'mdi mdi-folder-outline',    'admin', '2024-01-01 00:00:00', '', 'CMS栏目管理');
INSERT INTO sys_menu VALUES (111, '内容管理', 100, 2, 'article',     'page/article.html',      '', '', 1, 0, 'C', '0', '0', 'cms:article:list',   'mdi mdi-file-document',     'admin', '2024-01-01 00:00:00', '', 'CMS内容管理');
INSERT INTO sys_menu VALUES (112, '模板管理', 100, 3, 'template',    'page/template.html',     '', '', 1, 0, 'C', '0', '0', 'cms:template:list',  'mdi mdi-code-tags',         'admin', '2024-01-01 00:00:00', '', 'CMS模板管理');

-- ----------------------------
-- 角色菜单关联 (管理员拥有所有菜单)
-- ----------------------------
INSERT INTO sys_role_menu VALUES
(1, 1), (1, 10), (1, 11), (1, 12), (1, 13), (1, 14), (1, 15), (1, 16), (1, 17),
(1, 1001), (1, 1002), (1, 1003), (1, 1004), (1, 1005),
(1, 1011), (1, 1012), (1, 1013), (1, 1014),
(1, 2), (1, 20), (1, 21), (1, 22), (1, 23),
(1, 3),
(1, 100), (1, 110), (1, 111), (1, 112);

-- 普通角色菜单
INSERT INTO sys_role_menu VALUES
(2, 1), (2, 10), (2, 1001),
(2, 2), (2, 20), (2, 21),
(2, 100), (2, 110), (2, 111);

-- ----------------------------
-- 字典类型数据
-- ----------------------------
INSERT INTO sys_dict_type VALUES (1,  '用户性别', 'sys_user_sex',        '0', 'admin', '2024-01-01 00:00:00', '', '用户性别列表');
INSERT INTO sys_dict_type VALUES (2,  '菜单状态', 'sys_show_hide',       '0', 'admin', '2024-01-01 00:00:00', '', '菜单状态列表');
INSERT INTO sys_dict_type VALUES (3,  '系统开关', 'sys_normal_disable',  '0', 'admin', '2024-01-01 00:00:00', '', '系统开关列表');
INSERT INTO sys_dict_type VALUES (4,  '通知类型', 'sys_notice_type',     '0', 'admin', '2024-01-01 00:00:00', '', '通知类型列表');
INSERT INTO sys_dict_type VALUES (5,  '操作类型', 'sys_oper_type',       '0', 'admin', '2024-01-01 00:00:00', '', '操作类型列表');

-- ----------------------------
-- 字典数据
-- ----------------------------
-- 用户性别
INSERT INTO sys_dict_data VALUES (1,  1, '男',       '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2024-01-01 00:00:00', '', '性别男');
INSERT INTO sys_dict_data VALUES (2,  2, '女',       '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2024-01-01 00:00:00', '', '性别女');
INSERT INTO sys_dict_data VALUES (3,  3, '未知',     '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2024-01-01 00:00:00', '', '性别未知');

-- 显示状态
INSERT INTO sys_dict_data VALUES (10, 1, '显示', '0', 'sys_show_hide', '', 'mdi mdi-eye',       'Y', '0', 'admin', '2024-01-01 00:00:00', '', '显示状态');
INSERT INTO sys_dict_data VALUES (11, 2, '隐藏', '1', 'sys_show_hide', '', 'mdi mdi-eye-off',   'N', '0', 'admin', '2024-01-01 00:00:00', '', '隐藏状态');

-- 系统开关
INSERT INTO sys_dict_data VALUES (20, 1, '正常', '0', 'sys_normal_disable', '', 'badge badge-success', 'Y', '0', 'admin', '2024-01-01 00:00:00', '', '正常状态');
INSERT INTO sys_dict_data VALUES (21, 2, '停用', '1', 'sys_normal_disable', '', 'badge badge-danger',  'N', '0', 'admin', '2024-01-01 00:00:00', '', '停用状态');

-- 通知类型
INSERT INTO sys_dict_data VALUES (30, 1, '通知', '1', 'sys_notice_type', '', '', 'Y', '0', 'admin', '2024-01-01 00:00:00', '', '通知');
INSERT INTO sys_dict_data VALUES (31, 2, '公告', '2', 'sys_notice_type', '', '', 'N', '0', 'admin', '2024-01-01 00:00:00', '', '公告');

-- 操作类型
INSERT INTO sys_dict_data VALUES (40, 1, '其它',   '0', 'sys_oper_type', '', '', 'Y', '0', 'admin', '2024-01-01 00:00:00', '', '其它操作');
INSERT INTO sys_dict_data VALUES (41, 2, '新增',   '1', 'sys_oper_type', '', '', 'N', '0', 'admin', '2024-01-01 00:00:00', '', '新增操作');
INSERT INTO sys_dict_data VALUES (42, 3, '修改',   '2', 'sys_oper_type', '', '', 'N', '0', 'admin', '2024-01-01 00:00:00', '', '修改操作');
INSERT INTO sys_dict_data VALUES (43, 4, '删除',   '3', 'sys_oper_type', '', '', 'N', '0', 'admin', '2024-01-01 00:00:00', '', '删除操作');

-- ----------------------------
-- 系统配置数据
-- ----------------------------
INSERT INTO sys_config VALUES (1, '用户管理-账号初始密码',          'sys.user.initPassword',     '123456',    'Y', 'admin', '2024-01-01 00:00:00', '', '初始密码');
INSERT INTO sys_config VALUES (2, '主框架页-默认皮肤样式名称',      'sys.index.skinName',        'skin-blue', 'Y', 'admin', '2024-01-01 00:00:00', '', '蓝色主题');
INSERT INTO sys_config VALUES (3, '账号自助-验证码开关',            'sys.account.captchaEnabled', 'false',     'Y', 'admin', '2024-01-01 00:00:00', '', '是否开启验证码');
INSERT INTO sys_config VALUES (4, 'CMS网站名称',                   'cms.site.name',             'XX科技官方网站', 'Y', 'admin', '2024-01-01 00:00:00', '', 'CMS站点名称');
INSERT INTO sys_config VALUES (5, 'CMS网站域名',                   'cms.site.domain',           'http://www.example.com', 'Y', 'admin', '2024-01-01 00:00:00', '', 'CMS站点域名');
INSERT INTO sys_config VALUES (6, 'CMS静态文件输出目录',           'cms.static.outputDir',      '/static/html/', 'Y', 'admin', '2024-01-01 00:00:00', '', '静态HTML输出目录');

-- ----------------------------
-- 通知公告数据
-- ----------------------------
INSERT INTO sys_notice VALUES (1, '关于升级系统框架的通知',                                 '2', 0xE59084E983A8E997A8EFBC9A0AE7B3BBE7BB9FE5B7B2E58886E58D87E887B3536F6C6F6E20332E31302E3720E280A020426565746C53514CE5AE9EE78EB0EFBC8CE8AFB7E5A4A7E5AEB6E6B3A8E6848FE696B0E58A9FE883BDE58F98E58C96E38082, '0', 'admin', '2024-01-01 00:00:00', '', '管理员');
INSERT INTO sys_notice VALUES (2, 'CMS企业网站管理系统正式上线',                              '1', 0x434D53E4BC81E4B89AE7BD91E7AB99E7AEA1E79086E7B3BBE7BB9FE6ADA3E5BC8FE4B88AE7BABFEFBC8CE694AFE68C81E6A8A1E69DBFE7AEA1E79086E38081E99D99E6808148544D4CE7949FE68890E7AD89E58A9FE883BDE38082, '0', 'admin', '2024-01-01 00:00:00', '', '管理员');
INSERT INTO sys_notice VALUES (3, '关于2024年春节放假安排的通知',                            '1', 0x32303234E5B9B4E698A5E88A82E694BEE58187E5AE89E68E92EFBC9A32E69C8839E697A5E887B33137E697A5E38082, '0', 'admin', '2024-01-01 00:00:00', '', '管理员');
