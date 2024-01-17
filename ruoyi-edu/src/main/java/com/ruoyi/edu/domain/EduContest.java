package com.ruoyi.edu.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 竞赛管理对象 edu_contest
 * 
 * @author ruoyi
 * @date 2024-01-17
 */
public class EduContest extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 竞赛ID */
    private Long contestId;

    /** 竞赛名称 */
    @Excel(name = "竞赛名称")
    private String contestName;

    /** 级别 */
    @Excel(name = "级别")
    private String level;

    /** 组织者 */
    @Excel(name = "组织者")
    private String organizer;

    public void setContestId(Long contestId) 
    {
        this.contestId = contestId;
    }

    public Long getContestId() 
    {
        return contestId;
    }
    public void setContestName(String contestName) 
    {
        this.contestName = contestName;
    }

    public String getContestName() 
    {
        return contestName;
    }
    public void setLevel(String level) 
    {
        this.level = level;
    }

    public String getLevel() 
    {
        return level;
    }
    public void setOrganizer(String organizer) 
    {
        this.organizer = organizer;
    }

    public String getOrganizer() 
    {
        return organizer;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("contestId", getContestId())
            .append("contestName", getContestName())
            .append("level", getLevel())
            .append("organizer", getOrganizer())
            .toString();
    }
}
