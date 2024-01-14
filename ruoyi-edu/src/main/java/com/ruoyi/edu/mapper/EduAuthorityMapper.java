package com.ruoyi.edu.mapper;

import java.util.List;
import com.ruoyi.edu.domain.EduAuthority;

/**
 * 认证机构Mapper接口
 * 
 * @author ruoyi
 * @date 2024-01-14
 */
public interface EduAuthorityMapper 
{
    /**
     * 查询认证机构
     * 
     * @param authorityId 认证机构主键
     * @return 认证机构
     */
    public EduAuthority selectEduAuthorityByAuthorityId(Long authorityId);

    /**
     * 查询认证机构列表
     * 
     * @param eduAuthority 认证机构
     * @return 认证机构集合
     */
    public List<EduAuthority> selectEduAuthorityList(EduAuthority eduAuthority);

    /**
     * 新增认证机构
     * 
     * @param eduAuthority 认证机构
     * @return 结果
     */
    public int insertEduAuthority(EduAuthority eduAuthority);

    /**
     * 修改认证机构
     * 
     * @param eduAuthority 认证机构
     * @return 结果
     */
    public int updateEduAuthority(EduAuthority eduAuthority);

    /**
     * 删除认证机构
     * 
     * @param authorityId 认证机构主键
     * @return 结果
     */
    public int deleteEduAuthorityByAuthorityId(Long authorityId);

    /**
     * 批量删除认证机构
     * 
     * @param authorityIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduAuthorityByAuthorityIds(Long[] authorityIds);
}
