-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO user_account (username, password, email, isAdmin, birthdate, gender, address)
VALUES ('Chen Siyu', 'Csy.123', 'csy@gmail.com', true, NULL, NULL, NULL);

INSERT INTO user_account (username, password, email, isAdmin, birthdate, gender, address)
VALUES ('Fancy', 'Fancy.123', 'fancy123@gmail.com', false, '2000-01-29', 'Female', NULL);

INSERT INTO user_account (username, password, email, isAdmin, birthdate, gender, address)
VALUES ('Brandy', 'Brandy.123', 'Brandy123@gmail.com', false, '2000-01-19', 'male', NULL);

-- ----------------------------
-- Records of artists
-- ----------------------------
INSERT INTO artists (name, bio)
VALUES ('Anjana Jain', 'I am an artist as well as homemaker. I become an artist at the age of 17 year. I make oil painting,sketches,portrait this is god gift for me .my family encouraged me to continue my painting and I came back professionally.');

INSERT INTO artists (name, bio)
VALUES ('Nabeel Jamil', 'Started painting and sketching in this lockdown period and found a good artist in me which i wasn''t aware of as i just had some interest in making cartoons on the drawing sheet in my early age.');

-- ----------------------------
-- Records of artworks
-- ----------------------------
INSERT INTO artworks (artist_id, title, description, price, image_url, type, user_id)
VALUES (1, 'Still Life', 'A beautiful painting alive.', 290, 'assets/images/artworks/digital/Stil_life.jpeg', 'digital', 2);

INSERT INTO artworks (artist_id, title, description, price, image_url, type, user_id)
VALUES (2, 'Purple flowers', 'What i see in my garden…', 269, 'assets/images/artworks/digital/Purple flowers.jpeg', 'digital', 1);

-- ----------------------------
-- Records of carts
-- ----------------------------
INSERT INTO carts (user_id, artwork_id)
VALUES (2, 1);

INSERT INTO carts (user_id, artwork_id)
VALUES (3, 2);

-- ----------------------------
-- Records of wishlist
-- ----------------------------
INSERT INTO wishlist (user_id, artwork_id)
VALUES (2, 2);

INSERT INTO wishlist (user_id, artwork_id)
VALUES (3, 1);

-- ----------------------------
-- Records of blind boxes
-- ----------------------------
INSERT INTO blind_boxes (user_id, artwork_id, price)
VALUES (1, 1, 125);

INSERT INTO blind_boxes (user_id, artwork_id, price)
VALUES (2, 2, 650);



