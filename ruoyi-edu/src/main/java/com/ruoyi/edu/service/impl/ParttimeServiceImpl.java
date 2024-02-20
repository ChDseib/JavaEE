package com.ruoyi.edu.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.edu.mapper.ParttimeMapper;
import com.ruoyi.edu.domain.Parttime;
import com.ruoyi.edu.service.IParttimeService;

/**
 * 社会兼职Service业务层处理
 * 
 * @author lennon
 * @date 2024-02-06
 */
@Service
public class ParttimeServiceImpl implements IParttimeService 
{
    @Autowired
    private ParttimeMapper parttimeMapper;

    /**
     * 查询社会兼职
     * 
     * @param parttimeId 社会兼职主键
     * @return 社会兼职
     */
    @Override
    public Parttime selectParttimeByParttimeId(Long parttimeId)
    {
        return parttimeMapper.selectParttimeByParttimeId(parttimeId);
    }

    /**
     * 查询社会兼职列表
     * 
     * @param parttime 社会兼职
     * @return 社会兼职
     */
    @Override
    public List<Parttime> selectParttimeList(Parttime parttime)
    {
        return parttimeMapper.selectParttimeList(parttime);
    }

    /**
     * 新增社会兼职
     * 
     * @param parttime 社会兼职
     * @return 结果
     */
    @Override
    public int insertParttime(Parttime parttime)
    {
        parttime.setCreateBy(SecurityUtils.getUsername());
        parttime.setCreateTime(DateUtils.getNowDate());
        return parttimeMapper.insertParttime(parttime);
    }

    /**
     * 修改社会兼职
     * 
     * @param parttime 社会兼职
     * @return 结果
     */
    @Override
    public int updateParttime(Parttime parttime)
    {

        parttime.setUpdateBy(SecurityUtils.getUsername());
        parttime.setUpdateTime(DateUtils.getNowDate());
        return parttimeMapper.updateParttime(parttime);
    }

    /**
     * 批量删除社会兼职
     * 
     * @param parttimeIds 需要删除的社会兼职主键
     * @return 结果
     */
    @Override
    public int deleteParttimeByParttimeIds(Long[] parttimeIds)
    {
        return parttimeMapper.deleteParttimeByParttimeIds(parttimeIds);
    }

    /**
     * 删除社会兼职信息
     * 
     * @param parttimeId 社会兼职主键
     * @return 结果
     */
    @Override
    public int deleteParttimeByParttimeId(Long parttimeId)
    {
        return parttimeMapper.deleteParttimeByParttimeId(parttimeId);
    }
}
