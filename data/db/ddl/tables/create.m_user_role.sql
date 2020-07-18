DROP TABLE IF EXISTS `m_user_role`;
CREATE TABLE `m_user_role` (
  `userid` varchar(10) NOT NULL,
  `roleid` varchar(10) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
 PRIMARY KEY(
  `userid`,
  `roleid`
 )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

