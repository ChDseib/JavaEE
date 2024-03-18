package com.ruoyi.edu.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.edu.mapper.EduCourseMapper;
import com.ruoyi.edu.domain.EduCourse;
import com.ruoyi.edu.service.IEduCourseService;

/**
 * 课程Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-13
 */
@Service
public class EduCourseServiceImpl implements IEduCourseService 
{
    @Autowired
    private EduCourseMapper eduCourseMapper;

    /**
     * 查询课程
     * 
     * @param courseId 课程主键
     * @return 课程
     */
    @Override
    public EduCourse selectEduCourseByCourseId(Long courseId)
    {
        return eduCourseMapper.selectEduCourseByCourseId(courseId);
    }

    /**
     * 查询课程列表
     * 
     * @param eduCourse 课程
     * @return 课程
     */
    @Override
    public List<EduCourse> selectEduCourseList(EduCourse eduCourse)
    {
        return eduCourseMapper.selectEduCourseList(eduCourse);
    }

    /**
     * 新增课程
     * 
     * @param eduCourse 课程
     * @return 结果
     */
    @Override
    public int insertEduCourse(EduCourse eduCourse)
    {
        return eduCourseMapper.insertEduCourse(eduCourse);
    }

    /**
     * 修改课程
     * 
     * @param eduCourse 课程
     * @return 结果
     */
    @Override
    public int updateEduCourse(EduCourse eduCourse)
    {
        return eduCourseMapper.updateEduCourse(eduCourse);
    }

    /**
     * 批量删除课程
     * 
     * @param courseIds 需要删除的课程主键
     * @return 结果
     */
    @Override
    public int deleteEduCourseByCourseIds(Long[] courseIds)
    {
        return eduCourseMapper.deleteEduCourseByCourseIds(courseIds);
    }

    /**
     * 删除课程信息
     * 
     * @param courseId 课程主键
     * @return 结果
     */
    @Override
    public int deleteEduCourseByCourseId(Long courseId)
    {
        return eduCourseMapper.deleteEduCourseByCourseId(courseId);
    }
}
