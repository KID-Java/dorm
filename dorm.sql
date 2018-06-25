/*
SQLyog Enterprise v12.09 (64 bit)
MySQL - 5.7.20 : Database - dorm
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`dorm` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `dorm`;

/*Table structure for table `dormitory` */

DROP TABLE IF EXISTS `dormitory`;

CREATE TABLE `dormitory` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '宿舍ID',
  `apartment` varchar(50) NOT NULL COMMENT '宿舍所属区域',
  `phone` char(11) DEFAULT NULL COMMENT '宿舍电话',
  `equipment` varchar(50) DEFAULT NULL COMMENT '宿舍设备',
  `environment` varchar(50) DEFAULT NULL COMMENT '宿舍卫生状况',
  `frame` varchar(50) DEFAULT NULL COMMENT '宿舍框架',
  `headmasterId` int(11) DEFAULT NULL COMMENT '宿舍长ID',
  `score` double DEFAULT NULL COMMENT '本周评分',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Table structure for table `stu_dorm` */

DROP TABLE IF EXISTS `stu_dorm`;

CREATE TABLE `stu_dorm` (
  `stuId` int(11) NOT NULL,
  `dormId` int(11) NOT NULL,
  PRIMARY KEY (`stuId`,`dormId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学生学号',
  `name` varchar(20) NOT NULL COMMENT '学生姓名',
  `age` int(3) NOT NULL COMMENT '学生年龄',
  `sex` char(2) NOT NULL COMMENT '学生性别',
  `telphone` char(11) DEFAULT NULL COMMENT '学生电话号码',
  `major` varchar(50) DEFAULT NULL COMMENT '学生专业名称',
  `department` varchar(50) DEFAULT NULL COMMENT '学生所属学院名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=346 DEFAULT CHARSET=utf8;

/*Table structure for table `visit_info` */

DROP TABLE IF EXISTS `visit_info`;

CREATE TABLE `visit_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '来访登记信息ID',
  `name` varchar(20) NOT NULL COMMENT '来访者姓名',
  `status` int(1) NOT NULL DEFAULT '2' COMMENT '1：本校 2：非本校',
  `address` varchar(100) DEFAULT NULL COMMENT '来访者地址',
  `date` datetime NOT NULL COMMENT '来访日期',
  `content` text NOT NULL COMMENT '来访内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
