/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50642
Source Host           : localhost:3306
Source Database       : xdclass-shiro2

Target Server Type    : MYSQL
Target Server Version : 50642
File Encoding         : 65001

Date: 2020-04-30 18:04:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL,
  `name` varchar(128) DEFAULT NULL COMMENT '名称',
  `url` varchar(128) DEFAULT NULL COMMENT '接口路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', 'video_update', '/api/video/update');
INSERT INTO `permission` VALUES ('2', 'video_delete', '/api/video/delete');
INSERT INTO `permission` VALUES ('3', 'video_add', '/api/video/add');
INSERT INTO `permission` VALUES ('4', 'order_list', '/api/order/list');
INSERT INTO `permission` VALUES ('5', 'user_list', '/api/user/list');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `description` varchar(64) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'admin', '普通管理员');
INSERT INTO `role` VALUES ('2', 'root', '超级管理员');
INSERT INTO `role` VALUES ('3', 'editor', '审核人员');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` int(11) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  `permission_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1', '3', '1');
INSERT INTO `role_permission` VALUES ('2', '3', '2');
INSERT INTO `role_permission` VALUES ('3', '3', '3');
INSERT INTO `role_permission` VALUES ('4', '2', '1');
INSERT INTO `role_permission` VALUES ('5', '2', '2');
INSERT INTO `role_permission` VALUES ('6', '2', '3');
INSERT INTO `role_permission` VALUES ('7', '2', '4');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(128) DEFAULT NULL,
  `password` varchar(256) DEFAULT NULL,
  `salt` varchar(128) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '盐',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '张三', '123456', null, null);
INSERT INTO `user` VALUES ('2', '王五', '123456', null, null);
INSERT INTO `user` VALUES ('3', '李四', '123456', null, null);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `remarks` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '3', '1', '张三是editor');
INSERT INTO `user_role` VALUES ('2', '1', '3', '李四是admin');
INSERT INTO `user_role` VALUES ('3', '2', '3', '李四是root');
INSERT INTO `user_role` VALUES ('4', '3', '3', '李四是editor');
INSERT INTO `user_role` VALUES ('5', '1', '2', '王五是admin');
