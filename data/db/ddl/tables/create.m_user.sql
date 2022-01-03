DROP TABLE IF EXISTS `m_user`;
CREATE TABLE `m_user` (
  `user_id` varchar(10) NOT NULL,
  `user_name` nvarchar(40) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `email_address_1` varchar(50) DEFAULT NULL,
  `email_address_2` varchar(50) DEFAULT NULL,
  `gender` varchar(1) DEFAULT NULL,
  `nationality_1` varchar(2) DEFAULT NULL,
  `nationality_2` varchar(2) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `address` nvarchar(50) DEFAULT NULL,
  `credit_card_no` varchar(16) DEFAULT NULL,
  `credit_card_expiration_date` date DEFAULT NULL,
  `password_hint` varchar(1) DEFAULT NULL,
  `password_hint_answer` nvarchar(50) DEFAULT NULL,
  `session_timeout` int DEFAULT NULL,
  `email_newsletter_1` boolean DEFAULT NULL,
  `email_newsletter_2` boolean DEFAULT NULL,
  `readonly` boolean DEFAULT NULL,
  `enabled` boolean DEFAULT NULL,
  `created_by` varchar(10) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_by` varchar(10) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
 PRIMARY KEY(
  `user_id`
 )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

