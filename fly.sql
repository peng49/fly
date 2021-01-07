/*
 Navicat Premium Data Transfer

 Source Server         : vagrant-java(192.168.99.15)
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : 192.168.99.15:3307
 Source Schema         : fly-bak

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 30/12/2020 21:20:53
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
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `uri` varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `component` varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `permission` varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `created_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updated_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_menu
-- ----------------------------
INSERT INTO `admin_menu` VALUES (2, 0, 2, 'Admin', 'fa-tasks', '', '', '', '2020-11-26 17:06:23', '2020-12-30 13:19:51');
INSERT INTO `admin_menu` VALUES (3, 2, 3, 'Users', 'fa-users', 'auth/users', '', '', '2020-12-30 13:19:51', '2020-12-30 13:19:51');
INSERT INTO `admin_menu` VALUES (4, 2, 4, 'Roles', 'fa-user', 'auth/roles', '', '', '2020-12-30 13:19:51', '2020-12-30 13:19:51');
INSERT INTO `admin_menu` VALUES (5, 2, 5, 'Permission', 'fa-ban', 'auth/permissions', '', '', '2020-12-30 13:19:51', '2020-12-30 13:19:51');
INSERT INTO `admin_menu` VALUES (6, 2, 6, 'Menu', 'fa-bars', 'auth/menu', '', '', '2020-12-30 13:19:51', '2020-12-30 13:19:51');
INSERT INTO `admin_menu` VALUES (7, 2, 7, 'Operation log', 'fa-history', 'auth/logs', '', '', '2020-12-30 13:19:51', '2020-12-30 13:19:51');
INSERT INTO `admin_menu` VALUES (8, 0, 0, '首页', '', '11', '', '', '2020-12-30 13:19:51', '2020-12-30 13:19:51');

-- ----------------------------
-- Table structure for admin_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `admin_operation_log`;
CREATE TABLE `admin_operation_log`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT 0,
  `path` varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `ip` varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `input` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updated_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `admin_operation_log_user_id_index`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for admin_permissions
-- ----------------------------
DROP TABLE IF EXISTS `admin_permissions`;
CREATE TABLE `admin_permissions`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL DEFAULT 0 COMMENT '上级权限',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `slug` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `http_method` varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `http_path` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updated_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `admin_permissions_name_unique`(`name`) USING BTREE,
  UNIQUE INDEX `admin_permissions_slug_unique`(`slug`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_permissions
-- ----------------------------
INSERT INTO `admin_permissions` VALUES (1, 0, 'All permission', '*', '', '/**', '2020-12-30 13:19:52', '2020-12-30 13:19:52');
INSERT INTO `admin_permissions` VALUES (2, 0, 'Dashboard', 'dashboard', 'GET', '/', '2020-12-30 13:19:52', '2020-12-30 13:19:52');
INSERT INTO `admin_permissions` VALUES (3, 0, 'Login', 'auth.login', '', '/auth/login\r\n/auth/logout', '2020-12-30 13:19:52', '2020-12-30 13:19:52');
INSERT INTO `admin_permissions` VALUES (4, 0, 'User setting', 'auth.setting', 'GET,PUT', '/auth/setting', '2020-12-30 13:19:52', '2020-12-30 13:19:52');
INSERT INTO `admin_permissions` VALUES (5, 0, 'Auth management', 'auth.management', '', '/auth/roles\r\n/auth/permissions\r\n/auth/menu\r\n/auth/logs', '2020-12-30 13:19:52', '2020-12-30 13:19:52');
INSERT INTO `admin_permissions` VALUES (6, 4, '测试权限', '23523235', 'GET', '/users**', '2020-12-30 13:19:52', '2020-12-30 13:19:52');
INSERT INTO `admin_permissions` VALUES (7, 4, '是的', 'sd', 'GET', 'sdgdg', '2020-12-30 13:19:52', '2020-12-30 13:19:52');
INSERT INTO `admin_permissions` VALUES (8, 2, 'sdfgh', 'asdg', 'agha', 'ha', '2020-12-30 13:19:52', '2020-12-30 13:19:52');

-- ----------------------------
-- Table structure for admin_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_menu`;
CREATE TABLE `admin_role_menu`  (
  `role_id` int(11) NOT NULL DEFAULT 0,
  `menu_id` int(11) NOT NULL DEFAULT 0,
  `created_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updated_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  INDEX `admin_role_menu_role_id_menu_id_index`(`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_role_menu
-- ----------------------------
INSERT INTO `admin_role_menu` VALUES (1, 2, '2020-12-30 13:19:52', '2020-12-30 13:19:52');

-- ----------------------------
-- Table structure for admin_role_permissions
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_permissions`;
CREATE TABLE `admin_role_permissions`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL DEFAULT 0,
  `permission_id` int(11) NOT NULL DEFAULT 0,
  `created_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updated_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `admin_role_permissions_role_id_permission_id_index`(`role_id`, `permission_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_role_permissions
-- ----------------------------
INSERT INTO `admin_role_permissions` VALUES (13, 1, 1, '2020-12-30 13:19:52', '2020-12-30 13:19:52');
INSERT INTO `admin_role_permissions` VALUES (14, 1, 5, '2020-12-30 13:19:52', '2020-12-30 13:19:52');
INSERT INTO `admin_role_permissions` VALUES (15, 2, 1, '2020-12-30 13:19:52', '2020-12-30 13:19:52');
INSERT INTO `admin_role_permissions` VALUES (16, 2, 2, '2020-12-30 13:19:52', '2020-12-30 13:19:52');
INSERT INTO `admin_role_permissions` VALUES (17, 2, 3, '2020-12-30 13:19:52', '2020-12-30 13:19:52');
INSERT INTO `admin_role_permissions` VALUES (18, 2, 4, '2020-12-30 13:19:52', '2020-12-30 13:19:52');
INSERT INTO `admin_role_permissions` VALUES (19, 2, 5, '2020-12-30 13:19:52', '2020-12-30 13:19:52');
INSERT INTO `admin_role_permissions` VALUES (20, 2, 6, '2020-12-30 13:19:52', '2020-12-30 13:19:52');
INSERT INTO `admin_role_permissions` VALUES (21, 2, 7, '2020-12-30 13:19:52', '2020-12-30 13:19:52');
INSERT INTO `admin_role_permissions` VALUES (22, 2, 8, '2020-12-30 13:19:52', '2020-12-30 13:19:52');

-- ----------------------------
-- Table structure for admin_role_users
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_users`;
CREATE TABLE `admin_role_users`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL DEFAULT 0,
  `user_id` int(11) NOT NULL DEFAULT 0,
  `created_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updated_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `admin_role_users_role_id_user_id_index`(`role_id`, `user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_role_users
-- ----------------------------
INSERT INTO `admin_role_users` VALUES (33, 1, 1, '2020-12-30 13:19:52', '2020-12-30 13:19:52');
INSERT INTO `admin_role_users` VALUES (34, 2, 1, '2020-12-30 13:19:52', '2020-12-30 13:19:52');
INSERT INTO `admin_role_users` VALUES (44, 2, 2, '2020-12-30 13:19:52', '2020-12-30 13:19:52');

-- ----------------------------
-- Table structure for admin_roles
-- ----------------------------
DROP TABLE IF EXISTS `admin_roles`;
CREATE TABLE `admin_roles`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `slug` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `created_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updated_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `admin_roles_name_unique`(`name`) USING BTREE,
  UNIQUE INDEX `admin_roles_slug_unique`(`slug`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_roles
-- ----------------------------
INSERT INTO `admin_roles` VALUES (1, 'Administrator', 'administrator', '2020-12-30 13:19:52', '2020-12-30 13:19:52');
INSERT INTO `admin_roles` VALUES (2, 'test', 'test23', '2020-12-30 13:19:52', '2020-12-30 13:19:52');

-- ----------------------------
-- Table structure for admin_user_permissions
-- ----------------------------
DROP TABLE IF EXISTS `admin_user_permissions`;
CREATE TABLE `admin_user_permissions`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT 0,
  `permission_id` int(11) NOT NULL DEFAULT 0,
  `created_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updated_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `admin_user_permissions_user_id_permission_id_index`(`user_id`, `permission_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 140 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_user_permissions
-- ----------------------------
INSERT INTO `admin_user_permissions` VALUES (105, 1, 1, '2020-12-30 13:19:52', '2020-12-30 13:19:52');
INSERT INTO `admin_user_permissions` VALUES (106, 1, 2, '2020-12-30 13:19:52', '2020-12-30 13:19:52');
INSERT INTO `admin_user_permissions` VALUES (107, 1, 8, '2020-12-30 13:19:52', '2020-12-30 13:19:52');
INSERT INTO `admin_user_permissions` VALUES (108, 1, 3, '2020-12-30 13:19:52', '2020-12-30 13:19:52');
INSERT INTO `admin_user_permissions` VALUES (137, 2, 3, '2020-12-30 13:19:52', '2020-12-30 13:19:52');
INSERT INTO `admin_user_permissions` VALUES (138, 2, 6, '2020-12-30 13:19:52', '2020-12-30 13:19:52');
INSERT INTO `admin_user_permissions` VALUES (139, 2, 5, '2020-12-30 13:19:52', '2020-12-30 13:19:52');

-- ----------------------------
-- Table structure for admin_users
-- ----------------------------
DROP TABLE IF EXISTS `admin_users`;
CREATE TABLE `admin_users`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `password` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `name` varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `avatar` varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `remember_token` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updated_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `admin_users_username_unique`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_users
-- ----------------------------
INSERT INTO `admin_users` VALUES (1, 'admin', '$2a$10$.OV/8hrpjUz0yX7vcQXeG.DEPxw9m9hsbg6W732o9oBNrWePax.V2', 'dfgd2', '', '', '2020-10-15 09:55:34', '2020-10-29 19:46:32');
INSERT INTO `admin_users` VALUES (2, 'test', '$2a$10$1Pc9sSC3L70HpWOX/ozL0ellERiPa3GAPJF8smtDfnZkqxgU89LKi', 'stst', '', '', '2020-12-30 13:19:52', '2020-11-02 17:27:01');

-- ----------------------------
-- Table structure for columns
-- ----------------------------
DROP TABLE IF EXISTS `columns`;
CREATE TABLE `columns`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `sort` int(11) NOT NULL DEFAULT 0,
  `status` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of columns
-- ----------------------------
INSERT INTO `columns` VALUES (1, '技术', 1, 0);
INSERT INTO `columns` VALUES (2, '问答', 4, 0);
INSERT INTO `columns` VALUES (3, '测试栏', 6, 0);

-- ----------------------------
-- Table structure for friend_links
-- ----------------------------
DROP TABLE IF EXISTS `friend_links`;
CREATE TABLE `friend_links`  (
  `id` bigint(20) UNSIGNED NOT NULL DEFAULT 0,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `status` tinyint(255) NOT NULL DEFAULT 0,
  `created_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updated_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for message_queue
-- ----------------------------
DROP TABLE IF EXISTS `message_queue`;
CREATE TABLE `message_queue`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `sender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '发送人',
  `receiver` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '接收人',
  `type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '类型',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '标题',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '内容',
  `attachment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '附件',
  `status` tinyint(255) NOT NULL DEFAULT 0 COMMENT '状态',
  `fail_number` int(11) NOT NULL DEFAULT 0 COMMENT '失败次数',
  `fail_message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '失败原因',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for oauth_account
-- ----------------------------
DROP TABLE IF EXISTS `oauth_account`;
CREATE TABLE `oauth_account`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL DEFAULT 0,
  `platform` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `openid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `created_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updated_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '第三方授权账户信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for post_auto_draft
-- ----------------------------
DROP TABLE IF EXISTS `post_auto_draft`;
CREATE TABLE `post_auto_draft`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT 0,
  `post_id` int(11) NOT NULL DEFAULT 0,
  `title` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '自动保存的草稿数据' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for post_comment_agree
-- ----------------------------
DROP TABLE IF EXISTS `post_comment_agree`;
CREATE TABLE `post_comment_agree`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) NOT NULL DEFAULT 0 COMMENT '用户Id',
  `post_id` bigint(11) NOT NULL DEFAULT 0,
  `post_comment_id` bigint(11) NOT NULL DEFAULT 0,
  `created_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `post_comment_id`(`post_comment_id`, `user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1337273744463273987 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of post_comment_agree
-- ----------------------------
INSERT INTO `post_comment_agree` VALUES (1337242484147761154, 1336960357791449089, 1336963516165324801, 1336965897653329922, '2020-12-11 11:47:08');
INSERT INTO `post_comment_agree` VALUES (1337273744463273986, 1336960357791449089, 1336963516165324801, 1336965984680943618, '2020-12-11 13:51:21');

-- ----------------------------
-- Table structure for post_comments
-- ----------------------------
DROP TABLE IF EXISTS `post_comments`;
CREATE TABLE `post_comments`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL DEFAULT 0,
  `post_id` bigint(20) NOT NULL DEFAULT 0,
  `parent_id` bigint(20) NOT NULL DEFAULT 0,
  `agree_count` int(11) NOT NULL DEFAULT 0,
  `level` int(11) NOT NULL DEFAULT 1,
  `created_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1336965984680943619 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of post_comments
-- ----------------------------
INSERT INTO `post_comments` VALUES (1336965538293751809, 1336960357791449089, 1336963516165324801, 0, 0, 0, '2020-12-10 17:26:39', '<p>test</p>');
INSERT INTO `post_comments` VALUES (1336965897653329922, 1336960357791449089, 1336963516165324801, 0, 1, 0, '2020-12-10 17:28:04', '<p>test</p>');
INSERT INTO `post_comments` VALUES (1336965984680943618, 1336960357791449089, 1336963516165324801, 1336965538293751809, 1, 0, '2020-12-10 17:28:25', '<p>@<a href=\'/u/1336960357791449089\'>admin</a>  测试@</p>');

-- ----------------------------
-- Table structure for posts
-- ----------------------------
DROP TABLE IF EXISTS `posts`;
CREATE TABLE `posts`  (
  `id` bigint(20) UNSIGNED NOT NULL DEFAULT 0,
  `column_id` int(11) NOT NULL DEFAULT 0,
  `author_id` bigint(11) NOT NULL DEFAULT 0 COMMENT 'users 表Id',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `original_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 0,
  `reply_count` int(11) NOT NULL DEFAULT 0,
  `view_count` int(11) NOT NULL DEFAULT 0,
  `publish_at` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `heat` double(12, 4) NOT NULL DEFAULT 0.0000 COMMENT '热度',
  `created_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_at` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `essence` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否加精',
  `top` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否置顶',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of posts
-- ----------------------------
INSERT INTO `posts` VALUES (1336963516165324801, 1, 1336960357791449089, 'Hello World', '```\necho \"hello world\"；\n```', '<pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"pln\">echo </span><span class=\"str\">\"hello world\"</span><span class=\"pun\">；</span></code></li></ol></pre>', 1, 3, 91, '2020-12-10 17:18:36', 0.5668, '2020-12-10 17:18:36', '2020-12-10 17:18:36', 0, 0);


DROP TABLE IF EXISTS `post_agree`;
CREATE TABLE `post_agree` (
  `id` bigint(20) NOT NULL DEFAULT 0,
  `user_id` bigint(20) NOT NULL DEFAULT 0,
  `post_id` bigint(20) NOT NULL DEFAULT 0,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`),
  UNIQUE KEY `post_id` (`post_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for system_config
-- ----------------------------
DROP TABLE IF EXISTS `system_config`;
CREATE TABLE `system_config`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `attribute` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `created_at` datetime(0) NOT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `updated_at` datetime(0) NOT NULL,
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `attribute_unique`(`attribute`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of system_config
-- ----------------------------
INSERT INTO `system_config` VALUES (1, 'gitee_oauth_client_id', '2020-12-28 13:57:22', '', '2020-12-28 17:17:47', '');
INSERT INTO `system_config` VALUES (2, 'gitee_oauth_client_secret', '2020-12-28 13:57:22', '', '2020-12-28 13:57:22', '');
INSERT INTO `system_config` VALUES (3, 'gitee_oauth_redirect_uri', '2020-12-28 13:57:22', '', '2020-12-28 13:57:22', 'http://localhost:8001/oauth/gitee/callback');
INSERT INTO `system_config` VALUES (4, 'github_oauth_client_id', '2020-12-28 13:57:22', '', '2020-12-28 16:31:21', 'sdfgsg');
INSERT INTO `system_config` VALUES (5, 'github_oauth_client_secret', '2020-12-28 13:57:22', 'github oauth 秘钥', '2020-12-28 17:06:29', 'asdfag');
INSERT INTO `system_config` VALUES (6, 'github_oauth_redirect_uri', '2020-12-28 13:57:22', '', '2020-12-28 13:57:22', '');

-- ----------------------------
-- Table structure for user_message
-- ----------------------------
DROP TABLE IF EXISTS `user_message`;
CREATE TABLE `user_message`  (
  `id` bigint(20) UNSIGNED NOT NULL DEFAULT 0,
  `sender_id` bigint(11) NOT NULL DEFAULT 0,
  `receiver_id` bigint(11) NOT NULL DEFAULT 0,
  `type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `created_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user_collection
-- ----------------------------
DROP TABLE IF EXISTS `user_collection`;
CREATE TABLE `user_collection`  (
  `id` bigint(20) UNSIGNED NOT NULL DEFAULT 0,
  `user_id` bigint(20) NOT NULL DEFAULT 0,
  `post_id` bigint(20) NOT NULL DEFAULT 0,
  `created_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_collection
-- ----------------------------
INSERT INTO `user_collection` VALUES (1336965494991757314, 1336960357791449089, 1336963516165324801, '2020-12-10 17:26:28');

-- ----------------------------
-- Table structure for user_token
-- ----------------------------
DROP TABLE IF EXISTS `user_token`;
CREATE TABLE `user_token`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT 0 COMMENT '用户Id',
  `type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT 'token类型',
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT 'token',
  `created_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `expire_in` bigint(255) NULL DEFAULT 0 COMMENT '过期时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` bigint(20) UNSIGNED NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '密码',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '邮箱',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '名称',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '头像',
  `signature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '清明',
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '城市',
  `experience` int(11) NOT NULL DEFAULT 0 COMMENT '经验值',
  `is_admin` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否管理员',
  `created_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updated_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1336960357791449089, 'admin', '$2a$10$9jA84HsIZjgj58ELhKURCO22SdWImAdwGorUSkwTUUBX/peRNedTG', 'mail@t.com', '', '/static/b1031017-2e3e-4076-93b9-79c490cf4654.png', 'S', 'VB', 0, 0, '2020-12-10 17:35:28', '2020-12-10 17:06:03');


DROP TABLE IF EXISTS `user_follow`;
CREATE TABLE `user_follow` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `follow_user_id` bigint(20) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `user_blacklist`;
CREATE TABLE `user_blacklist` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `black_user_id` bigint(20) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


SET FOREIGN_KEY_CHECKS = 1;
