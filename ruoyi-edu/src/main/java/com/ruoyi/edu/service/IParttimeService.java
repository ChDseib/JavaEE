package com.ruoyi.edu.service;

import java.util.List;
import com.ruoyi.edu.domain.Parttime;

/**
 * 社会兼职Service接口
 * 
 * @author lennon
 * @date 2024-02-06
 */
public interface IParttimeService 
{
    /**
     * 查询社会兼职
     * 
     * @param parttimeId 社会兼职主键
     * @return 社会兼职
     */
    public Parttime selectParttimeByParttimeId(Long parttimeId);

    /**
     * 查询社会兼职列表
     * 
     * @param parttime 社会兼职
     * @return 社会兼职集合
     */
    public List<Parttime> selectParttimeList(Parttime parttime);

    /**
     * 新增社会兼职
     * 
     * @param parttime 社会兼职
     * @return 结果
     */
    public int insertParttime(Parttime parttime);

    /**
     * 修改社会兼职
     * 
     * @param parttime 社会兼职
     * @return 结果
     */
    public int updateParttime(Parttime parttime);

    /**
     * 批量删除社会兼职
     * 
     * @param parttimeIds 需要删除的社会兼职主键集合
     * @return 结果
     */
    public int deleteParttimeByParttimeIds(Long[] parttimeIds);

    /**
     * 删除社会兼职信息
     * 
     * @param parttimeId 社会兼职主键
     * @return 结果
     */
    public int deleteParttimeByParttimeId(Long parttimeId);
}
