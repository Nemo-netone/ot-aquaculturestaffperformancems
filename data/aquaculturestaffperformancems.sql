/*
 Navicat MySQL Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : aquaculturestaffperformancems

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 19/02/2025 18:20:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_department
-- ----------------------------
DROP TABLE IF EXISTS `t_department`;
CREATE TABLE `t_department`  (
  `did` int(0) NOT NULL AUTO_INCREMENT,
  `dname` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `description` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `ctime` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `operator` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`did`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_department
-- ----------------------------
INSERT INTO `t_department` VALUES (1, '行政人资部门', '负责公司的行政和人力资源管理', '2024-12-18 09:45:16', 'system');
INSERT INTO `t_department` VALUES (2, '肉猪养殖部门', '负责肉猪的养殖工作，保障肉猪的健康生长，提升肉猪品质，实现养殖效益的最大化', '2024-12-18 17:57:49', 'admin');
INSERT INTO `t_department` VALUES (4, '肉牛养殖部门', '负责肉牛的养殖工作，保障肉牛的健康生长，提升肉牛品质，实现养殖效益的最大化。', '2025-01-24 16:13:10', 'admin');
INSERT INTO `t_department` VALUES (5, '家禽养殖部门', '负责鸡、鸭、鹅等家禽的养殖管理。', '2025-01-24 16:31:30', 'admin');
INSERT INTO `t_department` VALUES (6, '羊养殖部门', '负责羊的养殖工作，保障羊的健康生长，提升羊品质，实现养殖效益的最大化。', '2025-01-25 20:12:58', 'admin');

-- ----------------------------
-- Table structure for t_live
-- ----------------------------
DROP TABLE IF EXISTS `t_live`;
CREATE TABLE `t_live`  (
  `lid` int(0) NOT NULL AUTO_INCREMENT,
  `data` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `remarks` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `operator` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `time` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`lid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_live
-- ----------------------------
INSERT INTO `t_live` VALUES (1, '0.5 - 1.0', '猪', 'admin', '2024-12-18');
INSERT INTO `t_live` VALUES (2, '0.05 - 0.1', '鸡', 'admin', '2024-12-18');
INSERT INTO `t_live` VALUES (3, '1.0 - 2.0', '牛', 'admin', '2025-01-14');
INSERT INTO `t_live` VALUES (4, '0.2-0.3', '羊', 'admin', '2025-01-25');
INSERT INTO `t_live` VALUES (6, '0.05 -0.08', '鸭', 'admin', '2025-01-25');

-- ----------------------------
-- Table structure for t_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice`  (
  `nid` int(0) NOT NULL AUTO_INCREMENT,
  `title` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `notice` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `ctime` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `operator` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`nid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_notice
-- ----------------------------
INSERT INTO `t_notice` VALUES (1, '关于春节放假安排的通知', '全体员工：\n根据国家法定节假日安排，结合公司实际情况，2024年春节放假安排如下：\n放假时间：2025年1月1日（周日）至1月9日（周四），共8天。\n值班安排：各部门需安排人员值班，确保牲畜养殖工作正常进行。\n祝大家节日愉快！', '2024-12-18 18:00:04', 'admin');
INSERT INTO `t_notice` VALUES (2, '关于新员工入职培训的通知', '全体员工：\n为帮助新员工快速融入团队，公司将于2025年2月15日举办入职培训，具体安排如下：\n时间：2月15日 9:00 - 17:00\n地点：公司会议室\n公司文化介绍\n绩效考核制度讲解\n任务管理系统操作培训\n请新员工准时参加，其他员工也可自愿报名。', '2025-01-25 20:32:25', 'admin');
INSERT INTO `t_notice` VALUES (3, '关于2024年优秀员工表彰的决定', '全体员工：\n根据2024年前三季度绩效考核结果，以下员工表现突出，被评为“优秀员工”：\n李三（家禽养殖部门）\n李四（肉猪养殖部门）\n李五（羊养殖部门）\n他们将获得 奖金500元 及 荣誉证书。希望全体员工以他们为榜样，再接再厉！', '2025-02-18 21:37:13', 'admin');
INSERT INTO `t_notice` VALUES (7, '标题', '内容', '2025-02-19 14:57:47', 'admin1');

-- ----------------------------
-- Table structure for t_performance
-- ----------------------------
DROP TABLE IF EXISTS `t_performance`;
CREATE TABLE `t_performance`  (
  `pfmcid` int(0) NOT NULL AUTO_INCREMENT,
  `uid` int(0) NOT NULL,
  `period` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `result` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `status` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `appeal` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `utime` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`pfmcid`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  CONSTRAINT `t_performance_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `t_user` (`uid`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_performance
-- ----------------------------
INSERT INTO `t_performance` VALUES (21, 5, '2025-02-18至2025-02-26', '合格，增重均值在目标增重范围内。', '考核中', '-', '2025-02-19 17:53:57');
INSERT INTO `t_performance` VALUES (30, 5, '2025-01-02至2025-01-09', '申诉原因符合', '申诉成功', '申诉原因test', '2025-02-19 17:50:19');
INSERT INTO `t_performance` VALUES (31, 5, '2025-02-19至2025-02-26', '-', '考核中', '-', '2025-02-19 17:43:40');

-- ----------------------------
-- Table structure for t_position
-- ----------------------------
DROP TABLE IF EXISTS `t_position`;
CREATE TABLE `t_position`  (
  `pid` int(0) NOT NULL AUTO_INCREMENT,
  `did` int(0) NOT NULL,
  `pname` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `description` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `requirements` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`pid`) USING BTREE,
  INDEX `did`(`did`) USING BTREE,
  CONSTRAINT `t_position_ibfk_1` FOREIGN KEY (`did`) REFERENCES `t_department` (`did`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_position
-- ----------------------------
INSERT INTO `t_position` VALUES (1, 1, '人力资源岗', '负责员工的招聘、培训、绩效评估等工作', '需要具备人力资源管理相关证书和经验');
INSERT INTO `t_position` VALUES (6, 2, '养猪工人', '负责猪的养殖工作', '保证猪在指标内的增重');
INSERT INTO `t_position` VALUES (7, 2, '猪疫病防员工', '及时对猪进行治疗', '保证猪的健康并增重。');
INSERT INTO `t_position` VALUES (8, 4, '养牛工人', '负责牛的养殖工作	', '保证牛在指标内的增重');
INSERT INTO `t_position` VALUES (9, 5, '家禽养殖工人', '负责鸡、鸭、鹅等家禽的养殖。', '保证鸡、鸭、鹅等家禽在指标范围内的增重。');
INSERT INTO `t_position` VALUES (10, 6, '羊养殖工人', '负责羊的养殖工作', '保证羊在指标范围内的增重。');
INSERT INTO `t_position` VALUES (13, 2, '岗位名称', '岗位描述', '要求');

-- ----------------------------
-- Table structure for t_reward
-- ----------------------------
DROP TABLE IF EXISTS `t_reward`;
CREATE TABLE `t_reward`  (
  `rid` int(0) NOT NULL AUTO_INCREMENT,
  `uid` int(0) NOT NULL,
  `reason` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `type` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `time` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`rid`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  CONSTRAINT `t_reward_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `t_user` (`uid`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_reward
-- ----------------------------
INSERT INTO `t_reward` VALUES (3, 5, '一月工作优秀完成，奖励100元。', '奖', '2025-01-25');
INSERT INTO `t_reward` VALUES (5, 5, '工作优秀完成', '奖', '2025-01-26');
INSERT INTO `t_reward` VALUES (6, 15, '一月工作未达标，扣款50元', '惩', '2025-02-19');
INSERT INTO `t_reward` VALUES (7, 14, '工作未达标，扣款50元', '惩', '2025-02-19');
INSERT INTO `t_reward` VALUES (8, 16, '工作达标完成，奖励50元。', '奖', '2025-02-19');
INSERT INTO `t_reward` VALUES (9, 5, '工作优秀完成。test', '奖', '2025-02-19');
INSERT INTO `t_reward` VALUES (10, 5, '工作合格，奖励50，', '奖', '2025-02-19');

-- ----------------------------
-- Table structure for t_task
-- ----------------------------
DROP TABLE IF EXISTS `t_task`;
CREATE TABLE `t_task`  (
  `tid` int(0) NOT NULL AUTO_INCREMENT,
  `uid` int(0) NOT NULL,
  `description` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `stime` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `etime` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `status` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `completion` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `photo` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `operator` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`tid`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  CONSTRAINT `t_task_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `t_user` (`uid`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_task
-- ----------------------------
INSERT INTO `t_task` VALUES (6, 5, '按时投喂优质饲料，监测猪只生长状况，做好圈舍清洁与温湿度调控。', '2025-01-17', '2025-01-31', '已完成', '已完成工作任务', '90c6c9dc-39b0-4eec-9e62-fbfd46a0a3b3.jpg', 'admin');
INSERT INTO `t_task` VALUES (7, 5, '按时投喂优质饲料，监测猪只生长状况。', '2025-02-01', '2025-02-08', '已完成', '已完成饲养，清扫', '64d952e7-fabb-44d2-8ac6-d93d5615eda2.jpg', 'admin');
INSERT INTO `t_task` VALUES (9, 5, '按时投喂优质饲料，监测猪只生长状况，进行清扫任务。', '2025-02-08', '2025-02-15', '已完成', '-已完成', '4238234b-0cc4-430a-8050-9aec1372cf74.jpg', 'admin');
INSERT INTO `t_task` VALUES (12, 14, '合理补饲，监测增重情况，观察肉牛健康，定期清理牛舍并消毒。', '2025-02-12', '2025-02-19', '未完成', '-', '-', 'admin');
INSERT INTO `t_task` VALUES (13, 17, '适时补草补料，做好羊只疫病预防与羊圈卫生，观察羊只增重情况。', '2025-02-14', '2025-02-21', '未完成', '-', '-', 'admin');
INSERT INTO `t_task` VALUES (15, 5, '清扫任务，饲喂任务，设备维护。', '2025-02-19', '2025-02-26', '已完成', '已完成', 'b8370721-184f-4696-a106-c910c565d6c0.jpg', 'admin1');
INSERT INTO `t_task` VALUES (18, 5, '任务描述', '2025-03-01', '2025-03-30', '未完成', '-', '-', 'admin1');
INSERT INTO `t_task` VALUES (19, 15, 'test任务描述', '2025-02-19', '2025-02-26', '未完成', '-', '-', 'admin1');
INSERT INTO `t_task` VALUES (20, 14, '任务描述test', '2025-02-19', '2025-02-26', '未完成', '-', '-', 'admin1');
INSERT INTO `t_task` VALUES (21, 17, '合理补饲，监测增重情况，观察羊健康，定期清理羊舍并消毒。', '2025-02-19', '2025-02-26', '未完成', '-', '-', 'admin1');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `uid` int(0) NOT NULL AUTO_INCREMENT,
  `did` int(0) NOT NULL,
  `pid` int(0) NOT NULL,
  `username` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `identify` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `sex` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `age` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `birthdate` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `idcard` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `phone` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `avatar` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (5, 2, 6, 'lisi', 'lisi123', '普通员工', '李四', '女', '20', '2004-01-29', '411222200401230022', '16372838273', 'user.jpg');
INSERT INTO `t_user` VALUES (14, 4, 8, 'liyi', 'liyi123', '普通员工', '李毅', '男', '40', '1984-01-02', '411222198401022987', '138736278726', '1001bfb8-58f1-406c-9034-5530ece617a6.jpg');
INSERT INTO `t_user` VALUES (15, 2, 6, 'lier', 'lier123', '普通员工', '李二', '女', '20', '2004-02-02', '411222200402022987', '13872673872', '6bf2e5d1-899b-4b71-be67-4672933e6274.jpg');
INSERT INTO `t_user` VALUES (16, 5, 9, 'lisan', 'lisan123', '普通员工', '李三', '女', '22', '2002-01-22', '411222200201220292', '13887372637', '8494903d-170a-49a7-8117-983916991eb4.jpg');
INSERT INTO `t_user` VALUES (17, 6, 10, 'liwu', 'liwu123', '普通员工', '李五', '女', '34', '1990-03-22', '410292199003222938', '13782793092', 'ac09e037-f75f-411d-8728-f1b32d840fb1.jpg');
INSERT INTO `t_user` VALUES (18, 5, 6, 'admin', 'admin', '普通员工', '王四', '女', '34', '1990-03-22', '411222199003228789', '15903988652', '9a1e2aa3-3656-434b-9e10-4d27ec38350b.jpg');
INSERT INTO `t_user` VALUES (19, 1, 1, 'admin1', 'admin123', '管理员', '王五', '女', '27', '1997-02-03', '411222199702038786', '17283728379', '0809e082-8730-4ab4-b926-6e6a9e4a1c55.gif');
INSERT INTO `t_user` VALUES (22, 2, 13, 'wangsi', 'wangsi123', '普通员工', '-', '-', '', '-', '-', '-', 'user.jpg');

-- ----------------------------
-- Table structure for t_work
-- ----------------------------
DROP TABLE IF EXISTS `t_work`;
CREATE TABLE `t_work`  (
  `wid` int(0) NOT NULL AUTO_INCREMENT,
  `uid` int(0) NOT NULL,
  `data` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `remarks` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `worklist` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `time` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `photo` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`wid`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  CONSTRAINT `t_work_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `t_user` (`uid`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_work
-- ----------------------------
INSERT INTO `t_work` VALUES (12, 5, '0.5', '猪', '饲料投喂：每日按早、中、晚三次定时投喂优质配合饲料，确保饲料新鲜无霉变，保障猪群营\n养摄入以实现平均每只日增重 达到标准。\n卫生清洁：每天清扫猪舍地面、过道及排污沟，及时清除粪便、杂物等，保持猪舍干净整洁。\n设备维护：定期检查猪舍内的栏舍、食槽、饮水器、通风设备等设施设备，发现损坏或故障及\n时报修并记录，保障设备正常运行。', '2025-01-26', '0c4d71e6-abfb-417d-b554-7f941b8f98a7.jpg');
INSERT INTO `t_work` VALUES (16, 5, '0.5', '猪', '饲料投喂：每日按早、中、晚三次定时投喂优质配合饲料，确保饲料新鲜无霉变，保障猪群营\n养摄入以实现平均每只日增重 达到标准。\n卫生清洁：每天清扫猪舍地面、过道及排污沟，及时清除粪便、杂物等，保持猪舍干净整洁。\n设备维护：定期检查猪舍内的栏舍、食槽、饮水器、通风设备等设施设备，发现损坏或故障及\n时报修并记录，保障设备正常运行。', '2025-02-18', '60af5830-bf4b-40f8-8205-36db2fde127b.jpg');
INSERT INTO `t_work` VALUES (17, 5, '0.6', '猪', '饲料投喂：每日按早、中、晚三次定时投喂优质配合饲料，确保饲料新鲜无霉变，保障猪群营\n养摄入以实现平均每只日增重 达到标准。\n卫生清洁：每天清扫猪舍地面、过道及排污沟，及时清除粪便、杂物等，保持猪舍干净整洁。\n设备维护：定期检查猪舍内的栏舍、食槽、饮水器、通风设备等设施设备，发现损坏或故障及\n时报修并记录，保障设备正常运行。', '2025-02-19', 'cd93b39a-ac72-4d69-8649-73414880ceb3.jpg');
INSERT INTO `t_work` VALUES (20, 5, '0.6', '猪', 'test清扫完成饲喂完成，设备维护完成。', '2025-02-19', '5f6c7d6f-73ac-4a96-80e4-1545aecf1d9e.jpg');
INSERT INTO `t_work` VALUES (21, 5, '1', '猪', '清扫完毕，饲养完毕，一日三次。', '2025-02-19', '7f700f79-2405-4190-bf32-b7050ce36605.jpg');
INSERT INTO `t_work` VALUES (22, 22, '1', '猪', '工作已完成', '2025-02-19', '4a7bee28-63cc-4044-af70-70a592458793.jpg');

SET FOREIGN_KEY_CHECKS = 1;
