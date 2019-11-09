create database demo;

use demo;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for friends
-- ----------------------------
DROP TABLE IF EXISTS `friends`;
CREATE TABLE `friends`  (
  `id` int(8) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `a_id` int(8) NOT NULL COMMENT '发送好友请求的用户id',
  `b_id` int(8) NOT NULL COMMENT '接收好友请求的用户id',
  `status` int(1) NOT NULL DEFAULT 2 COMMENT '消息处理的状态，0表示接收好友请求，1表示拒绝好友请求，2表示该消息未处理',
  `add_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of friends
-- ----------------------------
INSERT INTO `friends` VALUES (2, 105, 17, 0, '2018-10-11 10:59:31');
INSERT INTO `friends` VALUES (3, 105, 8, 0, '2018-10-11 10:59:31');
INSERT INTO `friends` VALUES (4, 105, 7, 0, '2018-10-11 10:59:31');
INSERT INTO `friends` VALUES (5, 105, 6, 0, '2018-10-11 10:59:32');
INSERT INTO `friends` VALUES (6, 105, 106, 0, '2018-10-11 10:59:32');
INSERT INTO `friends` VALUES (7, 107, 105, 1, '2018-10-11 10:59:32');
INSERT INTO `friends` VALUES (8, 106, 107, 0, '2018-10-11 10:59:32');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `from_user_id` int(8) NOT NULL,
  `to_user_id` int(8) NOT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `send_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 66 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (2, 105, 8, 'test', '2018-10-11 13:49:40');
INSERT INTO `message` VALUES (3, 8, 105, 'fdaf', '2018-10-11 13:50:16');
INSERT INTO `message` VALUES (4, 105, 8, 'fdsafd', '2018-10-11 13:50:32');
INSERT INTO `message` VALUES (5, 8, 105, '你好', '2018-10-11 13:56:31');
INSERT INTO `message` VALUES (6, 105, 8, 'ddd', '2018-10-11 13:56:55');
INSERT INTO `message` VALUES (7, 105, 8, '测试', '2018-10-11 14:03:55');
INSERT INTO `message` VALUES (8, 105, 8, 'fdfds', '2018-10-11 14:05:10');
INSERT INTO `message` VALUES (9, 8, 105, 'dfadasf', '2018-10-11 14:05:43');
INSERT INTO `message` VALUES (10, 8, 105, 'fdsfa', '2018-10-11 14:05:52');
INSERT INTO `message` VALUES (11, 8, 105, 'gfdgfdgd', '2018-10-11 14:07:35');
INSERT INTO `message` VALUES (12, 8, 105, 'fdafdsfdsf', '2018-10-11 14:09:01');
INSERT INTO `message` VALUES (13, 8, 105, 'kuhkjhjk', '2018-10-11 14:09:44');
INSERT INTO `message` VALUES (14, 8, 105, 'jjjjjkkkkk', '2018-10-11 14:11:53');
INSERT INTO `message` VALUES (15, 105, 8, '你好', '2018-10-11 14:12:07');
INSERT INTO `message` VALUES (16, 8, 105, '测试', '2018-10-11 14:16:35');
INSERT INTO `message` VALUES (17, 105, 8, 'hello', '2018-10-11 14:17:13');
INSERT INTO `message` VALUES (18, 8, 105, '测', '2018-10-11 14:17:46');
INSERT INTO `message` VALUES (19, 8, 105, 'gdfgdg', '2018-10-11 14:18:03');
INSERT INTO `message` VALUES (20, 8, 105, 'gvdfdsf', '2018-10-11 14:18:25');
INSERT INTO `message` VALUES (21, 105, 8, 'fsdfdsfdsf', '2018-10-11 14:18:29');
INSERT INTO `message` VALUES (22, 105, 8, 'fdsfdsf', '2018-10-11 14:19:31');
INSERT INTO `message` VALUES (23, 105, 8, 'fdsafsdaf', '2018-10-11 14:20:04');
INSERT INTO `message` VALUES (24, 8, 105, 'fdsad', '2018-10-11 14:20:31');
INSERT INTO `message` VALUES (25, 105, 8, 'ddddddd', '2018-10-11 14:21:54');
INSERT INTO `message` VALUES (26, 8, 105, 'd', '2018-10-11 14:22:18');
INSERT INTO `message` VALUES (27, 105, 8, 'dfadf', '2018-10-11 14:22:31');
INSERT INTO `message` VALUES (28, 105, 8, 'dfdfdfdf', '2018-10-11 14:30:18');
INSERT INTO `message` VALUES (29, 105, 8, 'dfadf', '2018-10-11 14:30:31');
INSERT INTO `message` VALUES (30, 8, 105, 'dfdsdsfsdf', '2018-10-11 14:31:01');
INSERT INTO `message` VALUES (31, 8, 105, 'kjjkjlk', '2018-10-11 14:31:53');
INSERT INTO `message` VALUES (32, 8, 105, 'jkhkjh', '2018-10-11 14:32:28');
INSERT INTO `message` VALUES (33, 8, 105, 'huujkh', '2018-10-11 14:34:14');
INSERT INTO `message` VALUES (34, 8, 105, 'gdfgfdg', '2018-10-11 14:38:19');
INSERT INTO `message` VALUES (35, 8, 105, 'fdsafdsf', '2018-10-11 14:39:29');
INSERT INTO `message` VALUES (36, 8, 105, '1', '2018-10-11 14:44:15');
INSERT INTO `message` VALUES (37, 105, 8, '2', '2018-10-11 14:44:28');
INSERT INTO `message` VALUES (38, 8, 105, '3', '2018-10-11 14:44:39');
INSERT INTO `message` VALUES (39, 8, 105, 'fddfd', '2018-10-11 14:45:14');
INSERT INTO `message` VALUES (40, 105, 8, 'fdsfads', '2018-10-11 14:45:20');
INSERT INTO `message` VALUES (41, 8, 105, '33e43232', '2018-10-11 14:45:42');
INSERT INTO `message` VALUES (42, 8, 105, 'fdsafds', '2018-10-11 14:47:57');
INSERT INTO `message` VALUES (43, 8, 105, 'fdsfdsf', '2018-10-11 14:48:02');
INSERT INTO `message` VALUES (44, 8, 105, 'fsdaf', '2018-10-11 14:48:18');
INSERT INTO `message` VALUES (45, 8, 105, 'fdasfds', '2018-10-11 14:48:42');
INSERT INTO `message` VALUES (46, 105, 8, 'fsdaf', '2018-10-11 14:48:50');
INSERT INTO `message` VALUES (47, 105, 8, 'fadsfads', '2018-10-11 14:48:52');
INSERT INTO `message` VALUES (48, 105, 8, 'afdsfasd', '2018-10-11 14:48:56');
INSERT INTO `message` VALUES (49, 105, 8, 'fasdfadsf', '2018-10-11 14:49:04');
INSERT INTO `message` VALUES (50, 8, 105, 'fdsaadsafffsdgsadf  fdsafdsaf', '2018-10-11 14:49:29');
INSERT INTO `message` VALUES (51, 8, 105, 'fasdfaf               fasdfsdaf', '2018-10-11 14:50:03');
INSERT INTO `message` VALUES (52, 8, 105, 'fsadfdsfdsf', '2018-10-11 14:50:28');
INSERT INTO `message` VALUES (53, 8, 105, 'gfdsgdfds', '2018-10-11 14:53:52');
INSERT INTO `message` VALUES (54, 8, 105, 'fdsafsdf', '2018-10-11 14:54:03');
INSERT INTO `message` VALUES (55, 8, 105, '1', '2018-10-11 14:56:04');
INSERT INTO `message` VALUES (56, 8, 105, '2', '2018-10-11 14:56:20');
INSERT INTO `message` VALUES (57, 8, 105, 'ddd', '2018-10-11 14:56:38');
INSERT INTO `message` VALUES (58, 8, 105, 'dcdfsdfdsf', '2018-10-11 14:58:45');
INSERT INTO `message` VALUES (59, 8, 105, 'fsfdsfdsf', '2018-10-11 14:59:02');
INSERT INTO `message` VALUES (60, 8, 105, 'jkhjkhn', '2018-10-11 15:02:57');
INSERT INTO `message` VALUES (61, 8, 105, 'fdafdsfadsf', '2018-10-11 15:10:56');
INSERT INTO `message` VALUES (62, 105, 8, 'dfdafdsafsd', '2018-10-11 15:13:38');
INSERT INTO `message` VALUES (63, 105, 8, 'fdsfdsf', '2018-10-11 15:13:42');
INSERT INTO `message` VALUES (64, 8, 105, 'fdsfadsfadsf', '2018-10-11 15:23:29');
INSERT INTO `message` VALUES (65, 105, 8, '发的说法收费545', '2018-10-11 15:24:08');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `add_time` timestamp(0) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 108 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (3, 'test7', 22, '2018-10-11 11:10:00', 'test');
INSERT INTO `user_info` VALUES (6, 'test666', 22, '2018-10-11 11:10:00', 'test');
INSERT INTO `user_info` VALUES (7, 'test8', 22, '2018-10-11 11:10:00', 'test');
INSERT INTO `user_info` VALUES (8, 'test9', 22, '2018-10-11 11:10:00', 'test');
INSERT INTO `user_info` VALUES (9, 'test11', 22, '2018-10-11 11:10:00', 'test');
INSERT INTO `user_info` VALUES (11, 'test12', 22, '2018-10-11 11:10:00', 'test');
INSERT INTO `user_info` VALUES (12, 'test13', 22, '2018-10-11 11:10:01', 'test');
INSERT INTO `user_info` VALUES (13, 'test14', 22, '2018-10-11 11:10:01', 'test');
INSERT INTO `user_info` VALUES (14, 'test15', 22, '2018-10-11 11:10:01', 'test');
INSERT INTO `user_info` VALUES (16, 'test16', 22, '2018-10-11 11:10:01', 'test');
INSERT INTO `user_info` VALUES (17, 'test17', 22, '2018-10-11 11:10:01', 'test');
INSERT INTO `user_info` VALUES (18, 'test18', 22, '2018-10-11 11:10:01', 'test');
INSERT INTO `user_info` VALUES (19, 'test19', 22, '2018-10-11 11:10:01', 'test');
INSERT INTO `user_info` VALUES (20, 'test20', 22, '2018-10-11 11:10:01', 'test');
INSERT INTO `user_info` VALUES (21, 'test21', 22, '2018-10-11 11:10:01', 'test');
INSERT INTO `user_info` VALUES (22, 'test22', 22, '2018-10-11 11:10:01', 'test');
INSERT INTO `user_info` VALUES (24, 'test23', 22, '2018-10-11 11:10:01', 'test');
INSERT INTO `user_info` VALUES (25, 'test24', 22, '2018-10-11 11:10:01', 'test');
INSERT INTO `user_info` VALUES (26, 'test25', 22, '2018-10-11 11:10:01', 'test');
INSERT INTO `user_info` VALUES (27, 'test26', 22, '2018-10-11 11:10:01', 'test');
INSERT INTO `user_info` VALUES (28, 'test27', 22, '2018-10-11 11:10:01', 'test');
INSERT INTO `user_info` VALUES (29, 'test28', 22, '2018-10-11 11:10:01', 'test');
INSERT INTO `user_info` VALUES (30, 'test29', 22, '2018-10-11 11:10:01', 'test');
INSERT INTO `user_info` VALUES (31, 'test30', 22, '2018-10-11 11:10:01', 'test');
INSERT INTO `user_info` VALUES (32, 'test31', 22, '2018-10-11 11:10:01', 'test');
INSERT INTO `user_info` VALUES (105, 'test', 22, '2018-10-11 11:10:01', 'test');
INSERT INTO `user_info` VALUES (106, 'test2', 22, '2018-10-11 11:10:01', 'test');
INSERT INTO `user_info` VALUES (107, 'test3', 22, '2018-10-11 11:10:00', 'test');

SET FOREIGN_KEY_CHECKS = 1;
