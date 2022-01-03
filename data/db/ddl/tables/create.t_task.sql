DROP TABLE IF EXISTS `t_task`;
CREATE TABLE `t_task` (
  `task_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `scheduled_date` date DEFAULT NULL,
  `completion` boolean DEFAULT NULL,
  `description` text DEFAULT NULL,
  `created_by` varchar(10) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_by` varchar(10) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
 PRIMARY KEY(
  `task_id`
 )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

