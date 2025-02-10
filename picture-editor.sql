/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80036
 Source Host           : localhost:3306
 Source Schema         : picture-editor

 Target Server Type    : MySQL
 Target Server Version : 80036
 File Encoding         : 65001

 Date: 26/12/2024 01:23:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account_froze
-- ----------------------------
DROP TABLE IF EXISTS `account_froze`;
CREATE TABLE `account_froze`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `frozeId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NOT NULL,
  `frozeReason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NOT NULL,
  `startTime` datetime NULL DEFAULT NULL,
  `endTime` datetime NOT NULL,
  `frozeType` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `frozeId`) USING BTREE,
  INDEX `fkId`(`frozeId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_as_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account_froze
-- ----------------------------

-- ----------------------------
-- Table structure for account_vip_info
-- ----------------------------
DROP TABLE IF EXISTS `account_vip_info`;
CREATE TABLE `account_vip_info`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `userVipId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NULL DEFAULT NULL,
  `createTime` datetime NULL DEFAULT NULL,
  `endTime` datetime NULL DEFAULT NULL,
  `isForever` tinyint(1) NULL DEFAULT 0,
  `gradeScore` int NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `orderId`(`userVipId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_as_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account_vip_info
-- ----------------------------
INSERT INTO `account_vip_info` VALUES (7, '270af078-b641-46b2-a', '2024-04-12 17:46:51', '2027-04-27 17:47:10', 0, 14400);
INSERT INTO `account_vip_info` VALUES (8, '5c38c971-46dc-4e55-b', '2024-05-03 01:56:18', '2024-06-02 01:56:16', 0, 300);

-- ----------------------------
-- Table structure for statistic_day_amount
-- ----------------------------
DROP TABLE IF EXISTS `statistic_day_amount`;
CREATE TABLE `statistic_day_amount`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` date NULL DEFAULT NULL,
  `loginAmount` int NULL DEFAULT NULL,
  `registerAmount` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_as_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of statistic_day_amount
-- ----------------------------
INSERT INTO `statistic_day_amount` VALUES (3, '2024-03-08', 60, 20);
INSERT INTO `statistic_day_amount` VALUES (4, '2024-03-09', 68, 10);
INSERT INTO `statistic_day_amount` VALUES (5, '2024-03-10', 98, 26);
INSERT INTO `statistic_day_amount` VALUES (6, '2024-03-11', 195, 22);
INSERT INTO `statistic_day_amount` VALUES (7, '2024-03-12', 85, 33);
INSERT INTO `statistic_day_amount` VALUES (8, '2024-03-13', 412, 26);
INSERT INTO `statistic_day_amount` VALUES (9, '2024-03-14', 322, 71);
INSERT INTO `statistic_day_amount` VALUES (10, '2024-03-16', 234, 55);
INSERT INTO `statistic_day_amount` VALUES (11, '2024-03-17', 123, 22);
INSERT INTO `statistic_day_amount` VALUES (12, '2024-03-15', 149, 32);
INSERT INTO `statistic_day_amount` VALUES (13, '2024-03-18', 234, 22);
INSERT INTO `statistic_day_amount` VALUES (14, '2024-03-19', 0, 0);
INSERT INTO `statistic_day_amount` VALUES (15, '2024-04-01', 234, 55);
INSERT INTO `statistic_day_amount` VALUES (16, '2024-04-02', 195, 71);
INSERT INTO `statistic_day_amount` VALUES (17, '2024-03-27', 254, 32);
INSERT INTO `statistic_day_amount` VALUES (18, '2024-03-28', 115, 15);
INSERT INTO `statistic_day_amount` VALUES (19, '2024-03-29', 235, 35);
INSERT INTO `statistic_day_amount` VALUES (20, '2024-03-30', 352, 33);
INSERT INTO `statistic_day_amount` VALUES (21, '2024-03-31', 363, 43);
INSERT INTO `statistic_day_amount` VALUES (23, '2024-04-04', 0, 0);
INSERT INTO `statistic_day_amount` VALUES (24, '2024-04-09', 0, 0);
INSERT INTO `statistic_day_amount` VALUES (25, '2024-04-10', 0, 0);
INSERT INTO `statistic_day_amount` VALUES (26, '2024-04-11', 0, 0);
INSERT INTO `statistic_day_amount` VALUES (27, '2024-04-12', 1, 1);
INSERT INTO `statistic_day_amount` VALUES (35, '2024-04-13', 3, 4);
INSERT INTO `statistic_day_amount` VALUES (36, '2024-04-21', 1, 0);
INSERT INTO `statistic_day_amount` VALUES (37, '2024-04-22', 1, 0);
INSERT INTO `statistic_day_amount` VALUES (38, '2024-04-25', 1, 0);
INSERT INTO `statistic_day_amount` VALUES (39, '2024-04-26', 43, 11);
INSERT INTO `statistic_day_amount` VALUES (40, '2024-04-27', 31, 5);
INSERT INTO `statistic_day_amount` VALUES (41, '2024-04-28', 56, 29);
INSERT INTO `statistic_day_amount` VALUES (42, '2024-04-29', 48, 23);
INSERT INTO `statistic_day_amount` VALUES (43, '2024-04-30', 33, 11);
INSERT INTO `statistic_day_amount` VALUES (44, '2024-05-01', 57, 18);
INSERT INTO `statistic_day_amount` VALUES (45, '2024-05-02', 35, 2);
INSERT INTO `statistic_day_amount` VALUES (46, '2024-05-03', 66, 14);
INSERT INTO `statistic_day_amount` VALUES (47, '2024-05-06', 0, 0);

-- ----------------------------
-- Table structure for statistic_day_api
-- ----------------------------
DROP TABLE IF EXISTS `statistic_day_api`;
CREATE TABLE `statistic_day_api`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `successMigrate` int NULL DEFAULT NULL,
  `failMigrate` int NULL DEFAULT NULL,
  `successEnhance` int NULL DEFAULT NULL,
  `failEnhance` int NULL DEFAULT NULL,
  `successOcr` int NULL DEFAULT NULL,
  `failOcr` int NULL DEFAULT NULL,
  `successRecognition` int NULL DEFAULT NULL,
  `failRecognition` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_as_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of statistic_day_api
-- ----------------------------
INSERT INTO `statistic_day_api` VALUES (22, '2024-04-11', 8, 1, 1, 4, NULL, NULL, NULL, NULL);
INSERT INTO `statistic_day_api` VALUES (23, '2024-04-13', 2, 0, 5, 5, NULL, NULL, NULL, NULL);
INSERT INTO `statistic_day_api` VALUES (24, '2024-04-21', 0, 0, 0, 0, NULL, NULL, NULL, NULL);
INSERT INTO `statistic_day_api` VALUES (25, '2024-04-22', 0, 0, 0, 0, NULL, NULL, NULL, NULL);
INSERT INTO `statistic_day_api` VALUES (26, '2024-04-25', 2, 0, 0, 0, NULL, NULL, NULL, NULL);
INSERT INTO `statistic_day_api` VALUES (27, '2024-04-26', 156, 0, 213, 27, NULL, NULL, NULL, NULL);
INSERT INTO `statistic_day_api` VALUES (28, '2024-04-27', 111, 0, 142, 18, NULL, NULL, NULL, NULL);
INSERT INTO `statistic_day_api` VALUES (29, '2024-04-28', 188, 0, 266, 12, NULL, NULL, NULL, NULL);
INSERT INTO `statistic_day_api` VALUES (30, '2024-04-29', 178, 0, 255, 23, NULL, NULL, NULL, NULL);
INSERT INTO `statistic_day_api` VALUES (31, '2024-04-30', 155, 0, 210, 4, NULL, NULL, NULL, NULL);
INSERT INTO `statistic_day_api` VALUES (32, '2024-05-01', 166, 0, 223, 17, NULL, NULL, NULL, NULL);
INSERT INTO `statistic_day_api` VALUES (33, '2024-05-02', 101, 0, 134, 24, NULL, NULL, NULL, NULL);
INSERT INTO `statistic_day_api` VALUES (34, '2024-05-03', 150, 0, 179, 11, NULL, NULL, NULL, NULL);
INSERT INTO `statistic_day_api` VALUES (35, '2024-05-06', 0, 0, 0, 0, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for statistic_day_vip
-- ----------------------------
DROP TABLE IF EXISTS `statistic_day_vip`;
CREATE TABLE `statistic_day_vip`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `totalPrice` float NOT NULL,
  `totalPurchase` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_as_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of statistic_day_vip
-- ----------------------------
INSERT INTO `statistic_day_vip` VALUES (2, '2024-03-08', 99.99, 10);
INSERT INTO `statistic_day_vip` VALUES (3, '2024-03-09', 169, 16);
INSERT INTO `statistic_day_vip` VALUES (4, '2024-03-10', 216, 18);
INSERT INTO `statistic_day_vip` VALUES (5, '2024-03-11', 189.96, 13);
INSERT INTO `statistic_day_vip` VALUES (6, '2024-03-12', 316.69, 26);
INSERT INTO `statistic_day_vip` VALUES (7, '2024-03-13', 416.56, 43);
INSERT INTO `statistic_day_vip` VALUES (8, '2024-03-14', 106.82, 8);
INSERT INTO `statistic_day_vip` VALUES (9, '2024-03-15', 123.6, 11);
INSERT INTO `statistic_day_vip` VALUES (10, '2024-03-16', 234, 12);
INSERT INTO `statistic_day_vip` VALUES (11, '2024-03-17', 234, 12);
INSERT INTO `statistic_day_vip` VALUES (12, '2024-03-18', 123, 5);
INSERT INTO `statistic_day_vip` VALUES (13, '2024-03-27', 133, 15);
INSERT INTO `statistic_day_vip` VALUES (14, '2024-03-28', 120, 12);
INSERT INTO `statistic_day_vip` VALUES (15, '2024-03-29', 192, 16);
INSERT INTO `statistic_day_vip` VALUES (16, '2024-03-30', 211, 18);
INSERT INTO `statistic_day_vip` VALUES (17, '2024-03-31', 221, 19);
INSERT INTO `statistic_day_vip` VALUES (18, '2024-04-01', 168, 16);
INSERT INTO `statistic_day_vip` VALUES (19, '2024-04-02', 158, 12);
INSERT INTO `statistic_day_vip` VALUES (20, '2024-04-11', 1890, 7);
INSERT INTO `statistic_day_vip` VALUES (21, '2024-04-12', 2430, 8);
INSERT INTO `statistic_day_vip` VALUES (27, '2024-04-13', 270, 3);
INSERT INTO `statistic_day_vip` VALUES (28, '2024-04-26', 540, 6);
INSERT INTO `statistic_day_vip` VALUES (31, '2024-04-27', 270, 3);
INSERT INTO `statistic_day_vip` VALUES (32, '2024-04-28', 1350, 15);
INSERT INTO `statistic_day_vip` VALUES (33, '2024-04-29', 1080, 12);
INSERT INTO `statistic_day_vip` VALUES (34, '2024-04-30', 450, 5);
INSERT INTO `statistic_day_vip` VALUES (35, '2024-05-01', 810, 9);
INSERT INTO `statistic_day_vip` VALUES (36, '2024-05-02', 270, 3);
INSERT INTO `statistic_day_vip` VALUES (37, '2024-05-03', 630, 7);
INSERT INTO `statistic_day_vip` VALUES (38, '2024-05-03', 90, 1);

-- ----------------------------
-- Table structure for user_api
-- ----------------------------
DROP TABLE IF EXISTS `user_api`;
CREATE TABLE `user_api`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `apiId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NOT NULL,
  `userApiId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NULL DEFAULT NULL,
  `apiName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NULL DEFAULT NULL,
  `requestTime` datetime NULL DEFAULT NULL,
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NULL DEFAULT NULL,
  `statusCode` int NULL DEFAULT NULL,
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `apiId`) USING BTREE,
  INDEX `fkId`(`apiId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 91 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_as_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_api
-- ----------------------------
INSERT INTO `user_api` VALUES (12, 'vbs0rkjxjb1712925971294', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-04-12 20:46:11', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (13, 'r7rzpsg4xm1712925971370', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-04-12 20:46:11', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (14, 'bk9d0s2rrv1712925971371', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-04-12 20:46:11', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (15, 'usavow9qw21712925971444', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-04-12 20:46:11', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (16, 'omtg6su42t1712925971489', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-04-12 20:46:11', 'false', 500, 'AI处理失败');
INSERT INTO `user_api` VALUES (17, 'bf4qlt9mho1712925971722', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-04-12 20:46:11', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (18, 'xjim5p93lm1712925982412', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-04-12 20:46:22', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (19, 'ekerg6gcrj1712925982474', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-04-12 20:46:22', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (20, 'q9ulp5qm921712925982488', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-04-12 20:46:22', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (21, 'ks3cnxmrvc1712926000549', '29ef8ba3-d8f6-4845-b', 'enhance', '2024-04-12 20:46:40', 'false', 500, 'AI处理失败');
INSERT INTO `user_api` VALUES (22, 'p0ngnx37mc1712926000692', '29ef8ba3-d8f6-4845-b', 'enhance', '2024-04-12 20:46:40', 'false', 500, 'AI处理失败');
INSERT INTO `user_api` VALUES (23, 'dx0fdwu6ws1712926000871', '29ef8ba3-d8f6-4845-b', 'enhance', '2024-04-12 20:46:40', 'false', 500, 'AI处理失败');
INSERT INTO `user_api` VALUES (24, 'g3zexb94z81712926001010', '29ef8ba3-d8f6-4845-b', 'enhance', '2024-04-12 20:46:41', 'false', 500, 'AI处理失败');
INSERT INTO `user_api` VALUES (25, 'gdaf08rdxj1712926002964', '29ef8ba3-d8f6-4845-b', 'enhance', '2024-04-12 20:46:42', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (26, 'c90zwk3dcl1713009509910', 'ff29679e-c38c-4937-a', 'migrate', '2024-04-13 19:58:29', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (27, 'qkx1ucupig1713009515236', 'ff29679e-c38c-4937-a', 'enhance', '2024-04-13 19:58:35', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (28, 'dl3od3ooni1713009532357', 'ff29679e-c38c-4937-a', 'enhance', '2024-04-13 19:58:52', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (29, 'k4nhug18kb1713009540342', 'ff29679e-c38c-4937-a', 'enhance', '2024-04-13 19:59:00', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (30, 'svzligfjrq1713009542481', 'ff29679e-c38c-4937-a', 'enhance', '2024-04-13 19:59:02', 'false', 500, 'AI处理失败');
INSERT INTO `user_api` VALUES (31, 'oqnm0yxjki1713009542604', 'ff29679e-c38c-4937-a', 'enhance', '2024-04-13 19:59:02', 'false', 500, 'AI处理失败');
INSERT INTO `user_api` VALUES (32, 'd4i0fxie261713009542713', 'ff29679e-c38c-4937-a', 'enhance', '2024-04-13 19:59:02', 'false', 500, 'AI处理失败');
INSERT INTO `user_api` VALUES (33, 'y8x6y4u9il1713009542905', 'ff29679e-c38c-4937-a', 'enhance', '2024-04-13 19:59:02', 'false', 500, 'AI处理失败');
INSERT INTO `user_api` VALUES (34, 'dlmknk8nv41713009543083', 'ff29679e-c38c-4937-a', 'enhance', '2024-04-13 19:59:03', 'false', 500, 'AI处理失败');
INSERT INTO `user_api` VALUES (35, 'pg68tqzd891713009544030', 'ff29679e-c38c-4937-a', 'enhance', '2024-04-13 19:59:04', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (36, 'qbttq6bz081713009570255', '7a451e9d-aab2-4bef-8', 'migrate', '2024-04-13 19:59:30', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (37, 'j7b4p436bk1713009573782', '7a451e9d-aab2-4bef-8', 'enhance', '2024-04-13 19:59:33', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (38, 'koev1rclbw1714652231205', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-02 20:17:11', 'false', 500, 'AI处理失败');
INSERT INTO `user_api` VALUES (39, '7rzh190ne71714652748952', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-02 20:25:48', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (40, 'p6ts3luyrn1714652998368', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-02 20:29:58', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (41, 'cg1odsxtvm1714653120502', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-02 20:32:00', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (42, 'z5bfai0g5j1714653376742', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-02 20:36:16', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (43, 'g02ebcpz191714653386906', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-02 20:36:26', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (44, 'xf4iv2phxv1714653517167', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-02 20:38:37', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (45, 'f77jqwj87q1714653652078', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-02 20:40:52', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (46, 'xe9zvpdnkl1714654212692', '29ef8ba3-d8f6-4845-b', 'enhance', '2024-05-02 20:50:12', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (47, 'lw28582ew91714654239324', '29ef8ba3-d8f6-4845-b', 'enhance', '2024-05-02 20:50:39', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (48, 'nwgv9ivr3a1714667113525', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-03 00:25:13', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (49, 'k8zk6ivkif1714667182895', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-03 00:26:22', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (50, 'fz7viaqcpg1714668863386', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-03 00:54:23', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (51, 'd3vpxn90w71714669053639', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-03 00:57:33', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (52, '2rhjncxxrb1714669137699', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-03 00:58:57', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (53, 'nn0a40sjqp1714669354607', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-03 01:02:34', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (54, 'iw0yhskpjp1714669356040', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-03 01:02:36', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (55, 'oaas46sanu1714669364200', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-03 01:02:44', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (56, 'ji6s2q3uz61714669365378', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-03 01:02:45', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (57, 'cvra500wxk1714669366219', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-03 01:02:46', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (58, 'rcy65dxawj1714669366977', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-03 01:02:46', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (59, 'cfef9rnfuk1714669367727', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-03 01:02:47', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (60, 'prapzz4ju31714669368359', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-03 01:02:48', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (61, 'v1d8poymgc1714669369008', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-03 01:02:49', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (62, '2ecu0rvcgg1714669369679', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-03 01:02:49', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (63, 'r5h6gz3giq1714669973344', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-03 01:12:53', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (64, 'o6yqpe78ch1714669975089', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-03 01:12:55', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (65, '6vt8bqvxqv1714669976106', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-03 01:12:56', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (66, 'xco2yl4wde1714669976864', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-03 01:12:56', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (67, 'neb9ownyw41714669977798', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-03 01:12:57', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (68, 'z241ppxoaj1714669978521', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-03 01:12:58', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (69, 'furc9sl5ij1714669979219', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-03 01:12:59', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (70, 't4f9stk9ov1714670013032', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-03 01:13:33', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (71, '0fx1deaudq1714670029491', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-03 01:13:49', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (72, 'gzw9236agg1714670030799', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-03 01:13:50', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (73, '7ogrrkxmfa1714670031593', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-03 01:13:51', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (74, '1zrbhgptsd1714670032322', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-03 01:13:52', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (75, 'zzvwb07x1o1714670033055', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-03 01:13:53', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (76, 'nucy6phi4i1714670033804', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-03 01:13:53', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (77, 'c8y0o9b1wn1714670035072', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-03 01:13:55', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (78, 'zvo8tusq4w1714670043594', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-03 01:14:03', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (79, 'owpyh2l2f41714670160121', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-03 01:16:00', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (80, 'l296gn8vf71714670177001', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-03 01:16:17', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (81, 'gkmw4p3d5e1714670187815', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-03 01:16:27', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (82, '51eaeaovrp1714732779074', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-03 18:39:39', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (83, 'e211r7z6c11714796452182', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-04 12:20:52', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (84, 'chonvawlkb1714796511388', '29ef8ba3-d8f6-4845-b', 'enhance', '2024-05-04 12:21:51', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (85, 'plixs5o7o41714796516796', '29ef8ba3-d8f6-4845-b', 'enhance', '2024-05-04 12:21:56', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (86, 'xj64pl96ix1714808412151', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-04 15:40:12', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (87, '02x7pb7g591714808427448', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-04 15:40:27', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (88, 'ojmigcdv881714808441847', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-04 15:40:41', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (89, 'ox1rvtwevb1714809608768', '29ef8ba3-d8f6-4845-b', 'migrate', '2024-05-04 16:00:08', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (90, 'ng1m5davhg1714810622924', '29ef8ba3-d8f6-4845-b', 'enhance', '2024-05-04 16:17:02', 'true', 200, 'AI处理成功');
INSERT INTO `user_api` VALUES (91, '8cwhflwf5s1714810634961', '29ef8ba3-d8f6-4845-b', 'enhance', '2024-05-04 16:17:14', 'true', 200, 'AI处理成功');

-- ----------------------------
-- Table structure for user_feature_associations
-- ----------------------------
DROP TABLE IF EXISTS `user_feature_associations`;
CREATE TABLE `user_feature_associations`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `openId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NOT NULL,
  `userVipId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NULL DEFAULT NULL,
  `userPictureId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NULL DEFAULT NULL,
  `userApiId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `openId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_as_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_feature_associations
-- ----------------------------
INSERT INTO `user_feature_associations` VALUES (6, 'a28cxmenud1712908566457', '270af078-b641-46b2-a', 'a3f960a2-50cd-4cee-b', '29ef8ba3-d8f6-4845-b');
INSERT INTO `user_feature_associations` VALUES (7, 'cpaqexdugh1713009429429', '88dc5cb2-371f-430f-a', '15491bac-e9e8-4dff-a', 'ff29679e-c38c-4937-a');
INSERT INTO `user_feature_associations` VALUES (8, '0qi739m47s1713009444042', '3ebdad36-4d90-4da8-a', 'e359c744-330d-4511-9', '7a451e9d-aab2-4bef-8');
INSERT INTO `user_feature_associations` VALUES (9, 'qi56kuxusa1713009459406', 'cb8bd1e7-b235-435d-9', 'f3a74005-463b-40fb-8', '8c34a0d8-53f6-4c21-a');
INSERT INTO `user_feature_associations` VALUES (10, 'caluujbsbk1713009471963', '4d8ba6ca-5788-4a7b-9', 'c7fe2a31-cb90-439a-a', '5ddac58a-7575-4c6e-b');
INSERT INTO `user_feature_associations` VALUES (11, '612wgbq29g1714672473864', '5c38c971-46dc-4e55-b', '8459d43e-023f-4667-a', 'd2c7ed43-ca5f-49b4-b');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `openId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NOT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NOT NULL,
  `telephone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NOT NULL,
  `frozeId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NULL DEFAULT NULL,
  `nickName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NULL DEFAULT NULL,
  `password` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NOT NULL,
  `birthday` date NULL DEFAULT NULL,
  `identity` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NOT NULL DEFAULT '0',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NULL DEFAULT NULL,
  `sex` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NULL DEFAULT NULL,
  `email` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NULL DEFAULT NULL,
  `idCard` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NULL DEFAULT NULL,
  `createTime` datetime NULL DEFAULT NULL,
  `lastLoginTime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `openId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_as_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (17, 'a28cxmenud1712908566457', 'http://localhost:9000/api/icon/defaultAvatar.png', '18650271024', '80743b77-56dd-463e-8', '飞', '123123', NULL, 'manager', NULL, NULL, NULL, '111', '2024-04-12 15:56:06', '2024-05-04 15:59:54');
INSERT INTO `user_info` VALUES (18, 'cpaqexdugh1713009429429', 'http://localhost:9000/api/icon/defaultAvatar.png', '18650271031', '59812cf1-c4fb-4705-b', '测试账户1', '123123', NULL, 'user', NULL, NULL, NULL, NULL, '2024-04-13 19:57:09', '2024-04-13 19:58:02');
INSERT INTO `user_info` VALUES (19, '0qi739m47s1713009444042', 'http://localhost:9000/api/icon/defaultAvatar.png', '18650271032', 'e044fa44-f3ea-4b9d-9', '测试账户2', '123123', NULL, 'user', NULL, NULL, NULL, NULL, '2024-04-13 19:57:24', '2024-04-13 19:59:18');
INSERT INTO `user_info` VALUES (20, 'qi56kuxusa1713009459406', 'http://localhost:9000/api/icon/defaultAvatar.png', '18650271033', 'b550a30d-cc79-4f71-a', '测试账户3', '123123', NULL, 'user', NULL, NULL, NULL, NULL, '2024-04-13 19:57:39', NULL);
INSERT INTO `user_info` VALUES (21, 'caluujbsbk1713009471963', 'http://localhost:9000/api/icon/defaultAvatar.png', '18650271034', 'd626fa04-f7b6-4510-a', '测试账户4', '123123', NULL, 'user', NULL, NULL, NULL, NULL, '2024-04-13 19:57:52', NULL);
INSERT INTO `user_info` VALUES (22, '612wgbq29g1714672473864', 'http://localhost:9000/api/icon/defaultAvatar.png', '18650271026', '0b08ce98-85e0-4e1e-9', 'ccc', '123123', NULL, 'user', NULL, NULL, NULL, '22222', '2024-05-03 01:54:33', '2024-05-03 01:54:37');

-- ----------------------------
-- Table structure for user_picture
-- ----------------------------
DROP TABLE IF EXISTS `user_picture`;
CREATE TABLE `user_picture`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `pictureId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NOT NULL,
  `userPictureId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NOT NULL,
  `pictureUrl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NULL DEFAULT NULL,
  `createTime` datetime NULL DEFAULT NULL,
  `updateTime` datetime NULL DEFAULT NULL,
  `pictureSuffix` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NULL DEFAULT NULL,
  `pictureType` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `pictureId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 176 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_as_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_picture
-- ----------------------------
INSERT INTO `user_picture` VALUES (161, '5qflfi2zl81714653578495', 'a3f960a2-50cd-4cee-b', 'http://localhost:9000/api/migratePicture/5qflfi2zl81714653578495.png', '2024-05-02 20:39:38', '2024-05-02 20:39:38', 'png', 'migrate');
INSERT INTO `user_picture` VALUES (162, 'qzr089w8bf1714653654287', 'a3f960a2-50cd-4cee-b', 'http://localhost:9000/api/migratePicture/qzr089w8bf1714653654287.png', '2024-05-02 20:40:54', '2024-05-02 20:40:54', 'png', 'migrate');
INSERT INTO `user_picture` VALUES (166, 'vjtxlgf5ys1714658880697', 'a3f960a2-50cd-4cee-b', 'http://localhost:9000/api/migratePicture/vjtxlgf5ys1714658880697.png', '2024-05-02 22:08:00', '2024-05-02 22:08:00', 'png', 'migrate');
INSERT INTO `user_picture` VALUES (167, '8jpmi4rz3x1714670022297', 'a3f960a2-50cd-4cee-b', 'http://localhost:9000/api/migratePicture/8jpmi4rz3x1714670022297.png', '2024-05-03 01:13:42', '2024-05-03 01:13:42', 'png', 'migrate');
INSERT INTO `user_picture` VALUES (169, 'z30d9snq2x1714808424562', 'a3f960a2-50cd-4cee-b', 'http://localhost:9000/api/migratePicture/z30d9snq2x1714808424562.png', '2024-05-04 15:40:24', '2024-05-04 15:40:24', 'png', 'migrate');
INSERT INTO `user_picture` VALUES (170, '7mls5u0aej1714808494751', 'a3f960a2-50cd-4cee-b', 'http://localhost:9000/api/migratePicture/7mls5u0aej1714808494751.png', '2024-05-04 15:41:34', '2024-05-04 15:41:34', 'png', 'migrate');
INSERT INTO `user_picture` VALUES (171, 'a3gcni7m411714808504442', 'a3f960a2-50cd-4cee-b', 'http://localhost:9000/api/enhancePicture/a3gcni7m411714808504442.png', '2024-05-04 15:41:44', '2024-05-04 15:41:44', 'png', 'enhance');
INSERT INTO `user_picture` VALUES (172, 'do3rluuvai1714808515866', 'a3f960a2-50cd-4cee-b', 'http://localhost:9000/api/enhancePicture/do3rluuvai1714808515866.png', '2024-05-04 15:41:55', '2024-05-04 15:41:55', 'png', 'enhance');
INSERT INTO `user_picture` VALUES (173, '2haih6h35k1714808520723', 'a3f960a2-50cd-4cee-b', 'http://localhost:9000/api/enhancePicture/2haih6h35k1714808520723.png', '2024-05-04 15:42:00', '2024-05-04 15:42:00', 'png', 'enhance');
INSERT INTO `user_picture` VALUES (174, 'tf145dubtc1714808529749', 'a3f960a2-50cd-4cee-b', 'http://localhost:9000/api/migratePicture/tf145dubtc1714808529749.png', '2024-05-04 15:42:09', '2024-05-04 15:42:09', 'png', 'migrate');
INSERT INTO `user_picture` VALUES (175, 's1bapu1c241714808539380', 'a3f960a2-50cd-4cee-b', 'http://localhost:9000/api/migratePicture/s1bapu1c241714808539380.png', '2024-05-04 15:42:19', '2024-05-04 15:42:19', 'png', 'migrate');
INSERT INTO `user_picture` VALUES (176, 'wdgg2ka0lh1714809764947', 'a3f960a2-50cd-4cee-b', 'http://localhost:9000/api/enhancePicture/wdgg2ka0lh1714809764947.png', '2024-05-04 16:02:44', '2024-05-04 16:02:44', 'png', 'enhance');

-- ----------------------------
-- Table structure for vip_charge_record
-- ----------------------------
DROP TABLE IF EXISTS `vip_charge_record`;
CREATE TABLE `vip_charge_record`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `chargeId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NOT NULL,
  `userVipId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NOT NULL,
  `payTime` datetime NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `unit` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NULL DEFAULT NULL,
  `score` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NULL DEFAULT NULL,
  `payType` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NULL DEFAULT NULL,
  `renewDays` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NULL DEFAULT NULL,
  `operatingUser` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_as_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vip_charge_record
-- ----------------------------
INSERT INTO `vip_charge_record` VALUES (16, 'p7cz7sd6qf1712915208169', '270af078-b641-46b2-a', '2024-04-12 17:46:49', 90.00, '元', '300', '微信', '30', '用户');
INSERT INTO `vip_charge_record` VALUES (17, '4fz1tmdedb1712915215549', '270af078-b641-46b2-a', '2024-04-12 17:46:58', 270.00, '元', '900', '微信', '90', '用户');
INSERT INTO `vip_charge_record` VALUES (18, 'wre8namegs1712915223371', '270af078-b641-46b2-a', '2024-04-12 17:47:04', 540.00, '元', '1800', '微信', '180', '用户');
INSERT INTO `vip_charge_record` VALUES (19, 'bubbyyrxcu1712915229284', '270af078-b641-46b2-a', '2024-04-12 17:47:10', 270.00, '元', '900', '微信', '90', '用户');
INSERT INTO `vip_charge_record` VALUES (20, '231r3eog2q1712915246942', '270af078-b641-46b2-a', '2024-04-12 17:47:29', 540.00, '元', '1800', '微信', '180', '用户');
INSERT INTO `vip_charge_record` VALUES (21, 'jjcvv49bzl1712915261323', '270af078-b641-46b2-a', '2024-04-12 17:47:42', 90.00, '元', '300', '微信', '30', '用户');
INSERT INTO `vip_charge_record` VALUES (22, 'q34536b5y71712915269847', '270af078-b641-46b2-a', '2024-04-12 17:47:52', 90.00, '元', '300', '微信', '30', '用户');
INSERT INTO `vip_charge_record` VALUES (23, 'urzid5wviu1712920483059', '270af078-b641-46b2-a', '2024-04-12 19:14:45', 540.00, '元', '1800', '微信', '180', '用户');
INSERT INTO `vip_charge_record` VALUES (24, 'goi1j4486s1712932016637', '270af078-b641-46b2-a', '2024-04-12 22:26:58', 90.00, '元', '300', '微信', '30', '用户');
INSERT INTO `vip_charge_record` VALUES (25, 'kqa2r7l0os1713009362197', '270af078-b641-46b2-a', '2024-04-13 19:56:03', 90.00, '元', '300', '微信', '30', '用户');
INSERT INTO `vip_charge_record` VALUES (26, 'o4vyrfwlbm1713009389685', '270af078-b641-46b2-a', '2024-04-13 19:56:31', 540.00, '元', '1800', '微信', '180', '用户');
INSERT INTO `vip_charge_record` VALUES (27, 'd5vohz2few1713009396843', '270af078-b641-46b2-a', '2024-04-13 19:56:38', 90.00, '元', '300', '微信', '30', '用户');
INSERT INTO `vip_charge_record` VALUES (28, 'pey5heezxp1714653637986', '270af078-b641-46b2-a', '2024-05-02 20:40:39', 90.00, '元', '300', '微信', '30', '用户');
INSERT INTO `vip_charge_record` VALUES (29, 'eh49vqwz6n1714672574004', '5c38c971-46dc-4e55-b', '2024-05-03 01:56:16', 90.00, '元', '300', '微信', '30', '用户');
INSERT INTO `vip_charge_record` VALUES (30, 'vfj5pdwhl31714676008997', '270af078-b641-46b2-a', '2024-05-03 02:53:34', 540.00, '元', '1800', '微信', '180', '用户');
INSERT INTO `vip_charge_record` VALUES (31, '88moqet68r1714676023264', '270af078-b641-46b2-a', '2024-05-03 02:53:46', 270.00, '元', '900', '微信', '90', '用户');
INSERT INTO `vip_charge_record` VALUES (32, '4umuehe7af1714796634264', '270af078-b641-46b2-a', '2024-05-04 12:23:56', 90.00, '元', '300', '微信', '30', '用户');
INSERT INTO `vip_charge_record` VALUES (33, 'toyce8ydyi1714809833488', '270af078-b641-46b2-a', '2024-05-04 16:03:55', 90.00, '元', '300', '微信', '30', '用户');

SET FOREIGN_KEY_CHECKS = 1;
