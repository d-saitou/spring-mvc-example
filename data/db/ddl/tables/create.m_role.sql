DROP TABLE IF EXISTS `m_role`;
CREATE TABLE `m_role` (
  `role_id` varchar(10) NOT NULL,
  `role_name` varchar(40) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `created_by` varchar(10) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_by` varchar(10) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
 PRIMARY KEY(
  `role_id`
 )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

