DROP TABLE IF EXISTS `t_scheduledtask_history`;
CREATE TABLE `t_scheduledtask_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `method` varchar(30) DEFAULT NULL,
  `message` varchar(100) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
 PRIMARY KEY(
  `id`
 )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

