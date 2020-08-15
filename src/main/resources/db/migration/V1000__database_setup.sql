CREATE TABLE user (
  id BIGINT NOT NULL AUTO_INCREMENT,
  password VARCHAR(60) NOT NULL,
  email VARCHAR(60) NOT NULL,
  name VARCHAR(60) NOT NULL,
  enabled boolean NOT NULL default true,
  role varchar(16) NOT NULL default 'USER',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_unique` (`email` ASC)
);

CREATE TABLE place (
  id BIGINT NOT NULL AUTO_INCREMENT,
  title VARCHAR(60) NOT NULL,
  description VARCHAR(240) NOT NULL,
  rating DOUBLE NOT NULL default 0,
  votes int NOT NULL default 0,
  PRIMARY KEY (id)
);

CREATE TABLE review (
  id BIGINT NOT NULL AUTO_INCREMENT,
  description VARCHAR(60) NOT NULL,
  rating DOUBLE NOT NULL,
  user_id BIGINT NOT NULL,
  place_id BIGINT NOT NULL,
  date_time timestamp NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  FOREIGN KEY (`place_id`) REFERENCES `place` (`id`)
);

CREATE TABLE image (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(60) NOT NULL,
  place_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (`place_id`) REFERENCES `place` (`id`)
);
