package com.ruoyi.edu.service;

import java.util.List;
import com.ruoyi.edu.domain.EduCourse;

/**
 * 课程Service接口
 * 
 * @author ruoyi
 * @date 2024-03-13
 */
public interface IEduCourseService 
{
    /**
     * 查询课程
     * 
     * @param courseId 课程主键
     * @return 课程
     */
    public EduCourse selectEduCourseByCourseId(Long courseId);

    /**
     * 查询课程列表
     * 
     * @param eduCourse 课程
     * @return 课程集合
     */
    public List<EduCourse> selectEduCourseList(EduCourse eduCourse);

    /**
     * 新增课程
     * 
     * @param eduCourse 课程
     * @return 结果
     */
    public int insertEduCourse(EduCourse eduCourse);

    /**
     * 修改课程
     * 
     * @param eduCourse 课程
     * @return 结果
     */
    public int updateEduCourse(EduCourse eduCourse);

    /**
     * 批量删除课程
     * 
     * @param courseIds 需要删除的课程主键集合
     * @return 结果
     */
    public int deleteEduCourseByCourseIds(Long[] courseIds);

    /**
     * 删除课程信息
     * 
     * @param courseId 课程主键
     * @return 结果
     */
    public int deleteEduCourseByCourseId(Long courseId);
}
