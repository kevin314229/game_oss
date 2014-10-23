
--  Oss 数据修复  Oss --


--  创建项目表

CREATE TABLE `t_project` (
  `project_id` int(11) NOT NULL auto_increment,
  `project_name` varchar(255) default NULL,
  `project_describe` varchar(255) default NULL,
  PRIMARY KEY  (`project_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='项目表';


INSERT into t_project(project_id ,project_name,project_describe)
values (1,'封魔','封魔游戏');

INSERT into t_project(project_id ,project_name,project_describe)
values (2,'战魂西游','战魂西游游戏');

alter table t_oss_role add PROJECT_ID int(11) default 0 comment '项目ID';

alter table t_oss_role add PARENT_ROLE_ID int(11) default 0 comment '上级岗位ID';

update t_oss_role set PARENT_ROLE_ID=1 where OSS_ROLE_ID !=1 ;

-- 设置默认项目为封魔
update t_oss_role set PROJECT_ID=1 ;


alter table t_oss_menu add PROJECT_ID int(11) DEFAULT 0 COMMENT '项目ID';

update t_oss_menu set PROJECT_ID =1 ;

ALTER table t_oss_server add PROJECT_ID int(11) DEFAULT 0 COMMENT '项目ID';

update t_oss_server set PROJECT_ID =1 ;



