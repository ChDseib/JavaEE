package com.ruoyi.edu.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 教师奖项对象 edu_honor
 * 
 * @author Lennon
 * @date 2024-01-31
 */
public class EduHonor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 奖项ID */
    private Long honorId;

    /** 教师ID */
    @Excel(name = "教师ID")
    private Long teacherId;

    /** 教师 */
    @Excel(name = "教师姓名",targetAttr = "teacherName")
    private Teacher teacher;

    /** 奖项 */
    @Excel(name = "奖项")
    private String honorName;

    /** 级别 */
    @Excel(name = "级别", dictType = "edu_contest_level")
    private String level;

    /** 获奖时间 */
    @JsonFormat(pattern = "yyyy-MM")
    @Excel(name = "获奖时间", width = 30, dateFormat = "yyyy-MM")
    private Date issueTime;



    /** 附件 */
    @Excel(name = "附件")
    private String fileUrl;

    public void setHonorId(Long honorId) 
    {
        this.honorId = honorId;
    }

    public Long getHonorId() 
    {
        return honorId;
    }
    public void setTeacherId(Long teacherId) 
    {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() 
    {
        return teacherId;
    }
    public void setHonorName(String honorName) 
    {
        this.honorName = honorName;
    }

    public String getHonorName() 
    {
        return honorName;
    }
    public void setLevel(String level) 
    {
        this.level = level;
    }

    public String getLevel() 
    {
        return level;
    }
    public void setIssueTime(Date issueTime) 
    {
        this.issueTime = issueTime;
    }

    public Date getIssueTime() 
    {
        return issueTime;
    }
    public void setFileUrl(String fileUrl) 
    {
        this.fileUrl = fileUrl;
    }

    public String getFileUrl() 
    {
        return fileUrl;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("honorId", getHonorId())
            .append("teacherId", getTeacherId())
            .append("honorName", getHonorName())
            .append("level", getLevel())
            .append("issueTime", getIssueTime())
            .append("fileUrl", getFileUrl())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
