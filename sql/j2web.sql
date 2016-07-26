/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50631
Source Host           : localhost:3306
Source Database       : j2web

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2016-07-26 10:56:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------

-- ----------------------------
-- Table structure for sys_error
-- ----------------------------
DROP TABLE IF EXISTS `sys_error`;
CREATE TABLE `sys_error` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createdate` datetime DEFAULT NULL,
  `modifydate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `operator` varchar(20) DEFAULT NULL,
  `method` varchar(255) DEFAULT NULL,
  `args` varchar(255) DEFAULT NULL,
  `error` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_error
-- ----------------------------

-- ----------------------------
-- Table structure for user_roles
-- ----------------------------
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT 'users表的id',
  `authority` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_roles
-- ----------------------------
INSERT INTO `user_roles` VALUES ('1', '1', 'ROLE_ADMIN');
INSERT INTO `user_roles` VALUES ('2', '2', 'ROLE_USER');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createdate` datetime DEFAULT NULL,
  `modifydate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(150) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `sex` tinyint(4) DEFAULT NULL COMMENT '0: 女; 1: 男',
  `enabled` tinyint(4) DEFAULT '1' COMMENT '1: 启用; 0: 不启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '2016-07-12 15:58:19', '2016-07-12 15:58:19', 'su', '$2a$10$InGRgJWmPM/M6PANsuF2Q.VarLOJkDbCFU6ectzQq/aGqi1wYHDQG', 'shinejaie@gmail.com', '1', '1');
INSERT INTO `users` VALUES ('2', '2016-07-25 10:43:53', '2016-07-25 10:45:02', 'wxj', '$2a$10$InGRgJWmPM/M6PANsuF2Q.VarLOJkDbCFU6ectzQq/aGqi1wYHDQG', '391940025@qq.com', '1', '1');
SET FOREIGN_KEY_CHECKS=1;
