package com.ruoyi.edu.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 教学成果对象 edu_achievement
 * 
 * @author ruoyi
 * @date 2024-03-24
 */
public class Achievement extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 教学成果ID */
    private Long achievementId;

    /** 名称 */
    @Excel(name = "名称")
    private String achievementName;

    /** 级别 */
    @Excel(name = "级别")
    private String level;

    /** 等级 */
    @Excel(name = "等级")
    private String grade;

    /** 获奖时间 */
    @JsonFormat(pattern = "yyyy-MM")
    @Excel(name = "获奖时间", width = 30, dateFormat = "yyyy-MM")
    private Date issueTime;

    /** 负责人 */
    private Long[] teacherIds;

    private List<Teacher> teachers;

    public void setAchievementId(Long achievementId)
    {
        this.achievementId = achievementId;
    }

    public Long getAchievementId() 
    {
        return achievementId;
    }
    public void setAchievementName(String achievementName) 
    {
        this.achievementName = achievementName;
    }

    public String getAchievementName() 
    {
        return achievementName;
    }
    public void setLevel(String level) 
    {
        this.level = level;
    }

    public String getLevel() 
    {
        return level;
    }
    public void setGrade(String grade) 
    {
        this.grade = grade;
    }

    public String getGrade() 
    {
        return grade;
    }
    public void setIssueTime(Date issueTime) 
    {
        this.issueTime = issueTime;
    }

    public Date getIssueTime() 
    {
        return issueTime;
    }

    public Long[] getTeacherIds() {
        return teacherIds;
    }

    public void setTeacherIds(Long[] teacherIds) {
        this.teacherIds = teacherIds;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("achievementId", getAchievementId())
            .append("achievementName", getAchievementName())
            .append("level", getLevel())
            .append("grade", getGrade())
            .append("issueTime", getIssueTime())
            .toString();
    }
}
