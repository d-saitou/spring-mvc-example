DROP TABLE IF EXISTS `m_user`;
CREATE TABLE `m_user` (
  `userid` varchar(10) NOT NULL,
  `username` varchar(40) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
 PRIMARY KEY(
  `userid`
 )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

