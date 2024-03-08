package com.ruoyi.edu.service;

import java.util.List;

import com.ruoyi.edu.domain.EduAttachment;
import com.ruoyi.edu.domain.EduHonor;

/**
 * 教师奖项Service接口
 * 
 * @author Lennon
 * @date 2024-01-31
 */
public interface IEduHonorService 
{
    /**
     * 查询教师奖项
     * 
     * @param honorId 教师奖项主键
     * @return 教师奖项
     */
    public EduHonor selectEduHonorByHonorId(Long honorId);

    /**
     * 查询教师奖项列表
     * 
     * @param eduHonor 教师奖项
     * @return 教师奖项集合
     */
    public List<EduHonor> selectEduHonorList(EduHonor eduHonor);

    /**
     * 新增教师奖项
     * 
     * @param eduHonor 教师奖项
     * @return 结果
     */
    public int insertEduHonor(EduHonor eduHonor);

    /**
     * 修改教师奖项
     * 
     * @param eduHonor 教师奖项
     * @return 结果
     */
    public int updateEduHonor(EduHonor eduHonor);

    /**
     * 批量删除教师奖项
     * 
     * @param honorIds 需要删除的教师奖项主键集合
     * @return 结果
     */
    public int deleteEduHonorByHonorIds(Long[] honorIds);

    /**
     * 删除教师奖项信息
     * 
     * @param honorId 教师奖项主键
     * @return 结果
     */
    public int deleteEduHonorByHonorId(Long honorId);

    List<EduAttachment> getEduAttachments(Long[] honorIds);
}
