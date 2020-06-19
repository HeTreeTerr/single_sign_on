/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : springbootbasic

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2020-06-19 09:27:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `USERNAME` varchar(75) NOT NULL COMMENT '用户名',
  `NAME` varchar(75) DEFAULT NULL COMMENT '用户姓名',
  `PASSWORD` varchar(75) NOT NULL COMMENT '密码',
  `MOBILENUMBER` varchar(20) DEFAULT NULL COMMENT '手机号',
  `BRITHDAY` date DEFAULT NULL COMMENT '生日',
  `SEX` smallint(2) NOT NULL DEFAULT '1' COMMENT '性别：1男2女',
  `HEADIMGURL` varchar(150) DEFAULT NULL COMMENT '头像',
  `TFADMIN` smallint(2) NOT NULL DEFAULT '0' COMMENT '是否管理员',
  `CREATEUSER` varchar(50) NOT NULL COMMENT '创建者名',
  `CREATEDATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATEUSER` varchar(50) DEFAULT NULL COMMENT '修改者名',
  `UPDATEDATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'hss', '何森森', '11111111', '11111111111', '2020-06-19', '1', null, '0', 'hss', '2020-06-19 09:09:29', null, '2020-06-19 09:11:54');
