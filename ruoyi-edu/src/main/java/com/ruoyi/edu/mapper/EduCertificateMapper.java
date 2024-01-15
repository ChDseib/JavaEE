package com.ruoyi.edu.mapper;

import java.util.List;
import com.ruoyi.edu.domain.EduCertificate;

/**
 * 学生证书Mapper接口
 * 
 * @author ruoyi
 * @date 2024-01-15
 */
public interface EduCertificateMapper 
{
    /**
     * 查询学生证书
     * 
     * @param certificateId 学生证书主键
     * @return 学生证书
     */
    public EduCertificate selectEduCertificateByCertificateId(Long certificateId);

    /**
     * 查询学生证书列表
     * 
     * @param eduCertificate 学生证书
     * @return 学生证书集合
     */
    public List<EduCertificate> selectEduCertificateList(EduCertificate eduCertificate);

    /**
     * 新增学生证书
     * 
     * @param eduCertificate 学生证书
     * @return 结果
     */
    public int insertEduCertificate(EduCertificate eduCertificate);

    /**
     * 修改学生证书
     * 
     * @param eduCertificate 学生证书
     * @return 结果
     */
    public int updateEduCertificate(EduCertificate eduCertificate);

    /**
     * 删除学生证书
     * 
     * @param certificateId 学生证书主键
     * @return 结果
     */
    public int deleteEduCertificateByCertificateId(Long certificateId);

    /**
     * 批量删除学生证书
     * 
     * @param certificateIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduCertificateByCertificateIds(Long[] certificateIds);
}
