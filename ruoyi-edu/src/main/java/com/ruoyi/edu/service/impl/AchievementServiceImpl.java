package com.ruoyi.edu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.edu.domain.AchievementTeacher;
import com.ruoyi.edu.mapper.AchievementTeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.edu.mapper.AchievementMapper;
import com.ruoyi.edu.domain.Achievement;
import com.ruoyi.edu.service.IAchievementService;

/**
 * 教学成果Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-24
 */
@Service
public class AchievementServiceImpl implements IAchievementService 
{
    @Autowired
    private AchievementMapper achievementMapper;

    @Autowired
    private AchievementTeacherMapper achievementTeacherMapper;
    /**
     * 查询教学成果
     * 
     * @param achievementId 教学成果主键
     * @return 教学成果
     */
    @Override
    public Achievement selectAchievementByAchievementId(Long achievementId)
    {
        return achievementMapper.selectAchievementByAchievementId(achievementId);
    }

    /**
     * 查询教学成果列表
     * 
     * @param achievement 教学成果
     * @return 教学成果
     */
    @Override
    public List<Achievement> selectAchievementList(Achievement achievement)
    {
        return achievementMapper.selectAchievementList(achievement);
    }

    /**
     * 新增教学成果
     * 
     * @param achievement 教学成果
     * @return 结果
     */
    @Override
    public int insertAchievement(Achievement achievement)
    {
        int rows = achievementMapper.insertAchievement(achievement);
        insertAchievementTeacher(achievement);
        return rows;
    }

    /**
     * 修改教学成果
     * 
     * @param achievement 教学成果
     * @return 结果
     */
    @Override
    public int updateAchievement(Achievement achievement)
    {
        return achievementMapper.updateAchievement(achievement);
    }

    /**
     * 批量删除教学成果
     * 
     * @param achievementIds 需要删除的教学成果主键
     * @return 结果
     */
    @Override
    public int deleteAchievementByAchievementIds(Long[] achievementIds)
    {
        return achievementMapper.deleteAchievementByAchievementIds(achievementIds);
    }

    /**
     * 删除教学成果信息
     * 
     * @param achievementId 教学成果主键
     * @return 结果
     */
    @Override
    public int deleteAchievementByAchievementId(Long achievementId)
    {
        return achievementMapper.deleteAchievementByAchievementId(achievementId);
    }

    public void insertAchievementTeacher(Achievement achievement)
    {
        Long[] teachers = achievement.getTeacherIds();
        if (StringUtils.isNotNull(teachers))
        {
            List<AchievementTeacher> list = new ArrayList<AchievementTeacher>();
            for (Long teacherId : teachers)
            {
                AchievementTeacher at = new AchievementTeacher();
                at.setAchievementId(achievement.getAchievementId());
                at.setTeacherId(teacherId);
                list.add(at);
            }
            if (list.size() > 0)
            {
                achievementTeacherMapper.batchAchievementTeacher(list);
            }
        }
    }
}
