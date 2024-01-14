package com.ruoyi.edu.mapper;

import java.util.List;
import com.ruoyi.edu.domain.EduStudent;

/**
 * 学生Mapper接口
 * 
 * @author ruoyi
 * @date 2024-01-14
 */
public interface EduStudentMapper 
{
    /**
     * 查询学生
     * 
     * @param studentId 学生主键
     * @return 学生
     */
    public EduStudent selectEduStudentByStudentId(Long studentId);

    /**
     * 查询学生列表
     * 
     * @param eduStudent 学生
     * @return 学生集合
     */
    public List<EduStudent> selectEduStudentList(EduStudent eduStudent);

    /**
     * 新增学生
     * 
     * @param eduStudent 学生
     * @return 结果
     */
    public int insertEduStudent(EduStudent eduStudent);

    /**
     * 修改学生
     * 
     * @param eduStudent 学生
     * @return 结果
     */
    public int updateEduStudent(EduStudent eduStudent);

    /**
     * 删除学生
     * 
     * @param studentId 学生主键
     * @return 结果
     */
    public int deleteEduStudentByStudentId(Long studentId);

    /**
     * 批量删除学生
     * 
     * @param studentIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduStudentByStudentIds(Long[] studentIds);
}
