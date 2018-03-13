/*
SQLyog Enterprise - MySQL GUI v8.14 
MySQL - 5.5.15 : Database - chatserver
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`chatserver` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `chatserver`;

/*Table structure for table `chat` */

DROP TABLE IF EXISTS `chat`;

CREATE TABLE `chat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` int(11) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `online` int(11) DEFAULT '0',
  `nickname` varchar(20) DEFAULT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `birthday` int(20) DEFAULT NULL,
  `province` varchar(20) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `introduce` varchar(20) DEFAULT 'null',
  `school` varchar(20) DEFAULT 'null',
  `email` varchar(20) DEFAULT 'null',
  `constellation` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

/*Data for the table `chat` */

insert  into `chat`(`id`,`username`,`password`,`online`,`nickname`,`sex`,`birthday`,`province`,`city`,`introduce`,`school`,`email`,`constellation`) values (1,527898,'123456',0,'拾掇忧伤','man',19931225,'甘肃','兰州','null','null','null','摩羯座'),(2,700071,'qwerty',0,'诺克','woman',20000101,'新疆','乌鲁木齐','null','null','null','双鱼座'),(3,363894,'asdfgh',0,'不天天','man',19900606,'四川','成都','哎。。','四川大学','805650956@qq.com','双子座'),(4,302772,'zzxxcc',0,'黑亿万','man',19880614,'甘肃','兰州','啧啧。。。。','null','null','双子座'),(5,811780,'11223344',0,'巨人','man',19981125,'甘肃','兰州','null','null','null','射手座'),(6,123828,'11223344',0,'狂风','woman',19850521,'甘肃','兰州','null','null','null','双子座'),(7,982685,'2932638',0,'126','man',19910101,'甘肃省','兰州市','null','null','null','摩羯座'),(8,381456,'qwerty',0,'我是盲僧','man',19860625,'甘肃','兰州','null','null','null','巨蟹座'),(9,638270,'1122334455',0,'我是坏蛋','man',19870417,'甘肃','兰州','null','null','null','白羊座'),(10,999140,'asdfgh',0,'我是蛮王','man',19631201,'甘肃','兰州','null','null','null','射手座'),(11,435129,'zzxxcc',0,'我的小孩','woman',19621124,'四川','成都','null','null','null','射手座'),(12,536825,'123456',0,'窝窝头','man',19861201,'甘肃','兰州','null','null','null','射手座'),(13,849549,'123456',0,'生命一号','man',19880311,'甘肃','兰州','null','null','null','双鱼座'),(14,891551,'qwerty',0,'生命二号','woman',20010615,'甘肃','庆阳','null','null','null','双子座'),(15,165320,'qwerty',0,'生命三号','man',19551223,'甘肃','平凉','null','null','null','摩羯座'),(16,771124,'qwerty',0,'我是薇恩','man',19960528,'甘肃','兰州','哎。。就专业那个吧','null','null','双子座'),(17,496062,'2932638',0,'123','man',19910101,'煞笔','煞笔','null','null','null','摩羯座'),(18,439758,'123456789',1,'茶叶','woman',19630523,'甘肃','兰州','null','null','null','双子座'),(19,225677,'123456789',0,'鸡蛋','man',19771101,'四川','成都','null','null','null','天蝎座'),(20,944575,'123456789',0,'呵呵','man',19631101,'甘肃','兰州','null','null','null','天蝎座'),(21,909490,'123456',0,'aa','man',19951101,'gan','ss','null','null','null','天蝎座'),(22,786285,'123456',1,'哈哈','woman',19961102,'甘肃','兰州','null','null','null','天蝎座');


CREATE TABLE `talk` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(20) DEFAULT NULL,
  `text` varchar(180) DEFAULT NULL,
  
  `musicname` varchar(50) DEFAULT NULL,
  `time` varchar(30) DEFAULT NULL,
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

insert  into `talk`(`id`,`nickname`,`text`,`musicname`,`time`) values (1,'527898','还不错','a1.mp3','20160107'),(2,'700071','qwerty','a1.mp3','20160108');

insert  into `talk`(`id`,`nickname`,`text`,`musicname`,`time`) values (3,'tommy','真好听啊','a2.mp3','20160107'),(4,'Mike','verygoodl','a2.mp3','20160108');

insert  into `talk`(`id`,`nickname`,`text`,`musicname`,`time`) values (5,'simida','hahahahah','a2.mp3','2016/1/7/ 10:52:33'),(6,'lucy','verygoodl','a2.mp3','2016/1/8/ 09:22:10');

insert  into `talk`(`id`,`nickname`,`text`,`musicname`,`time`) values (7,'simida','hahahahah','cat.3gp','2016/1/7/ 10:52:33'),(8,'lucy','verygoodl','peng.3gp','2016/1/8/ 09:22:10');

insert  into `talk`(`id`,`nickname`,`text`,`musicname`,`time`) values (9,'simida','hahahahah','chenglong.3gp','2016/1/7/ 10:52:33'),(10,'lucy','verygoodl','text2.3gp','2016/1/8/ 09:22:10');

insert  into `talk`(`id`,`nickname`,`text`,`musicname`,`time`) values (11,'simida','真好看啊','a1.mp3','2016/1/7/ 10:52:33'),(12,'lucy','可以哦','text2.3gp','2016/1/8/ 09:22:10');

insert  into `talk`(`id`,`nickname`,`text`,`musicname`,`time`) values (13,'527898','还不错','a2.mp3','20160107'),(14,'700071','呵呵','a1.mp3','20160108');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
