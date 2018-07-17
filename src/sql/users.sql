/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50631
 Source Host           : localhost
 Source Database       : ccpush

 Target Server Type    : MySQL
 Target Server Version : 50631
 File Encoding         : utf-8

 Date: 11/03/2017 13:25:46 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) NOT NULL COMMENT '用户名',
  `pwd` varchar(50) NOT NULL COMMENT '密码',
  `salt` varchar(128) NOT NULL COMMENT '盐',
  `roles_id` int(11) NOT NULL COMMENT '角色id',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态 0禁用 1启用',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `email` varchar(200) NOT NULL DEFAULT '123',
  `mobile` varchar(50) NOT NULL DEFAULT '11111111111',
  `reason` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
--  Records of `users`
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES ('1', 'admin', '647599eb0bac180fb012392dbdc36db8', '!RvWzI7NR1o3uTij', '1', '1', '2017-02-20 14:43:38', 'panwei1@coocaa.com', '13652443171', null), ('25', '13682342230', 'a3bd0147345aa5344a954c0a00ffda7e', '+=r;lmOvvU6!P{r*', '7', '1', '2016-07-01 02:03:39', 'lixiaorong@coocaa.com', '13682342230', null), ('27', '18818534105', '611af59ed1edfcfe6947ea522cfbacb8', 'VRdBLM{_)y5l=pS8', '8', '1', '2016-07-04 09:27:33', 'yangdan@coocaa.com', '18818534105', null), ('28', '18688998776', 'c1f9bd229f0b51ec67631cba86abc263', '1ZqV;DyFxA4O$._-', '8', '1', '2016-07-06 07:38:37', 'xujianxin@coocaa.com', '18688998776', null), ('29', '18818996639', '192ce29c478243c49ee17d4f3394b798', 'ZVD*2Y9kXsD6aOtH', '1', '1', '2016-07-07 01:48:21', 'liupeng@coocaa.com', '18818996639', null), ('30', '13510476543', '6744361feaaca95308b52a3ec0be44b0', 'zQ{B{-x)naDyNBI<', '8', '1', '2016-07-07 01:57:00', 'litaiyi@coocaa.com', '13510476543', null), ('31', '15889674404', '4cbbe12a485238e59bf422c559985bef', '@2v%gcKf6c47K=^Y', '13', '1', '2016-07-07 01:58:11', '104020338@qq.com', '15889674404', null), ('32', '15919892657', 'e188cb3b38c1d212959f7fafa297cc45', '2Zsmhy*FX4,sIvYk', '12', '1', '2016-07-07 02:05:36', 'guanhaowen@skyworth.com', '15919892657', null), ('33', '13652443171', '9eb821f004d3c39773e513ac96350e38', '):qlyq8FWP*gr>m.', '1', '1', '2016-07-07 03:02:49', 'panwei3@coocaa.com', '13652443171', ''), ('34', '13640965562', 'dac0fb28b41a09ab01d50cb46da6b833', 'T3~{kU-w|lJ0!>)K', '1', '1', '2016-07-07 03:20:06', 'guanhuifang@coocaa.com', '13640965562', null), ('35', '13425175743', 'b587ee925b02072126cf1274466809f9', 'zb-US`kEtSyHezK)', '10', '1', '2016-07-07 06:52:35', 'chengjing@skyworth.com', '13425175743', null), ('36', '18682282552', '8a67775cb8904832e6421c57ba181ff1', '`bBFPu+}mcVUbGr3', '12', '1', '2016-07-07 09:32:10', 'lutinghuan@coocaa.com', '18682282552', null), ('37', '15013785106', '4b726f81bf8a9eb770438000a0e4cea2', '6`i`O(bQp0CtK.6!', '1', '1', '2016-07-13 01:44:52', 'tanwenliang@coocaa.com', '15013785106', null), ('38', '13928475531', '50847ac23694ea865264ae6c542186a4', '!}`)91pu>vg<b|2B', '4', '1', '2016-07-14 10:21:20', '814430761@qq.com', '13928475531', null), ('39', '18123986518', '1990bf46f1d851df082b45aad3f7e89b', 'XM7!!v{hUYz%B)qX', '4', '1', '2016-07-15 06:49:49', 'zhouhui@coocaa.com', '18123986518', null), ('40', '13631535170', '5d886f98124be930260e9232dc26f113', '~xF:e~YX$`wgn8ot', '8', '1', '2016-07-15 09:51:09', '3250065814@qq.com', '13631535170', null), ('41', '13420912971', 'c08171cd827cb74cdde800ba9b7acd6e', 'EP,*m8d+h<{998:=', '4', '1', '2016-07-21 02:08:06', 'huangzhexin@coocaa.com', '13420912971', null), ('43', '15019423431', 'd1285e9da910bd95859fcef6c89fa08d', '`+6c,(bodQfW%Ma*', '4', '1', '2016-07-28 02:55:10', 'xiechunding@coocaa.com', '15019423431', null), ('44', '13128736245', '7e274476c726dbf91003597fd2ce19da', 'JrC~,q$kkWY<OP3C', '11', '1', '2016-08-03 02:16:30', 'tianjisheng@skyworth.com', '13128736245', null), ('45', '13828734694', 'e7670b0001589e98e1f14fb927d016bd', 'BLWF$Um!*n|hXYK|', '1', '1', '2016-08-08 03:03:23', 'yexingwang@coocaa.com', '13828734694', null), ('46', '13923790560', '252565b7292e2461c3bf4b09332a8a56', '$`k(-fJV(RAnCDkN', '12', '1', '2016-08-16 12:14:20', 'fukai@skyworth.com', '13923790560', null), ('47', '13554858324', 'b8f33e82a0d1b8f51bd356e5be9ad161', 'zSI6iIlp=xAg=X>j', '12', '1', '2016-09-01 09:24:02', 'huangguanyin@coocaa.com', '13554858324', null), ('48', '15602979893', '34f05866fa7b3386c810bd4557b8b768', '-VXd76=gL`+(LQu3', '15', '1', '2016-09-26 07:07:16', 'zhaojiani@skyworth.com', '15602979893', null), ('49', '13632988153', '7d5c26eab6cc33d93a3c1de263f5737a', 'Xu}|_|V%AKdes.w0', '15', '1', '2016-09-26 07:27:51', 'chenwei10@skyworth.com', '13632988153', null), ('50', '18621103110', '0fba17b3e98b60899c15fcb1c5f74963', 'EI(kz}>v,;t`q_f-', '9', '0', '2016-09-28 07:41:03', 'zhoushaoyou@skyworth.com', '18621103110', null), ('51', '15013799511', '73e7f6d2223ecd41de54aceb7f8dc3e2', 'z%5{q=BK+$+Q0VY`', '15', '1', '2016-09-28 09:12:36', 'yanlifeng@skyworth.com', '15013799511', null), ('56', '13652443173', 'efec7eb2289d26d3cbfdcd431bd2c24d', 'a0^zR.Y42nE|@fzb', '9', '1', '2017-04-05 11:43:05', 'panwei@coocaa.com', '13652443173', 'test');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
