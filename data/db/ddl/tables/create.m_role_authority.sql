DROP TABLE IF EXISTS `m_role_authority`;
CREATE TABLE `m_role_authority` (
  `role_id` varchar(10) NOT NULL,
  `authority_id` varchar(10) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
 PRIMARY KEY(
  `role_id`,
  `authority_id`
 )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

