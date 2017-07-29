DROP TABLE IF EXISTS `m_role_authority`;
CREATE TABLE `m_role_authority` (
  `roleid` varchar(10) NOT NULL,
  `authorityid` varchar(10) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
 PRIMARY KEY(
  `roleid`,
  `authorityid`
 )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

