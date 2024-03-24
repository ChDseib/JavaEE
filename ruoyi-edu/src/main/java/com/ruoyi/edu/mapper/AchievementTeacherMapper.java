package com.ruoyi.edu.mapper;

import com.ruoyi.edu.domain.AchievementTeacher;

import java.util.List;

public interface AchievementTeacherMapper {

    public int deleteAchievementTeacherByAchievementId(Long achievementId);

    public int countAchievementTeacherById(Long teacherId);

    public int deleteAchievementTeacher(Long[] ids);

    public int batchAchievementTeacher(List<AchievementTeacher> achievementTeacherList);
}
