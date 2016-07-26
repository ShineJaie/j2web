/*
Navicat MySQL Data Transfer

Source Server         : 192.168.78.130_3306
Source Server Version : 50630
Source Host           : 192.168.78.130:3306
Source Database       : j2web_slave

Target Server Type    : MYSQL
Target Server Version : 50630
File Encoding         : 65001

Date: 2016-07-27 01:51:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(25) DEFAULT NULL,
  `remark` varchar(125) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'su', '超级管理员账号');
INSERT INTO `users` VALUES ('2', 'wxj', '普通用户账号');
SET FOREIGN_KEY_CHECKS=1;
