package com.ruoyi.edu.service;

import java.util.List;
import com.ruoyi.edu.domain.Achievement;

/**
 * 教学成果Service接口
 * 
 * @author ruoyi
 * @date 2024-03-24
 */
public interface IAchievementService 
{
    /**
     * 查询教学成果
     * 
     * @param achievementId 教学成果主键
     * @return 教学成果
     */
    public Achievement selectAchievementByAchievementId(Long achievementId);

    /**
     * 查询教学成果列表
     * 
     * @param achievement 教学成果
     * @return 教学成果集合
     */
    public List<Achievement> selectAchievementList(Achievement achievement);

    /**
     * 新增教学成果
     * 
     * @param achievement 教学成果
     * @return 结果
     */
    public int insertAchievement(Achievement achievement);

    /**
     * 修改教学成果
     * 
     * @param achievement 教学成果
     * @return 结果
     */
    public int updateAchievement(Achievement achievement);

    /**
     * 批量删除教学成果
     * 
     * @param achievementIds 需要删除的教学成果主键集合
     * @return 结果
     */
    public int deleteAchievementByAchievementIds(Long[] achievementIds);

    /**
     * 删除教学成果信息
     * 
     * @param achievementId 教学成果主键
     * @return 结果
     */
    public int deleteAchievementByAchievementId(Long achievementId);
}
