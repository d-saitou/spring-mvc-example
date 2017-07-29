DROP TABLE IF EXISTS `t_task`;
CREATE TABLE `t_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `scheduledate` date DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `description` text DEFAULT NULL,
  `userid` varchar(10) DEFAULT NULL,
 PRIMARY KEY(
  `id`
 )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

