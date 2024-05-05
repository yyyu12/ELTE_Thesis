USE thesisdb;

-- ----------------------------
-- Table structure for user_account
-- ----------------------------
CREATE TABLE user_account (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    isAdmin BOOLEAN NOT NULL DEFAULT FALSE,
    birthdate DATE NULL,
    gender VARCHAR(20) NULL,
    address VARCHAR(255) NULL,
    UNIQUE KEY unique_username (username),
    UNIQUE KEY unique_email (email)
);

select * from thesisdb.user_account;

-- ----------------------------
-- Records of user_account
-- ----------------------------
INSERT INTO `user_account` (username, password, email, isAdmin, birthdate, gender, address) 
VALUES ('csy', '$2a$10$8acpvPJe32LDL/NRAunkFefGDLHmfnC40kz9XPEYAOQeZQFCwpoJa', 'csy123@gmail.com', 1, '2002-08-12', 'Female', NULL);
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
-- Table structure for artist
-- ----------------------------
CREATE TABLE artists (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    bio TEXT
);

-- ----------------------------
-- Records of artist
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
-- Table structure for artwork
-- ----------------------------
CREATE TABLE artworks (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    artist_id BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2),
    image_url VARCHAR(255),
    type VARCHAR(100),
    user_id BIGINT NULL,
    FOREIGN KEY (artist_id) REFERENCES artists(id)
);

ALTER TABLE artworks ADD CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES user_account(id);
CREATE INDEX idx_user ON artworks(user_id);

-- ----------------------------
-- Records of artwork
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
INSERT INTO `artworks` (artist_id, title, description, price, image_url, type, user_id)
VALUES (6, 'Amsterdam view opus', 'In the foreground, there is a black and white tour boat with a red canopy.  Behind it are several colorful houseboats lining the canal. In the background, there are buildings with tall, narrow windows and gabled roofs. The sky is bright blue with white clouds.', 1862, 'assets/images/artworks/NFT/Amsterdam_view_opus .jpeg', 'nft', NULL);
INSERT INTO `artworks` (artist_id, title, description, price, image_url, type, user_id)
VALUES (1, 'Monochromatic Sunshine', 'The artwork radiates joy, depicting the sun as a luminous orb casting intricate shadows.', 850, 'assets/images/artworks/NFT/Monochromatic_Sunshine.jpeg', 'nft', NULL);
INSERT INTO `artworks` (artist_id, title, description, price, image_url, type, user_id)
VALUES (2, 'Moon Temple', 'A crescent moon floating above a blue and white temple  with  towers that reach up towards the sky. There are also birds flying around the moon.', 700, 'assets/images/artworks/NFT/Moon_Temple_II.jpeg',	'nft', 5);
INSERT INTO `artworks` (artist_id, title, description, price, image_url, type, user_id)
VALUES (3, 'Wildflowers and peaches', 'A bouquet of wildflowers in a glass vase sitting on a wooden table. There are also two peaches resting on the table beside the vase. The background is a neutral color.', 800, 'assets/images/artworks/paintings/Wildflowers_and_peaches.jpeg', 'painting', NULL);
INSERT INTO `artworks` (artist_id, title, description, price, image_url, type, user_id)
VALUES (4, 'Red Panda', 'The tree branch that the red panda is sitting on is thick and gnarled. It is covered in green leaves and has a small cluster of red berries growing on it.', 926, 'assets/images/artworks/NFT/Red_Panda.png', 'nft', NULL);
INSERT INTO `artworks` (artist_id, title, description, price, image_url, type, user_id)
VALUES (5, 'Flowers on the straps', 'Flowers on the straps. It is so fancy!', 450, 'assets/images/artworks/physical /Flowers_on_the_straps.jpeg','physical', NULL);
INSERT INTO `artworks`(artist_id, title, description, price, image_url, type, user_id)
 VALUES (6, 'High summer colors', 'A vibrant pysical painting with a rich tapestry of colors and shapes that evoke a sense of chaotic beauty.', 539, 'assets/images/artworks/physical /High_summer_colors.jpeg', 'physical', NULL);
INSERT INTO `artworks` (artist_id, title, description, price, image_url, type, user_id)
VALUES (1, 'Somewhere in Middle Himalayas', 'The scene is from Middle Himalayas. There are forested mountains in the back drop.', 250, 'assets/images/artworks/physical /Somewhere_in_Middle_Himalayas.jpeg', 'physical', NULL);
INSERT INTO `artworks` (artist_id, title, description, price, image_url, type, user_id)
VALUES (2, 'Castle on an Islet and Orange Clouds', 'This surrealist painting is a scene from a far away planet in a distant solar system.', 189, 'assets/images/artworks/physical /Castle_on_an_Islet_and_Orange_Clouds.jpeg', 'physical', 6);
INSERT INTO `artworks` (artist_id, title, description, price, image_url, type, user_id)
VALUES (3, 'Dreams', 'This original acrylic painting on canvas portrays a serene scene of a woman sleeping amidst the blue expanse of the sky, cradling the horn of the moon in her hands.', 1562, 'assets/images/artworks/abstractArt/Dreams.jpeg', 'abstract', 2);
INSERT INTO `artworks` (artist_id, title, description, price, image_url, type, user_id)
VALUES (4, 'Purple Sunrise', 'This artwork brings a peaceful mood, evokes memories of the early morning sky in a warm summer day, has a rich texture of different shades.', 1667, 'assets/images/artworks/paintings/Purple_Sunrise.png',	'painting', NULL);

select * from artworks;

-- ----------------------------
-- Table structure for cart
-- ----------------------------
CREATE TABLE carts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    artwork_id BIGINT NOT NULL,
    added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY unique_artwork_user (user_id, artwork_id),
    FOREIGN KEY (user_id) REFERENCES user_account(id),
    FOREIGN KEY (artwork_id) REFERENCES artworks(id)
);

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `carts` (user_id, artwork_id)
VALUES (2, 2);
INSERT INTO `carts`(user_id, artwork_id)
VALUES (3, 3);
INSERT INTO `carts` (user_id, artwork_id)
VALUES (4, 6);

-- ----------------------------
-- Table structure for wishlist
-- ----------------------------
CREATE TABLE wishlist (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    artwork_id BIGINT NOT NULL,
    added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user_account(id),
    FOREIGN KEY (artwork_id) REFERENCES artworks(id),
    UNIQUE KEY unique_wishlist (user_id, artwork_id)
);

-- ----------------------------
-- Records of wishlist
-- ----------------------------
INSERT INTO `wishlist` (user_id, artwork_id)
VALUES (2, 2);
INSERT INTO `wishlist` (user_id, artwork_id)
VALUES (5, 3);
INSERT INTO `wishlist` (user_id, artwork_id)
VALUES (6, 7);

-- ------------------------------
-- Table structure for Blind Box
-- ------------------------------
CREATE TABLE blind_boxes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    artwork_id BIGINT NOT NULL,
    purchase_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user_account(id),
    FOREIGN KEY (artwork_id) REFERENCES artworks(id)
);

-- ----------------------------
-- Records of blind_boxes
-- ----------------------------
INSERT INTO `blind_boxes`(user_id, artwork_id, price)
VALUES (2, 1, 1250);
INSERT INTO `blind_boxes` (user_id, artwork_id, price)
VALUES (6, 20, 125);