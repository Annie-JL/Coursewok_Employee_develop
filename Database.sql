CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `points` int(11) DEFAULT 0,
  `finished` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` int(11) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  `points` int(11) DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `id_role_id` (`role_id`),
  CONSTRAINT `id_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

CREATE TABLE `user_course` (
	`id` int(11) not NULL AUTO_INCREMENT,
  `user_id` int(11)  NOT NULL,
  `course_id` int(11)  NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_usercourse_user_id` (`user_id`),
  KEY `id_usercourse_course_id` (`course_id`),
  CONSTRAINT `id_usercourse_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id_usercourse_course_id` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO `role` VALUES (1,'ADMIN');
INSERT INTO `role` VALUES (2,'USER');

INSERT INTO `user` (`active`, `email`, `name`, `password`, `role_id`)
VALUES (1, 'admin@gmail.com', 'Anna', '$2a$12$wmf0J2wspo/pflB3faBeyuePBMT29xk.UUNWAvs3BaZWlEL3OKZnW', 1);


