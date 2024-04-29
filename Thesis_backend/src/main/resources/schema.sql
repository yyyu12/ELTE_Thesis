-- ----------------------------
-- Users Table
-- ----------------------------
DROP TABLE IF EXISTS user_account;
CREATE TABLE user_account (
  id bigint AUTO_INCREMENT PRIMARY KEY,
  username varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  email varchar(255) NOT NULL,
  isAdmin BOOLEAN NOT NULL DEFAULT FALSE,
  birthdate datetime(6),
  gender varchar(255),
  address varchar(255),
  UNIQUE (username),
  UNIQUE (email)
);

-- ----------------------------
-- Artists Table
-- ----------------------------
DROP TABLE IF EXISTS artists;
CREATE TABLE artists (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    bio TEXT
);

-- ----------------------------
-- Artworks Table
-- ----------------------------
DROP TABLE IF EXISTS artworks;
CREATE TABLE artworks (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    artist_id BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2),
    image_url VARCHAR(255),
    type VARCHAR(100),
    user_id BIGINT NULL,
    FOREIGN KEY (artist_id) REFERENCES artists(id),
    FOREIGN KEY (user_id) REFERENCES user_account(id)
);

-- ----------------------------
-- Cart Table
-- ----------------------------
DROP TABLE IF EXISTS carts;
CREATE TABLE carts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    artwork_id BIGINT NOT NULL,
    added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (user_id, artwork_id),
    FOREIGN KEY (user_id) REFERENCES user_account(id),
    FOREIGN KEY (artwork_id) REFERENCES artworks(id)
);

-- ----------------------------
-- Wishlist Table
-- ----------------------------
CREATE TABLE wishlist (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    artwork_id BIGINT NOT NULL,
    added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user_account(id),
    FOREIGN KEY (artwork_id) REFERENCES artworks(id),
    UNIQUE (user_id, artwork_id)
);


-- ----------------------------
-- Blind Box Table
-- ----------------------------
DROP TABLE IF EXISTS blind_boxes;
CREATE TABLE blind_boxes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    artwork_id BIGINT NOT NULL,
    purchase_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user_account(id),
    FOREIGN KEY (artwork_id) REFERENCES artworks(id)
);

