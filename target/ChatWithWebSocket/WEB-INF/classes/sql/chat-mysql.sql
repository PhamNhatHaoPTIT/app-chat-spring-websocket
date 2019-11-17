create database ltw;
use ltw;
SET NAMES utf8mb4;

DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `add_time` timestamp(0) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `status` int,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 108 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `friends`;
CREATE TABLE `friends`  (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `a_id` int(8) NOT NULL,
  `b_id` int(8) NOT NULL,
  `status` int(1) NOT NULL DEFAULT 2,
  `add_time` timestamp(0) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  FOREIGN KEY (a_id) REFERENCES user_info(id),
  FOREIGN KEY (b_id) REFERENCES user_info(id),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `from_user_id` int(8) NOT NULL,
  `to_user_id` int(8) NOT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `send_time` timestamp(0) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  FOREIGN KEY (from_user_id) REFERENCES user_info(id),
  FOREIGN KEY (to_user_id) REFERENCES user_info(id),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 66 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;
