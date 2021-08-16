DROP TABLE IF EXISTS `m_role`;
CREATE TABLE `m_role` (
  `role_id` varchar(10) NOT NULL,
  `roll_name` varchar(40) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
 PRIMARY KEY(
  `role_id`
 )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

