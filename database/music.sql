
/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`music` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `music`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `pwd` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `admin` */

insert  into `admin`(`id`,`name`,`pwd`) values (1,'admin','e10adc3949ba59abbe56e057f20f883e'),(3,'cjgong','e10adc3949ba59abbe56e057f20f883e');

/*Table structure for table `comments` */

DROP TABLE IF EXISTS `comments`;

CREATE TABLE `comments` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `value` text,
  `name` varchar(20) DEFAULT NULL,
  `music_id` int(4) DEFAULT NULL,
  `time` varchar(13) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `comments` */

insert  into `comments`(`id`,`value`,`name`,`music_id`,`time`) values (3,'','',4,'1249184435265'),(4,'非常好听，所有人都要听哦！','游客1',5,'1282140270512');


/*Table structure for table `music` */

DROP TABLE IF EXISTS `music`;

CREATE TABLE `music` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `singer` varchar(30) NOT NULL,
  `special` varchar(30) NOT NULL,
  `price` varchar(10) ,
  `value` text NOT NULL,
  `time` varchar(13) NOT NULL,
  `click` int(5) NOT NULL,
  `url` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `music` */

insert  into `music`(`id`,`title`,`singer`,`special`,`value`,`time`,`click`,`url`) values (5,'我的摇篮','末知道','末知道','非常好听，愿所有的人都来听。','1282139756543',5,'upload\\201008189964.mp3'),(6,'红装','徐良','犯贱','好听。。。。。。。。。。。。。。。。。。。。','1377085760035',0,'upload\\201308213608.mp3');

/*Table structure for table `tip` */

DROP TABLE IF EXISTS `tip`;

CREATE TABLE `tip` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `value` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `tip` */

insert  into `tip`(`id`,`value`) values (5,'[cjgong] '),(6,'[cjgong] 分享了歌曲 [我的摇篮]'),(7,'[java1234] 分享了歌曲 [红装]');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `pwd` varchar(32) DEFAULT NULL,
  `music_box` longtext,
  `dingyue`int(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `singer`;

CREATE TABLE `singer` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL DEFAULT 0,
  `type` varchar(10) NOT NULL DEFAULT 0,
  `age` varchar(10) NOT NULL DEFAULT 0,
  `sex`varchar(2) NOT NULL DEFAULT "男",
   `clicks`varchar(10) NOT NULL DEFAULT 0,
   `info`varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `album`;

CREATE TABLE `album` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT 0,
  `type` varchar(10) DEFAULT NULL,
  `time` varchar(10) NOT NULL DEFAULT 0,
  `price`varchar(10)  NOT NULL DEFAULT 0,
   `singer`varchar(5) NOT NULL DEFAULT 0,
   `com`varchar(10) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`pwd`,`music_box`) values (8,'cjgong','e10adc3949ba59abbe56e057f20f883e','3,4,5'),(9,'cjgong1','e10adc3949ba59abbe56e057f20f883e','3'),(10,'java1234','e10adc3949ba59abbe56e057f20f883e',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
