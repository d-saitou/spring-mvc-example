DROP TABLE IF EXISTS `t_task`;
CREATE TABLE `t_task` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `schedule_date` date DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `description` text DEFAULT NULL,
  `user_id` varchar(10) DEFAULT NULL,
 PRIMARY KEY(
  `id`
 )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

