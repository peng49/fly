/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.99.15(vagrant-java docker hello.db)
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : 192.168.99.15:3307
 Source Schema         : fly

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 21/06/2020 18:36:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for posts
-- ----------------------------
DROP TABLE IF EXISTS `posts`;
CREATE TABLE `posts`  (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `column_id` int(11) DEFAULT NULL,
  `author_id` int(11) DEFAULT NULL COMMENT 'users 表Id',
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  `status` tinyint(1) DEFAULT NULL,
  `reply_count` int(11) DEFAULT NULL,
  `view_count` int(11) DEFAULT NULL,
  `publish_at` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `essence` tinyint(1) DEFAULT NULL COMMENT '是否加精',
  `top` tinyint(1) DEFAULT NULL COMMENT '是否置顶',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `post_comments`;
CREATE TABLE `post_comments` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `post_id` int(11) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `agree_count` int(11) DEFAULT NULL,
  `comment_time` datetime DEFAULT NULL,
  `content` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `experience` int(11) NOT NULL DEFAULT '0' COMMENT '经验值',
  `is_admin` tinyint(1) DEFAULT '0' COMMENT '是否管理员',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `user_message`;
CREATE TABLE `user_message` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `type` varchar(64) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `columns`;
CREATE TABLE `columns` (
	`id` INT ( 11 ) UNSIGNED NOT NULL AUTO_INCREMENT,
	`name` VARCHAR ( 255 ) DEFAULT NULL,
	`sort` INT ( 11 ) DEFAULT NULL,
PRIMARY KEY ( `id` )
) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `user_posts`;
CREATE TABLE `user_posts` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
