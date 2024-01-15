package com.ruoyi.edu.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.edu.mapper.EduCertificateMapper;
import com.ruoyi.edu.domain.EduCertificate;
import com.ruoyi.edu.service.IEduCertificateService;

/**
 * 学生证书Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-01-15
 */
@Service
public class EduCertificateServiceImpl implements IEduCertificateService 
{
    @Autowired
    private EduCertificateMapper eduCertificateMapper;

    /**
     * 查询学生证书
     * 
     * @param certificateId 学生证书主键
     * @return 学生证书
     */
    @Override
    public EduCertificate selectEduCertificateByCertificateId(Long certificateId)
    {
        return eduCertificateMapper.selectEduCertificateByCertificateId(certificateId);
    }

    /**
     * 查询学生证书列表
     * 
     * @param eduCertificate 学生证书
     * @return 学生证书
     */
    @Override
    public List<EduCertificate> selectEduCertificateList(EduCertificate eduCertificate)
    {
        return eduCertificateMapper.selectEduCertificateList(eduCertificate);
    }

    /**
     * 新增学生证书
     * 
     * @param eduCertificate 学生证书
     * @return 结果
     */
    @Override
    public int insertEduCertificate(EduCertificate eduCertificate)
    {
        eduCertificate.setCreateTime(DateUtils.getNowDate());
        return eduCertificateMapper.insertEduCertificate(eduCertificate);
    }

    /**
     * 修改学生证书
     * 
     * @param eduCertificate 学生证书
     * @return 结果
     */
    @Override
    public int updateEduCertificate(EduCertificate eduCertificate)
    {
        eduCertificate.setUpdateTime(DateUtils.getNowDate());
        return eduCertificateMapper.updateEduCertificate(eduCertificate);
    }

    /**
     * 批量删除学生证书
     * 
     * @param certificateIds 需要删除的学生证书主键
     * @return 结果
     */
    @Override
    public int deleteEduCertificateByCertificateIds(Long[] certificateIds)
    {
        return eduCertificateMapper.deleteEduCertificateByCertificateIds(certificateIds);
    }

    /**
     * 删除学生证书信息
     * 
     * @param certificateId 学生证书主键
     * @return 结果
     */
    @Override
    public int deleteEduCertificateByCertificateId(Long certificateId)
    {
        return eduCertificateMapper.deleteEduCertificateByCertificateId(certificateId);
    }
}
