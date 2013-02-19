DROP TABLE IF EXISTS `employee`;
CREATE TABLE  `employee` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `email`           varchar(128) NOT NULL,
  `firstname`       varchar(32)  NOT NULL,
  `lastname`        varchar(32)  NOT NULL,
  `city`            varchar(32),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `UNI_ID` (`id`),
  UNIQUE KEY `UNI_EMAIL` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8;
