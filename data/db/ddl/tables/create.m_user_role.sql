DROP TABLE IF EXISTS `m_user_role`;
CREATE TABLE `m_user_role` (
  `user_id` varchar(10) NOT NULL,
  `role_id` varchar(10) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
 PRIMARY KEY(
  `user_id`,
  `role_id`
 )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

