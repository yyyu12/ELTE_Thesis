DROP TABLE IF EXISTS "user";
CREATE TABLE "user" (
  id bigint AUTO_INCREMENT PRIMARY KEY,
  username varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  email varchar(255) NOT NULL,
  isAdmin tinyint NOT NULL DEFAULT 0,
  birthdate datetime(6),
  gender varchar(255),
  address varchar(255),
  UNIQUE (username),
  UNIQUE (email)
);