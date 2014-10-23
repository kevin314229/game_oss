-- -------------------  begin   ---------------------------

-- 初始用户名密码 ossadmin  what!#
INSERT INTO T_OSS_USER(USERNAME, PASSWORD, LAST_LOGIN_TIME, LAST_LOGIN_IP, LOGIN_NUM, CREATE_TIME, REALNAMES ,IS_OPERATOR,STATUS) 
VALUES('ossadmin', 'BE477341A0EB8A9C3878A2E66AA1D9CE', '2013-11-23 14:01:30', '127.0.0.1', 1, '2011-04-26 11:12:10', '管理员','0','1');
-- 添加角色
INSERT INTO T_OSS_ROLE(OSS_ROLE_ID ,ROLE_DESC, ROLE_CODE, ROLE_NAME, ROLE_TYPE)
 VALUES(1,'系统管理员','admin','系统管理员','0');
		
-- 添加用户角色关联
INSERT INTO T_OSS_USER_ROLE(USERNAME, OSS_ROLE_ID) VALUES('ossadmin',1);

-- 添加权限管理页面权限
INSERT INTO T_OSS_ROLE_MENU(OSS_ROLE_ID, OSS_MENU_ID) VALUES(1, '06002');

-- 测试运营商
INSERT INTO t_oss_operation(id,operation_name,carrier_operator,operation_detail,dividend_rate )
values(1,'测试服','test','test server',50);

-- 添加测试服务器
INSERT into t_oss_server(id,server_id,server_code,server_provider,`name`,url,communicate_key,create_time,create_user ,update_time)
values(1,1,'test','test','test server one','http://127.1.1.1:10900','25mnDSghfMV6e1NOmK2PVg==',SYSDATE(),1,SYSDATE());

-- 添加服务器 用户关联

INSERT into t_oss_user_server(id,username,operation_id,server_id,create_time,create_user)
values(1,'ossadmin',1,1,SYSDATE(),1);


 -- 2、t_oss_menu  这个表数据同步
-- --------   eng   -------------------------------------------