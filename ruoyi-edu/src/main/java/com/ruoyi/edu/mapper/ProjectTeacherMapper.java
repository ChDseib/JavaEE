package com.ruoyi.edu.mapper;

import com.ruoyi.edu.domain.ProjectTeacher;

public interface ProjectTeacherMapper {

    public int insertProjectTeacher(ProjectTeacher projectTeacher);
    public int deleteByProjectId(Long projectId);
}