package com.jcwx.game.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 项目表
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "t_project")
public class Project implements Serializable {
    public final static int projectId_裁决王座 = 3;
    public final static int projectId_嘟嘟熊 = 4;
    public final static int projectId_封魔游戏 = 1;
    public final static int projectId_战魂西游 = 2;
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Column(name = "project_Describe")
    private String projectDescribe;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Integer projectId;

    @Column(name = "project_Name")
    private String projectName;

    public Project() {
    }

    public Project(Integer projectId, String projectName, String projectDescribe) {
	this.projectId = projectId;
	this.projectName = projectName;
	this.projectDescribe = projectDescribe;
    }

    public String getProjectDescribe() {
	return projectDescribe;
    }

    public Integer getProjectId() {
	return projectId;
    }

    public String getProjectName() {
	return projectName;
    }

    public void setProjectDescribe(String projectDescribe) {
	this.projectDescribe = projectDescribe;
    }

    public void setProjectId(Integer projectId) {
	this.projectId = projectId;
    }

    public void setProjectName(String projectName) {
	this.projectName = projectName;
    }

    @Override
    public String toString() {
	return "Project [projectDescribe=" + projectDescribe + ", projectId="
		+ projectId + ", projectName=" + projectName + "]";
    }

    
}
