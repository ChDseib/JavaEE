package com.ruoyi.edu.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.edu.mapper.TeacherMapper;
import com.ruoyi.edu.domain.Teacher;
import com.ruoyi.edu.service.ITeacherService;

/**
 * 教师Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-01-20
 */
@Service
public class TeacherServiceImpl implements ITeacherService 
{
    @Autowired
    private TeacherMapper teacherMapper;

    /**
     * 查询教师
     * 
     * @param teacherId 教师主键
     * @return 教师
     */
    @Override
    public Teacher selectTeacherByTeacherId(Long teacherId)
    {
        return teacherMapper.selectTeacherByTeacherId(teacherId);
    }

    /**
     * 查询教师列表
     * 
     * @param teacher 教师
     * @return 教师
     */
    @Override
    public List<Teacher> selectTeacherList(Teacher teacher)
    {
        return teacherMapper.selectTeacherList(teacher);
    }

    @Override
    public List<Teacher> selectTeacherAll()
    {
        return teacherMapper.selectTeacherAll();
    }

    /**
     * 新增教师
     * 
     * @param teacher 教师
     * @return 结果
     */
    @Override
    public int insertTeacher(Teacher teacher)
    {
        teacher.setCreateTime(DateUtils.getNowDate());
        return teacherMapper.insertTeacher(teacher);
    }

    /**
     * 修改教师
     * 
     * @param teacher 教师
     * @return 结果
     */
    @Override
    public int updateTeacher(Teacher teacher)
    {
        teacher.setUpdateTime(DateUtils.getNowDate());
        return teacherMapper.updateTeacher(teacher);
    }

    /**
     * 批量删除教师
     * 
     * @param teacherIds 需要删除的教师主键
     * @return 结果
     */
    @Override
    public int deleteTeacherByTeacherIds(Long[] teacherIds)
    {
        return teacherMapper.deleteTeacherByTeacherIds(teacherIds);
    }

    /**
     * 删除教师信息
     * 
     * @param teacherId 教师主键
     * @return 结果
     */
    @Override
    public int deleteTeacherByTeacherId(Long teacherId)
    {
        return teacherMapper.deleteTeacherByTeacherId(teacherId);
    }

    @Override
    public List<Teacher> selectTeacherListByAchievementId(Long achievementId) {
        return teacherMapper.selectTeacherListByAchievementId(achievementId);
    }
}
