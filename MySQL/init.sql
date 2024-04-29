SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_account
-- ----------------------------
DROP TABLE IF EXISTS `user_account`;
CREATE TABLE `user_account`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `isAdmin` tinyint(1) NOT NULL DEFAULT 0,
  `birthdate` datetime(6) NULL DEFAULT NULL,
  `gender` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique_username`(`username` ASC) USING BTREE,
  UNIQUE INDEX `unique_email`(`email` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_account
-- ----------------------------
INSERT INTO `user_account` VALUES (1, 'csy', 'csy20020812', 'csy@gmail.com', 1, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
