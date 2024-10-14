package com.ruoyi.edu.service.impl;

import java.util.List;

import com.ruoyi.edu.domain.ProjectTeacher;
import com.ruoyi.edu.mapper.ProjectTeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.edu.mapper.ProjectMapper;
import com.ruoyi.edu.domain.Project;
import com.ruoyi.edu.service.IProjectService;

/**
 * 项目Service业务层处理
 * 
 * @author sanda
 * @date 2024-09-30
 */
@Service
public class ProjectServiceImpl implements IProjectService 
{
    @Autowired
    private ProjectMapper projectMapper;


    @Autowired
    private ProjectTeacherMapper projectTeacherMapper;
    /**
     * 查询项目
     * 
     * @param projectId 项目主键
     * @return 项目
     */
    @Override
    public Project selectProjectByProjectId(Long projectId)
    {
        return projectMapper.selectProjectByProjectId(projectId);
    }

    /**
     * 查询项目列表
     * 
     * @param project 项目
     * @return 项目
     */
    @Override
    public List<Project> selectProjectList(Project project)
    {
        return projectMapper.selectProjectList(project);
    }

    /**
     * 新增项目
     * 
     * @param project 项目
     * @return 结果
     */
    @Override
    public int insertProject(Project project)
    {
        int rows = projectMapper.insertProject(project);
        for (Long teacherId: project.getTeacherIds()) {
            ProjectTeacher pt = new ProjectTeacher();
            pt.setProjectId(project.getProjectId());
            pt.setTeacherId(teacherId);
            projectTeacherMapper.insertProjectTeacher(pt);
        }

        return rows;
    }

    /**
     * 修改项目
     * 
     * @param project 项目
     * @return 结果
     */
    @Override
    public int updateProject(Project project)
    {
        projectTeacherMapper.deleteByProjectId(project.getProjectId());

        insertProjectTeacher(project);
        return projectMapper.updateProject(project);
    }
    private void insertProjectTeacher(Project project) {

        for (Long teacherId : project.getTeacherIds()) {

            ProjectTeacher pt = new ProjectTeacher();

            pt.setProjectId(project.getProjectId());

            pt.setTeacherId(teacherId);

            projectTeacherMapper.insertProjectTeacher(pt);

        }
    }
    /**
     * 批量删除项目
     * 
     * @param projectIds 需要删除的项目主键
     * @return 结果
     */
    @Override
    public int deleteProjectByProjectIds(Long[] projectIds)
    {

        return projectMapper.deleteProjectByProjectIds(projectIds);
    }

    /**
     * 删除项目信息
     * 
     * @param projectId 项目主键
     * @return 结果
     */
    @Override
    public int deleteProjectByProjectId(Long projectId)
    {
        projectTeacherMapper.deleteByProjectId(projectId);
        return projectMapper.deleteProjectByProjectId(projectId);
    }
}
