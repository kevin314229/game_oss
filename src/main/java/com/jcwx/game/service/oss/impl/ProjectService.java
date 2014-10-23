package com.jcwx.game.service.oss.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcwx.game.dao.IBaseDAO;
import com.jcwx.game.domain.Project;
import com.jcwx.game.service.oss.IProjectService;

/**
 * oss项目实现
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings("unchecked")
@Service
public class ProjectService implements IProjectService {

    @Autowired
    private IBaseDAO baseDAO;

    @Override
    public Integer createProject(Project project) {
	return (Integer) this.baseDAO.insert("Project.createProject", project);
    }

    @Override
    public void deleteProjectByID(String projectId) {
	this.baseDAO.delete("Project.deleteProjectByID", projectId);
    }

    @Override
    public Project getProjectByID(String projectId) {
	return (Project) this.baseDAO.queryForObject("Project.getProjectByID",
		projectId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Project> getProjectList() {
	return this.baseDAO.queryForList("Project.getProjectList");
    }

    @Override
    public List<Project> getProjectListbyUserName(String userName) {
	return this.baseDAO.queryForList("Project.getProjectListbyUserName",
		userName);
    }

    @Override
    public void updateProject(Project project) {
	this.baseDAO.update("Project.updateProject", project);
    }

}
