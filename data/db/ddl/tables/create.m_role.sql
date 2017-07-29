DROP TABLE IF EXISTS `m_role`;
CREATE TABLE `m_role` (
  `roleid` varchar(10) NOT NULL,
  `rollname` varchar(40) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
 PRIMARY KEY(
  `roleid`
 )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

