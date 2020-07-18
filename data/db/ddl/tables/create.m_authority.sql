DROP TABLE IF EXISTS `m_authority`;
CREATE TABLE `m_authority` (
  `authorityid` varchar(10) NOT NULL,
  `authorityname` varchar(40) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
 PRIMARY KEY(
  `authorityid`
 )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

