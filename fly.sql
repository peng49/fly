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
CREATE TABLE `users`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `signature` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `experience` int(11) NOT NULL DEFAULT 0 COMMENT '经验值',
  `is_admin` tinyint(1) NULL DEFAULT 0 COMMENT '是否管理员',
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


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

SET FOREIGN_KEY_CHECKS = 1;
