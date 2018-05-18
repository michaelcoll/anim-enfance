CREATE TABLE `partenaire` (
  `ptn_id` int(11) NOT NULL AUTO_INCREMENT,
  `ptn_name` varchar(255) NOT NULL,
  PRIMARY KEY (`ptn_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `creche` (
  `cre_id` int(11) NOT NULL AUTO_INCREMENT,
  `cre_name` varchar(255) NOT NULL,
  PRIMARY KEY (`cre_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
