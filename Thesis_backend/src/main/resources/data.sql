-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user_account` (username, password, email, isAdmin, birthdate, gender, address) 
VALUES ('Chen Siyu', '$2a$10$8acpvPJe32LDL/NRAunkFefGDLHmfnC40kz9XPEYAOQeZQFCwpoJa', 'csy123@gmail.com', 1, '2002-08-12', 'Female', NULL);
INSERT INTO `user_account` (username, password, email, isAdmin, birthdate, gender, address) 
VALUES ('Fancy', '$2a$10$gsc4tl8ERXgC3sinbNalkOZgRQ0cLna8TPMCiLpV3ZiIgGm1wRvyu', 'fancy123@gmail.com', 0, '2000-03-25', NULL, NULL);
INSERT INTO `user_account` (username, password, email, isAdmin, birthdate, gender, address) 
VALUES ('Allen', '$2a$10$PuUALeRGZD/p06m2mnJh1OH.fyK8fa7HlBTr2SqxGnw1Qtnxgv6vq', 'allen123@gmail.com', 0, '2000-01-29', 'Male', NULL);
INSERT INTO `user_account` (username, password, email, isAdmin, birthdate, gender, address) 
VALUES ('Brandy', '$2a$10$WkzlDpsuzrs3Xed0nN8p1.AnQgHj8kE4AnakI/kiZQmugYtnn1kWG', 'brandy123@gmail.com', 0, NULL, NULL, NULL);
INSERT INTO `user_account` (username, password, email, isAdmin, birthdate, gender, address) 
VALUES ('Cheng gu', '$2a$10$uyJTWF2oUbDvTcAcmTu9..4UjClCuI3i72UbfWaJZqmciqVp5cR8u', 'Chengu123@gmail.com', 0, NULL, NULL, NULL);
INSERT INTO `user_account` (username, password, email, isAdmin, birthdate, gender, address) 
VALUES ('Jackson', '$2a$10$.14J8PFuHqzPK0egErjYJeHDqT.u.ItowLz4hzjHSTBGNc9HuebQi', 'Jackson123@gmail.com', 0, NULL, NULL, NULL);

-- ----------------------------
-- Records of artists
-- ----------------------------
INSERT INTO `artists` (name, bio)
VALUES ('Geert Lemmers', 'Welcome to my page! I am Geert Lemmers. artist fine photo art, new media and paintings. Have a look at my work. If you have any questions contact me. Almost everything is possible.');
INSERT INTO `artists` (name, bio)
VALUES ('Anjana Jain', 'I am an artist as well as homemaker. I become an artist at the age of 17 year. I make oil painting,sketches,portrait this is god gift for me .my family encouraged me to continue my painting and I came back professionally.');
INSERT INTO `artists` (name, bio)
VALUES ('Vorden Art', 'I am a person whose passion is art, probably due to the fact that one of my key features is unusual sensitivity towards the surrounding reality. My paintings reflect unusual character of each moment I experience.');
INSERT INTO `artists` (name, bio)
VALUES ('Mark Flake', 'As an abstract painter, I find myself immersed in the boundless realm of imagination and emotion. My canvas becomes a playground where colors dance freely, shapes morph into intriguing forms, and textures tell stories of their own.');
INSERT INTO `artists` (name, bio)
VALUES ('Nabeel Jamil', 'Started painting and sketching in this lockdown period and found a good artist in me which i was not aware of as i just had some interest in making cartoons on the drawing sheet in my early age.');
INSERT INTO `artists` (name, bio)
VALUES ('Anandi Dantas', 'She loves nature and traveling. Her paintings depict these, especially seascapes, trees and others.');


-- ----------------------------
-- Records of artworks
-- ----------------------------
INSERT INTO `artworks` (artist_id, title, description, price, image_url, type, user_id)
VALUES (1, 'Still Life', 'A beautiful painting alive.',	1200, 'assets/images/artworks/digital/Stil_life.jpeg', 'digital', 3);
INSERT INTO `artworks` (artist_id, title, description, price, image_url, type, user_id)
VALUES (2, 'Girl in a hat with wildflowers',	'A girl in a beautiful blooming field. Summer landscape, yellow field, blue sky. A celebration of bright flowers and herbs on a summer day creates an atmosphere of relaxation, harmony, and bliss.', 1400,	'assets/images/artworks/paintings/Girl_in_a_hat_with_wildflowers.jpeg',	'painting',	NULL);
INSERT INTO `artworks` (artist_id, title, description, price, image_url, type, user_id)
VALUES (3, 'High summer colors',	'Artwork comes with an international certificate of authenticity, signed by the artist. This is a combination of photography and wet and digital paintings and drawings and in the end digital post processing.', 650, 'assets/images/artworks/digital/Lonely-in-silence.jpeg', 'physical', NULL);
INSERT INTO `artworks` (artist_id, title, description, price, image_url, type, user_id)
VALUES (4, 'The Voyager On Blue', 'In this painting the boat being the object of focus draws quite an attention. The ripples in water compliment the same creating a beautiful balance in nature.', 660,'assets/images/artworks/physical /The_Voyager_On_Blue.jpeg' ,'physical', NULL);
INSERT INTO `artworks` (artist_id, title, description, price, image_url, type, user_id)
VALUES (5, 'Cascade canvas creation', 'Immerse yourself in the serenity of cascading waters through my artwork. Each brushstroke captures the graceful dance of water as it plunges and flows a tranquil symphony of nature beauty.', 1368, 'assets/images/artworks/paintings/Cascade_canvas_creation.jpeg', 'painting', NULL);
INSERT INTO `artworks` (artist_id, title, description, price, image_url, type, user_id)
VALUES (6, 'Galaxy', 'Dream and Universe have same meaning to life, the more you discover the less you know about it!!!', 366, 'assets/images/artworks/digital/Galaxy.jpeg', 'digital', NULL);
INSERT INTO `artworks` (artist_id, title, description, price, image_url, type, user_id)
VALUES (1, 'After the rain',	'This oil painting on canvas has a landscape displaying a scene just after the rain. It shows how clean the sky looks, how bright trees and plants look, how attractive everything looks just after the rain.',	379, 'assets/images/artworks/paintings/After_the_rain.jpeg', 'painting', 2);
INSERT INTO `artworks` (artist_id, title, description, price, image_url, type, user_id)
VALUES (2, 'Purple flowers',	'What i see in my garden…',	269, 'assets/images/artworks/digital/Purple flowers.jpeg',	'digital', 6);
INSERT INTO `artworks` (artist_id, title, description, price, image_url, type, user_id)
VALUES (3, 'Abstract Painting Moon', 'This is drawn by an amazing artist and the topic is the moon.', 126, 'assets/images/artworks/abstractArt/Abstract_painting_moon.jpeg', 'abstract', NULL);
INSERT INTO `artworks` (artist_id, title, description, price, image_url, type, user_id)
VALUES (4, 'Shadows', 'A bus stop bathed in the long shadows of sunset, with a leafy tree hinting at autumn.', 421.5,'assets/images/artworks/abstractArt/Shadows.jpeg',	'abstract', NULL);
INSERT INTO `artworks` (artist_id, title, description, price, image_url, type, user_id)
VALUES (5, 'The Narrow Blue Line', 'A close-up of a blue and green abstract painting. The brushstrokes are thick and textured. There is a variety of blues and greens used, with some white highlights.',	89	,'assets/images/artworks/abstractArt/The_Narrow_Blue_Line.jpeg', 'abstract', NULL);


-- ----------------------------
-- Records of carts
-- ----------------------------
INSERT INTO `carts` (user_id, artwork_id)
VALUES (2, 2);
INSERT INTO `carts`(user_id, artwork_id)
VALUES (3, 3);
INSERT INTO `carts` (user_id, artwork_id)
VALUES (4, 6);

-- ----------------------------
-- Records of wishlist
-- ----------------------------
INSERT INTO `wishlist` (user_id, artwork_id)
VALUES (2, 2);
INSERT INTO `wishlist` (user_id, artwork_id)
VALUES (5, 3);
INSERT INTO `wishlist` (user_id, artwork_id)
VALUES (6, 7);

-- ----------------------------
-- Records of blind boxes
-- ----------------------------
INSERT INTO `blind_boxes`(user_id, artwork_id, price)
VALUES (2, 1, 1250);
INSERT INTO `blind_boxes` (user_id, artwork_id, price)
VALUES (3, 5, 125);



