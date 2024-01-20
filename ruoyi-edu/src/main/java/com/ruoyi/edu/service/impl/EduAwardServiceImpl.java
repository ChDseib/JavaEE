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
import com.ruoyi.edu.mapper.EduAwardMapper;
import com.ruoyi.edu.domain.EduAward;
import com.ruoyi.edu.service.IEduAwardService;

/**
 * 奖项Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-01-17
 */
@Service
public class EduAwardServiceImpl implements IEduAwardService 
{
    @Autowired
    private EduAwardMapper eduAwardMapper;

    /**
     * 查询奖项
     * 
     * @param awardId 奖项主键
     * @return 奖项
     */
    @Override
    public EduAward selectEduAwardByAwardId(Long awardId)
    {
        return eduAwardMapper.selectEduAwardByAwardId(awardId);
    }

    /**
     * 查询奖项列表
     * 
     * @param eduAward 奖项
     * @return 奖项
     */
    @Override
    public List<EduAward> selectEduAwardList(EduAward eduAward)
    {
        return eduAwardMapper.selectEduAwardList(eduAward);
    }

    /**
     * 新增奖项
     * 
     * @param eduAward 奖项
     * @return 结果
     */
    @Override
    public int insertEduAward(EduAward eduAward)
    {
        eduAward.setCreateBy(SecurityUtils.getUsername());
        eduAward.setCreateTime(DateUtils.getNowDate());
        return eduAwardMapper.insertEduAward(eduAward);
    }

    /**
     * 修改奖项
     * 
     * @param eduAward 奖项
     * @return 结果
     */
    @Override
    public int updateEduAward(EduAward eduAward)
    {
        eduAward.setUpdateBy(SecurityUtils.getUsername());
        eduAward.setUpdateTime(DateUtils.getNowDate());
        return eduAwardMapper.updateEduAward(eduAward);
    }

    /**
     * 批量删除奖项
     * 
     * @param awardIds 需要删除的奖项主键
     * @return 结果
     */
    @Override
    public int deleteEduAwardByAwardIds(Long[] awardIds)
    {
        return eduAwardMapper.deleteEduAwardByAwardIds(awardIds);
    }

    /**
     * 删除奖项信息
     * 
     * @param awardId 奖项主键
     * @return 结果
     */
    @Override
    public int deleteEduAwardByAwardId(Long awardId)
    {
        return eduAwardMapper.deleteEduAwardByAwardId(awardId);
    }

    @Override
    public List<EduAttachment> getEduAttachments(Long[] awardIds) {
        List<EduAttachment> attachments = new ArrayList<>();
        for (Long awardId: awardIds) {
            EduAward award = eduAwardMapper.selectEduAwardByAwardId(awardId);
            if (StringUtils.isNotEmpty(award.getFileUrl())) {
                EduAttachment attachment = new EduAttachment();
                attachment.setFileUrl(award.getFileUrl());
                attachment.setFileUrl(award.getFileUrl());
                StringBuilder name = new StringBuilder();
                name.append(award.getStudent().getStudentName());
                name.append("_");
                name.append(award.getStudent().getStudentCode());
                name.append("_");
                name.append(award.getContest().getContestName());
                name.append("_");
                name.append(DictUtils.getDictLabel("edu_award_grade", award.getGrade()));
                String ext = StringUtils.substringAfterLast(award.getFileUrl(), ".");
                attachment.setFileName(name.append(".").append(ext).toString());
                attachments.add(attachment);
            }
        }
        return attachments;
    }
}
