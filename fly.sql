-- MySQL dump 10.14  Distrib 5.5.65-MariaDB, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: fly
-- ------------------------------------------------------
-- Server version	5.7.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin_menu`
--

DROP TABLE IF EXISTS `admin_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_menu` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL DEFAULT '0',
  `sort` int(11) NOT NULL DEFAULT '0',
  `title` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `icon` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `uri` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `component` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `permission` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_menu`
--

LOCK TABLES `admin_menu` WRITE;
/*!40000 ALTER TABLE `admin_menu` DISABLE KEYS */;
INSERT INTO `admin_menu` VALUES (2,0,2,'Admin','fa-tasks','','','','2020-11-26 17:06:23','2020-12-30 13:19:51'),(3,2,3,'Users','fa-users','auth/users','','','2020-12-30 13:19:51','2020-12-30 13:19:51'),(4,2,4,'Roles','fa-user','auth/roles','','','2020-12-30 13:19:51','2020-12-30 13:19:51'),(5,2,5,'Permission','fa-ban','auth/permissions','','','2020-12-30 13:19:51','2020-12-30 13:19:51'),(6,2,6,'Menu','fa-bars','auth/menu','','','2020-12-30 13:19:51','2020-12-30 13:19:51'),(7,2,7,'Operation log','fa-history','auth/logs','','','2020-12-30 13:19:51','2020-12-30 13:19:51'),(8,0,0,'首页','','11','','','2020-12-30 13:19:51','2020-12-30 13:19:51');
/*!40000 ALTER TABLE `admin_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_operation_log`
--

DROP TABLE IF EXISTS `admin_operation_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_operation_log` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0',
  `path` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `method` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `ip` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `input` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `admin_operation_log_user_id_index` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_operation_log`
--

LOCK TABLES `admin_operation_log` WRITE;
/*!40000 ALTER TABLE `admin_operation_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin_operation_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_permissions`
--

DROP TABLE IF EXISTS `admin_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_permissions` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '上级权限',
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `slug` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `http_method` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `http_path` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `admin_permissions_name_unique` (`name`) USING BTREE,
  UNIQUE KEY `admin_permissions_slug_unique` (`slug`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_permissions`
--

LOCK TABLES `admin_permissions` WRITE;
/*!40000 ALTER TABLE `admin_permissions` DISABLE KEYS */;
INSERT INTO `admin_permissions` VALUES (1,0,'All permission','*','','/**','2020-12-30 13:19:52','2020-12-30 13:19:52'),(2,0,'Dashboard','dashboard','GET','/','2020-12-30 13:19:52','2020-12-30 13:19:52'),(3,0,'Login','auth.login','','/auth/login\r\n/auth/logout','2020-12-30 13:19:52','2020-12-30 13:19:52'),(4,0,'User setting','auth.setting','GET,PUT','/auth/setting','2020-12-30 13:19:52','2020-12-30 13:19:52'),(5,0,'Auth management','auth.management','','/auth/roles\r\n/auth/permissions\r\n/auth/menu\r\n/auth/logs','2020-12-30 13:19:52','2020-12-30 13:19:52'),(6,4,'测试权限','23523235','GET','/users**','2020-12-30 13:19:52','2020-12-30 13:19:52'),(7,4,'是的','sd','GET','sdgdg','2020-12-30 13:19:52','2020-12-30 13:19:52'),(8,2,'sdfgh','asdg','agha','ha','2020-12-30 13:19:52','2020-12-30 13:19:52');
/*!40000 ALTER TABLE `admin_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_role_menu`
--

DROP TABLE IF EXISTS `admin_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_role_menu` (
  `role_id` int(11) NOT NULL DEFAULT '0',
  `menu_id` int(11) NOT NULL DEFAULT '0',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `admin_role_menu_role_id_menu_id_index` (`role_id`,`menu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_role_menu`
--

LOCK TABLES `admin_role_menu` WRITE;
/*!40000 ALTER TABLE `admin_role_menu` DISABLE KEYS */;
INSERT INTO `admin_role_menu` VALUES (1,2,'2020-12-30 13:19:52','2020-12-30 13:19:52');
/*!40000 ALTER TABLE `admin_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_role_permissions`
--

DROP TABLE IF EXISTS `admin_role_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_role_permissions` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL DEFAULT '0',
  `permission_id` int(11) NOT NULL DEFAULT '0',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `admin_role_permissions_role_id_permission_id_index` (`role_id`,`permission_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_role_permissions`
--

LOCK TABLES `admin_role_permissions` WRITE;
/*!40000 ALTER TABLE `admin_role_permissions` DISABLE KEYS */;
INSERT INTO `admin_role_permissions` VALUES (13,1,1,'2020-12-30 13:19:52','2020-12-30 13:19:52'),(14,1,5,'2020-12-30 13:19:52','2020-12-30 13:19:52'),(15,2,1,'2020-12-30 13:19:52','2020-12-30 13:19:52'),(16,2,2,'2020-12-30 13:19:52','2020-12-30 13:19:52'),(17,2,3,'2020-12-30 13:19:52','2020-12-30 13:19:52'),(18,2,4,'2020-12-30 13:19:52','2020-12-30 13:19:52'),(19,2,5,'2020-12-30 13:19:52','2020-12-30 13:19:52'),(20,2,6,'2020-12-30 13:19:52','2020-12-30 13:19:52'),(21,2,7,'2020-12-30 13:19:52','2020-12-30 13:19:52'),(22,2,8,'2020-12-30 13:19:52','2020-12-30 13:19:52');
/*!40000 ALTER TABLE `admin_role_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_role_users`
--

DROP TABLE IF EXISTS `admin_role_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_role_users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL DEFAULT '0',
  `user_id` int(11) NOT NULL DEFAULT '0',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `admin_role_users_role_id_user_id_index` (`role_id`,`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_role_users`
--

LOCK TABLES `admin_role_users` WRITE;
/*!40000 ALTER TABLE `admin_role_users` DISABLE KEYS */;
INSERT INTO `admin_role_users` VALUES (33,1,1,'2020-12-30 13:19:52','2020-12-30 13:19:52'),(34,2,1,'2020-12-30 13:19:52','2020-12-30 13:19:52'),(44,2,2,'2020-12-30 13:19:52','2020-12-30 13:19:52');
/*!40000 ALTER TABLE `admin_role_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_roles`
--

DROP TABLE IF EXISTS `admin_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_roles` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `slug` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `admin_roles_name_unique` (`name`) USING BTREE,
  UNIQUE KEY `admin_roles_slug_unique` (`slug`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_roles`
--

LOCK TABLES `admin_roles` WRITE;
/*!40000 ALTER TABLE `admin_roles` DISABLE KEYS */;
INSERT INTO `admin_roles` VALUES (1,'Administrator','administrator','2020-12-30 13:19:52','2020-12-30 13:19:52'),(2,'test','test23','2020-12-30 13:19:52','2020-12-30 13:19:52');
/*!40000 ALTER TABLE `admin_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_user_permissions`
--

DROP TABLE IF EXISTS `admin_user_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_user_permissions` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0',
  `permission_id` int(11) NOT NULL DEFAULT '0',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `admin_user_permissions_user_id_permission_id_index` (`user_id`,`permission_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=140 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_user_permissions`
--

LOCK TABLES `admin_user_permissions` WRITE;
/*!40000 ALTER TABLE `admin_user_permissions` DISABLE KEYS */;
INSERT INTO `admin_user_permissions` VALUES (105,1,1,'2020-12-30 13:19:52','2020-12-30 13:19:52'),(106,1,2,'2020-12-30 13:19:52','2020-12-30 13:19:52'),(107,1,8,'2020-12-30 13:19:52','2020-12-30 13:19:52'),(108,1,3,'2020-12-30 13:19:52','2020-12-30 13:19:52'),(137,2,3,'2020-12-30 13:19:52','2020-12-30 13:19:52'),(138,2,6,'2020-12-30 13:19:52','2020-12-30 13:19:52'),(139,2,5,'2020-12-30 13:19:52','2020-12-30 13:19:52');
/*!40000 ALTER TABLE `admin_user_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_users`
--

DROP TABLE IF EXISTS `admin_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(190) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `password` varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `avatar` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `remember_token` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `admin_users_username_unique` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_users`
--

LOCK TABLES `admin_users` WRITE;
/*!40000 ALTER TABLE `admin_users` DISABLE KEYS */;
INSERT INTO `admin_users` VALUES (1,'admin','$2a$10$stu3n5ppS2KkPVyjLZrE.eCJ/P4OuoCOZP9ERdQJL633ZtKK2BRi2','dfgd2','','','2020-10-15 09:55:34','2020-10-29 19:46:32'),(2,'test','$2a$10$1Pc9sSC3L70HpWOX/ozL0ellERiPa3GAPJF8smtDfnZkqxgU89LKi','stst','','','2020-12-30 13:19:52','2020-11-02 17:27:01');
/*!40000 ALTER TABLE `admin_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `columns`
--

DROP TABLE IF EXISTS `columns`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `columns` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `sort` int(11) NOT NULL DEFAULT '0',
  `status` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `columns`
--

LOCK TABLES `columns` WRITE;
/*!40000 ALTER TABLE `columns` DISABLE KEYS */;
INSERT INTO `columns` VALUES (1,'技术',1,0),(2,'问答',4,0);
/*!40000 ALTER TABLE `columns` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friend_links`
--

DROP TABLE IF EXISTS `friend_links`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friend_links` (
  `id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `url` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `status` tinyint(255) NOT NULL DEFAULT '0',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friend_links`
--

LOCK TABLES `friend_links` WRITE;
/*!40000 ALTER TABLE `friend_links` DISABLE KEYS */;
/*!40000 ALTER TABLE `friend_links` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message_queue`
--

DROP TABLE IF EXISTS `message_queue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message_queue` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sender` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '发送人',
  `receiver` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '接收人',
  `type` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '类型',
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '标题',
  `content` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '内容',
  `attachment` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '附件',
  `status` tinyint(255) NOT NULL DEFAULT '0' COMMENT '状态',
  `fail_number` int(11) NOT NULL DEFAULT '0' COMMENT '失败次数',
  `fail_message` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '失败原因',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_queue`
--

LOCK TABLES `message_queue` WRITE;
/*!40000 ALTER TABLE `message_queue` DISABLE KEYS */;
/*!40000 ALTER TABLE `message_queue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `navigations`
--

DROP TABLE IF EXISTS `navigations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `navigations` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(256) NOT NULL DEFAULT '' COMMENT '标题',
  `url` varchar(256) NOT NULL DEFAULT '' COMMENT 'Url',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '上级Id',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态 1 可用 0 禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `navigations`
--

LOCK TABLES `navigations` WRITE;
/*!40000 ALTER TABLE `navigations` DISABLE KEYS */;
INSERT INTO `navigations` VALUES (1,'首页','/',1,0,1),(2,'技术','/column/1',2,0,1),(3,'提问','/column/2',2,0,1);
/*!40000 ALTER TABLE `navigations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_account`
--

DROP TABLE IF EXISTS `oauth_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_account` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL DEFAULT '0',
  `platform` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `openid` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT COMMENT='第三方授权账户信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_account`
--

LOCK TABLES `oauth_account` WRITE;
/*!40000 ALTER TABLE `oauth_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `oauth_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_agree`
--

DROP TABLE IF EXISTS `post_agree`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post_agree` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `user_id` bigint(20) NOT NULL DEFAULT '0',
  `post_id` bigint(20) NOT NULL DEFAULT '0',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `post_id` (`post_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_agree`
--

LOCK TABLES `post_agree` WRITE;
/*!40000 ALTER TABLE `post_agree` DISABLE KEYS */;
INSERT INTO `post_agree` VALUES (1345359389030092802,1336960357791449089,1336963516165324704,'2021-01-02 13:20:49'),(1345628173490008066,1336960357791449089,1336963516165324714,'2021-01-03 07:08:52'),(1345659228049690626,1336960357791449089,1336963516165324801,'2021-01-03 09:12:16'),(1345659977823473666,1336960357791449089,1336963516165324715,'2021-01-03 09:15:15');
/*!40000 ALTER TABLE `post_agree` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_auto_draft`
--

DROP TABLE IF EXISTS `post_auto_draft`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post_auto_draft` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0',
  `post_id` int(11) NOT NULL DEFAULT '0',
  `title` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT COMMENT='自动保存的草稿数据';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_auto_draft`
--

LOCK TABLES `post_auto_draft` WRITE;
/*!40000 ALTER TABLE `post_auto_draft` DISABLE KEYS */;
/*!40000 ALTER TABLE `post_auto_draft` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_comment_agree`
--

DROP TABLE IF EXISTS `post_comment_agree`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post_comment_agree` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '用户Id',
  `post_id` bigint(11) NOT NULL DEFAULT '0',
  `post_comment_id` bigint(11) NOT NULL DEFAULT '0',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `post_comment_id` (`post_comment_id`,`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1345659221309444099 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_comment_agree`
--

LOCK TABLES `post_comment_agree` WRITE;
/*!40000 ALTER TABLE `post_comment_agree` DISABLE KEYS */;
INSERT INTO `post_comment_agree` VALUES (1345659167660101633,1336960357791449089,1336963516165324801,1336965538293751809,'2021-01-03 09:12:01'),(1345659221309444098,1336960357791449089,1336963516165324801,1336965984680943618,'2021-01-03 09:12:14');
/*!40000 ALTER TABLE `post_comment_agree` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_comments`
--

DROP TABLE IF EXISTS `post_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post_comments` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL DEFAULT '0',
  `post_id` bigint(20) NOT NULL DEFAULT '0',
  `parent_id` bigint(20) NOT NULL DEFAULT '0',
  `agree_count` int(11) NOT NULL DEFAULT '0',
  `level` int(11) NOT NULL DEFAULT '1',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `content` text COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1336965984680943619 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_comments`
--

LOCK TABLES `post_comments` WRITE;
/*!40000 ALTER TABLE `post_comments` DISABLE KEYS */;
INSERT INTO `post_comments` VALUES (1336965538293751809,1336960357791449089,1336963516165324801,0,1,0,'2020-12-10 17:26:39','<p>test</p>'),(1336965897653329922,1336960357791449089,1336963516165324801,0,0,0,'2020-12-10 17:28:04','<p>test</p>'),(1336965984680943618,1336960357791449089,1336963516165324801,1336965538293751809,1,0,'2020-12-10 17:28:25','<p>@<a href=\'/u/1336960357791449089\'>admin</a>  测试@</p>');
/*!40000 ALTER TABLE `post_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_tags`
--

DROP TABLE IF EXISTS `post_tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post_tags` (
  `id` bigint(20) NOT NULL,
  `post_id` bigint(20) NOT NULL,
  `tag_id` bigint(20) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_tags`
--

LOCK TABLES `post_tags` WRITE;
/*!40000 ALTER TABLE `post_tags` DISABLE KEYS */;
/*!40000 ALTER TABLE `post_tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posts`
--

DROP TABLE IF EXISTS `posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `posts` (
  `id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `column_id` int(11) NOT NULL DEFAULT '0',
  `author_id` bigint(11) NOT NULL DEFAULT '0' COMMENT 'users 表Id',
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `original_content` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `content` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `reply_count` int(11) NOT NULL DEFAULT '0',
  `view_count` int(11) NOT NULL DEFAULT '0',
  `publish_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `heat` double(12,4) NOT NULL DEFAULT '0.0000' COMMENT '热度',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `essence` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否加精',
  `top` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否置顶',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts`
--

LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
INSERT INTO `posts` VALUES (1336963516165324701,1,1336960357791449089,'freemarker if 判断是否为空','1.判断对象不为空\n```java\n<#if user??>\n\n</#if>\n```\n判断对象属性不为空\n```\n<#if (user.name)??>//判断对象属性不为空\n\n</#if>\n```\n2、判断List是不为空\n```\n<#if users?? && (users?size > 0) >\n\n</#if>\n```\n\n','<p>1.判断对象不为空</p>\n<pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code class=\"lang-java\"><span class=\"pun\">&lt;#</span><span class=\"kwd\">if</span><span class=\"pln\"> user</span><span class=\"pun\">??&gt;</span></code></li><li class=\"L1\"><code class=\"lang-java\"></code></li><li class=\"L2\"><code class=\"lang-java\"><span class=\"pun\">&lt;/#</span><span class=\"kwd\">if</span><span class=\"pun\">&gt;</span></code></li></ol></pre>\n<p>判断对象属性不为空</p>\n<pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"pln\">&lt;#if (user.name)?</span><span class=\"pun\">?&gt;</span><span class=\"pln\">//判断对象属性不为空</span></code></li><li class=\"L1\"><code></code></li><li class=\"L2\"><code><span class=\"pln\">&lt;/#if&gt;</span></code></li></ol></pre><p>2、判断List是不为空</p>\n<pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"pln\">&lt;#if users?? &amp;&amp; (users?size &gt; 0) &gt;</span></code></li><li class=\"L1\"><code></code></li><li class=\"L2\"><code><span class=\"pln\">&lt;/#if&gt;</span></code></li></ol></pre>',1,0,550,'2020-08-07 03:58:28',0.0018,'2020-07-29 01:48:01','2020-07-29 01:48:01',0,0),(1336963516165324702,1,1336960357791449089,'使用IDEA进行远程调试jar程序','##### 远程使用特定参数执行jar包\n\n	java -jar -Xdebug -Xrunjdwp:transport=dt_socket,suspend=n,server=y,address=0.0.0.0:5555 app.jar\n\n\n##### 本地连接远程服务器debug端口\n\nIDEA: Edit Configurations -> Remote\n指定 host port 保存\n\n使用debug模式启动，设置断点，开始调试\n\n\n\n','<h5 id=\"h5--jar-\"><a name=\"远程使用特定参数执行jar包\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>远程使用特定参数执行jar包</h5><pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"pln\">java </span><span class=\"pun\">-</span><span class=\"pln\">jar </span><span class=\"pun\">-</span><span class=\"typ\">Xdebug</span><span class=\"pln\"> </span><span class=\"pun\">-</span><span class=\"typ\">Xrunjdwp</span><span class=\"pun\">:</span><span class=\"pln\">transport</span><span class=\"pun\">=</span><span class=\"pln\">dt_socket</span><span class=\"pun\">,</span><span class=\"pln\">suspend</span><span class=\"pun\">=</span><span class=\"pln\">n</span><span class=\"pun\">,</span><span class=\"pln\">server</span><span class=\"pun\">=</span><span class=\"pln\">y</span><span class=\"pun\">,</span><span class=\"pln\">address</span><span class=\"pun\">=</span><span class=\"lit\">0.0</span><span class=\"pun\">.</span><span class=\"lit\">0.0</span><span class=\"pun\">:</span><span class=\"lit\">5555</span><span class=\"pln\"> app</span><span class=\"pun\">.</span><span class=\"pln\">jar</span></code></li></ol></pre><h5 id=\"h5--debug-\"><a name=\"本地连接远程服务器debug端口\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>本地连接远程服务器debug端口</h5><p>IDEA: Edit Configurations -&gt; Remote<br>指定 host port 保存</p>\n<p>使用debug模式启动，设置断点，开始调试</p>\n',1,0,493,'2020-08-07 11:58:47',0.0017,'2020-07-31 06:55:13','2020-07-31 06:55:13',0,0),(1336963516165324703,1,1336960357791449089,'maven打包指定模块命令','	 mvn package -pl moduleName -am -amd -Dmaven.test.skip=true','<pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"pln\"> mvn </span><span class=\"kwd\">package</span><span class=\"pln\"> </span><span class=\"pun\">-</span><span class=\"pln\">pl moduleName </span><span class=\"pun\">-</span><span class=\"pln\">am </span><span class=\"pun\">-</span><span class=\"pln\">amd </span><span class=\"pun\">-</span><span class=\"typ\">Dmaven</span><span class=\"pun\">.</span><span class=\"pln\">test</span><span class=\"pun\">.</span><span class=\"pln\">skip</span><span class=\"pun\">=</span><span class=\"kwd\">true</span></code></li></ol></pre>',1,0,228,'2020-08-08 11:58:59',0.0014,'2020-08-02 09:34:04','2020-08-02 09:34:04',0,0),(1336963516165324704,2,1336960357791449089,'提问测试？','测试内容','<p>测试内容</p>\n',1,0,428,'2020-08-04 11:59:09',0.0016,'2020-08-01 12:47:01','2020-12-07 12:56:32',0,0),(1336963516165324705,1,1336960357791449089,'Mysql创建新用户并授权','##### 1. 创建新用户\n	create user \'username\'@\'%\' identified by \'#password\';\n	\nusername表示用户名, % 表示这个用户可以远程登录,#password是这个新用户的密码\n如果要创建一个只允许本地登录的用户，sql如下：\n\n	create user \'username\'@\'localhost\' identified by \'#password\';\n	\n如果要创建一个只允许本地登录并且密码为空的用户\n\n	create user \'username\'@\'localhost\';\n	\n##### 2. 为新用户授权(指定库的所有权限)\n	grant all privileges on database.* to \'username\'@\'%\';\n	\n只读权限:\n\n	grant  select on database.* to \'username\'@\'%\';\n	\n指定表的只读权限\n\n	grant  select on database.tablename to \'username\'@\'%\';\n	\n读写权限\n	\n	grant  select,update on database.* to \'username\'@\'%\';\n\n\n##### 3.刷新用户权限\n	flush privileges;\n	\n##### 附加内容\n查询赋予用户的权限\n\n	show grants for username;\n','<h5 id=\"h5-1-\"><a name=\"1. 创建新用户\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>1. 创建新用户</h5><pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"pln\">create user </span><span class=\"str\">\'username\'</span><span class=\"pun\">@</span><span class=\"str\">\'%\'</span><span class=\"pln\"> identified </span><span class=\"kwd\">by</span><span class=\"pln\"> </span><span class=\"str\">\'#password\'</span><span class=\"pun\">;</span></code></li></ol></pre><p>username表示用户名, % 表示这个用户可以远程登录,#password是这个新用户的密码<br>如果要创建一个只允许本地登录的用户，sql如下：</p>\n<pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"pln\">create user </span><span class=\"str\">\'username\'</span><span class=\"pun\">@</span><span class=\"str\">\'localhost\'</span><span class=\"pln\"> identified </span><span class=\"kwd\">by</span><span class=\"pln\"> </span><span class=\"str\">\'#password\'</span><span class=\"pun\">;</span></code></li></ol></pre><p>如果要创建一个只允许本地登录并且密码为空的用户</p>\n<pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"pln\">create user </span><span class=\"str\">\'username\'</span><span class=\"pun\">@</span><span class=\"str\">\'localhost\'</span><span class=\"pun\">;</span></code></li></ol></pre><h5 id=\"h5-2-\"><a name=\"2. 为新用户授权(指定库的所有权限)\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>2. 为新用户授权(指定库的所有权限)</h5><pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"pln\">grant all privileges on database</span><span class=\"pun\">.*</span><span class=\"pln\"> to </span><span class=\"str\">\'username\'</span><span class=\"pun\">@</span><span class=\"str\">\'%\'</span><span class=\"pun\">;</span></code></li></ol></pre><p>只读权限:</p>\n<pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"pln\">grant  </span><span class=\"kwd\">select</span><span class=\"pln\"> on database</span><span class=\"pun\">.*</span><span class=\"pln\"> to </span><span class=\"str\">\'username\'</span><span class=\"pun\">@</span><span class=\"str\">\'%\'</span><span class=\"pun\">;</span></code></li></ol></pre><p>指定表的只读权限</p>\n<pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"pln\">grant  </span><span class=\"kwd\">select</span><span class=\"pln\"> on database</span><span class=\"pun\">.</span><span class=\"pln\">tablename to </span><span class=\"str\">\'username\'</span><span class=\"pun\">@</span><span class=\"str\">\'%\'</span><span class=\"pun\">;</span></code></li></ol></pre><p>读写权限</p>\n<pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"pln\">grant  </span><span class=\"kwd\">select</span><span class=\"pun\">,</span><span class=\"pln\">update on database</span><span class=\"pun\">.*</span><span class=\"pln\"> to </span><span class=\"str\">\'username\'</span><span class=\"pun\">@</span><span class=\"str\">\'%\'</span><span class=\"pun\">;</span></code></li></ol></pre><h5 id=\"h5-3-\"><a name=\"3.刷新用户权限\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>3.刷新用户权限</h5><pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"pln\">flush privileges</span><span class=\"pun\">;</span></code></li></ol></pre><h5 id=\"h5-u9644u52A0u5185u5BB9\"><a name=\"附加内容\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>附加内容</h5><p>查询赋予用户的权限</p>\n<pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"pln\">show grants </span><span class=\"kwd\">for</span><span class=\"pln\"> username</span><span class=\"pun\">;</span></code></li></ol></pre>',1,0,326,'2020-08-08 11:59:15',0.0015,'2020-08-05 13:04:45','2020-08-05 13:04:45',0,0),(1336963516165324706,1,1336960357791449089,'postfix发送邮件','##### 1. 安装\n删除sendmail\n> yum remove sendmail\n\n安装postfix mailx\n> yum install postfix mailx -y\n\n##### 2. 配置\n修改postfix配置文件 /etc/postfix/main.cf\n\n修改以下参数\n```\n#设置系统的主机名\nmyhostname = mail.test.com\n#设置域名（此处设置将成为E-mail地址“@”后面的部分）\nmydomain = test.com\n#将发信地址“@”后面的部分设置为域名（非系统主机名)\nmyorigin = $mydomain\n#接受来自所有网络的请求\ninet_interfaces = all\ninet_protocols = ipv4\n#指定发给本地邮件的域名\nmydestination = $myhostname, localhost.$mydomain, localhost, $mydomain\n#指定用户邮箱目录\nhome_mailbox = Maildir/\n```\n\n设置hostname\n\n	echo \"test.com\" > /etc/hostname\n\n##### 3. 启动服务\n\n	 systemctl start postfix\n\n##### 4. 测试发送邮件\n\n	echo \"email content\" | mail -s \"title\" xxxxx@sohu.com\n	\n查看待发送的邮件队列\n>sudo mailq\n\n使用 sudo postsuper -d #{QUEUEID} 删除待发送队列中的邮件  \n>sudo postsuper -d 02A1430CA726\n\n参考：\nhttps://blog.csdn.net/witton/article/details/105882959\nhttps://www.cnblogs.com/zdz8207/p/Linux-postfix-sendmail.html','<h5 id=\"h5-1-\"><a name=\"1. 安装\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>1. 安装</h5><p>删除sendmail</p>\n<blockquote>\n<p>yum remove sendmail</p>\n</blockquote>\n<p>安装postfix mailx</p>\n<blockquote>\n<p>yum install postfix mailx -y</p>\n</blockquote>\n<h5 id=\"h5-2-\"><a name=\"2. 配置\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>2. 配置</h5><p>修改postfix配置文件 /etc/postfix/main.cf</p>\n<p>修改以下参数</p>\n<pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"com\">#设置系统的主机名</span></code></li><li class=\"L1\"><code><span class=\"pln\">myhostname </span><span class=\"pun\">=</span><span class=\"pln\"> mail</span><span class=\"pun\">.</span><span class=\"pln\">test</span><span class=\"pun\">.</span><span class=\"pln\">com</span></code></li><li class=\"L2\"><code><span class=\"com\">#设置域名（此处设置将成为E-mail地址“@”后面的部分）</span></code></li><li class=\"L3\"><code><span class=\"pln\">mydomain </span><span class=\"pun\">=</span><span class=\"pln\"> test</span><span class=\"pun\">.</span><span class=\"pln\">com</span></code></li><li class=\"L4\"><code><span class=\"com\">#将发信地址“@”后面的部分设置为域名（非系统主机名)</span></code></li><li class=\"L5\"><code><span class=\"pln\">myorigin </span><span class=\"pun\">=</span><span class=\"pln\"> $mydomain</span></code></li><li class=\"L6\"><code><span class=\"com\">#接受来自所有网络的请求</span></code></li><li class=\"L7\"><code><span class=\"pln\">inet_interfaces </span><span class=\"pun\">=</span><span class=\"pln\"> all</span></code></li><li class=\"L8\"><code><span class=\"pln\">inet_protocols </span><span class=\"pun\">=</span><span class=\"pln\"> ipv4</span></code></li><li class=\"L9\"><code><span class=\"com\">#指定发给本地邮件的域名</span></code></li><li class=\"L0\"><code><span class=\"pln\">mydestination </span><span class=\"pun\">=</span><span class=\"pln\"> $myhostname</span><span class=\"pun\">,</span><span class=\"pln\"> localhost</span><span class=\"pun\">.</span><span class=\"pln\">$mydomain</span><span class=\"pun\">,</span><span class=\"pln\"> localhost</span><span class=\"pun\">,</span><span class=\"pln\"> $mydomain</span></code></li><li class=\"L1\"><code><span class=\"com\">#指定用户邮箱目录</span></code></li><li class=\"L2\"><code><span class=\"pln\">home_mailbox </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"typ\">Maildir</span><span class=\"pun\">/</span></code></li></ol></pre><p>设置hostname</p>\n<pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"pln\">echo </span><span class=\"str\">\"test.com\"</span><span class=\"pln\"> </span><span class=\"pun\">&gt;</span><span class=\"pln\"> </span><span class=\"str\">/etc/</span><span class=\"pln\">hostname</span></code></li></ol></pre><h5 id=\"h5-3-\"><a name=\"3. 启动服务\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>3. 启动服务</h5><pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"pln\"> systemctl start postfix</span></code></li></ol></pre><h5 id=\"h5-4-\"><a name=\"4. 测试发送邮件\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>4. 测试发送邮件</h5><pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"pln\">echo </span><span class=\"str\">\"email content\"</span><span class=\"pln\"> </span><span class=\"pun\">|</span><span class=\"pln\"> mail </span><span class=\"pun\">-</span><span class=\"pln\">s </span><span class=\"str\">\"title\"</span><span class=\"pln\"> xxxxx@sohu</span><span class=\"pun\">.</span><span class=\"pln\">com</span></code></li></ol></pre><p>查看待发送的邮件队列</p>\n<blockquote>\n<p>sudo mailq</p>\n</blockquote>\n<p>使用 sudo postsuper -d #{QUEUEID} 删除待发送队列中的邮件  </p>\n<blockquote>\n<p>sudo postsuper -d 02A1430CA726</p>\n</blockquote>\n<p>参考：<br><a href=\"https://blog.csdn.net/witton/article/details/105882959\">https://blog.csdn.net/witton/article/details/105882959</a><br><a href=\"https://www.cnblogs.com/zdz8207/p/Linux-postfix-sendmail.html\">https://www.cnblogs.com/zdz8207/p/Linux-postfix-sendmail.html</a></p>\n',0,1,125,'2020-08-22 11:33:06',0.1960,'2020-08-22 03:33:06','2020-12-26 15:34:59',0,0),(1336963516165324707,1,1336960357791449089,'JS复制指定div中的内容','```js\n// 1 获取需要复制的div的dom对象\nlet div = document.getElementById(\"id\");\n\n//2  选中div中的内容\nlet range;\nif (document.body.createTextRange) { //$.browser.msie\n	range = document.body.createTextRange();\n    range.moveToElementText(div);\n	range.select();\n} else if (document.createRange) { //$.browser.mozilla || $.browser.opera\n    let selection = window.getSelection();\n    range = document.createRange();\n    range.selectNodeContents(div);\n    selection.removeAllRanges();\n    selection.addRange(range);\n} else if (window.getSelection && window.getSelection().setBaseAndExtent) { //$.browser.safari\n    let selection = window.getSelection();\n    selection.setBaseAndExtent(div, 0, div, 1);\n}\n\n//3  执行Copy命令复制内容\ndocument.execCommand(\"Copy\");\n\n//4 取消选中（可选）\nwindow.getSelection().empty();\n```','<pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code class=\"lang-js\"><span class=\"com\">// 1 获取需要复制的div的dom对象</span></code></li><li class=\"L1\"><code class=\"lang-js\"><span class=\"pln\">let div </span><span class=\"pun\">=</span><span class=\"pln\"> document</span><span class=\"pun\">.</span><span class=\"pln\">getElementById</span><span class=\"pun\">(</span><span class=\"str\">\"id\"</span><span class=\"pun\">);</span></code></li><li class=\"L2\"><code class=\"lang-js\"></code></li><li class=\"L3\"><code class=\"lang-js\"><span class=\"com\">//2  选中div中的内容</span></code></li><li class=\"L4\"><code class=\"lang-js\"><span class=\"pln\">let range</span><span class=\"pun\">;</span></code></li><li class=\"L5\"><code class=\"lang-js\"><span class=\"kwd\">if</span><span class=\"pln\"> </span><span class=\"pun\">(</span><span class=\"pln\">document</span><span class=\"pun\">.</span><span class=\"pln\">body</span><span class=\"pun\">.</span><span class=\"pln\">createTextRange</span><span class=\"pun\">)</span><span class=\"pln\"> </span><span class=\"pun\">{</span><span class=\"pln\"> </span><span class=\"com\">//$.browser.msie</span></code></li><li class=\"L6\"><code class=\"lang-js\"><span class=\"pln\">    range </span><span class=\"pun\">=</span><span class=\"pln\"> document</span><span class=\"pun\">.</span><span class=\"pln\">body</span><span class=\"pun\">.</span><span class=\"pln\">createTextRange</span><span class=\"pun\">();</span></code></li><li class=\"L7\"><code class=\"lang-js\"><span class=\"pln\">    range</span><span class=\"pun\">.</span><span class=\"pln\">moveToElementText</span><span class=\"pun\">(</span><span class=\"pln\">div</span><span class=\"pun\">);</span></code></li><li class=\"L8\"><code class=\"lang-js\"><span class=\"pln\">    range</span><span class=\"pun\">.</span><span class=\"pln\">select</span><span class=\"pun\">();</span></code></li><li class=\"L9\"><code class=\"lang-js\"><span class=\"pun\">}</span><span class=\"pln\"> </span><span class=\"kwd\">else</span><span class=\"pln\"> </span><span class=\"kwd\">if</span><span class=\"pln\"> </span><span class=\"pun\">(</span><span class=\"pln\">document</span><span class=\"pun\">.</span><span class=\"pln\">createRange</span><span class=\"pun\">)</span><span class=\"pln\"> </span><span class=\"pun\">{</span><span class=\"pln\"> </span><span class=\"com\">//$.browser.mozilla || $.browser.opera</span></code></li><li class=\"L0\"><code class=\"lang-js\"><span class=\"pln\">    let selection </span><span class=\"pun\">=</span><span class=\"pln\"> window</span><span class=\"pun\">.</span><span class=\"pln\">getSelection</span><span class=\"pun\">();</span></code></li><li class=\"L1\"><code class=\"lang-js\"><span class=\"pln\">    range </span><span class=\"pun\">=</span><span class=\"pln\"> document</span><span class=\"pun\">.</span><span class=\"pln\">createRange</span><span class=\"pun\">();</span></code></li><li class=\"L2\"><code class=\"lang-js\"><span class=\"pln\">    range</span><span class=\"pun\">.</span><span class=\"pln\">selectNodeContents</span><span class=\"pun\">(</span><span class=\"pln\">div</span><span class=\"pun\">);</span></code></li><li class=\"L3\"><code class=\"lang-js\"><span class=\"pln\">    selection</span><span class=\"pun\">.</span><span class=\"pln\">removeAllRanges</span><span class=\"pun\">();</span></code></li><li class=\"L4\"><code class=\"lang-js\"><span class=\"pln\">    selection</span><span class=\"pun\">.</span><span class=\"pln\">addRange</span><span class=\"pun\">(</span><span class=\"pln\">range</span><span class=\"pun\">);</span></code></li><li class=\"L5\"><code class=\"lang-js\"><span class=\"pun\">}</span><span class=\"pln\"> </span><span class=\"kwd\">else</span><span class=\"pln\"> </span><span class=\"kwd\">if</span><span class=\"pln\"> </span><span class=\"pun\">(</span><span class=\"pln\">window</span><span class=\"pun\">.</span><span class=\"pln\">getSelection </span><span class=\"pun\">&amp;&amp;</span><span class=\"pln\"> window</span><span class=\"pun\">.</span><span class=\"pln\">getSelection</span><span class=\"pun\">().</span><span class=\"pln\">setBaseAndExtent</span><span class=\"pun\">)</span><span class=\"pln\"> </span><span class=\"pun\">{</span><span class=\"pln\"> </span><span class=\"com\">//$.browser.safari</span></code></li><li class=\"L6\"><code class=\"lang-js\"><span class=\"pln\">    let selection </span><span class=\"pun\">=</span><span class=\"pln\"> window</span><span class=\"pun\">.</span><span class=\"pln\">getSelection</span><span class=\"pun\">();</span></code></li><li class=\"L7\"><code class=\"lang-js\"><span class=\"pln\">    selection</span><span class=\"pun\">.</span><span class=\"pln\">setBaseAndExtent</span><span class=\"pun\">(</span><span class=\"pln\">div</span><span class=\"pun\">,</span><span class=\"pln\"> </span><span class=\"lit\">0</span><span class=\"pun\">,</span><span class=\"pln\"> div</span><span class=\"pun\">,</span><span class=\"pln\"> </span><span class=\"lit\">1</span><span class=\"pun\">);</span></code></li><li class=\"L8\"><code class=\"lang-js\"><span class=\"pun\">}</span></code></li><li class=\"L9\"><code class=\"lang-js\"></code></li><li class=\"L0\"><code class=\"lang-js\"><span class=\"com\">//3  执行Copy命令复制内容</span></code></li><li class=\"L1\"><code class=\"lang-js\"><span class=\"pln\">document</span><span class=\"pun\">.</span><span class=\"pln\">execCommand</span><span class=\"pun\">(</span><span class=\"str\">\"Copy\"</span><span class=\"pun\">);</span></code></li><li class=\"L2\"><code class=\"lang-js\"></code></li><li class=\"L3\"><code class=\"lang-js\"><span class=\"com\">//4 取消选中（可选）</span></code></li><li class=\"L4\"><code class=\"lang-js\"><span class=\"pln\">window</span><span class=\"pun\">.</span><span class=\"pln\">getSelection</span><span class=\"pun\">().</span><span class=\"pln\">empty</span><span class=\"pun\">();</span></code></li></ol></pre>\n',1,0,139,'2020-08-08 11:59:26',0.0013,'2020-08-06 03:33:13','2020-08-06 03:33:13',0,0),(1336963516165324708,1,1336960357791449089,'gogs+drone搭建一个CI/CD平台','环境准备\ndocker\nMysql\n\n##### 1. 下载镜像\n	docker pull gogs/gogs\n	docker pull drone/drone\n	\n##### 2. 初始化环境\n\n运行gogs容器\n\n	docker run --name=gogs -p 10022:22 -p 3000:3000 -v /tmp/gogs:/data -d gogs/gogs\n\n初始化gogs\n\n启动Drone Server\n```\ndocker run \\\n  --volume=/opt/docker/drone:/data \\\n  --env=DRONE_AGENTS_ENABLED=true \\\n  --env=DRONE_GOGS_SERVER=http://192.168.99.15:3000 \\\n  --env=DRONE_RPC_SECRET=drone@secret \\\n  --env=DRONE_SERVER_HOST=192.168.99.15:8080 \\\n  --env=DRONE_SERVER_PROTO=http \\\n  --publish=8080:80 \\\n  --publish=4430:443 \\\n  --restart=always \\\n  --detach=true \\\n  --name=drone \\\n  drone/drone\n```\n\n启动Drone runner\n```\ndocker run -d \\\n  -v /run/docker.sock:/var/run/docker.sock \\\n  -e DRONE_RPC_PROTO=http \\\n  -e DRONE_RPC_HOST=192.168.99.15:8080 \\\n  -e DRONE_RPC_SECRET=drone@secret \\\n  -e DRONE_RUNNER_CAPACITY=2 \\\n  -e DRONE_RUNNER_NAME=${HOSTNAME} \\\n  -e DRONE_ADMIN=gogs \\\n  -p 3000:3000 \\\n  --restart always \\\n  --name runner \\\n  drone/drone-runner-docker\n```\n\n\n\n','<p>环境准备<br>docker<br>Mysql</p>\n<h5 id=\"h5-1-\"><a name=\"1. 下载镜像\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>1. 下载镜像</h5><pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"pln\">docker pull gogs</span><span class=\"pun\">/</span><span class=\"pln\">gogs</span></code></li><li class=\"L1\"><code><span class=\"pln\">docker pull drone</span><span class=\"pun\">/</span><span class=\"pln\">drone</span></code></li></ol></pre><h5 id=\"h5-2-\"><a name=\"2. 初始化环境\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>2. 初始化环境</h5><p>运行gogs容器</p>\n<pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"pln\">docker run </span><span class=\"pun\">--</span><span class=\"pln\">name</span><span class=\"pun\">=</span><span class=\"pln\">gogs </span><span class=\"pun\">-</span><span class=\"pln\">p </span><span class=\"lit\">10022</span><span class=\"pun\">:</span><span class=\"lit\">22</span><span class=\"pln\"> </span><span class=\"pun\">-</span><span class=\"pln\">p </span><span class=\"lit\">3000</span><span class=\"pun\">:</span><span class=\"lit\">3000</span><span class=\"pln\"> </span><span class=\"pun\">-</span><span class=\"pln\">v </span><span class=\"pun\">/</span><span class=\"pln\">tmp</span><span class=\"pun\">/</span><span class=\"pln\">gogs</span><span class=\"pun\">:</span><span class=\"str\">/data -d gogs/</span><span class=\"pln\">gogs</span></code></li></ol></pre><p>初始化gogs</p>\n<p>启动Drone Server</p>\n<pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"pln\">docker run \\</span></code></li><li class=\"L1\"><code><span class=\"pln\">  </span><span class=\"pun\">--</span><span class=\"pln\">volume</span><span class=\"pun\">=</span><span class=\"str\">/opt/</span><span class=\"pln\">docker</span><span class=\"pun\">/</span><span class=\"pln\">drone</span><span class=\"pun\">:/</span><span class=\"pln\">data \\</span></code></li><li class=\"L2\"><code><span class=\"pln\">  </span><span class=\"pun\">--</span><span class=\"pln\">env</span><span class=\"pun\">=</span><span class=\"pln\">DRONE_AGENTS_ENABLED</span><span class=\"pun\">=</span><span class=\"kwd\">true</span><span class=\"pln\"> \\</span></code></li><li class=\"L3\"><code><span class=\"pln\">  </span><span class=\"pun\">--</span><span class=\"pln\">env</span><span class=\"pun\">=</span><span class=\"pln\">DRONE_GOGS_SERVER</span><span class=\"pun\">=</span><span class=\"pln\">http</span><span class=\"pun\">:</span><span class=\"com\">//192.168.99.15:3000 \\</span></code></li><li class=\"L4\"><code><span class=\"pln\">  </span><span class=\"pun\">--</span><span class=\"pln\">env</span><span class=\"pun\">=</span><span class=\"pln\">DRONE_RPC_SECRET</span><span class=\"pun\">=</span><span class=\"pln\">drone@secret \\</span></code></li><li class=\"L5\"><code><span class=\"pln\">  </span><span class=\"pun\">--</span><span class=\"pln\">env</span><span class=\"pun\">=</span><span class=\"pln\">DRONE_SERVER_HOST</span><span class=\"pun\">=</span><span class=\"lit\">192.168</span><span class=\"pun\">.</span><span class=\"lit\">99.15</span><span class=\"pun\">:</span><span class=\"lit\">8080</span><span class=\"pln\"> \\</span></code></li><li class=\"L6\"><code><span class=\"pln\">  </span><span class=\"pun\">--</span><span class=\"pln\">env</span><span class=\"pun\">=</span><span class=\"pln\">DRONE_SERVER_PROTO</span><span class=\"pun\">=</span><span class=\"pln\">http \\</span></code></li><li class=\"L7\"><code><span class=\"pln\">  </span><span class=\"pun\">--</span><span class=\"pln\">publish</span><span class=\"pun\">=</span><span class=\"lit\">8080</span><span class=\"pun\">:</span><span class=\"lit\">80</span><span class=\"pln\"> \\</span></code></li><li class=\"L8\"><code><span class=\"pln\">  </span><span class=\"pun\">--</span><span class=\"pln\">publish</span><span class=\"pun\">=</span><span class=\"lit\">4430</span><span class=\"pun\">:</span><span class=\"lit\">443</span><span class=\"pln\"> \\</span></code></li><li class=\"L9\"><code><span class=\"pln\">  </span><span class=\"pun\">--</span><span class=\"pln\">restart</span><span class=\"pun\">=</span><span class=\"pln\">always \\</span></code></li><li class=\"L0\"><code><span class=\"pln\">  </span><span class=\"pun\">--</span><span class=\"pln\">detach</span><span class=\"pun\">=</span><span class=\"kwd\">true</span><span class=\"pln\"> \\</span></code></li><li class=\"L1\"><code><span class=\"pln\">  </span><span class=\"pun\">--</span><span class=\"pln\">name</span><span class=\"pun\">=</span><span class=\"pln\">drone \\</span></code></li><li class=\"L2\"><code><span class=\"pln\">  drone</span><span class=\"pun\">/</span><span class=\"pln\">drone</span></code></li></ol></pre><p>启动Drone runner</p>\n<pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"pln\">docker run </span><span class=\"pun\">-</span><span class=\"pln\">d \\</span></code></li><li class=\"L1\"><code><span class=\"pln\">  </span><span class=\"pun\">-</span><span class=\"pln\">v </span><span class=\"pun\">/</span><span class=\"pln\">run</span><span class=\"pun\">/</span><span class=\"pln\">docker</span><span class=\"pun\">.</span><span class=\"pln\">sock</span><span class=\"pun\">:</span><span class=\"str\">/var/</span><span class=\"pln\">run</span><span class=\"pun\">/</span><span class=\"pln\">docker</span><span class=\"pun\">.</span><span class=\"pln\">sock \\</span></code></li><li class=\"L2\"><code><span class=\"pln\">  </span><span class=\"pun\">-</span><span class=\"pln\">e DRONE_RPC_PROTO</span><span class=\"pun\">=</span><span class=\"pln\">http \\</span></code></li><li class=\"L3\"><code><span class=\"pln\">  </span><span class=\"pun\">-</span><span class=\"pln\">e DRONE_RPC_HOST</span><span class=\"pun\">=</span><span class=\"lit\">192.168</span><span class=\"pun\">.</span><span class=\"lit\">99.15</span><span class=\"pun\">:</span><span class=\"lit\">8080</span><span class=\"pln\"> \\</span></code></li><li class=\"L4\"><code><span class=\"pln\">  </span><span class=\"pun\">-</span><span class=\"pln\">e DRONE_RPC_SECRET</span><span class=\"pun\">=</span><span class=\"pln\">drone@secret \\</span></code></li><li class=\"L5\"><code><span class=\"pln\">  </span><span class=\"pun\">-</span><span class=\"pln\">e DRONE_RUNNER_CAPACITY</span><span class=\"pun\">=</span><span class=\"lit\">2</span><span class=\"pln\"> \\</span></code></li><li class=\"L6\"><code><span class=\"pln\">  </span><span class=\"pun\">-</span><span class=\"pln\">e DRONE_RUNNER_NAME</span><span class=\"pun\">=</span><span class=\"pln\">$</span><span class=\"pun\">{</span><span class=\"pln\">HOSTNAME</span><span class=\"pun\">}</span><span class=\"pln\"> \\</span></code></li><li class=\"L7\"><code><span class=\"pln\">  </span><span class=\"pun\">-</span><span class=\"pln\">e DRONE_ADMIN</span><span class=\"pun\">=</span><span class=\"pln\">gogs \\</span></code></li><li class=\"L8\"><code><span class=\"pln\">  </span><span class=\"pun\">-</span><span class=\"pln\">p </span><span class=\"lit\">3000</span><span class=\"pun\">:</span><span class=\"lit\">3000</span><span class=\"pln\"> \\</span></code></li><li class=\"L9\"><code><span class=\"pln\">  </span><span class=\"pun\">--</span><span class=\"pln\">restart always \\</span></code></li><li class=\"L0\"><code><span class=\"pln\">  </span><span class=\"pun\">--</span><span class=\"pln\">name runner \\</span></code></li><li class=\"L1\"><code><span class=\"pln\">  drone</span><span class=\"pun\">/</span><span class=\"pln\">drone</span><span class=\"pun\">-</span><span class=\"pln\">runner</span><span class=\"pun\">-</span><span class=\"pln\">docker</span></code></li></ol></pre>',0,0,112,'2020-08-23 13:26:35',0.3637,'2020-08-23 05:26:35','2020-08-23 05:26:35',0,0),(1336963516165324709,1,1336960357791449089,'jvm介绍','#### 程序计数器\n###### 作用\n记住jvm下一个指令的地址（通过寄存器实现）\n\n###### 特点\n线程私有\n没有内存溢出\n\n#### 栈（先进后出）\n1. 每个线程运行时所需要的内存，称为虚拟机栈\n2. 每个栈有过个**栈帧**(每个方法运行时需要的内存)组成，对应着每次方法调用时所占用的内存\n3. 每个线程只能有一个活动栈帧，对应着当前正在执行的那个方法\n','<h4 id=\"h4-u7A0Bu5E8Fu8BA1u6570u5668\"><a name=\"程序计数器\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>程序计数器</h4><h6 id=\"h6-u4F5Cu7528\"><a name=\"作用\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>作用</h6><p>记住jvm下一个指令的地址（通过寄存器实现）</p>\n<h6 id=\"h6-u7279u70B9\"><a name=\"特点\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>特点</h6><p>线程私有<br>没有内存溢出</p>\n<h4 id=\"h4--\"><a name=\"栈（先进后出）\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>栈（先进后出）</h4><ol>\n<li>每个线程运行时所需要的内存，称为虚拟机栈</li><li>每个栈有过个<strong>栈帧</strong>(每个方法运行时需要的内存)组成，对应着每次方法调用时所占用的内存</li><li>每个线程只能有一个活动栈帧，对应着当前正在执行的那个方法</li></ol>\n',0,0,5,'2020-12-30 13:27:23',0.0000,'2020-08-30 14:08:35','2020-08-30 14:08:35',0,0),(1336963516165324710,1,1336960357791449089,'java的两种动态代理技术','#### 基于jdk的动态代理\n\n##### 1. 新建一个接口 TargetInterface\n```\ninterface TargetInterface{\n	public void save();\n}\n```\n\n##### 2. 新建一个Target类实现TargetInterface\n```\nclass Target implement TargetInterface{\n	public void save(){\n		System.out.println(\"save running\");\n	}\n}\n```\n\n##### 3. 创建一个增强类\n```\nclass Advice{\n	public void before()\n	{\n		System.out.println(\"save before running\");\n	}\n	\n	public void afterReturn(){\n		System.out.println(\"save after running\");\n	}\n}\n```\n\n##### 4. 测试运行\n```\n		Target target = new Target();\n		\n		TargetInterface o = (TargetInterface) Proxy.newProxyInstance(\n                Target.class.getClassLoader(),\n                Target.class.getInterfaces(),\n                new InvocationHandler() {\n                    @Override\n                    public Object invoke(Object proxy, Method method, Object[] args1) throws Throwable {\n                        System.out.println(\"proxy before\");\n                        Object invoke = method.invoke(target, args1);\n                        System.out.println(\"proxy after\");\n                        return invoke;\n                    }\n                });\n        o.method();\n```\n\n\n\n#### 基于cglib的动态代理\n\n##### 1. 新建一个Target类\n```\nclass Target{\n	public void save(){\n		System.out.println(\"save running\");\n	}\n}\n```\n\n##### 2. 创建一个增强类\n```\nclass Advice{\n	public void before()\n	{\n		System.out.println(\"save before running\");\n	}\n	\n	public void afterReturn(){\n		System.out.println(\"save after running\");\n	}\n}\n```\n\n##### 3. 创建一个测试类\n```\nclass CglibProxyTest{\n	public static void main(String[] args){\n		//目标对象\n		final Target target = new Target();\n		\n		final Advice advice = new Advice();\n		\n		//1.基于cglib生成动态代理对象\n		Enhancer enhancer = new Enhancer();\n		//2.设置目录类为父类\n		enhancer.setSuperclass(Target.class);\n		//3.设置回调\n		enhancer.setCallback(new MethodInterceptor(){\n 			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {\n				advice.before();\n				Object object=proxy.invokeSuper(obj, args);\n				advice.afterReturn();\n				return object;\n			}\n		});\n		\n		//4.创建代理对象\n		Target o = (Target)enhancer.create();\n		\n		o.save();\n	}\n}\n```\n\n\n\n\n\n\n\n\n\n\n','<h4 id=\"h4--jdk-\"><a name=\"基于jdk的动态代理\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>基于jdk的动态代理</h4><h5 id=\"h5-1-targetinterface\"><a name=\"1. 新建一个接口 TargetInterface\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>1. 新建一个接口 TargetInterface</h5><pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"kwd\">interface</span><span class=\"pln\"> </span><span class=\"typ\">TargetInterface</span><span class=\"pun\">{</span></code></li><li class=\"L1\"><code><span class=\"pln\">    </span><span class=\"kwd\">public</span><span class=\"pln\"> </span><span class=\"kwd\">void</span><span class=\"pln\"> save</span><span class=\"pun\">();</span></code></li><li class=\"L2\"><code><span class=\"pun\">}</span></code></li></ol></pre><h5 id=\"h5-2-target-targetinterface\"><a name=\"2. 新建一个Target类实现TargetInterface\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>2. 新建一个Target类实现TargetInterface</h5><pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"kwd\">class</span><span class=\"pln\"> </span><span class=\"typ\">Target</span><span class=\"pln\"> implement </span><span class=\"typ\">TargetInterface</span><span class=\"pun\">{</span></code></li><li class=\"L1\"><code><span class=\"pln\">    </span><span class=\"kwd\">public</span><span class=\"pln\"> </span><span class=\"kwd\">void</span><span class=\"pln\"> save</span><span class=\"pun\">(){</span></code></li><li class=\"L2\"><code><span class=\"pln\">        </span><span class=\"typ\">System</span><span class=\"pun\">.</span><span class=\"kwd\">out</span><span class=\"pun\">.</span><span class=\"pln\">println</span><span class=\"pun\">(</span><span class=\"str\">\"save running\"</span><span class=\"pun\">);</span></code></li><li class=\"L3\"><code><span class=\"pln\">    </span><span class=\"pun\">}</span></code></li><li class=\"L4\"><code><span class=\"pun\">}</span></code></li></ol></pre><h5 id=\"h5-3-\"><a name=\"3. 创建一个增强类\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>3. 创建一个增强类</h5><pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"kwd\">class</span><span class=\"pln\"> </span><span class=\"typ\">Advice</span><span class=\"pun\">{</span></code></li><li class=\"L1\"><code><span class=\"pln\">    </span><span class=\"kwd\">public</span><span class=\"pln\"> </span><span class=\"kwd\">void</span><span class=\"pln\"> before</span><span class=\"pun\">()</span></code></li><li class=\"L2\"><code><span class=\"pln\">    </span><span class=\"pun\">{</span></code></li><li class=\"L3\"><code><span class=\"pln\">        </span><span class=\"typ\">System</span><span class=\"pun\">.</span><span class=\"kwd\">out</span><span class=\"pun\">.</span><span class=\"pln\">println</span><span class=\"pun\">(</span><span class=\"str\">\"save before running\"</span><span class=\"pun\">);</span></code></li><li class=\"L4\"><code><span class=\"pln\">    </span><span class=\"pun\">}</span></code></li><li class=\"L5\"><code></code></li><li class=\"L6\"><code><span class=\"pln\">    </span><span class=\"kwd\">public</span><span class=\"pln\"> </span><span class=\"kwd\">void</span><span class=\"pln\"> afterReturn</span><span class=\"pun\">(){</span></code></li><li class=\"L7\"><code><span class=\"pln\">        </span><span class=\"typ\">System</span><span class=\"pun\">.</span><span class=\"kwd\">out</span><span class=\"pun\">.</span><span class=\"pln\">println</span><span class=\"pun\">(</span><span class=\"str\">\"save after running\"</span><span class=\"pun\">);</span></code></li><li class=\"L8\"><code><span class=\"pln\">    </span><span class=\"pun\">}</span></code></li><li class=\"L9\"><code><span class=\"pun\">}</span></code></li></ol></pre><h5 id=\"h5-4-\"><a name=\"4. 测试运行\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>4. 测试运行</h5><pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"pln\">        </span><span class=\"typ\">Target</span><span class=\"pln\"> target </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"kwd\">new</span><span class=\"pln\"> </span><span class=\"typ\">Target</span><span class=\"pun\">();</span></code></li><li class=\"L1\"><code></code></li><li class=\"L2\"><code><span class=\"pln\">        </span><span class=\"typ\">TargetInterface</span><span class=\"pln\"> o </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"pun\">(</span><span class=\"typ\">TargetInterface</span><span class=\"pun\">)</span><span class=\"pln\"> </span><span class=\"typ\">Proxy</span><span class=\"pun\">.</span><span class=\"pln\">newProxyInstance</span><span class=\"pun\">(</span></code></li><li class=\"L3\"><code><span class=\"pln\">                </span><span class=\"typ\">Target</span><span class=\"pun\">.</span><span class=\"kwd\">class</span><span class=\"pun\">.</span><span class=\"pln\">getClassLoader</span><span class=\"pun\">(),</span></code></li><li class=\"L4\"><code><span class=\"pln\">                </span><span class=\"typ\">Target</span><span class=\"pun\">.</span><span class=\"kwd\">class</span><span class=\"pun\">.</span><span class=\"pln\">getInterfaces</span><span class=\"pun\">(),</span></code></li><li class=\"L5\"><code><span class=\"pln\">                </span><span class=\"kwd\">new</span><span class=\"pln\"> </span><span class=\"typ\">InvocationHandler</span><span class=\"pun\">()</span><span class=\"pln\"> </span><span class=\"pun\">{</span></code></li><li class=\"L6\"><code><span class=\"pln\">                    </span><span class=\"lit\">@Override</span></code></li><li class=\"L7\"><code><span class=\"pln\">                    </span><span class=\"kwd\">public</span><span class=\"pln\"> </span><span class=\"typ\">Object</span><span class=\"pln\"> invoke</span><span class=\"pun\">(</span><span class=\"typ\">Object</span><span class=\"pln\"> proxy</span><span class=\"pun\">,</span><span class=\"pln\"> </span><span class=\"typ\">Method</span><span class=\"pln\"> method</span><span class=\"pun\">,</span><span class=\"pln\"> </span><span class=\"typ\">Object</span><span class=\"pun\">[]</span><span class=\"pln\"> args1</span><span class=\"pun\">)</span><span class=\"pln\"> </span><span class=\"kwd\">throws</span><span class=\"pln\"> </span><span class=\"typ\">Throwable</span><span class=\"pln\"> </span><span class=\"pun\">{</span></code></li><li class=\"L8\"><code><span class=\"pln\">                        </span><span class=\"typ\">System</span><span class=\"pun\">.</span><span class=\"kwd\">out</span><span class=\"pun\">.</span><span class=\"pln\">println</span><span class=\"pun\">(</span><span class=\"str\">\"proxy before\"</span><span class=\"pun\">);</span></code></li><li class=\"L9\"><code><span class=\"pln\">                        </span><span class=\"typ\">Object</span><span class=\"pln\"> invoke </span><span class=\"pun\">=</span><span class=\"pln\"> method</span><span class=\"pun\">.</span><span class=\"pln\">invoke</span><span class=\"pun\">(</span><span class=\"pln\">target</span><span class=\"pun\">,</span><span class=\"pln\"> args1</span><span class=\"pun\">);</span></code></li><li class=\"L0\"><code><span class=\"pln\">                        </span><span class=\"typ\">System</span><span class=\"pun\">.</span><span class=\"kwd\">out</span><span class=\"pun\">.</span><span class=\"pln\">println</span><span class=\"pun\">(</span><span class=\"str\">\"proxy after\"</span><span class=\"pun\">);</span></code></li><li class=\"L1\"><code><span class=\"pln\">                        </span><span class=\"kwd\">return</span><span class=\"pln\"> invoke</span><span class=\"pun\">;</span></code></li><li class=\"L2\"><code><span class=\"pln\">                    </span><span class=\"pun\">}</span></code></li><li class=\"L3\"><code><span class=\"pln\">                </span><span class=\"pun\">});</span></code></li><li class=\"L4\"><code><span class=\"pln\">        o</span><span class=\"pun\">.</span><span class=\"pln\">method</span><span class=\"pun\">();</span></code></li></ol></pre><h4 id=\"h4--cglib-\"><a name=\"基于cglib的动态代理\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>基于cglib的动态代理</h4><h5 id=\"h5-1-target-\"><a name=\"1. 新建一个Target类\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>1. 新建一个Target类</h5><pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"kwd\">class</span><span class=\"pln\"> </span><span class=\"typ\">Target</span><span class=\"pun\">{</span></code></li><li class=\"L1\"><code><span class=\"pln\">    </span><span class=\"kwd\">public</span><span class=\"pln\"> </span><span class=\"kwd\">void</span><span class=\"pln\"> save</span><span class=\"pun\">(){</span></code></li><li class=\"L2\"><code><span class=\"pln\">        </span><span class=\"typ\">System</span><span class=\"pun\">.</span><span class=\"kwd\">out</span><span class=\"pun\">.</span><span class=\"pln\">println</span><span class=\"pun\">(</span><span class=\"str\">\"save running\"</span><span class=\"pun\">);</span></code></li><li class=\"L3\"><code><span class=\"pln\">    </span><span class=\"pun\">}</span></code></li><li class=\"L4\"><code><span class=\"pun\">}</span></code></li></ol></pre><h5 id=\"h5-2-\"><a name=\"2. 创建一个增强类\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>2. 创建一个增强类</h5><pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"kwd\">class</span><span class=\"pln\"> </span><span class=\"typ\">Advice</span><span class=\"pun\">{</span></code></li><li class=\"L1\"><code><span class=\"pln\">    </span><span class=\"kwd\">public</span><span class=\"pln\"> </span><span class=\"kwd\">void</span><span class=\"pln\"> before</span><span class=\"pun\">()</span></code></li><li class=\"L2\"><code><span class=\"pln\">    </span><span class=\"pun\">{</span></code></li><li class=\"L3\"><code><span class=\"pln\">        </span><span class=\"typ\">System</span><span class=\"pun\">.</span><span class=\"kwd\">out</span><span class=\"pun\">.</span><span class=\"pln\">println</span><span class=\"pun\">(</span><span class=\"str\">\"save before running\"</span><span class=\"pun\">);</span></code></li><li class=\"L4\"><code><span class=\"pln\">    </span><span class=\"pun\">}</span></code></li><li class=\"L5\"><code></code></li><li class=\"L6\"><code><span class=\"pln\">    </span><span class=\"kwd\">public</span><span class=\"pln\"> </span><span class=\"kwd\">void</span><span class=\"pln\"> afterReturn</span><span class=\"pun\">(){</span></code></li><li class=\"L7\"><code><span class=\"pln\">        </span><span class=\"typ\">System</span><span class=\"pun\">.</span><span class=\"kwd\">out</span><span class=\"pun\">.</span><span class=\"pln\">println</span><span class=\"pun\">(</span><span class=\"str\">\"save after running\"</span><span class=\"pun\">);</span></code></li><li class=\"L8\"><code><span class=\"pln\">    </span><span class=\"pun\">}</span></code></li><li class=\"L9\"><code><span class=\"pun\">}</span></code></li></ol></pre><h5 id=\"h5-3-\"><a name=\"3. 创建一个测试类\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>3. 创建一个测试类</h5><pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"kwd\">class</span><span class=\"pln\"> </span><span class=\"typ\">CglibProxyTest</span><span class=\"pun\">{</span></code></li><li class=\"L1\"><code><span class=\"pln\">    </span><span class=\"kwd\">public</span><span class=\"pln\"> </span><span class=\"kwd\">static</span><span class=\"pln\"> </span><span class=\"kwd\">void</span><span class=\"pln\"> main</span><span class=\"pun\">(</span><span class=\"typ\">String</span><span class=\"pun\">[]</span><span class=\"pln\"> args</span><span class=\"pun\">){</span></code></li><li class=\"L2\"><code><span class=\"pln\">        </span><span class=\"com\">//目标对象</span></code></li><li class=\"L3\"><code><span class=\"pln\">        </span><span class=\"kwd\">final</span><span class=\"pln\"> </span><span class=\"typ\">Target</span><span class=\"pln\"> target </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"kwd\">new</span><span class=\"pln\"> </span><span class=\"typ\">Target</span><span class=\"pun\">();</span></code></li><li class=\"L4\"><code></code></li><li class=\"L5\"><code><span class=\"pln\">        </span><span class=\"kwd\">final</span><span class=\"pln\"> </span><span class=\"typ\">Advice</span><span class=\"pln\"> advice </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"kwd\">new</span><span class=\"pln\"> </span><span class=\"typ\">Advice</span><span class=\"pun\">();</span></code></li><li class=\"L6\"><code></code></li><li class=\"L7\"><code><span class=\"pln\">        </span><span class=\"com\">//1.基于cglib生成动态代理对象</span></code></li><li class=\"L8\"><code><span class=\"pln\">        </span><span class=\"typ\">Enhancer</span><span class=\"pln\"> enhancer </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"kwd\">new</span><span class=\"pln\"> </span><span class=\"typ\">Enhancer</span><span class=\"pun\">();</span></code></li><li class=\"L9\"><code><span class=\"pln\">        </span><span class=\"com\">//2.设置目录类为父类</span></code></li><li class=\"L0\"><code><span class=\"pln\">        enhancer</span><span class=\"pun\">.</span><span class=\"pln\">setSuperclass</span><span class=\"pun\">(</span><span class=\"typ\">Target</span><span class=\"pun\">.</span><span class=\"kwd\">class</span><span class=\"pun\">);</span></code></li><li class=\"L1\"><code><span class=\"pln\">        </span><span class=\"com\">//3.设置回调</span></code></li><li class=\"L2\"><code><span class=\"pln\">        enhancer</span><span class=\"pun\">.</span><span class=\"pln\">setCallback</span><span class=\"pun\">(</span><span class=\"kwd\">new</span><span class=\"pln\"> </span><span class=\"typ\">MethodInterceptor</span><span class=\"pun\">(){</span></code></li><li class=\"L3\"><code><span class=\"pln\">             </span><span class=\"kwd\">public</span><span class=\"pln\"> </span><span class=\"typ\">Object</span><span class=\"pln\"> intercept</span><span class=\"pun\">(</span><span class=\"typ\">Object</span><span class=\"pln\"> obj</span><span class=\"pun\">,</span><span class=\"pln\"> </span><span class=\"typ\">Method</span><span class=\"pln\"> method</span><span class=\"pun\">,</span><span class=\"pln\"> </span><span class=\"typ\">Object</span><span class=\"pun\">[]</span><span class=\"pln\"> args</span><span class=\"pun\">,</span><span class=\"pln\"> </span><span class=\"typ\">MethodProxy</span><span class=\"pln\"> proxy</span><span class=\"pun\">)</span><span class=\"pln\"> </span><span class=\"kwd\">throws</span><span class=\"pln\"> </span><span class=\"typ\">Throwable</span><span class=\"pln\"> </span><span class=\"pun\">{</span></code></li><li class=\"L4\"><code><span class=\"pln\">                advice</span><span class=\"pun\">.</span><span class=\"pln\">before</span><span class=\"pun\">();</span></code></li><li class=\"L5\"><code><span class=\"pln\">                </span><span class=\"typ\">Object</span><span class=\"pln\"> </span><span class=\"kwd\">object</span><span class=\"pun\">=</span><span class=\"pln\">proxy</span><span class=\"pun\">.</span><span class=\"pln\">invokeSuper</span><span class=\"pun\">(</span><span class=\"pln\">obj</span><span class=\"pun\">,</span><span class=\"pln\"> args</span><span class=\"pun\">);</span></code></li><li class=\"L6\"><code><span class=\"pln\">                advice</span><span class=\"pun\">.</span><span class=\"pln\">afterReturn</span><span class=\"pun\">();</span></code></li><li class=\"L7\"><code><span class=\"pln\">                </span><span class=\"kwd\">return</span><span class=\"pln\"> </span><span class=\"kwd\">object</span><span class=\"pun\">;</span></code></li><li class=\"L8\"><code><span class=\"pln\">            </span><span class=\"pun\">}</span></code></li><li class=\"L9\"><code><span class=\"pln\">        </span><span class=\"pun\">});</span></code></li><li class=\"L0\"><code></code></li><li class=\"L1\"><code><span class=\"pln\">        </span><span class=\"com\">//4.创建代理对象</span></code></li><li class=\"L2\"><code><span class=\"pln\">        </span><span class=\"typ\">Target</span><span class=\"pln\"> o </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"pun\">(</span><span class=\"typ\">Target</span><span class=\"pun\">)</span><span class=\"pln\">enhancer</span><span class=\"pun\">.</span><span class=\"pln\">create</span><span class=\"pun\">();</span></code></li><li class=\"L3\"><code></code></li><li class=\"L4\"><code><span class=\"pln\">        o</span><span class=\"pun\">.</span><span class=\"pln\">save</span><span class=\"pun\">();</span></code></li><li class=\"L5\"><code><span class=\"pln\">    </span><span class=\"pun\">}</span></code></li><li class=\"L6\"><code><span class=\"pun\">}</span></code></li></ol></pre>',0,0,22,'2020-12-30 13:27:23',0.0000,'2020-09-02 13:33:31','2020-09-02 13:33:31',0,0),(1336963516165324711,1,1336960357791449089,'Docker Registry','##### 获取registry镜像\n> docker pull registry\n\n##### 启动容器\n	docker run -it -d -v /opt/docker/registry/:/var/lib/registry -p 5000:5000 --restart=always --name=registry registry\n\n可以使用 **docker ps**查看运行中的容器\n\n也可已通过http请求查看注册中心中的容器\n\n	curl -XGET http://localhost:5000/v2/_catalog\n\n##### 使用 docker push命令推送本地镜像到本地注册中心\n先从远程仓库中获取一个ubuntu镜像\n> docker pull ubuntu\n\n标记这个镜像是可以推送到本地仓库的\n> docker image tag ubuntu localhost:5000/myimages/ubuntu\n\n推送镜像到本地注册中心\n> docker push localhost:5000/myimages/ubuntu\n\n##### 获取本地仓库镜像\n> docker pull localhost:5000/myimages/ubuntu\n\n\n','<h5 id=\"h5--registry-\"><a name=\"获取registry镜像\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>获取registry镜像</h5><blockquote>\n<p>docker pull registry</p>\n</blockquote>\n<h5 id=\"h5-u542Fu52A8u5BB9u5668\"><a name=\"启动容器\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>启动容器</h5><pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"pln\">docker run </span><span class=\"pun\">-</span><span class=\"pln\">it </span><span class=\"pun\">-</span><span class=\"pln\">d </span><span class=\"pun\">-</span><span class=\"pln\">v </span><span class=\"pun\">/</span><span class=\"pln\">opt</span><span class=\"pun\">/</span><span class=\"pln\">docker</span><span class=\"pun\">/</span><span class=\"pln\">registry</span><span class=\"pun\">/:</span><span class=\"str\">/var/</span><span class=\"pln\">lib</span><span class=\"pun\">/</span><span class=\"pln\">registry </span><span class=\"pun\">-</span><span class=\"pln\">p </span><span class=\"lit\">5000</span><span class=\"pun\">:</span><span class=\"lit\">5000</span><span class=\"pln\"> </span><span class=\"pun\">--</span><span class=\"pln\">restart</span><span class=\"pun\">=</span><span class=\"pln\">always </span><span class=\"pun\">--</span><span class=\"pln\">name</span><span class=\"pun\">=</span><span class=\"pln\">registry registry</span></code></li></ol></pre><p>可以使用 <strong>docker ps</strong>查看运行中的容器</p>\n<p>也可已通过http请求查看注册中心中的容器</p>\n<pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"pln\">curl </span><span class=\"pun\">-</span><span class=\"pln\">XGET http</span><span class=\"pun\">:</span><span class=\"com\">//localhost:5000/v2/_catalog</span></code></li></ol></pre><h5 id=\"h5--docker-push-\"><a name=\"使用 docker push命令推送本地镜像到本地注册中心\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>使用 docker push命令推送本地镜像到本地注册中心</h5><p>先从远程仓库中获取一个ubuntu镜像</p>\n<blockquote>\n<p>docker pull ubuntu</p>\n</blockquote>\n<p>标记这个镜像是可以推送到本地仓库的</p>\n<blockquote>\n<p>docker image tag ubuntu localhost:5000/myimages/ubuntu</p>\n</blockquote>\n<p>推送镜像到本地注册中心</p>\n<blockquote>\n<p>docker push localhost:5000/myimages/ubuntu</p>\n</blockquote>\n<h5 id=\"h5-u83B7u53D6u672Cu5730u4ED3u5E93u955Cu50CF\"><a name=\"获取本地仓库镜像\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>获取本地仓库镜像</h5><blockquote>\n<p>docker pull localhost:5000/myimages/ubuntu</p>\n</blockquote>\n',0,0,15,'2020-12-30 13:27:23',0.0000,'2020-09-04 14:19:00','2020-09-04 14:19:00',0,0),(1336963516165324712,1,1336960357791449089,'Linux 用户管理','#### 用户组操作\n\n##### 1.查看用户组\n查看所有用户组\n> cat /etc/group\n\n显示当前用户所属的所有用户组\n> groups\n\n##### 2. 添加一个用户组\n添加一个组名为testGroup的用户组\n> sudo groupadd testGroup\n\n执行之后通过上面查看用户组的命令应该可以看到新添加的用户组\n\n##### 3. 修改用户组信息\n修改组名,将testGroup改为newGroup\n> sudo groupmod -n newGroup testGroup\n\n通过 **cat /etc/group** 查看修改效果\n\n##### 4. 删除一个用户组\n删除用户组 newGroup\n\n> sudo groupdel newGroup\n\n#### 用户操作\n##### 1. 添加一个新用户\n添加一个新用户\n> useradd testuser\n\n主要参数\n\n| 参数        | 说明   |\n|:---: |:---|\n| -c |加上备注文字，备注文字保存在passwd的备注栏中|\n| -d |指定用户登入时的主目录，替换系统默认值/home/<用户名>|\n| -D |变更预设值|\n| -e |指定账号的失效日期，日期格式为MM/DD/YY，例06/30/12。缺省表示永久有效|\n| -f |指定在密码过期后多少天即关闭该账号。如果为0账号立即被停用；如果为-1则账号一直可用。默认值为-1|\n| -g |指定用户所属的群组。值可以使组名也可以是GID。用户组必须已经存在的，期默认值为100，即users|\n| -G |指定用户所属的附加群组|\n| -m |自动建立用户的登入目录|\n| -M |不要自动建立用户的登入目录|\n| -n |取消建立以用户名称为名的群组|\n| -r | 建立系统账号|\n| -s |指定用户登入后所使用的shell。默认值为/bin/bash。如果要指定的用户不能登录，可以设置值为 /sbin/nologin 或者 /bin/false|\n| -u |指定用户ID号。该值在系统中必须是唯一的。0~499默认是保留给系统用户账号使用的，所以该值必须大于499|\n\n添加一个用户目录为 /home/testuser2 用户组为root，用户名为test2的新用户\n> useradd -d /home/testuser2 -g root test2\n\n\n##### 2. 查看系统用户\n\n> cat /etc/passwd 或 cat /etc/shadow\n\n##### 3. 修改指定用户信息\n###### 修改/设置用户密码\n> sudo passwd testuser\n\n输入两次密码，出现如下提示信息时说明修改成功\n```\nChanging password for user testuser.\nNew password:\nBAD PASSWORD: The password fails the dictionary check - it is based on a dictionary word\nRetype new password:\npasswd: all authentication tokens updated successfully.\n```\n\n###### 修改用户名\n将testuser修改为newuser\n> sudo usermod -l newuser testuser\n\n###### 修改用户组\n将newuser的用户组修改为root\n> sudo usermod -g root newuser\n\n执行成功之后用户newuser就只属于root用户组了\n##### 为用户新增一个用户组\n> usermod -aG wheel newuser\n\n##### 4. 删除指定用户\n删除用户名为newuser的用户\n> sudo userdel newuser\n\n\n\n\n\n','<h4 id=\"h4-u7528u6237u7EC4u64CDu4F5C\"><a name=\"用户组操作\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>用户组操作</h4><h5 id=\"h5-1-\"><a name=\"1.查看用户组\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>1.查看用户组</h5><p>查看所有用户组</p>\n<blockquote>\n<p>cat /etc/group</p>\n</blockquote>\n<p>显示当前用户所属的所有用户组</p>\n<blockquote>\n<p>groups</p>\n</blockquote>\n<h5 id=\"h5-2-\"><a name=\"2. 添加一个用户组\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>2. 添加一个用户组</h5><p>添加一个组名为testGroup的用户组</p>\n<blockquote>\n<p>sudo groupadd testGroup</p>\n</blockquote>\n<p>执行之后通过上面查看用户组的命令应该可以看到新添加的用户组</p>\n<h5 id=\"h5-3-\"><a name=\"3. 修改用户组信息\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>3. 修改用户组信息</h5><p>修改组名,将testGroup改为newGroup</p>\n<blockquote>\n<p>sudo groupmod -n newGroup testGroup</p>\n</blockquote>\n<p>通过 <strong>cat /etc/group</strong> 查看修改效果</p>\n<h5 id=\"h5-4-\"><a name=\"4. 删除一个用户组\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>4. 删除一个用户组</h5><p>删除用户组 newGroup</p>\n<blockquote>\n<p>sudo groupdel newGroup</p>\n</blockquote>\n<h4 id=\"h4-u7528u6237u64CDu4F5C\"><a name=\"用户操作\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>用户操作</h4><h5 id=\"h5-1-\"><a name=\"1. 添加一个新用户\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>1. 添加一个新用户</h5><p>添加一个新用户</p>\n<blockquote>\n<p>useradd testuser</p>\n</blockquote>\n<p>主要参数</p>\n<table>\n<thead>\n<tr>\n<th style=\"text-align:center\">参数</th>\n<th style=\"text-align:left\">说明</th>\n</tr>\n</thead>\n<tbody>\n<tr>\n<td style=\"text-align:center\">-c</td>\n<td style=\"text-align:left\">加上备注文字，备注文字保存在passwd的备注栏中</td>\n</tr>\n<tr>\n<td style=\"text-align:center\">-d</td>\n<td style=\"text-align:left\">指定用户登入时的主目录，替换系统默认值/home/&lt;用户名&gt;</td>\n</tr>\n<tr>\n<td style=\"text-align:center\">-D</td>\n<td style=\"text-align:left\">变更预设值</td>\n</tr>\n<tr>\n<td style=\"text-align:center\">-e</td>\n<td style=\"text-align:left\">指定账号的失效日期，日期格式为MM/DD/YY，例06/30/12。缺省表示永久有效</td>\n</tr>\n<tr>\n<td style=\"text-align:center\">-f</td>\n<td style=\"text-align:left\">指定在密码过期后多少天即关闭该账号。如果为0账号立即被停用；如果为-1则账号一直可用。默认值为-1</td>\n</tr>\n<tr>\n<td style=\"text-align:center\">-g</td>\n<td style=\"text-align:left\">指定用户所属的群组。值可以使组名也可以是GID。用户组必须已经存在的，期默认值为100，即users</td>\n</tr>\n<tr>\n<td style=\"text-align:center\">-G</td>\n<td style=\"text-align:left\">指定用户所属的附加群组</td>\n</tr>\n<tr>\n<td style=\"text-align:center\">-m</td>\n<td style=\"text-align:left\">自动建立用户的登入目录</td>\n</tr>\n<tr>\n<td style=\"text-align:center\">-M</td>\n<td style=\"text-align:left\">不要自动建立用户的登入目录</td>\n</tr>\n<tr>\n<td style=\"text-align:center\">-n</td>\n<td style=\"text-align:left\">取消建立以用户名称为名的群组</td>\n</tr>\n<tr>\n<td style=\"text-align:center\">-r</td>\n<td style=\"text-align:left\">建立系统账号</td>\n</tr>\n<tr>\n<td style=\"text-align:center\">-s</td>\n<td style=\"text-align:left\">指定用户登入后所使用的shell。默认值为/bin/bash。如果要指定的用户不能登录，可以设置值为 /sbin/nologin 或者 /bin/false</td>\n</tr>\n<tr>\n<td style=\"text-align:center\">-u</td>\n<td style=\"text-align:left\">指定用户ID号。该值在系统中必须是唯一的。0~499默认是保留给系统用户账号使用的，所以该值必须大于499</td>\n</tr>\n</tbody>\n</table>\n<p>添加一个用户目录为 /home/testuser2 用户组为root，用户名为test2的新用户</p>\n<blockquote>\n<p>useradd -d /home/testuser2 -g root test2</p>\n</blockquote>\n<h5 id=\"h5-2-\"><a name=\"2. 查看系统用户\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>2. 查看系统用户</h5><blockquote>\n<p>cat /etc/passwd 或 cat /etc/shadow</p>\n</blockquote>\n<h5 id=\"h5-3-\"><a name=\"3. 修改指定用户信息\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>3. 修改指定用户信息</h5><h6 id=\"h6--\"><a name=\"修改/设置用户密码\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>修改/设置用户密码</h6><blockquote>\n<p>sudo passwd testuser</p>\n</blockquote>\n<p>输入两次密码，出现如下提示信息时说明修改成功</p>\n<pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"typ\">Changing</span><span class=\"pln\"> password </span><span class=\"kwd\">for</span><span class=\"pln\"> user testuser</span><span class=\"pun\">.</span></code></li><li class=\"L1\"><code><span class=\"typ\">New</span><span class=\"pln\"> password</span><span class=\"pun\">:</span></code></li><li class=\"L2\"><code><span class=\"pln\">BAD PASSWORD</span><span class=\"pun\">:</span><span class=\"pln\"> </span><span class=\"typ\">The</span><span class=\"pln\"> password fails the dictionary check </span><span class=\"pun\">-</span><span class=\"pln\"> it </span><span class=\"kwd\">is</span><span class=\"pln\"> based on a dictionary word</span></code></li><li class=\"L3\"><code><span class=\"typ\">Retype</span><span class=\"pln\"> </span><span class=\"kwd\">new</span><span class=\"pln\"> password</span><span class=\"pun\">:</span></code></li><li class=\"L4\"><code><span class=\"pln\">passwd</span><span class=\"pun\">:</span><span class=\"pln\"> all authentication tokens updated successfully</span><span class=\"pun\">.</span></code></li></ol></pre><h6 id=\"h6-u4FEEu6539u7528u6237u540D\"><a name=\"修改用户名\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>修改用户名</h6><p>将testuser修改为newuser</p>\n<blockquote>\n<p>sudo usermod -l newuser testuser</p>\n</blockquote>\n<h6 id=\"h6-u4FEEu6539u7528u6237u7EC4\"><a name=\"修改用户组\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>修改用户组</h6><p>将newuser的用户组修改为root</p>\n<blockquote>\n<p>sudo usermod -g root newuser</p>\n</blockquote>\n<p>执行成功之后用户newuser就只属于root用户组了</p>\n<h5 id=\"h5-u4E3Au7528u6237u65B0u589Eu4E00u4E2Au7528u6237u7EC4\"><a name=\"为用户新增一个用户组\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>为用户新增一个用户组</h5><blockquote>\n<p>usermod -aG wheel newuser</p>\n</blockquote>\n<h5 id=\"h5-4-\"><a name=\"4. 删除指定用户\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>4. 删除指定用户</h5><p>删除用户名为newuser的用户</p>\n<blockquote>\n<p>sudo userdel newuser</p>\n</blockquote>\n',1,0,246,'2020-08-25 15:31:51',0.0015,'2020-08-25 07:31:32','2020-08-25 07:31:32',0,0),(1336963516165324713,1,1336960357791449089,'Java 将一段Html内容转换为文本','代码如下\n\n```\nimport static org.springframework.web.util.HtmlUtils.htmlUnescape;\n\npublic class TextUtils {\n    public static String html2text(String html) {\n        return htmlUnescape(html) //将html中的实体转换为对应的字符串\n                .replaceAll(\"</?\\\\w+.*?>\", \"\") //所有的html标签替换为空\n                .replaceAll(\"\\\\u00A0+\", \" \")//替换不间断空格，ASCII值是160\n                .replaceAll(\"\\\\s+\", \" \")//所有特殊字符替换为空格\n                .replaceAll(\" +\", \" \")//将多个连续的空格替换为单个空格\n                .trim();//去掉首尾空格\n    }\n}\n```','<p>代码如下</p>\n<pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"kwd\">import</span><span class=\"pln\"> </span><span class=\"kwd\">static</span><span class=\"pln\"> org</span><span class=\"pun\">.</span><span class=\"pln\">springframework</span><span class=\"pun\">.</span><span class=\"pln\">web</span><span class=\"pun\">.</span><span class=\"pln\">util</span><span class=\"pun\">.</span><span class=\"typ\">HtmlUtils</span><span class=\"pun\">.</span><span class=\"pln\">htmlUnescape</span><span class=\"pun\">;</span></code></li><li class=\"L1\"><code></code></li><li class=\"L2\"><code><span class=\"kwd\">public</span><span class=\"pln\"> </span><span class=\"kwd\">class</span><span class=\"pln\"> </span><span class=\"typ\">TextUtils</span><span class=\"pln\"> </span><span class=\"pun\">{</span></code></li><li class=\"L3\"><code><span class=\"pln\">    </span><span class=\"kwd\">public</span><span class=\"pln\"> </span><span class=\"kwd\">static</span><span class=\"pln\"> </span><span class=\"typ\">String</span><span class=\"pln\"> html2text</span><span class=\"pun\">(</span><span class=\"typ\">String</span><span class=\"pln\"> html</span><span class=\"pun\">)</span><span class=\"pln\"> </span><span class=\"pun\">{</span></code></li><li class=\"L4\"><code><span class=\"pln\">        </span><span class=\"kwd\">return</span><span class=\"pln\"> htmlUnescape</span><span class=\"pun\">(</span><span class=\"pln\">html</span><span class=\"pun\">)</span><span class=\"pln\"> </span><span class=\"com\">//将html中的实体转换为对应的字符串</span></code></li><li class=\"L5\"><code><span class=\"pln\">                </span><span class=\"pun\">.</span><span class=\"pln\">replaceAll</span><span class=\"pun\">(</span><span class=\"str\">\"&lt;/?\\\\w+.*?&gt;\"</span><span class=\"pun\">,</span><span class=\"pln\"> </span><span class=\"str\">\"\"</span><span class=\"pun\">)</span><span class=\"pln\"> </span><span class=\"com\">//所有的html标签替换为空</span></code></li><li class=\"L6\"><code><span class=\"pln\">                </span><span class=\"pun\">.</span><span class=\"pln\">replaceAll</span><span class=\"pun\">(</span><span class=\"str\">\"\\\\u00A0+\"</span><span class=\"pun\">,</span><span class=\"pln\"> </span><span class=\"str\">\" \"</span><span class=\"pun\">)</span><span class=\"com\">//替换不间断空格，ASCII值是160</span></code></li><li class=\"L7\"><code><span class=\"pln\">                </span><span class=\"pun\">.</span><span class=\"pln\">replaceAll</span><span class=\"pun\">(</span><span class=\"str\">\"\\\\s+\"</span><span class=\"pun\">,</span><span class=\"pln\"> </span><span class=\"str\">\" \"</span><span class=\"pun\">)</span><span class=\"com\">//所有特殊字符替换为空格</span></code></li><li class=\"L8\"><code><span class=\"pln\">                </span><span class=\"pun\">.</span><span class=\"pln\">replaceAll</span><span class=\"pun\">(</span><span class=\"str\">\" +\"</span><span class=\"pun\">,</span><span class=\"pln\"> </span><span class=\"str\">\" \"</span><span class=\"pun\">)</span><span class=\"com\">//将多个连续的空格替换为单个空格</span></code></li><li class=\"L9\"><code><span class=\"pln\">                </span><span class=\"pun\">.</span><span class=\"pln\">trim</span><span class=\"pun\">();</span><span class=\"com\">//去掉首尾空格</span></code></li><li class=\"L0\"><code><span class=\"pln\">    </span><span class=\"pun\">}</span></code></li><li class=\"L1\"><code><span class=\"pun\">}</span></code></li></ol></pre>',1,0,338,'2020-08-26 11:50:43',0.0016,'2020-08-26 03:50:43','2020-08-26 03:50:43',0,0),(1336963516165324714,1,1336960357791449089,'JS通过页面html内容生成一个目录导航','```\n//获取文章内容\nlet content = document.getElementsByClassName(\'detail-body\')[0].innerHTML\n//通过正则获取html内容中的所有html标签\nlet matches = content.match(/<h(\\d) .*?>.*?<\\/h\\d>/g)\n//遍历标签生成一个目录数组\n\nlet tree = []\nlet rootLevel = 1\nlet parents = []\nfor(i in matches){\n    let h = matches[i]\n    let m = h.match(/<h(\\d) /)\n    let level = Number(m[1])\n    if(tree.length == 0){\n        rootLevel = level\n    }\n    let con = h.replace(/<\\/?\\w.*?>/g,\'\')\n    //console.log(con)\n    console.log(level)\n    let id = Number(i)+1\n    if(rootLevel == level){\n        tree.push({id:id,name:con,level:level,parentId:0})\n        parents = [{id:0,level:1},{id:id,level:level}]\n    }else{\n        if(level - parents[parents.length - 1].level < 0){\n            parents.pop()\n        }\n        tree.push({id:id,name:con,level:level,parentId:parents[parents.length - 1].id})\n		if(level - parents[parents.length - 1].level > 1){\n            parents.push({id:id,level:level})\n        }\n    }\n}\nconsole.log(tree)\n//渲染数组生成标签\nfunction getChildren(items,parent){\n	let result = []\n	for(i in items){\n		let item = items[i]\n		if(item.parentId == parent.id){\n			let children = getChildren(items,item)\n			if(children.length > 0){\n				item.children = children\n			}\n			result.push(item)\n		}\n	}\n	return result\n}\nlet result = []\nfor(i in tree){\n	let item = tree[i]\n	if(item.parentId == 0){\n		item.children = getChildren(tree,item)\n		result.push(item)\n	}\n}\nconsole.log(result)\n\nfunction renderTree(items,level){\n	let ul = \"\"\n	for(i in items){\n		let item = items[i]\n		let childrenHtml = \"\"\n		if(item.children && item.children.length > 0){\n			level++\n			childrenHtml = \"<ul class=\'sub-list d\"+level+\"\'>\"+renderTree(item.children,level)+\"</ul>\"\n		}\n		ul += \"<li>\"+item.name+childrenHtml+\"</li>\"\n	}\n	return ul\n}\nlet ul = \"<ul>\"+renderTree(result,1)+\"</ul>\"\nconsole.log(ul)\n```','<pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"com\">//获取文章内容</span></code></li><li class=\"L1\"><code><span class=\"kwd\">let</span><span class=\"pln\"> content </span><span class=\"pun\">=</span><span class=\"pln\"> document</span><span class=\"pun\">.</span><span class=\"pln\">getElementsByClassName</span><span class=\"pun\">(</span><span class=\"str\">\'detail-body\'</span><span class=\"pun\">)[</span><span class=\"lit\">0</span><span class=\"pun\">].</span><span class=\"pln\">innerHTML</span></code></li><li class=\"L2\"><code><span class=\"com\">//通过正则获取html内容中的所有html标签</span></code></li><li class=\"L3\"><code><span class=\"kwd\">let</span><span class=\"pln\"> matches </span><span class=\"pun\">=</span><span class=\"pln\"> content</span><span class=\"pun\">.</span><span class=\"pln\">match</span><span class=\"pun\">(</span><span class=\"str\">/&lt;h(\\d) .*?&gt;.*?&lt;\\/h\\d&gt;/</span><span class=\"pln\">g</span><span class=\"pun\">)</span></code></li><li class=\"L4\"><code><span class=\"com\">//遍历标签生成一个目录数组</span></code></li><li class=\"L5\"><code></code></li><li class=\"L6\"><code><span class=\"kwd\">let</span><span class=\"pln\"> tree </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"pun\">[]</span></code></li><li class=\"L7\"><code><span class=\"kwd\">let</span><span class=\"pln\"> rootLevel </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"lit\">1</span></code></li><li class=\"L8\"><code><span class=\"kwd\">let</span><span class=\"pln\"> parents </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"pun\">[]</span></code></li><li class=\"L9\"><code><span class=\"kwd\">for</span><span class=\"pun\">(</span><span class=\"pln\">i </span><span class=\"kwd\">in</span><span class=\"pln\"> matches</span><span class=\"pun\">){</span></code></li><li class=\"L0\"><code><span class=\"pln\">    </span><span class=\"kwd\">let</span><span class=\"pln\"> h </span><span class=\"pun\">=</span><span class=\"pln\"> matches</span><span class=\"pun\">[</span><span class=\"pln\">i</span><span class=\"pun\">]</span></code></li><li class=\"L1\"><code><span class=\"pln\">    </span><span class=\"kwd\">let</span><span class=\"pln\"> m </span><span class=\"pun\">=</span><span class=\"pln\"> h</span><span class=\"pun\">.</span><span class=\"pln\">match</span><span class=\"pun\">(</span><span class=\"str\">/&lt;h(\\d) /</span><span class=\"pun\">)</span></code></li><li class=\"L2\"><code><span class=\"pln\">    </span><span class=\"kwd\">let</span><span class=\"pln\"> level </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"typ\">Number</span><span class=\"pun\">(</span><span class=\"pln\">m</span><span class=\"pun\">[</span><span class=\"lit\">1</span><span class=\"pun\">])</span></code></li><li class=\"L3\"><code><span class=\"pln\">    </span><span class=\"kwd\">if</span><span class=\"pun\">(</span><span class=\"pln\">tree</span><span class=\"pun\">.</span><span class=\"pln\">length </span><span class=\"pun\">==</span><span class=\"pln\"> </span><span class=\"lit\">0</span><span class=\"pun\">){</span></code></li><li class=\"L4\"><code><span class=\"pln\">        rootLevel </span><span class=\"pun\">=</span><span class=\"pln\"> level</span></code></li><li class=\"L5\"><code><span class=\"pln\">    </span><span class=\"pun\">}</span></code></li><li class=\"L6\"><code><span class=\"pln\">    </span><span class=\"kwd\">let</span><span class=\"pln\"> con </span><span class=\"pun\">=</span><span class=\"pln\"> h</span><span class=\"pun\">.</span><span class=\"pln\">replace</span><span class=\"pun\">(</span><span class=\"str\">/&lt;\\/?\\w.*?&gt;/</span><span class=\"pln\">g</span><span class=\"pun\">,</span><span class=\"str\">\'\'</span><span class=\"pun\">)</span></code></li><li class=\"L7\"><code><span class=\"pln\">    </span><span class=\"com\">//console.log(con)</span></code></li><li class=\"L8\"><code><span class=\"pln\">    console</span><span class=\"pun\">.</span><span class=\"pln\">log</span><span class=\"pun\">(</span><span class=\"pln\">level</span><span class=\"pun\">)</span></code></li><li class=\"L9\"><code><span class=\"pln\">    </span><span class=\"kwd\">let</span><span class=\"pln\"> id </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"typ\">Number</span><span class=\"pun\">(</span><span class=\"pln\">i</span><span class=\"pun\">)+</span><span class=\"lit\">1</span></code></li><li class=\"L0\"><code><span class=\"pln\">    </span><span class=\"kwd\">if</span><span class=\"pun\">(</span><span class=\"pln\">rootLevel </span><span class=\"pun\">==</span><span class=\"pln\"> level</span><span class=\"pun\">){</span></code></li><li class=\"L1\"><code><span class=\"pln\">        tree</span><span class=\"pun\">.</span><span class=\"pln\">push</span><span class=\"pun\">({</span><span class=\"pln\">id</span><span class=\"pun\">:</span><span class=\"pln\">id</span><span class=\"pun\">,</span><span class=\"pln\">name</span><span class=\"pun\">:</span><span class=\"pln\">con</span><span class=\"pun\">,</span><span class=\"pln\">level</span><span class=\"pun\">:</span><span class=\"pln\">level</span><span class=\"pun\">,</span><span class=\"pln\">parentId</span><span class=\"pun\">:</span><span class=\"lit\">0</span><span class=\"pun\">})</span></code></li><li class=\"L2\"><code><span class=\"pln\">        parents </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"pun\">[{</span><span class=\"pln\">id</span><span class=\"pun\">:</span><span class=\"lit\">0</span><span class=\"pun\">,</span><span class=\"pln\">level</span><span class=\"pun\">:</span><span class=\"lit\">1</span><span class=\"pun\">},{</span><span class=\"pln\">id</span><span class=\"pun\">:</span><span class=\"pln\">id</span><span class=\"pun\">,</span><span class=\"pln\">level</span><span class=\"pun\">:</span><span class=\"pln\">level</span><span class=\"pun\">}]</span></code></li><li class=\"L3\"><code><span class=\"pln\">    </span><span class=\"pun\">}</span><span class=\"kwd\">else</span><span class=\"pun\">{</span></code></li><li class=\"L4\"><code><span class=\"pln\">        </span><span class=\"kwd\">if</span><span class=\"pun\">(</span><span class=\"pln\">level </span><span class=\"pun\">-</span><span class=\"pln\"> parents</span><span class=\"pun\">[</span><span class=\"pln\">parents</span><span class=\"pun\">.</span><span class=\"pln\">length </span><span class=\"pun\">-</span><span class=\"pln\"> </span><span class=\"lit\">1</span><span class=\"pun\">].</span><span class=\"pln\">level </span><span class=\"pun\">&lt;</span><span class=\"pln\"> </span><span class=\"lit\">0</span><span class=\"pun\">){</span></code></li><li class=\"L5\"><code><span class=\"pln\">            parents</span><span class=\"pun\">.</span><span class=\"pln\">pop</span><span class=\"pun\">()</span></code></li><li class=\"L6\"><code><span class=\"pln\">        </span><span class=\"pun\">}</span></code></li><li class=\"L7\"><code><span class=\"pln\">        tree</span><span class=\"pun\">.</span><span class=\"pln\">push</span><span class=\"pun\">({</span><span class=\"pln\">id</span><span class=\"pun\">:</span><span class=\"pln\">id</span><span class=\"pun\">,</span><span class=\"pln\">name</span><span class=\"pun\">:</span><span class=\"pln\">con</span><span class=\"pun\">,</span><span class=\"pln\">level</span><span class=\"pun\">:</span><span class=\"pln\">level</span><span class=\"pun\">,</span><span class=\"pln\">parentId</span><span class=\"pun\">:</span><span class=\"pln\">parents</span><span class=\"pun\">[</span><span class=\"pln\">parents</span><span class=\"pun\">.</span><span class=\"pln\">length </span><span class=\"pun\">-</span><span class=\"pln\"> </span><span class=\"lit\">1</span><span class=\"pun\">].</span><span class=\"pln\">id</span><span class=\"pun\">})</span></code></li><li class=\"L8\"><code><span class=\"pln\">        </span><span class=\"kwd\">if</span><span class=\"pun\">(</span><span class=\"pln\">level </span><span class=\"pun\">-</span><span class=\"pln\"> parents</span><span class=\"pun\">[</span><span class=\"pln\">parents</span><span class=\"pun\">.</span><span class=\"pln\">length </span><span class=\"pun\">-</span><span class=\"pln\"> </span><span class=\"lit\">1</span><span class=\"pun\">].</span><span class=\"pln\">level </span><span class=\"pun\">&gt;</span><span class=\"pln\"> </span><span class=\"lit\">1</span><span class=\"pun\">){</span></code></li><li class=\"L9\"><code><span class=\"pln\">            parents</span><span class=\"pun\">.</span><span class=\"pln\">push</span><span class=\"pun\">({</span><span class=\"pln\">id</span><span class=\"pun\">:</span><span class=\"pln\">id</span><span class=\"pun\">,</span><span class=\"pln\">level</span><span class=\"pun\">:</span><span class=\"pln\">level</span><span class=\"pun\">})</span></code></li><li class=\"L0\"><code><span class=\"pln\">        </span><span class=\"pun\">}</span></code></li><li class=\"L1\"><code><span class=\"pln\">    </span><span class=\"pun\">}</span></code></li><li class=\"L2\"><code><span class=\"pun\">}</span></code></li><li class=\"L3\"><code><span class=\"pln\">console</span><span class=\"pun\">.</span><span class=\"pln\">log</span><span class=\"pun\">(</span><span class=\"pln\">tree</span><span class=\"pun\">)</span></code></li><li class=\"L4\"><code><span class=\"com\">//渲染数组生成标签</span></code></li><li class=\"L5\"><code><span class=\"kwd\">function</span><span class=\"pln\"> getChildren</span><span class=\"pun\">(</span><span class=\"pln\">items</span><span class=\"pun\">,</span><span class=\"pln\">parent</span><span class=\"pun\">){</span></code></li><li class=\"L6\"><code><span class=\"pln\">    </span><span class=\"kwd\">let</span><span class=\"pln\"> result </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"pun\">[]</span></code></li><li class=\"L7\"><code><span class=\"pln\">    </span><span class=\"kwd\">for</span><span class=\"pun\">(</span><span class=\"pln\">i </span><span class=\"kwd\">in</span><span class=\"pln\"> items</span><span class=\"pun\">){</span></code></li><li class=\"L8\"><code><span class=\"pln\">        </span><span class=\"kwd\">let</span><span class=\"pln\"> item </span><span class=\"pun\">=</span><span class=\"pln\"> items</span><span class=\"pun\">[</span><span class=\"pln\">i</span><span class=\"pun\">]</span></code></li><li class=\"L9\"><code><span class=\"pln\">        </span><span class=\"kwd\">if</span><span class=\"pun\">(</span><span class=\"pln\">item</span><span class=\"pun\">.</span><span class=\"pln\">parentId </span><span class=\"pun\">==</span><span class=\"pln\"> parent</span><span class=\"pun\">.</span><span class=\"pln\">id</span><span class=\"pun\">){</span></code></li><li class=\"L0\"><code><span class=\"pln\">            </span><span class=\"kwd\">let</span><span class=\"pln\"> children </span><span class=\"pun\">=</span><span class=\"pln\"> getChildren</span><span class=\"pun\">(</span><span class=\"pln\">items</span><span class=\"pun\">,</span><span class=\"pln\">item</span><span class=\"pun\">)</span></code></li><li class=\"L1\"><code><span class=\"pln\">            </span><span class=\"kwd\">if</span><span class=\"pun\">(</span><span class=\"pln\">children</span><span class=\"pun\">.</span><span class=\"pln\">length </span><span class=\"pun\">&gt;</span><span class=\"pln\"> </span><span class=\"lit\">0</span><span class=\"pun\">){</span></code></li><li class=\"L2\"><code><span class=\"pln\">                item</span><span class=\"pun\">.</span><span class=\"pln\">children </span><span class=\"pun\">=</span><span class=\"pln\"> children</span></code></li><li class=\"L3\"><code><span class=\"pln\">            </span><span class=\"pun\">}</span></code></li><li class=\"L4\"><code><span class=\"pln\">            result</span><span class=\"pun\">.</span><span class=\"pln\">push</span><span class=\"pun\">(</span><span class=\"pln\">item</span><span class=\"pun\">)</span></code></li><li class=\"L5\"><code><span class=\"pln\">        </span><span class=\"pun\">}</span></code></li><li class=\"L6\"><code><span class=\"pln\">    </span><span class=\"pun\">}</span></code></li><li class=\"L7\"><code><span class=\"pln\">    </span><span class=\"kwd\">return</span><span class=\"pln\"> result</span></code></li><li class=\"L8\"><code><span class=\"pun\">}</span></code></li><li class=\"L9\"><code><span class=\"kwd\">let</span><span class=\"pln\"> result </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"pun\">[]</span></code></li><li class=\"L0\"><code><span class=\"kwd\">for</span><span class=\"pun\">(</span><span class=\"pln\">i </span><span class=\"kwd\">in</span><span class=\"pln\"> tree</span><span class=\"pun\">){</span></code></li><li class=\"L1\"><code><span class=\"pln\">    </span><span class=\"kwd\">let</span><span class=\"pln\"> item </span><span class=\"pun\">=</span><span class=\"pln\"> tree</span><span class=\"pun\">[</span><span class=\"pln\">i</span><span class=\"pun\">]</span></code></li><li class=\"L2\"><code><span class=\"pln\">    </span><span class=\"kwd\">if</span><span class=\"pun\">(</span><span class=\"pln\">item</span><span class=\"pun\">.</span><span class=\"pln\">parentId </span><span class=\"pun\">==</span><span class=\"pln\"> </span><span class=\"lit\">0</span><span class=\"pun\">){</span></code></li><li class=\"L3\"><code><span class=\"pln\">        item</span><span class=\"pun\">.</span><span class=\"pln\">children </span><span class=\"pun\">=</span><span class=\"pln\"> getChildren</span><span class=\"pun\">(</span><span class=\"pln\">tree</span><span class=\"pun\">,</span><span class=\"pln\">item</span><span class=\"pun\">)</span></code></li><li class=\"L4\"><code><span class=\"pln\">        result</span><span class=\"pun\">.</span><span class=\"pln\">push</span><span class=\"pun\">(</span><span class=\"pln\">item</span><span class=\"pun\">)</span></code></li><li class=\"L5\"><code><span class=\"pln\">    </span><span class=\"pun\">}</span></code></li><li class=\"L6\"><code><span class=\"pun\">}</span></code></li><li class=\"L7\"><code><span class=\"pln\">console</span><span class=\"pun\">.</span><span class=\"pln\">log</span><span class=\"pun\">(</span><span class=\"pln\">result</span><span class=\"pun\">)</span></code></li><li class=\"L8\"><code></code></li><li class=\"L9\"><code><span class=\"kwd\">function</span><span class=\"pln\"> renderTree</span><span class=\"pun\">(</span><span class=\"pln\">items</span><span class=\"pun\">,</span><span class=\"pln\">level</span><span class=\"pun\">){</span></code></li><li class=\"L0\"><code><span class=\"pln\">    </span><span class=\"kwd\">let</span><span class=\"pln\"> ul </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"str\">\"\"</span></code></li><li class=\"L1\"><code><span class=\"pln\">    </span><span class=\"kwd\">for</span><span class=\"pun\">(</span><span class=\"pln\">i </span><span class=\"kwd\">in</span><span class=\"pln\"> items</span><span class=\"pun\">){</span></code></li><li class=\"L2\"><code><span class=\"pln\">        </span><span class=\"kwd\">let</span><span class=\"pln\"> item </span><span class=\"pun\">=</span><span class=\"pln\"> items</span><span class=\"pun\">[</span><span class=\"pln\">i</span><span class=\"pun\">]</span></code></li><li class=\"L3\"><code><span class=\"pln\">        </span><span class=\"kwd\">let</span><span class=\"pln\"> childrenHtml </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"str\">\"\"</span></code></li><li class=\"L4\"><code><span class=\"pln\">        </span><span class=\"kwd\">if</span><span class=\"pun\">(</span><span class=\"pln\">item</span><span class=\"pun\">.</span><span class=\"pln\">children </span><span class=\"pun\">&amp;&amp;</span><span class=\"pln\"> item</span><span class=\"pun\">.</span><span class=\"pln\">children</span><span class=\"pun\">.</span><span class=\"pln\">length </span><span class=\"pun\">&gt;</span><span class=\"pln\"> </span><span class=\"lit\">0</span><span class=\"pun\">){</span></code></li><li class=\"L5\"><code><span class=\"pln\">            level</span><span class=\"pun\">++</span></code></li><li class=\"L6\"><code><span class=\"pln\">            childrenHtml </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"str\">\"&lt;ul class=\'sub-list d\"</span><span class=\"pun\">+</span><span class=\"pln\">level</span><span class=\"pun\">+</span><span class=\"str\">\"\'&gt;\"</span><span class=\"pun\">+</span><span class=\"pln\">renderTree</span><span class=\"pun\">(</span><span class=\"pln\">item</span><span class=\"pun\">.</span><span class=\"pln\">children</span><span class=\"pun\">,</span><span class=\"pln\">level</span><span class=\"pun\">)+</span><span class=\"str\">\"&lt;/ul&gt;\"</span></code></li><li class=\"L7\"><code><span class=\"pln\">        </span><span class=\"pun\">}</span></code></li><li class=\"L8\"><code><span class=\"pln\">        ul </span><span class=\"pun\">+=</span><span class=\"pln\"> </span><span class=\"str\">\"&lt;li&gt;\"</span><span class=\"pun\">+</span><span class=\"pln\">item</span><span class=\"pun\">.</span><span class=\"pln\">name</span><span class=\"pun\">+</span><span class=\"pln\">childrenHtml</span><span class=\"pun\">+</span><span class=\"str\">\"&lt;/li&gt;\"</span></code></li><li class=\"L9\"><code><span class=\"pln\">    </span><span class=\"pun\">}</span></code></li><li class=\"L0\"><code><span class=\"pln\">    </span><span class=\"kwd\">return</span><span class=\"pln\"> ul</span></code></li><li class=\"L1\"><code><span class=\"pun\">}</span></code></li><li class=\"L2\"><code><span class=\"kwd\">let</span><span class=\"pln\"> ul </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"str\">\"&lt;ul&gt;\"</span><span class=\"pun\">+</span><span class=\"pln\">renderTree</span><span class=\"pun\">(</span><span class=\"pln\">result</span><span class=\"pun\">,</span><span class=\"lit\">1</span><span class=\"pun\">)+</span><span class=\"str\">\"&lt;/ul&gt;\"</span></code></li><li class=\"L3\"><code><span class=\"pln\">console</span><span class=\"pun\">.</span><span class=\"pln\">log</span><span class=\"pun\">(</span><span class=\"pln\">ul</span><span class=\"pun\">)</span></code></li></ol></pre>',2,0,157,'2020-11-20 22:47:15',0.0118,'2020-08-07 19:51:40','2020-08-07 19:51:40',0,0),(1336963516165324715,1,1336960357791449089,'HttpClient发送http请求','#### 1. pom.xml 引入坐标\n```\n<dependency>\n	<groupId>org.apache.httpcomponents</groupId>\n    <artifactId>httpclient</artifactId>\n    <version>4.5.3</version>\n</dependency>\n```\n\n#### 2.测试\n\n##### 发送GET请求\n```\n        //1.创建客户端\n        CloseableHttpClient httpClient = HttpClients.createDefault();\n		\n        //2.声明get请求\n        HttpGet request = new HttpGet(\"http://www.baidu.com\");\n		\n        //3.发送请求\n        CloseableHttpResponse response = httpClient.execute(request);\n        //4.判断状态码\n        if (response.getStatusLine().getStatusCode() == 200) {\n            HttpEntity entity = response.getEntity();\n            //使用工具类EntityUtils，从响应中取出实体表示的内容并转换成字符串\n            String string = EntityUtils.toString(entity, \"UTF-8\");\n            System.out.println(string);\n        }\n        //5.关闭资源\n        response.close();\n        httpClient.close();\n```\n##### 发送POST请求\n```\n		//获取客户端\n        CloseableHttpClient client = HttpClients.createDefault();\n		\n		//声明POST请求\n        HttpPost request = new HttpPost(\"https://gitee.com/oauth/token\");\n		\n		//设置User-Agent\n        request.addHeader(\"User-Agent\", \"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36\");\n		\n		//构建请求参数\n        ArrayList<NameValuePair> parameters = new ArrayList<>();\n        parameters.add(new BasicNameValuePair(\"grant_type\", \"authorization_code\"));\n        parameters.add(new BasicNameValuePair(\"code\", \"xxx\"));\n        parameters.add(new BasicNameValuePair(\"client_id\", \"xxx\"));\n        parameters.add(new BasicNameValuePair(\"client_secret\", \"xxx\"));\n        parameters.add(new BasicNameValuePair(\"redirect_uri\", \"http://localhost:8001/oauth/gitee/callback\"));\n\n        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(parameters, \"UTF-8\");\n\n		//设置请求的参数\n        request.setEntity(urlEncodedFormEntity);\n		\n		//发送请求\n        CloseableHttpResponse response = client.execute(request);\n        System.out.println(response.getStatusLine().getStatusCode());\n        if (response.getStatusLine().getStatusCode() == 200) {\n            HttpEntity entity = response.getEntity();\n            String body = EntityUtils.toString(entity, \"UTF-8\");\n\n            System.out.println(body);\n        } else {\n            System.out.println(EntityUtils.toString(response.getEntity(), \"UTF-8\"));\n        }\n		\n		//关闭资源\n		response.close();\n        client.close();\n```','<h4 id=\"h4-1-pom-xml-\"><a name=\"1. pom.xml 引入坐标\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>1. pom.xml 引入坐标</h4><pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"tag\">&lt;dependency&gt;</span></code></li><li class=\"L1\"><code><span class=\"pln\">    </span><span class=\"tag\">&lt;groupId&gt;</span><span class=\"pln\">org.apache.httpcomponents</span><span class=\"tag\">&lt;/groupId&gt;</span></code></li><li class=\"L2\"><code><span class=\"pln\">    </span><span class=\"tag\">&lt;artifactId&gt;</span><span class=\"pln\">httpclient</span><span class=\"tag\">&lt;/artifactId&gt;</span></code></li><li class=\"L3\"><code><span class=\"pln\">    </span><span class=\"tag\">&lt;version&gt;</span><span class=\"pln\">4.5.3</span><span class=\"tag\">&lt;/version&gt;</span></code></li><li class=\"L4\"><code><span class=\"tag\">&lt;/dependency&gt;</span></code></li></ol></pre><h4 id=\"h4-2-\"><a name=\"2.测试\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>2.测试</h4><h5 id=\"h5--get-\"><a name=\"发送GET请求\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>发送GET请求</h5><pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"pln\">        </span><span class=\"com\">//1.创建客户端</span></code></li><li class=\"L1\"><code><span class=\"pln\">        </span><span class=\"typ\">CloseableHttpClient</span><span class=\"pln\"> httpClient </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"typ\">HttpClients</span><span class=\"pun\">.</span><span class=\"pln\">createDefault</span><span class=\"pun\">();</span></code></li><li class=\"L2\"><code></code></li><li class=\"L3\"><code><span class=\"pln\">        </span><span class=\"com\">//2.声明get请求</span></code></li><li class=\"L4\"><code><span class=\"pln\">        </span><span class=\"typ\">HttpGet</span><span class=\"pln\"> request </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"kwd\">new</span><span class=\"pln\"> </span><span class=\"typ\">HttpGet</span><span class=\"pun\">(</span><span class=\"str\">\"http://www.baidu.com\"</span><span class=\"pun\">);</span></code></li><li class=\"L5\"><code></code></li><li class=\"L6\"><code><span class=\"pln\">        </span><span class=\"com\">//3.发送请求</span></code></li><li class=\"L7\"><code><span class=\"pln\">        </span><span class=\"typ\">CloseableHttpResponse</span><span class=\"pln\"> response </span><span class=\"pun\">=</span><span class=\"pln\"> httpClient</span><span class=\"pun\">.</span><span class=\"pln\">execute</span><span class=\"pun\">(</span><span class=\"pln\">request</span><span class=\"pun\">);</span></code></li><li class=\"L8\"><code><span class=\"pln\">        </span><span class=\"com\">//4.判断状态码</span></code></li><li class=\"L9\"><code><span class=\"pln\">        </span><span class=\"kwd\">if</span><span class=\"pln\"> </span><span class=\"pun\">(</span><span class=\"pln\">response</span><span class=\"pun\">.</span><span class=\"pln\">getStatusLine</span><span class=\"pun\">().</span><span class=\"pln\">getStatusCode</span><span class=\"pun\">()</span><span class=\"pln\"> </span><span class=\"pun\">==</span><span class=\"pln\"> </span><span class=\"lit\">200</span><span class=\"pun\">)</span><span class=\"pln\"> </span><span class=\"pun\">{</span></code></li><li class=\"L0\"><code><span class=\"pln\">            </span><span class=\"typ\">HttpEntity</span><span class=\"pln\"> entity </span><span class=\"pun\">=</span><span class=\"pln\"> response</span><span class=\"pun\">.</span><span class=\"pln\">getEntity</span><span class=\"pun\">();</span></code></li><li class=\"L1\"><code><span class=\"pln\">            </span><span class=\"com\">//使用工具类EntityUtils，从响应中取出实体表示的内容并转换成字符串</span></code></li><li class=\"L2\"><code><span class=\"pln\">            </span><span class=\"typ\">String</span><span class=\"pln\"> </span><span class=\"kwd\">string</span><span class=\"pln\"> </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"typ\">EntityUtils</span><span class=\"pun\">.</span><span class=\"pln\">toString</span><span class=\"pun\">(</span><span class=\"pln\">entity</span><span class=\"pun\">,</span><span class=\"pln\"> </span><span class=\"str\">\"UTF-8\"</span><span class=\"pun\">);</span></code></li><li class=\"L3\"><code><span class=\"pln\">            </span><span class=\"typ\">System</span><span class=\"pun\">.</span><span class=\"kwd\">out</span><span class=\"pun\">.</span><span class=\"pln\">println</span><span class=\"pun\">(</span><span class=\"kwd\">string</span><span class=\"pun\">);</span></code></li><li class=\"L4\"><code><span class=\"pln\">        </span><span class=\"pun\">}</span></code></li><li class=\"L5\"><code><span class=\"pln\">        </span><span class=\"com\">//5.关闭资源</span></code></li><li class=\"L6\"><code><span class=\"pln\">        response</span><span class=\"pun\">.</span><span class=\"pln\">close</span><span class=\"pun\">();</span></code></li><li class=\"L7\"><code><span class=\"pln\">        httpClient</span><span class=\"pun\">.</span><span class=\"pln\">close</span><span class=\"pun\">();</span></code></li></ol></pre><h5 id=\"h5--post-\"><a name=\"发送POST请求\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>发送POST请求</h5><pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"pln\">        </span><span class=\"com\">//获取客户端</span></code></li><li class=\"L1\"><code><span class=\"pln\">        </span><span class=\"typ\">CloseableHttpClient</span><span class=\"pln\"> client </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"typ\">HttpClients</span><span class=\"pun\">.</span><span class=\"pln\">createDefault</span><span class=\"pun\">();</span></code></li><li class=\"L2\"><code></code></li><li class=\"L3\"><code><span class=\"pln\">        </span><span class=\"com\">//声明POST请求</span></code></li><li class=\"L4\"><code><span class=\"pln\">        </span><span class=\"typ\">HttpPost</span><span class=\"pln\"> request </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"kwd\">new</span><span class=\"pln\"> </span><span class=\"typ\">HttpPost</span><span class=\"pun\">(</span><span class=\"str\">\"https://gitee.com/oauth/token\"</span><span class=\"pun\">);</span></code></li><li class=\"L5\"><code></code></li><li class=\"L6\"><code><span class=\"pln\">        </span><span class=\"com\">//设置User-Agent</span></code></li><li class=\"L7\"><code><span class=\"pln\">        request</span><span class=\"pun\">.</span><span class=\"pln\">addHeader</span><span class=\"pun\">(</span><span class=\"str\">\"User-Agent\"</span><span class=\"pun\">,</span><span class=\"pln\"> </span><span class=\"str\">\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36\"</span><span class=\"pun\">);</span></code></li><li class=\"L8\"><code></code></li><li class=\"L9\"><code><span class=\"pln\">        </span><span class=\"com\">//构建请求参数</span></code></li><li class=\"L0\"><code><span class=\"pln\">        </span><span class=\"typ\">ArrayList</span><span class=\"pun\">&lt;</span><span class=\"typ\">NameValuePair</span><span class=\"pun\">&gt;</span><span class=\"pln\"> parameters </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"kwd\">new</span><span class=\"pln\"> </span><span class=\"typ\">ArrayList</span><span class=\"pun\">&lt;&gt;();</span></code></li><li class=\"L1\"><code><span class=\"pln\">        parameters</span><span class=\"pun\">.</span><span class=\"pln\">add</span><span class=\"pun\">(</span><span class=\"kwd\">new</span><span class=\"pln\"> </span><span class=\"typ\">BasicNameValuePair</span><span class=\"pun\">(</span><span class=\"str\">\"grant_type\"</span><span class=\"pun\">,</span><span class=\"pln\"> </span><span class=\"str\">\"authorization_code\"</span><span class=\"pun\">));</span></code></li><li class=\"L2\"><code><span class=\"pln\">        parameters</span><span class=\"pun\">.</span><span class=\"pln\">add</span><span class=\"pun\">(</span><span class=\"kwd\">new</span><span class=\"pln\"> </span><span class=\"typ\">BasicNameValuePair</span><span class=\"pun\">(</span><span class=\"str\">\"code\"</span><span class=\"pun\">,</span><span class=\"pln\"> </span><span class=\"str\">\"xxx\"</span><span class=\"pun\">));</span></code></li><li class=\"L3\"><code><span class=\"pln\">        parameters</span><span class=\"pun\">.</span><span class=\"pln\">add</span><span class=\"pun\">(</span><span class=\"kwd\">new</span><span class=\"pln\"> </span><span class=\"typ\">BasicNameValuePair</span><span class=\"pun\">(</span><span class=\"str\">\"client_id\"</span><span class=\"pun\">,</span><span class=\"pln\"> </span><span class=\"str\">\"xxx\"</span><span class=\"pun\">));</span></code></li><li class=\"L4\"><code><span class=\"pln\">        parameters</span><span class=\"pun\">.</span><span class=\"pln\">add</span><span class=\"pun\">(</span><span class=\"kwd\">new</span><span class=\"pln\"> </span><span class=\"typ\">BasicNameValuePair</span><span class=\"pun\">(</span><span class=\"str\">\"client_secret\"</span><span class=\"pun\">,</span><span class=\"pln\"> </span><span class=\"str\">\"xxx\"</span><span class=\"pun\">));</span></code></li><li class=\"L5\"><code><span class=\"pln\">        parameters</span><span class=\"pun\">.</span><span class=\"pln\">add</span><span class=\"pun\">(</span><span class=\"kwd\">new</span><span class=\"pln\"> </span><span class=\"typ\">BasicNameValuePair</span><span class=\"pun\">(</span><span class=\"str\">\"redirect_uri\"</span><span class=\"pun\">,</span><span class=\"pln\"> </span><span class=\"str\">\"http://localhost:8001/oauth/gitee/callback\"</span><span class=\"pun\">));</span></code></li><li class=\"L6\"><code></code></li><li class=\"L7\"><code><span class=\"pln\">        </span><span class=\"typ\">UrlEncodedFormEntity</span><span class=\"pln\"> urlEncodedFormEntity </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"kwd\">new</span><span class=\"pln\"> </span><span class=\"typ\">UrlEncodedFormEntity</span><span class=\"pun\">(</span><span class=\"pln\">parameters</span><span class=\"pun\">,</span><span class=\"pln\"> </span><span class=\"str\">\"UTF-8\"</span><span class=\"pun\">);</span></code></li><li class=\"L8\"><code></code></li><li class=\"L9\"><code><span class=\"pln\">        </span><span class=\"com\">//设置请求的参数</span></code></li><li class=\"L0\"><code><span class=\"pln\">        request</span><span class=\"pun\">.</span><span class=\"pln\">setEntity</span><span class=\"pun\">(</span><span class=\"pln\">urlEncodedFormEntity</span><span class=\"pun\">);</span></code></li><li class=\"L1\"><code></code></li><li class=\"L2\"><code><span class=\"pln\">        </span><span class=\"com\">//发送请求</span></code></li><li class=\"L3\"><code><span class=\"pln\">        </span><span class=\"typ\">CloseableHttpResponse</span><span class=\"pln\"> response </span><span class=\"pun\">=</span><span class=\"pln\"> client</span><span class=\"pun\">.</span><span class=\"pln\">execute</span><span class=\"pun\">(</span><span class=\"pln\">request</span><span class=\"pun\">);</span></code></li><li class=\"L4\"><code><span class=\"pln\">        </span><span class=\"typ\">System</span><span class=\"pun\">.</span><span class=\"kwd\">out</span><span class=\"pun\">.</span><span class=\"pln\">println</span><span class=\"pun\">(</span><span class=\"pln\">response</span><span class=\"pun\">.</span><span class=\"pln\">getStatusLine</span><span class=\"pun\">().</span><span class=\"pln\">getStatusCode</span><span class=\"pun\">());</span></code></li><li class=\"L5\"><code><span class=\"pln\">        </span><span class=\"kwd\">if</span><span class=\"pln\"> </span><span class=\"pun\">(</span><span class=\"pln\">response</span><span class=\"pun\">.</span><span class=\"pln\">getStatusLine</span><span class=\"pun\">().</span><span class=\"pln\">getStatusCode</span><span class=\"pun\">()</span><span class=\"pln\"> </span><span class=\"pun\">==</span><span class=\"pln\"> </span><span class=\"lit\">200</span><span class=\"pun\">)</span><span class=\"pln\"> </span><span class=\"pun\">{</span></code></li><li class=\"L6\"><code><span class=\"pln\">            </span><span class=\"typ\">HttpEntity</span><span class=\"pln\"> entity </span><span class=\"pun\">=</span><span class=\"pln\"> response</span><span class=\"pun\">.</span><span class=\"pln\">getEntity</span><span class=\"pun\">();</span></code></li><li class=\"L7\"><code><span class=\"pln\">            </span><span class=\"typ\">String</span><span class=\"pln\"> body </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"typ\">EntityUtils</span><span class=\"pun\">.</span><span class=\"pln\">toString</span><span class=\"pun\">(</span><span class=\"pln\">entity</span><span class=\"pun\">,</span><span class=\"pln\"> </span><span class=\"str\">\"UTF-8\"</span><span class=\"pun\">);</span></code></li><li class=\"L8\"><code></code></li><li class=\"L9\"><code><span class=\"pln\">            </span><span class=\"typ\">System</span><span class=\"pun\">.</span><span class=\"kwd\">out</span><span class=\"pun\">.</span><span class=\"pln\">println</span><span class=\"pun\">(</span><span class=\"pln\">body</span><span class=\"pun\">);</span></code></li><li class=\"L0\"><code><span class=\"pln\">        </span><span class=\"pun\">}</span><span class=\"pln\"> </span><span class=\"kwd\">else</span><span class=\"pln\"> </span><span class=\"pun\">{</span></code></li><li class=\"L1\"><code><span class=\"pln\">            </span><span class=\"typ\">System</span><span class=\"pun\">.</span><span class=\"kwd\">out</span><span class=\"pun\">.</span><span class=\"pln\">println</span><span class=\"pun\">(</span><span class=\"typ\">EntityUtils</span><span class=\"pun\">.</span><span class=\"pln\">toString</span><span class=\"pun\">(</span><span class=\"pln\">response</span><span class=\"pun\">.</span><span class=\"pln\">getEntity</span><span class=\"pun\">(),</span><span class=\"pln\"> </span><span class=\"str\">\"UTF-8\"</span><span class=\"pun\">));</span></code></li><li class=\"L2\"><code><span class=\"pln\">        </span><span class=\"pun\">}</span></code></li><li class=\"L3\"><code></code></li><li class=\"L4\"><code><span class=\"pln\">        </span><span class=\"com\">//关闭资源</span></code></li><li class=\"L5\"><code><span class=\"pln\">        response</span><span class=\"pun\">.</span><span class=\"pln\">close</span><span class=\"pun\">();</span></code></li><li class=\"L6\"><code><span class=\"pln\">        client</span><span class=\"pun\">.</span><span class=\"pln\">close</span><span class=\"pun\">();</span></code></li></ol></pre>',1,0,409,'2020-08-26 17:45:41',0.0017,'2020-08-26 09:44:39','2020-12-09 16:05:32',0,0),(1336963516165324716,1,1336960357791449089,'vagrant up启动卡在Booting VM','调试模式查看更错错误信息\n```\nvagrant up --debug\n```','<p>调试模式查看更错错误信息</p>\n<pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"pln\">vagrant up </span><span class=\"pun\">--</span><span class=\"pln\">debug</span></code></li></ol></pre>',0,0,3,'2020-12-30 05:27:23',0.0000,'2020-09-14 01:54:05','2020-09-14 01:54:05',0,0),(1336963516165324717,1,1336960357791449089,'Spark SQL','占坑','<p>占坑</p>\n',0,0,5,'2020-12-30 13:27:23',0.0000,'2020-09-19 02:15:31','2020-09-19 02:15:31',0,0),(1336963516165324718,1,1336960357791449089,'k8s','环境:Centos7\n\n###  Kubernetes简介和安装\n安装docker\n> yum install docker -y\n\nminikube 本地安装\nhttps://developer.aliyun.com/article/221687\n\n```\ncurl -Lo minikube https://kubernetes.oss-cn-hangzhou.aliyuncs.com/minikube/releases/v1.13.0/minikube-linux-amd64 && chmod +x minikube && sudo mv minikube /usr/local/bin/\n```\n\n\n\nkubeadm 集群安装\n\n\n### Kubernetes中的基本概念和操作\n#### 1 kubectl的基本使用\n#### 2 k8s的节点和标签\n#### 3 k8s调度的最小单位pod(上)\n#### 4 k8s调度的最小单位pod(下)\n#### 5 Namespace命名空间\n#### 6 创建我们自己的context\n#### 7 Controller和Deployment\n#### 8 deployment的演示\n#### 9 Replicaset在Deployment更新中的作用\n\n### 容器的的运维和监控\n#### 1 容器的基本监控\n#### 2 k8s集群运行资源监控——Heapster+Grafana+InfluxDB\n#### 3 根据资源占用自动横向伸缩\n#### 4 k8s集群Log的采集和展示——ELK+Fluentd\n#### 5 k8s集群监控方案Prometheus','<p>环境:Centos7</p>\n<h3 id=\"h3-kubernetes-\"><a name=\"Kubernetes简介和安装\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>Kubernetes简介和安装</h3><p>安装docker</p>\n<blockquote>\n<p>yum install docker -y</p>\n</blockquote>\n<p>minikube 本地安装<br><a href=\"https://developer.aliyun.com/article/221687\">https://developer.aliyun.com/article/221687</a></p>\n<pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"pln\">curl </span><span class=\"pun\">-</span><span class=\"typ\">Lo</span><span class=\"pln\"> minikube https</span><span class=\"pun\">:</span><span class=\"com\">//kubernetes.oss-cn-hangzhou.aliyuncs.com/minikube/releases/v1.13.0/minikube-linux-amd64 &amp;&amp; chmod +x minikube &amp;&amp; sudo mv minikube /usr/local/bin/</span></code></li></ol></pre><p>kubeadm 集群安装</p>\n<h3 id=\"h3-kubernetes-\"><a name=\"Kubernetes中的基本概念和操作\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>Kubernetes中的基本概念和操作</h3><h4 id=\"h4-1-kubectl-\"><a name=\"1 kubectl的基本使用\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>1 kubectl的基本使用</h4><h4 id=\"h4-2-k8s-\"><a name=\"2 k8s的节点和标签\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>2 k8s的节点和标签</h4><h4 id=\"h4-3-k8s-pod-\"><a name=\"3 k8s调度的最小单位pod(上)\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>3 k8s调度的最小单位pod(上)</h4><h4 id=\"h4-4-k8s-pod-\"><a name=\"4 k8s调度的最小单位pod(下)\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>4 k8s调度的最小单位pod(下)</h4><h4 id=\"h4-5-namespace-\"><a name=\"5 Namespace命名空间\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>5 Namespace命名空间</h4><h4 id=\"h4-6-context\"><a name=\"6 创建我们自己的context\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>6 创建我们自己的context</h4><h4 id=\"h4-7-controller-deployment\"><a name=\"7 Controller和Deployment\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>7 Controller和Deployment</h4><h4 id=\"h4-8-deployment-\"><a name=\"8 deployment的演示\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>8 deployment的演示</h4><h4 id=\"h4-9-replicaset-deployment-\"><a name=\"9 Replicaset在Deployment更新中的作用\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>9 Replicaset在Deployment更新中的作用</h4><h3 id=\"h3-u5BB9u5668u7684u7684u8FD0u7EF4u548Cu76D1u63A7\"><a name=\"容器的的运维和监控\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>容器的的运维和监控</h3><h4 id=\"h4-1-\"><a name=\"1 容器的基本监控\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>1 容器的基本监控</h4><h4 id=\"h4-2-k8s-heapster-grafana-influxdb\"><a name=\"2 k8s集群运行资源监控——Heapster+Grafana+InfluxDB\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>2 k8s集群运行资源监控——Heapster+Grafana+InfluxDB</h4><h4 id=\"h4-3-\"><a name=\"3 根据资源占用自动横向伸缩\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>3 根据资源占用自动横向伸缩</h4><h4 id=\"h4-4-k8s-log-elk-fluentd\"><a name=\"4 k8s集群Log的采集和展示——ELK+Fluentd\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>4 k8s集群Log的采集和展示——ELK+Fluentd</h4><h4 id=\"h4-5-k8s-prometheus\"><a name=\"5 k8s集群监控方案Prometheus\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>5 k8s集群监控方案Prometheus</h4>',0,0,8,'2020-12-30 13:27:23',0.0000,'2020-09-19 07:46:42','2020-09-19 07:46:42',0,0),(1336963516165324719,1,1336960357791449089,'lombok使用','##### 通过使用 @**Data** 和 @**Builder** 注解生成的类没有无参构造函数，导致mybatis构建实体时报错\n\n解决方式：通过在实体类中手动添加一个无参构造函数并且为无参构造函数添加 @**Tolerate** 注解,如下：\n```\n@Data\n@Builder\npublic class Column {\n    private int id;\n    private String name;\n    private int sort;\n\n    @Tolerate\n    Column(){}\n}\n```\n\n**注：这个错误在编译时不会报出来，可能在运行时才会抛出异常**','<h5 id=\"h5--strong-data-strong-strong-builder-strong-mybatis-\"><a name=\"通过使用 @<strong>Data</strong> 和 @<strong>Builder</strong> 注解生成的类没有无参构造函数，导致mybatis构建实体时报错\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>通过使用 @<strong>Data</strong> 和 @<strong>Builder</strong> 注解生成的类没有无参构造函数，导致mybatis构建实体时报错</h5><p>解决方式：通过在实体类中手动添加一个无参构造函数并且为无参构造函数添加 @<strong>Tolerate</strong> 注解,如下：</p>\n<pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"lit\">@Data</span></code></li><li class=\"L1\"><code><span class=\"lit\">@Builder</span></code></li><li class=\"L2\"><code><span class=\"kwd\">public</span><span class=\"pln\"> </span><span class=\"kwd\">class</span><span class=\"pln\"> </span><span class=\"typ\">Column</span><span class=\"pln\"> </span><span class=\"pun\">{</span></code></li><li class=\"L3\"><code><span class=\"pln\">    </span><span class=\"kwd\">private</span><span class=\"pln\"> </span><span class=\"kwd\">int</span><span class=\"pln\"> id</span><span class=\"pun\">;</span></code></li><li class=\"L4\"><code><span class=\"pln\">    </span><span class=\"kwd\">private</span><span class=\"pln\"> </span><span class=\"typ\">String</span><span class=\"pln\"> name</span><span class=\"pun\">;</span></code></li><li class=\"L5\"><code><span class=\"pln\">    </span><span class=\"kwd\">private</span><span class=\"pln\"> </span><span class=\"kwd\">int</span><span class=\"pln\"> sort</span><span class=\"pun\">;</span></code></li><li class=\"L6\"><code></code></li><li class=\"L7\"><code><span class=\"pln\">    </span><span class=\"lit\">@Tolerate</span></code></li><li class=\"L8\"><code><span class=\"pln\">    </span><span class=\"typ\">Column</span><span class=\"pun\">(){}</span></code></li><li class=\"L9\"><code><span class=\"pun\">}</span></code></li></ol></pre><p><strong>注：这个错误在编译时不会报出来，可能在运行时才会抛出异常</strong></p>\n',0,0,12,'2020-12-30 13:27:23',0.0000,'2020-09-21 03:24:45','2020-09-21 03:24:45',0,0),(1336963516165324720,1,1336960357791449089,'Linux 硬盘 内存 CPU查看命令','##### 硬盘\n查看硬盘大小\n> df -h\n\n查看目录文件的大小\n>du -h\n\n##### 内存\n查看内存大小\n>free -h\n\n##### CPU\n查看CPU个数\n> lscpu','<h5 id=\"h5-u786Cu76D8\"><a name=\"硬盘\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>硬盘</h5><p>查看硬盘大小</p>\n<blockquote>\n<p>df -h</p>\n</blockquote>\n<p>查看目录文件的大小</p>\n<blockquote>\n<p>du -h</p>\n</blockquote>\n<h5 id=\"h5-u5185u5B58\"><a name=\"内存\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>内存</h5><p>查看内存大小</p>\n<blockquote>\n<p>free -h</p>\n</blockquote>\n<h5 id=\"h5-cpu\"><a name=\"CPU\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>CPU</h5><p>查看CPU个数</p>\n<blockquote>\n<p>lscpu</p>\n</blockquote>\n',1,0,116,'2020-09-25 15:45:36',0.0015,'2020-09-07 01:47:35','2020-09-07 01:47:35',0,0),(1336963516165324721,1,1336960357791449089,'用Jenkins做测试环境的自动化构建与部署','#### 环境准备\n\n#### 安装\n##### 配置yum源\n> sudo wget -O /etc/yum.repos.d/jenkins.repo https://pkg.jenkins.io/redhat-stable/jenkins.repo\n\n##### 导入rpm密钥\n```\nrpm  --import  https://jenkins-ci.org/redhat/jenkins-ci.org.key\n```\n\n##### 安装kenkins\n> yum install jenkins -y\n\n##### 启动\n> systemctl start jenkins\n\n启动之后浏览器访问127.0.0.1:8080根据提示进行初始化\n\n1. 初始化密码\n2. 选择要安装的插件\n3. 创建管理员用户\n\n\n安装插件\nrebuilder\nsafe restart\n\n安全配置（默认用户登录后可以做所有操作，不安全）\n\n#### 自动化部署','<h4 id=\"h4-u73AFu5883u51C6u5907\"><a name=\"环境准备\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>环境准备</h4><h4 id=\"h4-u5B89u88C5\"><a name=\"安装\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>安装</h4><h5 id=\"h5--yum-\"><a name=\"配置yum源\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>配置yum源</h5><blockquote>\n<p>sudo wget -O /etc/yum.repos.d/jenkins.repo <a href=\"https://pkg.jenkins.io/redhat-stable/jenkins.repo\">https://pkg.jenkins.io/redhat-stable/jenkins.repo</a></p>\n</blockquote>\n<h5 id=\"h5--rpm-\"><a name=\"导入rpm密钥\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>导入rpm密钥</h5><pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"pln\">rpm  </span><span class=\"pun\">--</span><span class=\"kwd\">import</span><span class=\"pln\">  https</span><span class=\"pun\">:</span><span class=\"com\">//jenkins-ci.org/redhat/jenkins-ci.org.key</span></code></li></ol></pre><h5 id=\"h5--kenkins\"><a name=\"安装kenkins\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>安装kenkins</h5><blockquote>\n<p>yum install jenkins -y</p>\n</blockquote>\n<h5 id=\"h5-u542Fu52A8\"><a name=\"启动\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>启动</h5><blockquote>\n<p>systemctl start jenkins</p>\n</blockquote>\n<p>启动之后浏览器访问127.0.0.1:8080根据提示进行初始化</p>\n<ol>\n<li>初始化密码</li><li>选择要安装的插件</li><li>创建管理员用户</li></ol>\n<p>安装插件<br>rebuilder<br>safe restart</p>\n<p>安全配置（默认用户登录后可以做所有操作，不安全）</p>\n<h4 id=\"h4-u81EAu52A8u5316u90E8u7F72\"><a name=\"自动化部署\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>自动化部署</h4>',0,0,5,'2020-12-30 13:27:23',0.0000,'2020-10-10 13:53:58','2020-10-10 13:53:58',0,0),(1336963516165324722,1,1336960357791449089,'Jenkins+K8s实现持续集成','占坑','<p>占坑</p>\n',0,0,3,'2020-12-30 13:27:23',10.0000,'2020-10-11 04:13:12','2020-10-11 04:13:12',0,0),(1336963516165324723,1,1336960357791449089,'Docker使用说明','### Docker安装\n\n### Docker的镜像和容器\n#### 1 Docker架构和底层技术简介\ndocker提供了一个开发，打包，运行app的平台\n把app底层和infrastructure隔离开来\n\n**application**\n\n**docker engine**\n1. 后台进程(dockerd)\n2. REST API Server\n3. CLI接口（docker）\n\n**infrastructure(physical/virtual)**\n\n\n\n#### 2 Docker Image概述\n#### 3 DIY一个Base Image\n#### 4 初识Container\n#### 5 构建自己的Docker镜像\n#### 6 Dockerfile语法梳理及最佳实践\n#### 7 RUN vs CMD vs Entrypoint\n#### 8 镜像的发布\n#### 9 Dockerfile实战\n#### 10 容器的操作\n#### 11 Dockerfile实战(2)\n#### 12 容器的资源限制\n\n### Docker的网络\n#### 4-1 本章概述和实验环境介绍\n#### 4-2 网络基础回顾\n#### 4-3 Linux网络命名空间\n#### 4-4 Docker bridge0详解\n#### 4-5 容器之间的link\n#### 4-6 容器的端口映射\n#### 4-7 容器网络之host和none\n#### 4-8 多容器复杂应用的部署演示\n#### 4-9 Overlay和Underlay的通俗解释\n#### 4-10 Docker Overlay网络和etcd实现多机容器通信\n\n### Docker的持久化存储和数据共享\n#### 5-1 本章介绍\n#### 5-2 本章实验环境介绍\n#### 5-3 数据持久化之Data Volume\n#### 5-4 数据持久化之Bind Mouting\n#### 5-5 开发者利器-Docker+Bind Mout\n\n### Docker Compose多容器部署\n#### 6-1 根据前面所学部署一个wordpress\n#### 6-2 Docker Compose到底是什么\n#### 6-3 Docker Compose的安装和基本使用\n#### 6-4 水平扩展和负载均衡\n#### 6-5 部署一个复杂的投票应用\n\n\n### 容器编排Docker Swarm\n#### 7-1 容器编排Swarm介绍\n#### 7-2 创建一个三节点的swarm集群\n#### 7-3 Service的创建维护和水平扩展\n#### 7-4 在swarm集群里通过service部署wordpress\n#### 7-5 集群服务间通信之Routing Mesh\n#### 7-6 Routing Mesh之Ingress负载均衡\n#### 7-7 Docker Stack部署Wordpress\n#### 7-8 作业解答之部署投票应用\n#### 7-9 Docker Secret管理和使用\n#### 7-10 Docker Secret在Stack中的使用\n#### 7-11 Service更新','<h3 id=\"h3-docker-\"><a name=\"Docker安装\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>Docker安装</h3><h3 id=\"h3-docker-\"><a name=\"Docker的镜像和容器\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>Docker的镜像和容器</h3><h4 id=\"h4-1-docker-\"><a name=\"1 Docker架构和底层技术简介\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>1 Docker架构和底层技术简介</h4><p>docker提供了一个开发，打包，运行app的平台<br>把app底层和infrastructure隔离开来</p>\n<p><strong>application</strong></p>\n<p><strong>docker engine</strong></p>\n<ol>\n<li>后台进程(dockerd)</li><li>REST API Server</li><li>CLI接口（docker）</li></ol>\n<p><strong>infrastructure(physical/virtual)</strong></p>\n<h4 id=\"h4-2-docker-image-\"><a name=\"2 Docker Image概述\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>2 Docker Image概述</h4><h4 id=\"h4-3-diy-base-image\"><a name=\"3 DIY一个Base Image\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>3 DIY一个Base Image</h4><h4 id=\"h4-4-container\"><a name=\"4 初识Container\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>4 初识Container</h4><h4 id=\"h4-5-docker-\"><a name=\"5 构建自己的Docker镜像\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>5 构建自己的Docker镜像</h4><h4 id=\"h4-6-dockerfile-\"><a name=\"6 Dockerfile语法梳理及最佳实践\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>6 Dockerfile语法梳理及最佳实践</h4><h4 id=\"h4-7-run-vs-cmd-vs-entrypoint\"><a name=\"7 RUN vs CMD vs Entrypoint\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>7 RUN vs CMD vs Entrypoint</h4><h4 id=\"h4-8-\"><a name=\"8 镜像的发布\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>8 镜像的发布</h4><h4 id=\"h4-9-dockerfile-\"><a name=\"9 Dockerfile实战\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>9 Dockerfile实战</h4><h4 id=\"h4-10-\"><a name=\"10 容器的操作\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>10 容器的操作</h4><h4 id=\"h4-11-dockerfile-2-\"><a name=\"11 Dockerfile实战(2)\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>11 Dockerfile实战(2)</h4><h4 id=\"h4-12-\"><a name=\"12 容器的资源限制\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>12 容器的资源限制</h4><h3 id=\"h3-docker-\"><a name=\"Docker的网络\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>Docker的网络</h3><h4 id=\"h4-4-1-\"><a name=\"4-1 本章概述和实验环境介绍\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>4-1 本章概述和实验环境介绍</h4><h4 id=\"h4-4-2-\"><a name=\"4-2 网络基础回顾\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>4-2 网络基础回顾</h4><h4 id=\"h4-4-3-linux-\"><a name=\"4-3 Linux网络命名空间\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>4-3 Linux网络命名空间</h4><h4 id=\"h4-4-4-docker-bridge0-\"><a name=\"4-4 Docker bridge0详解\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>4-4 Docker bridge0详解</h4><h4 id=\"h4-4-5-link\"><a name=\"4-5 容器之间的link\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>4-5 容器之间的link</h4><h4 id=\"h4-4-6-\"><a name=\"4-6 容器的端口映射\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>4-6 容器的端口映射</h4><h4 id=\"h4-4-7-host-none\"><a name=\"4-7 容器网络之host和none\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>4-7 容器网络之host和none</h4><h4 id=\"h4-4-8-\"><a name=\"4-8 多容器复杂应用的部署演示\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>4-8 多容器复杂应用的部署演示</h4><h4 id=\"h4-4-9-overlay-underlay-\"><a name=\"4-9 Overlay和Underlay的通俗解释\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>4-9 Overlay和Underlay的通俗解释</h4><h4 id=\"h4-4-10-docker-overlay-etcd-\"><a name=\"4-10 Docker Overlay网络和etcd实现多机容器通信\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>4-10 Docker Overlay网络和etcd实现多机容器通信</h4><h3 id=\"h3-docker-\"><a name=\"Docker的持久化存储和数据共享\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>Docker的持久化存储和数据共享</h3><h4 id=\"h4-5-1-\"><a name=\"5-1 本章介绍\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>5-1 本章介绍</h4><h4 id=\"h4-5-2-\"><a name=\"5-2 本章实验环境介绍\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>5-2 本章实验环境介绍</h4><h4 id=\"h4-5-3-data-volume\"><a name=\"5-3 数据持久化之Data Volume\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>5-3 数据持久化之Data Volume</h4><h4 id=\"h4-5-4-bind-mouting\"><a name=\"5-4 数据持久化之Bind Mouting\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>5-4 数据持久化之Bind Mouting</h4><h4 id=\"h4-5-5-docker-bind-mout\"><a name=\"5-5 开发者利器-Docker+Bind Mout\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>5-5 开发者利器-Docker+Bind Mout</h4><h3 id=\"h3-docker-compose-\"><a name=\"Docker Compose多容器部署\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>Docker Compose多容器部署</h3><h4 id=\"h4-6-1-wordpress\"><a name=\"6-1 根据前面所学部署一个wordpress\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>6-1 根据前面所学部署一个wordpress</h4><h4 id=\"h4-6-2-docker-compose-\"><a name=\"6-2 Docker Compose到底是什么\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>6-2 Docker Compose到底是什么</h4><h4 id=\"h4-6-3-docker-compose-\"><a name=\"6-3 Docker Compose的安装和基本使用\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>6-3 Docker Compose的安装和基本使用</h4><h4 id=\"h4-6-4-\"><a name=\"6-4 水平扩展和负载均衡\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>6-4 水平扩展和负载均衡</h4><h4 id=\"h4-6-5-\"><a name=\"6-5 部署一个复杂的投票应用\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>6-5 部署一个复杂的投票应用</h4><h3 id=\"h3--docker-swarm\"><a name=\"容器编排Docker Swarm\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>容器编排Docker Swarm</h3><h4 id=\"h4-7-1-swarm-\"><a name=\"7-1 容器编排Swarm介绍\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>7-1 容器编排Swarm介绍</h4><h4 id=\"h4-7-2-swarm-\"><a name=\"7-2 创建一个三节点的swarm集群\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>7-2 创建一个三节点的swarm集群</h4><h4 id=\"h4-7-3-service-\"><a name=\"7-3 Service的创建维护和水平扩展\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>7-3 Service的创建维护和水平扩展</h4><h4 id=\"h4-7-4-swarm-service-wordpress\"><a name=\"7-4 在swarm集群里通过service部署wordpress\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>7-4 在swarm集群里通过service部署wordpress</h4><h4 id=\"h4-7-5-routing-mesh\"><a name=\"7-5 集群服务间通信之Routing Mesh\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>7-5 集群服务间通信之Routing Mesh</h4><h4 id=\"h4-7-6-routing-mesh-ingress-\"><a name=\"7-6 Routing Mesh之Ingress负载均衡\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>7-6 Routing Mesh之Ingress负载均衡</h4><h4 id=\"h4-7-7-docker-stack-wordpress\"><a name=\"7-7 Docker Stack部署Wordpress\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>7-7 Docker Stack部署Wordpress</h4><h4 id=\"h4-7-8-\"><a name=\"7-8 作业解答之部署投票应用\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>7-8 作业解答之部署投票应用</h4><h4 id=\"h4-7-9-docker-secret-\"><a name=\"7-9 Docker Secret管理和使用\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>7-9 Docker Secret管理和使用</h4><h4 id=\"h4-7-10-docker-secret-stack-\"><a name=\"7-10 Docker Secret在Stack中的使用\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>7-10 Docker Secret在Stack中的使用</h4><h4 id=\"h4-7-11-service-\"><a name=\"7-11 Service更新\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>7-11 Service更新</h4>',0,0,16,'2020-12-30 13:27:23',0.0000,'2020-10-11 13:29:27','2020-10-11 13:29:27',0,0),(1336963516165324799,1,1336960357791449089,'shell 命令行中格式化输出 json','> echo \'{\"key\":\"value\"}\' | python -mjson.tool\n\n效果如下\n\n```\n[root@localhost ~]# echo \'{\"key\":\"value\"}\' | python -mjson.tool\n{\n    \"key\": \"value\"\n}\n\n```','<blockquote>\n<p>echo ‘{“key”:”value”}’ | python -mjson.tool</p>\n</blockquote>\n<p>效果如下</p>\n<pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"pun\">[</span><span class=\"pln\">root@localhost </span><span class=\"pun\">~]#</span><span class=\"pln\"> echo </span><span class=\"str\">\'{\"key\":\"value\"}\'</span><span class=\"pln\"> </span><span class=\"pun\">|</span><span class=\"pln\"> python </span><span class=\"pun\">-</span><span class=\"pln\">mjson</span><span class=\"pun\">.</span><span class=\"pln\">tool</span></code></li><li class=\"L1\"><code><span class=\"pun\">{</span></code></li><li class=\"L2\"><code><span class=\"pln\">    </span><span class=\"str\">\"key\"</span><span class=\"pun\">:</span><span class=\"pln\"> </span><span class=\"str\">\"value\"</span></code></li><li class=\"L3\"><code><span class=\"pun\">}</span></code></li></ol></pre>',1,0,196,'2020-09-10 19:48:50',0.0015,'2020-09-10 19:48:50','2020-09-10 19:48:50',0,0),(1336963516165324800,1,1336960357791449089,'使用openssl的随机函数生成随机字符串','语法：\n>openssl rand [-out file] [-rand file(s)] [-base64] [-hex] num\n\n参数说明：\n\n|参数|说明|\n|:--:|:--:|\n| -out | 指定随机数输出文件，否则输出到标准输出 |\n|-rand file|指定随机数种子文件。种子文件中的字符越随机，openssl rand生成随机数的速度越快，随机度越高|\n|-base64| 指定生成的随机数的编码格式为base64 |\n|-hex|指定生成的随机数的编码格式为hex|\n|num|指定随机数的长度|\n\n示例如下:\n```\n[root@localhost ~]# openssl rand -base64 32\ngNAL1IMR/zbzYrzWoUUhQzIZUDP4SooScu8wAUesw4g=\n\n[root@localhost ~]# openssl rand -hex 32\n4c5a40cf3f5b6f2fc6a2dc189a1e80e967ce022013aab9e10852e3e272cdafc5\n```','<p>语法：</p>\n<blockquote>\n<p>openssl rand [-out file] [-rand file(s)] [-base64] [-hex] num</p>\n</blockquote>\n<p>参数说明：</p>\n<table>\n<thead>\n<tr>\n<th style=\"text-align:center\">参数</th>\n<th style=\"text-align:center\">说明</th>\n</tr>\n</thead>\n<tbody>\n<tr>\n<td style=\"text-align:center\">-out</td>\n<td style=\"text-align:center\">指定随机数输出文件，否则输出到标准输出</td>\n</tr>\n<tr>\n<td style=\"text-align:center\">-rand file</td>\n<td style=\"text-align:center\">指定随机数种子文件。种子文件中的字符越随机，openssl rand生成随机数的速度越快，随机度越高</td>\n</tr>\n<tr>\n<td style=\"text-align:center\">-base64</td>\n<td style=\"text-align:center\">指定生成的随机数的编码格式为base64</td>\n</tr>\n<tr>\n<td style=\"text-align:center\">-hex</td>\n<td style=\"text-align:center\">指定生成的随机数的编码格式为hex</td>\n</tr>\n<tr>\n<td style=\"text-align:center\">num</td>\n<td style=\"text-align:center\">指定随机数的长度</td>\n</tr>\n</tbody>\n</table>\n<p>示例如下:</p>\n<pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"pun\">[</span><span class=\"pln\">root@localhost </span><span class=\"pun\">~]#</span><span class=\"pln\"> openssl rand </span><span class=\"pun\">-</span><span class=\"pln\">base64 </span><span class=\"lit\">32</span></code></li><li class=\"L1\"><code><span class=\"pln\">gNAL1IMR</span><span class=\"pun\">/</span><span class=\"pln\">zbzYrzWoUUhQzIZUDP4SooScu8wAUesw4g</span><span class=\"pun\">=</span></code></li><li class=\"L2\"><code></code></li><li class=\"L3\"><code><span class=\"pun\">[</span><span class=\"pln\">root@localhost </span><span class=\"pun\">~]#</span><span class=\"pln\"> openssl rand </span><span class=\"pun\">-</span><span class=\"pln\">hex </span><span class=\"lit\">32</span></code></li><li class=\"L4\"><code><span class=\"lit\">4c5a40cf3f5b6f2fc6a2dc189a1e80e967ce022013aab9e10852e3e272cdafc5</span></code></li></ol></pre>',1,0,224,'2020-10-08 14:08:06',0.0017,'2020-10-08 14:08:06','2020-10-08 14:08:06',0,0),(1336963516165324801,1,1336960357791449089,'Hello World','```\necho \"hello world\"；\n```','<pre class=\"prettyprint linenums prettyprinted\" style=\"\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"pln\">echo </span><span class=\"str\">\"hello world\"</span><span class=\"pun\">；</span></code></li></ol></pre>',1,3,169,'2020-07-26 19:57:59',0.0017,'2020-07-17 12:54:19','2020-07-17 12:54:19',0,0);
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_config`
--

DROP TABLE IF EXISTS `system_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `system_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `attribute` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `created_at` datetime NOT NULL,
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `updated_at` datetime NOT NULL,
  `value` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `attribute_unique` (`attribute`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_config`
--

LOCK TABLES `system_config` WRITE;
/*!40000 ALTER TABLE `system_config` DISABLE KEYS */;
INSERT INTO `system_config` VALUES (1,'gitee_oauth_client_id','2020-12-28 13:57:22','','2020-12-28 17:17:47',''),(2,'gitee_oauth_client_secret','2020-12-28 13:57:22','','2020-12-28 13:57:22',''),(3,'gitee_oauth_redirect_uri','2020-12-28 13:57:22','','2020-12-28 13:57:22','http://localhost:8001/oauth/gitee/callback'),(4,'github_oauth_client_id','2020-12-28 13:57:22','','2020-12-28 16:31:21','sdfgsg'),(5,'github_oauth_client_secret','2020-12-28 13:57:22','github oauth 秘钥','2020-12-28 17:06:29','asdfag'),(6,'github_oauth_redirect_uri','2020-12-28 13:57:22','','2020-12-28 13:57:22',''),(7,'sitename','2021-08-01 19:03:01','站点名称','2021-08-01 19:03:01','技术社区'),(8,'site_keyword','2021-08-01 19:03:25','站点关键字','2021-08-01 19:03:25','Keyword'),(9,'site_description','2021-08-01 19:03:49','站点描述','2021-08-01 19:03:49','site description');
/*!40000 ALTER TABLE `system_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tags`
--

DROP TABLE IF EXISTS `tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tags` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL COMMENT '标签名称',
  `description` text NOT NULL COMMENT '标签描述',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tags`
--

LOCK TABLES `tags` WRITE;
/*!40000 ALTER TABLE `tags` DISABLE KEYS */;
/*!40000 ALTER TABLE `tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_blacklist`
--

DROP TABLE IF EXISTS `user_blacklist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_blacklist` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `black_user_id` bigint(20) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_blacklist`
--

LOCK TABLES `user_blacklist` WRITE;
/*!40000 ALTER TABLE `user_blacklist` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_blacklist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_categories`
--

DROP TABLE IF EXISTS `user_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_categories` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `name` varchar(256) NOT NULL DEFAULT '' COMMENT '用户分类名称',
  `enable` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否可用',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_categories`
--

LOCK TABLES `user_categories` WRITE;
/*!40000 ALTER TABLE `user_categories` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_collection`
--

DROP TABLE IF EXISTS `user_collection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_collection` (
  `id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `user_id` bigint(20) NOT NULL DEFAULT '0',
  `post_id` bigint(20) NOT NULL DEFAULT '0',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_collection`
--

LOCK TABLES `user_collection` WRITE;
/*!40000 ALTER TABLE `user_collection` DISABLE KEYS */;
INSERT INTO `user_collection` VALUES (1336965494991757314,1336960357791449089,1336963516165324801,'2020-12-10 17:26:28'),(1345032254100930561,1336960357791449089,1336963516165324707,'2021-01-01 15:40:54'),(1345032273046601729,1336960357791449089,1336963516165324703,'2021-01-01 15:40:58'),(1345032290897559553,1336960357791449089,1336963516165324705,'2021-01-01 15:41:02'),(1345032312376590338,1336960357791449089,1336963516165324712,'2021-01-01 15:41:07'),(1345032339547291649,1336960357791449089,1336963516165324704,'2021-01-01 15:41:14'),(1345032369809195010,1336960357791449089,1336963516165324702,'2021-01-01 15:41:21'),(1345032387161030657,1336960357791449089,1336963516165324713,'2021-01-01 15:41:25'),(1345032405309784065,1336960357791449089,1336963516165324701,'2021-01-01 15:41:30'),(1345032429880016898,1336960357791449089,1336963516165324715,'2021-01-01 15:41:35'),(1345636454463815682,1336963516165324799,1336960357791449089,'2021-01-03 07:41:46'),(1345638478391566337,1336960357791449089,1336963516165324799,'2021-01-03 07:49:49'),(1345659536012267521,1336960357791449089,1336963516165324714,'2021-01-03 09:13:29');
/*!40000 ALTER TABLE `user_collection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_follow`
--

DROP TABLE IF EXISTS `user_follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_follow` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `follow_user_id` bigint(20) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_follow`
--

LOCK TABLES `user_follow` WRITE;
/*!40000 ALTER TABLE `user_follow` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_message`
--

DROP TABLE IF EXISTS `user_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_message` (
  `id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `sender_id` bigint(11) NOT NULL DEFAULT '0',
  `receiver_id` bigint(11) NOT NULL DEFAULT '0',
  `type` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `content` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_message`
--

LOCK TABLES `user_message` WRITE;
/*!40000 ALTER TABLE `user_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_post_archive`
--

DROP TABLE IF EXISTS `user_post_archive`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_post_archive` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户Id',
  `year` int(4) NOT NULL DEFAULT '2021' COMMENT '年',
  `month` int(11) NOT NULL DEFAULT '1' COMMENT '月',
  `post_count` int(11) NOT NULL DEFAULT '0' COMMENT '文章数量',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_year_mouth` (`user_id`,`year`,`month`)
) ENGINE=InnoDB AUTO_INCREMENT=1422546445076037634 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_post_archive`
--

LOCK TABLES `user_post_archive` WRITE;
/*!40000 ALTER TABLE `user_post_archive` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_post_archive` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_tags`
--

DROP TABLE IF EXISTS `user_tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_tags` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户Id',
  `tag_id` int(11) NOT NULL DEFAULT '0' COMMENT '标签',
  `name` varchar(256) NOT NULL DEFAULT '' COMMENT '标签名称',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_tags`
--

LOCK TABLES `user_tags` WRITE;
/*!40000 ALTER TABLE `user_tags` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_token`
--

DROP TABLE IF EXISTS `user_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_token` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户Id',
  `type` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT 'token类型',
  `token` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT 'token',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `expire_in` bigint(255) DEFAULT '0' COMMENT '过期时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_token`
--

LOCK TABLES `user_token` WRITE;
/*!40000 ALTER TABLE `user_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) unsigned NOT NULL,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '密码',
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '邮箱',
  `name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '名称',
  `avatar` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '头像',
  `signature` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '清明',
  `city` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '城市',
  `experience` int(11) NOT NULL DEFAULT '0' COMMENT '经验值',
  `is_admin` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否管理员',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1336960357791449089,'peng49','$2a$10$stu3n5ppS2KkPVyjLZrE.eCJ/P4OuoCOZP9ERdQJL633ZtKK2BRi2','mail@t.com','','/static/b35dfb4c-8708-4a91-8839-86b5539c134b.png','S','SZ',0,1,'2020-12-13 08:24:47','2020-12-11 01:06:03');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-03 14:18:28
