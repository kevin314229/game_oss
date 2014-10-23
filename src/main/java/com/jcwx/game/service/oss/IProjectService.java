package com.jcwx.game.service.oss;

import java.util.List;

import com.jcwx.game.domain.Project;

/**
 * 项目表
 * 
 * @author Administrator
 * 
 */
public interface IProjectService {

    /**
     * 创建Project
     * 
     * @param project
     * @return 数据影响条数
     */
    public Integer createProject(Project project);

    /**
     * 通过主键ID删除Project
     * 
     * @param projectId
     * @return 数据影响条数
     */
    public void deleteProjectByID(String projectId);

    /**
     * 通过主键ID查询Project
     * 
     * @param projectId
     * @return Project
     */
    public Project getProjectByID(String projectId);

    /**
     * 查询所有的Project
     * 
     * @return Project的集合
     */
    public List<Project> getProjectList();

    /**
     * 查询所有的Project
     * 
     * @return Project的集合
     */
    public List<Project> getProjectListbyUserName(String userName);

    /**
     * 修改Project
     * 
     * @param project
     * @return 数据影响条数
     */
    public void updateProject(Project project);

}
