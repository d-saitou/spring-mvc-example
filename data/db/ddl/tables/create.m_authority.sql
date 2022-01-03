DROP TABLE IF EXISTS `m_authority`;
CREATE TABLE `m_authority` (
  `authority_id` varchar(10) NOT NULL,
  `authority_name` varchar(40) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `created_by` varchar(10) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_by` varchar(10) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
 PRIMARY KEY(
  `authority_id`
 )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

