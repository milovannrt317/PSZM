CREATE TABLE `USER` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` text CHARACTER SET latin1 NOT NULL,
  `last_name` text CHARACTER SET latin1 NOT NULL,
  `email` varchar(50) CHARACTER SET latin1 NOT NULL,
  `username` varchar(30) CHARACTER SET latin1 NOT NULL,
  `password` varchar(512) CHARACTER SET latin1 NOT NULL,
  `salt` varchar(100) CHARACTER SET latin1 NOT NULL,
  `recovery_pass` varchar(512) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `salt_UNIQUE` (`salt`),
  UNIQUE KEY `recovery_pass_UNIQUE` (`recovery_pass`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
