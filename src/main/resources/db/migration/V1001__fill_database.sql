INSERT INTO `user` (`id`,`password`,`email`,`name`,`enabled`,`role`) VALUES (1,'$2a$10$tNttum7fcQuJl6cF37Zdw.imRM7rf1znQnNUF4sdSw9z2so7BErqW','urmat@gmail.com','Урмат',1,'USER');
INSERT INTO `user` (`id`,`password`,`email`,`name`,`enabled`,`role`) VALUES (2,'$2a$10$xgKeawScFKNam03sOmIrbeTXwvx99SM47bJBDELlHUp1dXSSmppXG','geralt@gmail.com','Freya',1,'USER');

INSERT INTO `place` (`id`,`title`,`description`,`rating`,`votes`) VALUES (1,'Зара','Own an apartment',3.5,2);
INSERT INTO `place` (`id`,`title`,`description`,`rating`,`votes`) VALUES (2,'s','ыфыф',0,0);
INSERT INTO `place` (`id`,`title`,`description`,`rating`,`votes`) VALUES (3,'МЕЖА','свысывсывсы',0,0);

INSERT INTO `review` (`id`,`description`,`rating`,`user_id`,`place_id`,`date_time`) VALUES (1,'ЩВЫВЫСЫ',3,1,1,'2020-08-15 12:28:58');
INSERT INTO `review` (`id`,`description`,`rating`,`user_id`,`place_id`,`date_time`) VALUES (2,'dsdcffwfw',4,2,1,'2020-08-15 12:30:13');

INSERT INTO `image` (`id`,`name`,`place_id`) VALUES (1,'3bd3bd36551507.Y3JvcCwxNTMxLDExOTgsMTg2LDA.jpg',1);
INSERT INTO `image` (`id`,`name`,`place_id`) VALUES (2,'5846d201edbff.jpg',2);
INSERT INTO `image` (`id`,`name`,`place_id`) VALUES (3,'089198bff9d12995e1cce2224ba4bc5b.jpg',3);
INSERT INTO `image` (`id`,`name`,`place_id`) VALUES (4,'626482_1000.jpg',1);
INSERT INTO `image` (`id`,`name`,`place_id`) VALUES (5,'s1200.jpg',1);