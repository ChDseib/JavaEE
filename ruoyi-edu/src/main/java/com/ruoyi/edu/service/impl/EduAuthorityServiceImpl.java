package com.ruoyi.edu.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.edu.mapper.EduAuthorityMapper;
import com.ruoyi.edu.domain.EduAuthority;
import com.ruoyi.edu.service.IEduAuthorityService;

/**
 * 认证机构Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-01-14
 */
@Service
public class EduAuthorityServiceImpl implements IEduAuthorityService 
{
    @Autowired
    private EduAuthorityMapper eduAuthorityMapper;

    /**
     * 查询认证机构
     * 
     * @param authorityId 认证机构主键
     * @return 认证机构
     */
    @Override
    public EduAuthority selectEduAuthorityByAuthorityId(Long authorityId)
    {
        return eduAuthorityMapper.selectEduAuthorityByAuthorityId(authorityId);
    }

    /**
     * 查询认证机构列表
     * 
     * @param eduAuthority 认证机构
     * @return 认证机构
     */
    @Override
    public List<EduAuthority> selectEduAuthorityList(EduAuthority eduAuthority)
    {
        return eduAuthorityMapper.selectEduAuthorityList(eduAuthority);
    }

    /**
     * 新增认证机构
     * 
     * @param eduAuthority 认证机构
     * @return 结果
     */
    @Override
    public int insertEduAuthority(EduAuthority eduAuthority)
    {
        return eduAuthorityMapper.insertEduAuthority(eduAuthority);
    }

    /**
     * 修改认证机构
     * 
     * @param eduAuthority 认证机构
     * @return 结果
     */
    @Override
    public int updateEduAuthority(EduAuthority eduAuthority)
    {
        return eduAuthorityMapper.updateEduAuthority(eduAuthority);
    }

    /**
     * 批量删除认证机构
     * 
     * @param authorityIds 需要删除的认证机构主键
     * @return 结果
     */
    @Override
    public int deleteEduAuthorityByAuthorityIds(Long[] authorityIds)
    {
        return eduAuthorityMapper.deleteEduAuthorityByAuthorityIds(authorityIds);
    }

    /**
     * 删除认证机构信息
     * 
     * @param authorityId 认证机构主键
     * @return 结果
     */
    @Override
    public int deleteEduAuthorityByAuthorityId(Long authorityId)
    {
        return eduAuthorityMapper.deleteEduAuthorityByAuthorityId(authorityId);
    }
}
