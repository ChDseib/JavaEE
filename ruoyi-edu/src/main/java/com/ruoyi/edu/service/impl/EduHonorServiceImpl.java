package com.ruoyi.edu.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.DictUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.edu.domain.EduAttachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.edu.mapper.EduHonorMapper;
import com.ruoyi.edu.domain.EduHonor;
import com.ruoyi.edu.service.IEduHonorService;

/**
 * 教师奖项Service业务层处理
 * 
 * @author Lennon
 * @date 2024-01-31
 */
@Service
public class EduHonorServiceImpl implements IEduHonorService 
{
    @Autowired
    private EduHonorMapper eduHonorMapper;

    /**
     * 查询教师奖项
     * 
     * @param honorId 教师奖项主键
     * @return 教师奖项
     */
    @Override
    public EduHonor selectEduHonorByHonorId(Long honorId)
    {
        return eduHonorMapper.selectEduHonorByHonorId(honorId);
    }

    /**
     * 查询教师奖项列表
     * 
     * @param eduHonor 教师奖项
     * @return 教师奖项
     */
    @Override
    public List<EduHonor> selectEduHonorList(EduHonor eduHonor)
    {
        return eduHonorMapper.selectEduHonorList(eduHonor);
    }

    /**
     * 新增教师奖项
     * 
     * @param eduHonor 教师奖项
     * @return 结果
     */
    @Override
    public int insertEduHonor(EduHonor eduHonor)
    {
        eduHonor.setCreateBy(SecurityUtils.getUsername());
        eduHonor.setCreateTime(DateUtils.getNowDate());
        return eduHonorMapper.insertEduHonor(eduHonor);
    }

    /**
     * 修改教师奖项
     * 
     * @param eduHonor 教师奖项
     * @return 结果
     */
    @Override
    public int updateEduHonor(EduHonor eduHonor)
    {
        eduHonor.setUpdateBy(SecurityUtils.getUsername());
        eduHonor.setUpdateTime(DateUtils.getNowDate());
        return eduHonorMapper.updateEduHonor(eduHonor);
    }

    /**
     * 批量删除教师奖项
     * 
     * @param honorIds 需要删除的教师奖项主键
     * @return 结果
     */
    @Override
    public int deleteEduHonorByHonorIds(Long[] honorIds)
    {
        return eduHonorMapper.deleteEduHonorByHonorIds(honorIds);
    }

    /**
     * 删除教师奖项信息
     * 
     * @param honorId 教师奖项主键
     * @return 结果
     */
    @Override
    public int deleteEduHonorByHonorId(Long honorId)
    {
        return eduHonorMapper.deleteEduHonorByHonorId(honorId);
    }

    @Override
    public List<EduAttachment> getEduAttachments(Long[] honorIds) {
        List<EduAttachment> attachments = new ArrayList<>();
        for (Long awardId: honorIds) {
            EduHonor eduHonor = eduHonorMapper.selectEduHonorByHonorId(awardId);
            if (StringUtils.isNotEmpty(eduHonor.getFileUrl())) {
                EduAttachment attachment = new EduAttachment();
                attachment.setFileUrl(eduHonor.getFileUrl());
                attachment.setFileUrl(eduHonor.getFileUrl());
                StringBuilder name = new StringBuilder();
                name.append(eduHonor.getTeacher().getTeacherName());
                name.append("_");
                name.append(eduHonor.getTeacher().getTeacherCode());
                name.append("_");
                name.append(eduHonor.getHonorName());
                name.append("_");
                name.append(DictUtils.getDictLabel("edu_award_grade", eduHonor.getLevel()));
                String ext = StringUtils.substringAfterLast(eduHonor.getFileUrl(), ".");
                attachment.setFileName(name.append(".").append(ext).toString());
                attachments.add(attachment);
            }
        }
        return attachments;
    }
}
