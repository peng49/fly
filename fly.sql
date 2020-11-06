/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 50642
 Source Host           : 127.0.0.1:3306
 Source Schema         : fly

 Target Server Type    : MySQL
 Target Server Version : 50642
 File Encoding         : 65001

 Date: 06/11/2020 18:19:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_menu
-- ----------------------------
DROP TABLE IF EXISTS `admin_menu`;
CREATE TABLE `admin_menu`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL DEFAULT 0,
  `sort` int(11) NOT NULL DEFAULT 0,
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `uri` varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `component` varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `permission` varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `created_at` timestamp(0) NULL DEFAULT NULL,
  `updated_at` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_menu
-- ----------------------------
INSERT INTO `admin_menu` VALUES (2, 0, 2, 'Admin', 'fa-tasks', '', NULL, NULL, NULL, NULL);
INSERT INTO `admin_menu` VALUES (3, 2, 3, 'Users', 'fa-users', 'auth/users', NULL, NULL, NULL, NULL);
INSERT INTO `admin_menu` VALUES (4, 2, 4, 'Roles', 'fa-user', 'auth/roles', NULL, NULL, NULL, NULL);
INSERT INTO `admin_menu` VALUES (5, 2, 5, 'Permission', 'fa-ban', 'auth/permissions', NULL, NULL, NULL, NULL);
INSERT INTO `admin_menu` VALUES (6, 2, 6, 'Menu', 'fa-bars', 'auth/menu', NULL, NULL, NULL, NULL);
INSERT INTO `admin_menu` VALUES (7, 2, 7, 'Operation log', 'fa-history', 'auth/logs', NULL, NULL, NULL, NULL);
INSERT INTO `admin_menu` VALUES (8, 0, 0, '首页', '', '11', '', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for admin_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `admin_operation_log`;
CREATE TABLE `admin_operation_log`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `path` varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `ip` varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `input` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp(0) NULL DEFAULT NULL,
  `updated_at` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `admin_operation_log_user_id_index`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for admin_permissions
-- ----------------------------
DROP TABLE IF EXISTS `admin_permissions`;
CREATE TABLE `admin_permissions`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NULL DEFAULT 0 COMMENT '上级权限',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `slug` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `http_method` varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `http_path` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `created_at` timestamp(0) NULL DEFAULT NULL,
  `updated_at` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `admin_permissions_name_unique`(`name`) USING BTREE,
  UNIQUE INDEX `admin_permissions_slug_unique`(`slug`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_permissions
-- ----------------------------
INSERT INTO `admin_permissions` VALUES (1, 0, 'All permission', '*', '', '/**', NULL, NULL);
INSERT INTO `admin_permissions` VALUES (2, 0, 'Dashboard', 'dashboard', 'GET', '/', NULL, NULL);
INSERT INTO `admin_permissions` VALUES (3, 0, 'Login', 'auth.login', '', '/auth/login\r\n/auth/logout', NULL, NULL);
INSERT INTO `admin_permissions` VALUES (4, 0, 'User setting', 'auth.setting', 'GET,PUT', '/auth/setting', NULL, NULL);
INSERT INTO `admin_permissions` VALUES (5, 0, 'Auth management', 'auth.management', '', '/auth/roles\r\n/auth/permissions\r\n/auth/menu\r\n/auth/logs', NULL, NULL);
INSERT INTO `admin_permissions` VALUES (6, 4, '测试权限', '23523235', 'GET', '/users**', NULL, NULL);
INSERT INTO `admin_permissions` VALUES (7, 4, '是的', 'sd', 'GET', 'sdgdg', NULL, NULL);
INSERT INTO `admin_permissions` VALUES (8, 2, 'sdfgh', 'asdg', 'agha', 'ha', NULL, NULL);

-- ----------------------------
-- Table structure for admin_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_menu`;
CREATE TABLE `admin_role_menu`  (
  `role_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  `created_at` timestamp(0) NULL DEFAULT NULL,
  `updated_at` timestamp(0) NULL DEFAULT NULL,
  INDEX `admin_role_menu_role_id_menu_id_index`(`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_role_menu
-- ----------------------------
INSERT INTO `admin_role_menu` VALUES (1, 2, NULL, NULL);

-- ----------------------------
-- Table structure for admin_role_permissions
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_permissions`;
CREATE TABLE `admin_role_permissions`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  `created_at` timestamp(0) NULL DEFAULT NULL,
  `updated_at` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `admin_role_permissions_role_id_permission_id_index`(`role_id`, `permission_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_role_permissions
-- ----------------------------
INSERT INTO `admin_role_permissions` VALUES (13, 1, 1, NULL, NULL);
INSERT INTO `admin_role_permissions` VALUES (14, 1, 5, NULL, NULL);
INSERT INTO `admin_role_permissions` VALUES (15, 2, 1, NULL, NULL);
INSERT INTO `admin_role_permissions` VALUES (16, 2, 2, NULL, NULL);
INSERT INTO `admin_role_permissions` VALUES (17, 2, 3, NULL, NULL);
INSERT INTO `admin_role_permissions` VALUES (18, 2, 4, NULL, NULL);
INSERT INTO `admin_role_permissions` VALUES (19, 2, 5, NULL, NULL);
INSERT INTO `admin_role_permissions` VALUES (20, 2, 6, NULL, NULL);
INSERT INTO `admin_role_permissions` VALUES (21, 2, 7, NULL, NULL);
INSERT INTO `admin_role_permissions` VALUES (22, 2, 8, NULL, NULL);

-- ----------------------------
-- Table structure for admin_role_users
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_users`;
CREATE TABLE `admin_role_users`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_at` timestamp(0) NULL DEFAULT NULL,
  `updated_at` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `admin_role_users_role_id_user_id_index`(`role_id`, `user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_role_users
-- ----------------------------
INSERT INTO `admin_role_users` VALUES (33, 1, 1, NULL, NULL);
INSERT INTO `admin_role_users` VALUES (34, 2, 1, NULL, NULL);
INSERT INTO `admin_role_users` VALUES (44, 2, 2, NULL, NULL);

-- ----------------------------
-- Table structure for admin_roles
-- ----------------------------
DROP TABLE IF EXISTS `admin_roles`;
CREATE TABLE `admin_roles`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `slug` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp(0) NULL DEFAULT NULL,
  `updated_at` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `admin_roles_name_unique`(`name`) USING BTREE,
  UNIQUE INDEX `admin_roles_slug_unique`(`slug`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_roles
-- ----------------------------
INSERT INTO `admin_roles` VALUES (1, 'Administrator', 'administrator', '2020-10-15 09:55:34', '2020-10-15 09:55:34');
INSERT INTO `admin_roles` VALUES (2, 'test', 'test23', NULL, NULL);

-- ----------------------------
-- Table structure for admin_user_permissions
-- ----------------------------
DROP TABLE IF EXISTS `admin_user_permissions`;
CREATE TABLE `admin_user_permissions`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  `created_at` timestamp(0) NULL DEFAULT NULL,
  `updated_at` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `admin_user_permissions_user_id_permission_id_index`(`user_id`, `permission_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 140 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_user_permissions
-- ----------------------------
INSERT INTO `admin_user_permissions` VALUES (105, 1, 1, NULL, NULL);
INSERT INTO `admin_user_permissions` VALUES (106, 1, 2, NULL, NULL);
INSERT INTO `admin_user_permissions` VALUES (107, 1, 8, NULL, NULL);
INSERT INTO `admin_user_permissions` VALUES (108, 1, 3, NULL, NULL);
INSERT INTO `admin_user_permissions` VALUES (137, 2, 3, NULL, NULL);
INSERT INTO `admin_user_permissions` VALUES (138, 2, 6, NULL, NULL);
INSERT INTO `admin_user_permissions` VALUES (139, 2, 5, NULL, NULL);

-- ----------------------------
-- Table structure for admin_users
-- ----------------------------
DROP TABLE IF EXISTS `admin_users`;
CREATE TABLE `admin_users`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `avatar` varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `remember_token` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `created_at` timestamp(0) NULL DEFAULT NULL,
  `updated_at` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `admin_users_username_unique`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_users
-- ----------------------------
INSERT INTO `admin_users` VALUES (1, 'admin', '$2a$10$.OV/8hrpjUz0yX7vcQXeG.DEPxw9m9hsbg6W732o9oBNrWePax.V2', 'dfgd2', NULL, NULL, '2020-10-15 09:55:34', '2020-10-29 19:46:32');
INSERT INTO `admin_users` VALUES (2, 'test', '$2a$10$1Pc9sSC3L70HpWOX/ozL0ellERiPa3GAPJF8smtDfnZkqxgU89LKi', 'stst', NULL, NULL, NULL, '2020-11-02 17:27:01');

-- ----------------------------
-- Table structure for columns
-- ----------------------------
DROP TABLE IF EXISTS `columns`;
CREATE TABLE `columns`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sort` int(11) NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of columns
-- ----------------------------
INSERT INTO `columns` VALUES (1, '技术', 1, NULL);
INSERT INTO `columns` VALUES (2, '问答', 4, NULL);
INSERT INTO `columns` VALUES (5, '测试栏', 6, NULL);
INSERT INTO `columns` VALUES (7, 'VFSD', 3, NULL);
INSERT INTO `columns` VALUES (8, 'string', 0, NULL);
INSERT INTO `columns` VALUES (11, '测试', 0, NULL);
INSERT INTO `columns` VALUES (12, '测试', 0, NULL);
INSERT INTO `columns` VALUES (13, '测试', 0, NULL);
INSERT INTO `columns` VALUES (14, 'gh', 1, NULL);
INSERT INTO `columns` VALUES (15, 'FG', 0, NULL);
INSERT INTO `columns` VALUES (16, 'FGSET', 0, NULL);

-- ----------------------------
-- Table structure for friend_links
-- ----------------------------
DROP TABLE IF EXISTS `friend_links`;
CREATE TABLE `friend_links`  (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` tinyint(255) NULL DEFAULT 0,
  `created_at` datetime(0) NULL DEFAULT NULL,
  `updated_at` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of friend_links
-- ----------------------------
INSERT INTO `friend_links` VALUES (1, 'baidu', 'http://www.baidu.com', 1, '2020-10-21 17:27:12', '2020-10-21 17:27:15');

-- ----------------------------
-- Table structure for message_queue
-- ----------------------------
DROP TABLE IF EXISTS `message_queue`;
CREATE TABLE `message_queue`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `sender` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发送人',
  `receiver` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '接收人',
  `type` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `attachment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件',
  `status` tinyint(255) NULL DEFAULT NULL COMMENT '状态',
  `fail_number` int(11) NOT NULL DEFAULT 0 COMMENT '失败次数',
  `fail_message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '失败原因',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for oauth_account
-- ----------------------------
DROP TABLE IF EXISTS `oauth_account`;
CREATE TABLE `oauth_account`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `platform` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `openid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `created_at` datetime(0) NULL DEFAULT NULL,
  `update_at` datetime(0) NULL DEFAULT NULL,
  `updated_at` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '第三方授权账户信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for post_auto_draft
-- ----------------------------
DROP TABLE IF EXISTS `post_auto_draft`;
CREATE TABLE `post_auto_draft`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  `title` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `created_at` datetime(0) NULL DEFAULT NULL,
  `update_at` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '自动保存的草稿数据' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of post_auto_draft
-- ----------------------------
INSERT INTO `post_auto_draft` VALUES (1, 1, 1, 'dfsdf', 'asdasdasdasdasdasdasdasdasdasdasdasdasdasdzhezheshigeshenmeguineadfakldjfakldjgalkdjgakldjgakldjgalkdalkdjgaiodgadfgalkjdgalkdjgalkdjgaldkgjalsdkgadfasdfadsfasdagdfasdgasdhadfhdfhsdfhsfdbxcvbcvbsdgsdfhsfghsdfhsdfhxcvzxcvzcxvzxcvzxcbneirong123zheshiehensfgagakljgafglkadgjkldfg;lsdfjgk;sldfkg;sdlfkgs;ldfkhsd;lfkhs;dlfkhgsdf;lhks\\\'lhs\'f;lghs\\\'df;kds;lgkhs;ldfhs;ldfkhs;dlfkhs;lfdkhs;ldfkhs;lfkhsd;lfjkhspofhsdflkhs\'f;lhkoperyl;sdfkhs;flkhs;flhk', '2020-09-22 13:15:32', '2020-09-22 13:26:50');

-- ----------------------------
-- Table structure for post_comment_agree
-- ----------------------------
DROP TABLE IF EXISTS `post_comment_agree`;
CREATE TABLE `post_comment_agree`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT 0 COMMENT '用户Id',
  `post_id` int(11) NOT NULL DEFAULT 0,
  `comment_id` int(11) NOT NULL DEFAULT 0,
  `created_at` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of post_comment_agree
-- ----------------------------
INSERT INTO `post_comment_agree` VALUES (1, 1, 1, 36, '2020-09-21 19:13:03');

-- ----------------------------
-- Table structure for post_comments
-- ----------------------------
DROP TABLE IF EXISTS `post_comments`;
CREATE TABLE `post_comments`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT 0,
  `post_id` int(11) NOT NULL DEFAULT 0,
  `parent_id` int(11) NOT NULL DEFAULT 0,
  `agree_count` int(11) NOT NULL DEFAULT 0,
  `level` int(11) NOT NULL DEFAULT 1,
  `created_at` datetime(0) NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of post_comments
-- ----------------------------
INSERT INTO `post_comments` VALUES (31, 1, 16, 0, 0, 1, '2020-09-07 00:00:00', '<p>测试</p>');
INSERT INTO `post_comments` VALUES (32, 1, 16, 31, 0, 2, '2020-09-07 00:00:00', '<p>回复</p>');
INSERT INTO `post_comments` VALUES (36, 1, 14, 0, 0, 1, '2020-09-10 00:00:00', '<p>@T002 测试@功能</p>');
INSERT INTO `post_comments` VALUES (38, 1, 17, 0, 0, 1, '2020-10-10 00:00:00', '<p>@<a href=\'/u/1\'>admin</a>  中</p>');
INSERT INTO `post_comments` VALUES (39, 1, 8, 0, 0, 4, '2020-10-10 00:00:00', '<p>@<a href=\'/u/1\'>admin</a> 抓狂没见过</p>');
INSERT INTO `post_comments` VALUES (40, 1, 3, 0, 0, 1, '2020-10-10 00:00:00', '<p>@<a href=\'/u/1\'>admin</a>  中暗示</p>');
INSERT INTO `post_comments` VALUES (41, 6, 2, 0, 0, 1, '2020-11-05 09:55:16', '<p>ASG</p>');
INSERT INTO `post_comments` VALUES (42, 1, 3, 0, 0, 2, '2020-11-05 10:20:04', '<p>cest</p>');
INSERT INTO `post_comments` VALUES (43, 1, 3, 0, 0, 3, '2020-11-05 10:20:08', '<p>cest</p>');
INSERT INTO `post_comments` VALUES (44, 1, 3, 0, 0, 4, '2020-11-05 10:20:12', '<p>cest</p>');
INSERT INTO `post_comments` VALUES (45, 1, 26, 0, 0, 1, '2020-11-05 13:12:38', '<p>sdg</p>');
INSERT INTO `post_comments` VALUES (46, 1, 26, 0, 0, 2, '2020-11-05 13:12:42', '<p>sdg</p>');
INSERT INTO `post_comments` VALUES (47, 1, 26, 0, 0, 3, '2020-11-05 13:12:47', '<p>sdg</p>');
INSERT INTO `post_comments` VALUES (48, 1, 26, 0, 0, 4, '2020-11-05 13:13:32', '<p>sdg</p>');
INSERT INTO `post_comments` VALUES (49, 1, 26, 0, 0, 5, '2020-11-05 13:13:39', '<p>SDG</p>');
INSERT INTO `post_comments` VALUES (50, 1, 17, 0, 0, 2, '2020-11-05 13:27:04', '<p>asdgasdh</p>');
INSERT INTO `post_comments` VALUES (51, 1, 17, 0, 0, 3, '2020-11-05 13:32:09', '<p>SADGDSG</p>');
INSERT INTO `post_comments` VALUES (52, 1, 3, 0, 0, 5, '2020-11-05 16:16:44', '<p>ertyery</p>');

-- ----------------------------
-- Table structure for posts
-- ----------------------------
DROP TABLE IF EXISTS `posts`;
CREATE TABLE `posts`  (
  `id` bigint(20) UNSIGNED NOT NULL,
  `column_id` int(11) NOT NULL,
  `author_id` int(11) NOT NULL COMMENT 'users 表Id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `original_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `status` tinyint(1) NULL DEFAULT NULL,
  `reply_count` int(11) NOT NULL DEFAULT 0,
  `view_count` int(11) NOT NULL DEFAULT 0,
  `publish_at` datetime(0) NULL DEFAULT NULL,
  `heat` double(12, 4) NOT NULL DEFAULT 0.0000 COMMENT '热度',
  `created_at` datetime(0) NULL DEFAULT NULL,
  `update_at` datetime(0) NULL DEFAULT NULL,
  `essence` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否加精',
  `top` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否置顶',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of posts
-- ----------------------------
INSERT INTO `posts` VALUES (1, 2, 1, '编辑', '# 标题\n\n内容', '<h1 id=\"h1-u6807u9898\"><a name=\"标题\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>标题</h1><p>内容</p>\n', 0, 0, 364, '2020-06-03 14:11:20', 0.0084, '2020-07-06 09:21:12', '2020-07-06 09:21:12', 0, 0);
INSERT INTO `posts` VALUES (2, 1, 1, 'hello world', '```java\n	System.out.println(\"hello world\");\n	int a = 1 + 3;\n	System.out.println(a);\n	int b = 2\n```', '<ol class=\"linenums\">\n<li class=\"L0\"><code class=\"lang-java\">    <span class=\"typ\">System</span><span class=\"pun\">.</span><span class=\"pln\">out</span><span class=\"pun\">.</span><span class=\"pln\">println</span><span class=\"pun\">(</span><span class=\"str\">\"hello world\"</span><span class=\"pun\">);</span></code></li>\n<li class=\"L1\"><code class=\"lang-java\">    <span class=\"kwd\">int</span><span class=\"pln\"> a </span><span class=\"pun\">=</span> <span class=\"lit\">1</span> <span class=\"pun\">+</span> <span class=\"lit\">3</span><span class=\"pun\">;</span></code></li>\n<li class=\"L2\"><code class=\"lang-java\">    <span class=\"typ\">System</span><span class=\"pun\">.</span><span class=\"pln\">out</span><span class=\"pun\">.</span><span class=\"pln\">println</span><span class=\"pun\">(</span><span class=\"pln\">a</span><span class=\"pun\">);</span></code></li>\n<li class=\"L3\"><code class=\"lang-java\">    <span class=\"kwd\">int</span><span class=\"pln\"> b </span><span class=\"pun\">=</span> <span class=\"lit\">2</span></code></li>\n</ol>', 0, 1, 0, '2020-07-08 13:55:45', 0.0000, '2020-07-08 13:55:45', '2020-07-08 13:55:45', 0, 0);
INSERT INTO `posts` VALUES (3, 2, 1, 'heat', 'heat', '<p>heat</p>\n', 1, 5, 90, '2020-08-24 15:23:51', 0.0089, '2020-07-09 11:47:23', '2020-07-09 11:47:23', 0, 0);
INSERT INTO `posts` VALUES (4, 1, 1, 'test', NULL, 'test content', 0, 0, 5, '2020-08-13 11:26:20', 0.0375, '2020-08-13 11:26:20', '2020-08-13 11:26:20', 0, 0);
INSERT INTO `posts` VALUES (5, 1, 1, 'test1', '内容', '<p>内容</p>\n', 0, 0, 7, '2020-08-13 16:01:05', 0.0382, '2020-08-13 16:01:05', '2020-08-13 16:01:05', 0, 0);
INSERT INTO `posts` VALUES (6, 1, 1, 'test', 'test  中文难题荣', '<p>test  中文难题荣</p>\n', 1, 0, 13, '2020-08-13 16:04:21', 0.0050, '2020-08-13 16:04:21', '2020-08-13 16:04:21', 0, 0);
INSERT INTO `posts` VALUES (7, 1, 1, 'test', NULL, 'test content', 0, 0, 9, '2020-08-13 16:24:51', 0.0384, '2020-08-13 16:24:51', '2020-08-13 16:24:51', 0, 0);
INSERT INTO `posts` VALUES (8, 1, 1, 'test', 'test', '<p>test</p>\n', 1, 4, 43, '2020-08-24 14:57:40', 0.0081, '2020-08-13 16:26:32', '2020-08-13 16:26:32', 0, 0);
INSERT INTO `posts` VALUES (9, 2, 1, 'freemarked tpl', '# title\n\ntest content\n\n```java\nSystem.out.println(\'hello world\')\n```\n\n```python\nprint(\"hello world\")\n```\n\n```php\n<?php\n	echo \"hello world\";\n```\n\nfirst update', '<h1 id=\"h1-title\"><a name=\"title\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>title</h1><p>test content</p>\n<pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code class=\"lang-java\"><span class=\"typ\">System</span><span class=\"pun\">.</span><span class=\"pln\">out</span><span class=\"pun\">.</span><span class=\"pln\">println</span><span class=\"pun\">(</span><span class=\"str\">\'hello world\'</span><span class=\"pun\">)</span></code></li></ol></pre>\n<pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code class=\"lang-python\"><span class=\"kwd\">print</span><span class=\"pun\">(</span><span class=\"str\">\"hello world\"</span><span class=\"pun\">)</span></code></li></ol></pre>\n<pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code class=\"lang-php\"><span class=\"pun\">&lt;?</span><span class=\"pln\">php</span></code></li><li class=\"L1\"><code class=\"lang-php\"><span class=\"pln\">    echo </span><span class=\"str\">\"hello world\"</span><span class=\"pun\">;</span></code></li></ol></pre>\n<p>first update</p>\n', 1, 0, 57, '2020-08-14 14:44:50', 0.0052, '2020-08-14 14:44:50', '2020-08-14 14:44:50', 0, 0);
INSERT INTO `posts` VALUES (10, 1, 1, 'test', 'test content', '<p>test content</p>\n', 1, 0, 16, '2020-08-17 14:40:16', 0.0052, '2020-08-17 14:40:16', '2020-08-17 14:40:16', 0, 0);
INSERT INTO `posts` VALUES (11, 1, 1, '测试图片上传', '![](/static/5432a74f-f392-4c0e-b579-1bbcc98708d0.png)', '<p><img src=\"/static/5432a74f-f392-4c0e-b579-1bbcc98708d0.png\" alt=\"\"></p>\n', 0, 0, 73, '2020-08-18 15:44:49', 0.0746, '2020-08-18 15:44:49', '2020-08-18 15:44:49', 0, 0);
INSERT INTO `posts` VALUES (12, 1, 1, 'test', 'test content', '<p>test content</p>\n', 1, 0, 25, '2020-08-18 17:26:25', 0.0053, '2020-08-18 17:26:25', '2020-08-18 17:26:25', 0, 0);
INSERT INTO `posts` VALUES (13, 1, 1, '测试内容', '测试', '<p>测试</p>\n', 0, 0, 5, '2020-08-24 13:40:09', 5.8512, '2020-08-24 13:40:09', '2020-08-24 13:40:09', 0, 0);
INSERT INTO `posts` VALUES (14, 1, 1, '新帖', '测试发布', '<p>测试发布</p>\n', 1, 2, 129, '2020-08-24 14:21:12', 0.0075, '2020-08-24 14:19:08', '2020-08-24 14:19:08', 0, 0);
INSERT INTO `posts` VALUES (15, 2, 1, 'title', '### title\n\ncolumn 问答\n\n', '<h3 id=\"h3-title\"><a name=\"title\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>title</h3><p>column 问答</p>\n', 2, 0, 59, '2020-08-26 13:22:57', 0.0163, '2020-08-24 14:49:03', '2020-08-24 14:49:03', 0, 0);
INSERT INTO `posts` VALUES (16, 2, 1, '测试问题', '发布内容的\n', '<p>发布内容的</p>\n', 2, 2, 92, '2020-08-26 13:24:53', 0.0204, '2020-08-26 13:24:53', '2020-08-26 13:24:53', 0, 0);
INSERT INTO `posts` VALUES (17, 1, 1, 'Linux 用户操作', '#### 用户组操作\n\n##### 1.查看用户组\n查看所有用户组\n> cat /etc/group\n\n显示用户所属的用户组\n> groups\n\n##### 2. 添加一个用户组\n添加一个组名为testGroup的用户组\n> sudo groupadd testGroup\n\n执行之后通过上面查看用户组的命令应该可以看到新添加的用户组\n\n##### 3. 修改用户组信息\n修改组名,将testGroup改为newGroup\n> sudo groupmod -n newGroup testGroup\n\n通过 **cat /etc/group** 查看修改效果\n\n##### 4. 删除一个用户组\n删除用户组 newGroup\n\n> sudo groupdel newGroup\n\n#### 用户操作\n##### 1. 添加一个新用户\n添加一个新用户\n> useradd testuser\n\n##### 2. 查看系统用户\n\n> cat /etc/passwd 或 cat /etc/shadow\n\n##### 3. 修改指定用户信息\n修改/设置用户密码\n> sudo passwd testuser\n\n输入两次密码，出现如下提示信息时说明修改成功\n```\nChanging password for user testuser.\nNew password:\nBAD PASSWORD: The password fails the dictionary check - it is based on a dictionary word\nRetype new password:\npasswd: all authentication tokens updated successfully.\n```\n\n为用户指定用户组\n\n修改用户用户组\n\n##### 4. 删除指定用户\n删除用户名为testuser的用户\n> sudo userdel testuser\n\n\n\n\n\n', '<h4 id=\"h4-u7528u6237u7EC4u64CDu4F5C\"><a name=\"用户组操作\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>用户组操作</h4><h5 id=\"h5-1-\"><a name=\"1.查看用户组\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>1.查看用户组</h5><p>查看所有用户组</p>\n<blockquote>\n<p>cat /etc/group</p>\n</blockquote>\n<p>显示用户所属的用户组</p>\n<blockquote>\n<p>groups</p>\n</blockquote>\n<h5 id=\"h5-2-\"><a name=\"2. 添加一个用户组\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>2. 添加一个用户组</h5><p>添加一个组名为testGroup的用户组</p>\n<blockquote>\n<p>sudo groupadd testGroup</p>\n</blockquote>\n<p>执行之后通过上面查看用户组的命令应该可以看到新添加的用户组</p>\n<h5 id=\"h5-3-\"><a name=\"3. 修改用户组信息\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>3. 修改用户组信息</h5><p>修改组名,将testGroup改为newGroup</p>\n<blockquote>\n<p>sudo groupmod -n newGroup testGroup</p>\n</blockquote>\n<p>通过 <strong>cat /etc/group</strong> 查看修改效果</p>\n<h5 id=\"h5-4-\"><a name=\"4. 删除一个用户组\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>4. 删除一个用户组</h5><p>删除用户组 newGroup</p>\n<blockquote>\n<p>sudo groupdel newGroup</p>\n</blockquote>\n<h4 id=\"h4-u7528u6237u64CDu4F5C\"><a name=\"用户操作\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>用户操作</h4><h5 id=\"h5-1-\"><a name=\"1. 添加一个新用户\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>1. 添加一个新用户</h5><p>添加一个新用户</p>\n<blockquote>\n<p>useradd testuser</p>\n</blockquote>\n<h5 id=\"h5-2-\"><a name=\"2. 查看系统用户\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>2. 查看系统用户</h5><blockquote>\n<p>cat /etc/passwd 或 cat /etc/shadow</p>\n</blockquote>\n<h5 id=\"h5-3-\"><a name=\"3. 修改指定用户信息\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>3. 修改指定用户信息</h5><p>修改/设置用户密码</p>\n<blockquote>\n<p>sudo passwd testuser</p>\n</blockquote>\n<p>输入两次密码，出现如下提示信息时说明修改成功</p>\n<pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"typ\">Changing</span><span class=\"pln\"> password </span><span class=\"kwd\">for</span><span class=\"pln\"> user testuser</span><span class=\"pun\">.</span></code></li><li class=\"L1\"><code><span class=\"typ\">New</span><span class=\"pln\"> password</span><span class=\"pun\">:</span></code></li><li class=\"L2\"><code><span class=\"pln\">BAD PASSWORD</span><span class=\"pun\">:</span><span class=\"pln\"> </span><span class=\"typ\">The</span><span class=\"pln\"> password fails the dictionary check </span><span class=\"pun\">-</span><span class=\"pln\"> it </span><span class=\"kwd\">is</span><span class=\"pln\"> based on a dictionary word</span></code></li><li class=\"L3\"><code><span class=\"typ\">Retype</span><span class=\"pln\"> </span><span class=\"kwd\">new</span><span class=\"pln\"> password</span><span class=\"pun\">:</span></code></li><li class=\"L4\"><code><span class=\"pln\">passwd</span><span class=\"pun\">:</span><span class=\"pln\"> all authentication tokens updated successfully</span><span class=\"pun\">.</span></code></li></ol></pre><p>为用户指定用户组</p>\n<p>修改用户用户组</p>\n<h5 id=\"h5-4-\"><a name=\"4. 删除指定用户\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>4. 删除指定用户</h5><p>删除用户名为testuser的用户</p>\n<blockquote>\n<p>sudo userdel testuser</p>\n</blockquote>\n', 1, 3, 105, '2020-09-10 14:26:56', 0.0102, '2020-09-10 13:21:03', '2020-09-10 13:21:03', 0, 0);
INSERT INTO `posts` VALUES (18, 1, 1, 'fasdfas', 'dfasfasfasf', '<p>dfasfasfasf</p>\n', 0, 0, 0, NULL, 0.0000, '2020-09-18 13:58:46', '2020-09-18 13:58:46', 0, 0);
INSERT INTO `posts` VALUES (19, 1, 1, 'fsdfs', 'dfsdfsdfsdf', '<p>dfsdfsdfsdf</p>\n', 0, 0, 0, NULL, 0.0000, '2020-09-18 14:01:05', '2020-09-18 14:01:05', 0, 0);
INSERT INTO `posts` VALUES (20, 1, 1, '5yyft', 'fgdfghd', '<p>fgdfghd</p>\n', 0, 0, 0, NULL, 0.0000, '2020-09-18 16:31:37', '2020-09-18 16:31:37', 0, 0);
INSERT INTO `posts` VALUES (21, 1, 1, 'dhsdfg', 'sdfgsdfgsfdg', '<p>sdfgsdfgsfdg</p>\n', 0, 0, 0, NULL, 0.0000, '2020-09-18 16:32:35', '2020-09-18 16:32:35', 0, 0);
INSERT INTO `posts` VALUES (22, 1, 1, 'sdfsdfsdf', 'dffds', '<p>dffds</p>\n', 0, 0, 2, NULL, 0.0000, '2020-09-18 18:55:17', '2020-09-18 18:55:17', 0, 0);
INSERT INTO `posts` VALUES (23, 1, 1, '发斯蒂芬 大的as', 'dffdssdfa sdfasdfasdfadsfasd 中国水电费拉开伤筋动骨；拉框架多斯拉克就阿里看电视剧咖喱开始对接干极高的士大夫的阿萨德中时代峰峻啊抵抗力激发‘’奥德赛附近按实际的发生、dpfa ', '<p>dffdssdfa sdfasdfasdfadsfasd 中国水电费拉开伤筋动骨；拉框架多斯拉克就阿里看电视剧咖喱开始对接干极高的士大夫的阿萨德中时代峰峻啊抵抗力激发‘’奥德赛附近按实际的发生、dpfa </p>\n', 0, 0, 1, NULL, 0.0000, '2020-09-18 19:04:08', '2020-09-18 19:04:08', 0, 0);
INSERT INTO `posts` VALUES (24, 1, 1, 'ftytry', 'zhongakldjsfakdsdsdgzheshigeshenmegui', '<p>zhongakldjsfakdsdsdgzheshigeshenmegui</p>\n', 1, 0, 5, '2020-10-10 14:22:00', 0.0154, '2020-09-18 19:10:24', '2020-09-18 19:10:24', 0, 0);
INSERT INTO `posts` VALUES (25, 1, 1, '', NULL, '', 1, 0, 0, NULL, 0.0000, '2020-10-23 09:58:24', '2020-10-23 09:58:24', 0, 0);
INSERT INTO `posts` VALUES (26, 8, 1, 'WR', NULL, '', 1, 5, 6, NULL, 0.0000, '2020-10-23 10:08:40', '2020-10-23 10:08:40', 0, 0);
INSERT INTO `posts` VALUES (27, 0, 0, '', NULL, '', 1, 0, 0, NULL, 0.0000, '2020-10-23 10:11:18', '2020-10-23 10:11:18', 0, 0);
INSERT INTO `posts` VALUES (28, 0, 0, '', NULL, '', 1, 0, 0, NULL, 0.0000, '2020-10-23 10:43:02', '2020-10-23 10:43:02', 0, 0);
INSERT INTO `posts` VALUES (29, 0, 0, '', NULL, '', 1, 0, 0, NULL, 0.0000, '2020-10-23 11:10:56', '2020-10-23 11:10:56', 0, 0);
INSERT INTO `posts` VALUES (30, 1, 2, 'username', NULL, '<p>内容时代峰峻啊肯德基拉开打扫房间埃里克森DNF按理说肯德基&lsquo;&rsquo;阿斯蒂芬阿萨德</p>', 1, 0, 7, NULL, 0.0000, '2020-10-23 11:11:20', '2020-10-23 11:11:20', 0, 0);
INSERT INTO `posts` VALUES (31, 16, 4, 'username', NULL, '<p>asd aas ds ad adf sdf&nbsp;</p>', 1, 0, 4, NULL, 0.0000, '2020-10-23 11:12:24', '2020-10-23 11:12:24', 0, 0);
INSERT INTO `posts` VALUES (32, 1, 1, '标题', NULL, '<p>阿萨德啊as噶鬼地方个按个按个阿萨德</p>', 1, 0, 1, NULL, 0.0000, '2020-10-23 11:12:31', '2020-10-23 11:12:31', 0, 0);
INSERT INTO `posts` VALUES (33, 1, 1, 'biaoit ', NULL, '<p>按个啊噶十多个</p>', 1, 0, 4, NULL, 0.0000, '2020-10-23 11:13:33', '2020-10-23 11:13:33', 0, 0);
INSERT INTO `posts` VALUES (1324590652464361473, 1, 1, 'SFAdsf', 'ADSGADSG', '<p>ADSGADSG</p>\n', 1, 0, 2, '2020-11-06 13:53:16', 2.2624, '2020-11-06 13:53:16', '2020-11-06 13:53:16', 0, 0);
INSERT INTO `posts` VALUES (1324590671384866817, 1, 1, 'SFAdsf', 'ADSGADSG', '<p>ADSGADSG</p>\n', 1, 0, 0, '2020-11-06 13:53:20', 2.2585, '2020-11-06 13:53:20', '2020-11-06 13:53:20', 0, 0);
INSERT INTO `posts` VALUES (1324591501156630530, 1, 1, 'ASFGDGG', 'SDHGSDHSDH', '<p>SDHGSDHSDH</p>\n', 1, 0, 0, '2020-11-06 13:56:38', 2.2869, '2020-11-06 13:56:38', '2020-11-06 13:56:38', 0, 0);
INSERT INTO `posts` VALUES (1324591519536070657, 1, 1, 'ASFGDGG', 'SDHGSDHSDH', '<p>SDHGSDHSDH</p>\n', 1, 0, 4, '2020-11-06 13:56:43', 2.2967, '2020-11-06 13:56:43', '2020-11-06 13:56:43', 0, 0);
INSERT INTO `posts` VALUES (1324595572865257473, 1, 1, 'ASG', 'SDGSDGSDFDSAGSDSDG', '<p>SDGSDGSDFDSAGSDSDG</p>\n', 1, 0, 7, '2020-11-06 14:12:49', 2.4543, '2020-11-06 14:12:49', '2020-11-06 14:12:49', 0, 0);
INSERT INTO `posts` VALUES (1324596586339127298, 1, 1, 'ASDFtitle', 'ASDGDDSSDH', '<p>ASDGDDSSDH</p>\n', 1, 0, 51, '2020-11-06 14:16:51', 2.6042, '2020-11-06 14:16:51', '2020-11-06 14:16:51', 0, 0);

-- ----------------------------
-- Table structure for user_message
-- ----------------------------
DROP TABLE IF EXISTS `user_message`;
CREATE TABLE `user_message`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `sender_id` int(11) NOT NULL DEFAULT 0,
  `receiver_id` int(11) NOT NULL,
  `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_at` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_message
-- ----------------------------
INSERT INTO `user_message` VALUES (13, 1, 3, 'reply', '<p>@T002 测试@功能</p>', '2020-09-10 19:21:44');
INSERT INTO `user_message` VALUES (18, 1, 1, 'comment', '<p>ertyery</p>', '2020-11-05 16:16:44');

-- ----------------------------
-- Table structure for user_posts
-- ----------------------------
DROP TABLE IF EXISTS `user_posts`;
CREATE TABLE `user_posts`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `post_id` bigint(20) NOT NULL,
  `created_at` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_posts
-- ----------------------------
INSERT INTO `user_posts` VALUES (1, 1, 1, NULL);
INSERT INTO `user_posts` VALUES (2, 1, 2, NULL);
INSERT INTO `user_posts` VALUES (3, 1, 0, NULL);
INSERT INTO `user_posts` VALUES (6, 1, 8, NULL);
INSERT INTO `user_posts` VALUES (7, 1, 14, NULL);
INSERT INTO `user_posts` VALUES (11, 1, 14, NULL);

-- ----------------------------
-- Table structure for user_token
-- ----------------------------
DROP TABLE IF EXISTS `user_token`;
CREATE TABLE `user_token`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户Id',
  `type` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'token类型',
  `token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'token',
  `create_at` int(255) NULL DEFAULT NULL COMMENT '创建时间',
  `expire_in` bigint(255) NULL DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` bigint(20) UNSIGNED NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `signature` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '清明',
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市',
  `experience` int(11) NOT NULL DEFAULT 0 COMMENT '经验值',
  `is_admin` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否管理员',
  `created_at` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `updated_at` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'admin', '$2a$10$mdrju5MRlPbITzVpPCDNmuWk6z5JGJPDXGquT4nkFcWJCHEkWW8ey', 'use@t.com4', 'sdgsdg', '/static/ead4b6c9-43db-4d64-9e95-1dc644583cfe.jpg', 'sasdgasdgsfddf', 'WHS', 0, 1, '2020-10-21 10:47:35', '2020-07-06 09:18:29');
INSERT INTO `users` VALUES (2, 'username', NULL, 'admin@t.com', 'dfd', NULL, NULL, NULL, 0, 0, NULL, '2020-08-21 16:10:08');
INSERT INTO `users` VALUES (3, 'T002', 'JJJJJJJ', 'A@t.com', 'TTTTTT', NULL, NULL, NULL, 0, 0, NULL, '2020-09-10 18:52:47');
INSERT INTO `users` VALUES (4, 'yonghu', NULL, 'aming@t.com', 'GHJ', NULL, NULL, NULL, 0, 0, NULL, NULL);
INSERT INTO `users` VALUES (5, 'retg', NULL, 'eryt', 'FGVB', NULL, NULL, NULL, 0, 0, NULL, NULL);
INSERT INTO `users` VALUES (6, 'A0200', '$2a$10$mdrju5MRlPbITzVpPCDNmuWk6z5JGJPDXGquT4nkFcWJCHEkWW8ey', 'A@t.com', NULL, NULL, NULL, NULL, 0, 0, '2020-11-04 14:00:10', '2020-11-04 14:00:10');

SET FOREIGN_KEY_CHECKS = 1;
