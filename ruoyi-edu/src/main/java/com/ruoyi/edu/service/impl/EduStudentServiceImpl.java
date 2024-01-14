package com.ruoyi.edu.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.edu.mapper.EduStudentMapper;
import com.ruoyi.edu.domain.EduStudent;
import com.ruoyi.edu.service.IEduStudentService;

/**
 * 学生Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-01-14
 */
@Service
public class EduStudentServiceImpl implements IEduStudentService 
{
    @Autowired
    private EduStudentMapper eduStudentMapper;

    /**
     * 查询学生
     * 
     * @param studentId 学生主键
     * @return 学生
     */
    @Override
    public EduStudent selectEduStudentByStudentId(Long studentId)
    {
        return eduStudentMapper.selectEduStudentByStudentId(studentId);
    }

    /**
     * 查询学生列表
     * 
     * @param eduStudent 学生
     * @return 学生
     */
    @Override
    public List<EduStudent> selectEduStudentList(EduStudent eduStudent)
    {
        return eduStudentMapper.selectEduStudentList(eduStudent);
    }

    /**
     * 新增学生
     * 
     * @param eduStudent 学生
     * @return 结果
     */
    @Override
    public int insertEduStudent(EduStudent eduStudent)
    {
        eduStudent.setCreateTime(DateUtils.getNowDate());
        return eduStudentMapper.insertEduStudent(eduStudent);
    }

    /**
     * 修改学生
     * 
     * @param eduStudent 学生
     * @return 结果
     */
    @Override
    public int updateEduStudent(EduStudent eduStudent)
    {
        eduStudent.setUpdateTime(DateUtils.getNowDate());
        return eduStudentMapper.updateEduStudent(eduStudent);
    }

    /**
     * 批量删除学生
     * 
     * @param studentIds 需要删除的学生主键
     * @return 结果
     */
    @Override
    public int deleteEduStudentByStudentIds(Long[] studentIds)
    {
        return eduStudentMapper.deleteEduStudentByStudentIds(studentIds);
    }

    /**
     * 删除学生信息
     * 
     * @param studentId 学生主键
     * @return 结果
     */
    @Override
    public int deleteEduStudentByStudentId(Long studentId)
    {
        return eduStudentMapper.deleteEduStudentByStudentId(studentId);
    }
}
