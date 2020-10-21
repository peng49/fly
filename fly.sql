/*
 Navicat Premium Data Transfer

 Source Server         : vagrant-java(192.168.99.15)
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : 192.168.99.15:3307
 Source Schema         : fly

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 15/08/2020 12:17:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for columns
-- ----------------------------
DROP TABLE IF EXISTS `columns`;
CREATE TABLE `columns`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sort` int(11) NULL DEFAULT 0,
  `status` tinyint(1) NULL default 1 COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for friend_links
-- ----------------------------
DROP TABLE IF EXISTS `friend_links`;
CREATE TABLE `friend_links`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` tinyint(255) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for post_comments
-- ----------------------------
DROP TABLE IF EXISTS `post_comments`;
CREATE TABLE `post_comments`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT 0,
  `post_id` int(11) NULL DEFAULT 0,
  `parent_id` int(11) NULL DEFAULT 0,
  `agree_count` int(11) NULL DEFAULT 0,
  `level` int(11) NULL DEFAULT 1,
  `comment_time` datetime(0) NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

alter table post_comments alter column agree_count set default 0;

DROP TABLE IF EXISTS `post_comment_agree`;
CREATE TABLE `post_comment_agree`
(
  `id`         int(11) NOT NULL AUTO_INCREMENT,
  `user_id`    int(11)  DEFAULT 0 COMMENT '用户Id',
  `post_id`    int(11)  DEFAULT 0,
  `comment_id` int(11)  DEFAULT 0,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for posts
-- ----------------------------
DROP TABLE IF EXISTS `posts`;
CREATE TABLE `posts`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `column_id` int(11) NULL DEFAULT 0,
  `author_id` int(11) NULL DEFAULT 0 COMMENT 'users 表Id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `original_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `status` tinyint(1) NULL DEFAULT 0,
  `reply_count` int(11) NULL DEFAULT 0,
  `view_count` int(11) NULL DEFAULT 0,
  `publish_at` datetime(0) NULL DEFAULT NULL,
  `heat` double(12, 4) NULL DEFAULT 0 COMMENT '热度',
  `created_at` datetime(0) NULL DEFAULT NULL,
  `update_at` datetime(0) NULL DEFAULT NULL,
  `essence` tinyint(1) NULL DEFAULT 0 COMMENT '是否加精',
  `top` tinyint(1) NULL DEFAULT 0 COMMENT '是否置顶',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_message
-- ----------------------------
DROP TABLE IF EXISTS `user_message`;
CREATE TABLE `user_message` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sender_id` int(11) DEFAULT NULL,
  `receiver_id` int(11) NOT NULL,
  `type` varchar(64) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

alter table user_message modify content text null;
-- ----------------------------
-- Table structure for user_posts
-- ----------------------------
DROP TABLE IF EXISTS `user_posts`;
CREATE TABLE `user_posts`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
                         `id` INT ( 11 ) UNSIGNED NOT NULL AUTO_INCREMENT,
                         `username` VARCHAR ( 255 ) DEFAULT NULL COMMENT '用户名',
                         `password` VARCHAR ( 255 ) DEFAULT NULL COMMENT '密码',
                         `email` VARCHAR ( 255 ) DEFAULT NULL COMMENT '邮箱',
                         `name` VARCHAR ( 64 ) DEFAULT NULL COMMENT '名称',
                         `avatar` VARCHAR ( 255 ) DEFAULT NULL COMMENT '头像',
                         `signature` VARCHAR ( 255 ) DEFAULT NULL COMMENT '清明',
                         `city` VARCHAR ( 255 ) DEFAULT NULL COMMENT '城市',
                         `experience` INT ( 11 ) NOT NULL DEFAULT '0' COMMENT '经验值',
                         `is_admin` TINYINT ( 1 ) DEFAULT '0' COMMENT '是否管理员',
                         `created_at` datetime DEFAULT NULL,
                         `updated_at` datetime DEFAULT NULL,
                         PRIMARY KEY ( `id` ) USING BTREE
) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS `message_queue`;
CREATE TABLE `message_queue` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sender` varchar(255) DEFAULT NULL COMMENT '发送人',
  `receiver` varchar(255) NOT NULL COMMENT '接收人',
  `type` varchar(16) DEFAULT NULL COMMENT '类型',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `attachment` varchar(255) DEFAULT NULL COMMENT '附件',
  `status` tinyint(255) DEFAULT 0 COMMENT '状态',
  `fail_number` int(11) DEFAULT 0 COMMENT '失败次数',
  `fail_message` varchar(255) DEFAULT NULL COMMENT '失败原因',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user_token`;
CREATE TABLE `user_token` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户Id',
  `type` varchar(16) DEFAULT NULL COMMENT 'token类型',
  `token` varchar(255) DEFAULT NULL COMMENT 'token',
  `create_at` int(255) DEFAULT NULL COMMENT '创建时间',
  `expire_in` bigint(255) DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `oauth_account`;
CREATE TABLE `oauth_account`
(
    `id`         INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id`    INT(11)          NOT NULL,
    `platform`   VARCHAR(16)      NOT NULL,
    `openid`     VARCHAR(255)     NOT NULL,
    `created_at` datetime DEFAULT NULL,
    `update_at`  datetime DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8 COMMENT = '第三方授权账户信息';

DROP TABLE IF EXISTS `post_auto_draft`;
CREATE TABLE `post_auto_draft` (
                                   `id` int(11) NOT NULL AUTO_INCREMENT,
                                   `user_id` int(11) NOT NULL,
                                   `post_id` int(11) DEFAULT NULL,
                                   `title` varchar(256) DEFAULT NULL,
                                   `content` longtext,
                                   `created_at` datetime DEFAULT NULL,
                                   `update_at` datetime DEFAULT NULL,
                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='自动保存的草稿数据';

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
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_menu
-- ----------------------------
INSERT INTO `admin_menu` VALUES (1, 0, 1, 'Dashboard', 'fa-bar-chart', '/', NULL, NULL, NULL, NULL);
INSERT INTO `admin_menu` VALUES (2, 0, 2, 'Admin', 'fa-tasks', '', NULL, NULL, NULL, NULL);
INSERT INTO `admin_menu` VALUES (3, 2, 3, 'Users', 'fa-users', 'auth/users', NULL, NULL, NULL, NULL);
INSERT INTO `admin_menu` VALUES (4, 2, 4, 'Roles', 'fa-user', 'auth/roles', NULL, NULL, NULL, NULL);
INSERT INTO `admin_menu` VALUES (5, 2, 5, 'Permission', 'fa-ban', 'auth/permissions', NULL, NULL, NULL, NULL);
INSERT INTO `admin_menu` VALUES (6, 2, 6, 'Menu', 'fa-bars', 'auth/menu', NULL, NULL, NULL, NULL);
INSERT INTO `admin_menu` VALUES (7, 2, 7, 'Operation log', 'fa-history', 'auth/logs', NULL, NULL, NULL, NULL);

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
                                      `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
                                      `slug` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
                                      `http_method` varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
                                      `http_path` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
                                      `created_at` timestamp(0) NULL DEFAULT NULL,
                                      `updated_at` timestamp(0) NULL DEFAULT NULL,
                                      PRIMARY KEY (`id`) USING BTREE,
                                      UNIQUE INDEX `admin_permissions_name_unique`(`name`) USING BTREE,
                                      UNIQUE INDEX `admin_permissions_slug_unique`(`slug`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_permissions
-- ----------------------------
INSERT INTO `admin_permissions` VALUES (1, 'All permission', '*', '', '*', NULL, NULL);
INSERT INTO `admin_permissions` VALUES (2, 'Dashboard', 'dashboard', 'GET', '/', NULL, NULL);
INSERT INTO `admin_permissions` VALUES (3, 'Login', 'auth.login', '', '/auth/login\r\n/auth/logout', NULL, NULL);
INSERT INTO `admin_permissions` VALUES (4, 'User setting', 'auth.setting', 'GET,PUT', '/auth/setting', NULL, NULL);
INSERT INTO `admin_permissions` VALUES (5, 'Auth management', 'auth.management', '', '/auth/roles\r\n/auth/permissions\r\n/auth/menu\r\n/auth/logs', NULL, NULL);

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
-- Table structure for admin_role_users
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_users`;
CREATE TABLE `admin_role_users`  (
                                     `role_id` int(11) NOT NULL,
                                     `user_id` int(11) NOT NULL,
                                     `created_at` timestamp(0) NULL DEFAULT NULL,
                                     `updated_at` timestamp(0) NULL DEFAULT NULL,
                                     INDEX `admin_role_users_role_id_user_id_index`(`role_id`, `user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_role_users
-- ----------------------------
INSERT INTO `admin_role_users` VALUES (1, 1, NULL, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_roles
-- ----------------------------
INSERT INTO `admin_roles` VALUES (1, 'Administrator', 'administrator', '2020-10-15 09:55:34', '2020-10-15 09:55:34');

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
#                                            FOREIGN KEY (`role_id`) references admin_roles(`id`) on update cascade on delete cascade,
                                           PRIMARY KEY (`id`) USING BTREE,
                                           INDEX `admin_role_permissions_role_id_permission_id_index`(`role_id`, `permission_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_role_permissions
-- ----------------------------
INSERT INTO `admin_role_permissions` VALUES (1, 1, 1, NULL,NULL);

-- ----------------------------
-- Table structure for admin_user_permissions
-- ----------------------------
DROP TABLE IF EXISTS `admin_user_permissions`;
CREATE TABLE `admin_user_permissions`  (
                                           `user_id` int(11) NOT NULL,
                                           `permission_id` int(11) NOT NULL,
                                           `created_at` timestamp(0) NULL DEFAULT NULL,
                                           `updated_at` timestamp(0) NULL DEFAULT NULL,
                                           INDEX `admin_user_permissions_user_id_permission_id_index`(`user_id`, `permission_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_users
-- ----------------------------
INSERT INTO `admin_users` VALUES (1, 'admin', '$2y$10$ItfvyxAjzmzVFIWCu7T1FOC1jjG1abKu32UkNcrXocHEEqv2zzemG', 'Administrator', NULL, NULL, '2020-10-15 09:55:34', '2020-10-15 09:55:34');


SET FOREIGN_KEY_CHECKS = 1;
