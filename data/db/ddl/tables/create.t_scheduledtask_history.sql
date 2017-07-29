DROP TABLE IF EXISTS `t_scheduledtask_history`;
CREATE TABLE `t_scheduledtask_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `function` varchar(30) DEFAULT NULL,
  `message` varchar(100) DEFAULT NULL,
  `updatedate` datetime DEFAULT NULL,
 PRIMARY KEY(
  `id`
 )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

