SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_interface
-- ----------------------------
DROP TABLE IF EXISTS `sys_interface`;
CREATE TABLE `sys_interface`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `parent_id` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '父级id，根路径为0',
  `pms_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '名称',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '接口路径',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_url`(`url`) USING BTREE COMMENT '接口路径唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '系统接口表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_interface
-- ----------------------------
INSERT INTO `sys_interface` VALUES (1, 0, '管理员权限', '/session/admin');
INSERT INTO `sys_interface` VALUES (2, 0, '普通用户权限', '/session/normal');
INSERT INTO `sys_interface` VALUES (3, 0, '添加用户', '/user/addUser');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '角色名称',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '系统角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', '2020-07-18 14:05:37', NULL, '2020-07-18 14:05:37', NULL);
INSERT INTO `sys_role` VALUES (2, '普通用户', '2020-07-18 14:05:45', NULL, '2020-07-18 14:05:45', NULL);

-- ----------------------------
-- Table structure for sys_role_interface
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_interface`;
CREATE TABLE `sys_role_interface`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `sys_role_id` int(11) NULL DEFAULT NULL COMMENT '角色id',
  `sys_interface_id` int(11) NULL DEFAULT NULL COMMENT '路径id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '角色与接口路径关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_interface
-- ----------------------------
INSERT INTO `sys_role_interface` VALUES (1, 1, 1);
INSERT INTO `sys_role_interface` VALUES (2, 1, 2);
INSERT INTO `sys_role_interface` VALUES (3, 1, 3);
INSERT INTO `sys_role_interface` VALUES (4, 2, 2);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `username` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  `name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '名称',
  `user_status` int(4) UNSIGNED NULL DEFAULT 1 COMMENT '用户状态 1启用 2停用',
  `lock_status` int(4) NULL DEFAULT 1 COMMENT '预留状态 1正常 2锁定',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_username`(`username`) USING BTREE COMMENT '用户名唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '系统用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'kanade', '$2a$10$HUURp7wXRBJxP2IKoDMOEOp/AEXz2QtrNzJYzKUrrzxBicgSH4vjG', 'kanade', 1, 1, '2020-07-18 14:10:08', '2020-07-18 10:19:42', NULL, '2020-07-18 14:10:08', NULL);
INSERT INTO `sys_user` VALUES (2, 'user', '$2a$10$6ZhokxWqwzY9OxmQLowyUui4r/ySadZDxSf5K2ju3TRlYLhQ/qrZy', 'user', 1, 1, '2020-07-18 14:13:38', '2020-07-18 14:10:37', NULL, '2020-07-18 14:21:13', NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `sys_user_id` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '系统用户主键id',
  `sys_role_id` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '系统角色主键id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 1);
INSERT INTO `sys_user_role` VALUES (2, 1, 2);
INSERT INTO `sys_user_role` VALUES (3, 2, 2);

SET FOREIGN_KEY_CHECKS = 1;
