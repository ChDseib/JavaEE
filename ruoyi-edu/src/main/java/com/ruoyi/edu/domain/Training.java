package com.ruoyi.edu.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 教师培训对象 edu_training
 * 
 * @author ruoyi
 * @date 2024-01-20
 */
public class Training extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 教师培训ID */
    private Long trainingId;

    /** 教师ID */
    @Excel(name = "教师ID")
    private Long teacherId;

    /** 机构ID */
    @Excel(name = "机构ID")
    private Long authorityId;

    /** 培训名称 */
    @Excel(name = "培训名称")
    private String trainingName;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 培训学时 */
    @Excel(name = "培训学时")
    private Long classHour;

    /** 证书附件 */
    @Excel(name = "证书附件")
    private String fileUrl;

    public void setTrainingId(Long trainingId) 
    {
        this.trainingId = trainingId;
    }

    public Long getTrainingId() 
    {
        return trainingId;
    }
    public void setTeacherId(Long teacherId) 
    {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() 
    {
        return teacherId;
    }
    public void setAuthorityId(Long authorityId) 
    {
        this.authorityId = authorityId;
    }

    public Long getAuthorityId() 
    {
        return authorityId;
    }
    public void setTrainingName(String trainingName) 
    {
        this.trainingName = trainingName;
    }

    public String getTrainingName() 
    {
        return trainingName;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public void setClassHour(Long classHour) 
    {
        this.classHour = classHour;
    }

    public Long getClassHour() 
    {
        return classHour;
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
            .append("trainingId", getTrainingId())
            .append("teacherId", getTeacherId())
            .append("authorityId", getAuthorityId())
            .append("trainingName", getTrainingName())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("classHour", getClassHour())
            .append("fileUrl", getFileUrl())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
