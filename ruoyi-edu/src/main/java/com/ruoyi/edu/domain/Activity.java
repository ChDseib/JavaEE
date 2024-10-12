package com.ruoyi.edu.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 社会活动对象 edu_activity
 * 
 * @author sanda
 * @date 2024-10-12
 */
public class Activity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 社会活动ID */
    private Long activityId;

    /** 教师ID */
    @Excel(name = "教师ID")
    private Long teacherId;

    /** 活动详情 */
    @Excel(name = "活动详情")
    private String detail;

    /** 活动时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "活动时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date issueTime;

    /** 链接 */
    @Excel(name = "链接")
    private String url;

    /** 附件 */
    @Excel(name = "附件")
    private String fileUrl;

    @Excel(name = "教师姓名", targetAttr = "teacherName")
    private Teacher teacher;

    public Teacher getTeacher() {
        return teacher;

    }
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void setActivityId(Long activityId) 
    {
        this.activityId = activityId;
    }

    public Long getActivityId() 
    {
        return activityId;
    }
    public void setTeacherId(Long teacherId) 
    {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() 
    {
        return teacherId;
    }
    public void setDetail(String detail) 
    {
        this.detail = detail;
    }

    public String getDetail() 
    {
        return detail;
    }
    public void setIssueTime(Date issueTime) 
    {
        this.issueTime = issueTime;
    }

    public Date getIssueTime() 
    {
        return issueTime;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }
    public void setFileUrl(String fileUrl) 
    {
        this.fileUrl = fileUrl;
    }

    public String getFileUrl() 
    {
        return fileUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("activityId", getActivityId())
            .append("teacherId", getTeacherId())
            .append("detail", getDetail())
            .append("issueTime", getIssueTime())
            .append("remark", getRemark())
            .append("url", getUrl())
            .append("fileUrl", getFileUrl())
            .toString();
    }

}
