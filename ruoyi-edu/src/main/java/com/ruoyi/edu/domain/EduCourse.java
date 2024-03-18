package com.ruoyi.edu.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excels;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 课程对象 edu_course
 * 
 * @author ruoyi
 * @date 2024-03-13
 */
public class EduCourse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 课程ID */
    private Long courseId;

    /** 负责人 */
    @Excel(name = "负责人")
    private Long teacherId;



    @Excels({
            @Excel(name = "部门", targetAttr = "deptId", type = Excel.Type.EXPORT),
            @Excel(name = "工号", targetAttr = "teacherCode", type = Excel.Type.EXPORT),
            @Excel(name = "姓名", targetAttr = "teacherName", type = Excel.Type.EXPORT)
    })
    private Teacher teacher;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String courseName;

    /** 级别 */
    @Excel(name = "级别")
    private String level;

    /** 类别 */
    @Excel(name = "类别")
    private String courseType;

    /** 认定时间 */
    @JsonFormat(pattern = "yyyy")
    @Excel(name = "认定时间", width = 30, dateFormat = "yyyy")
    private Date issueTime;

    /** 上级拨款 */
    @Excel(name = "上级拨款")
    private Double externalAmount;

    /** 校内配套 */
    @Excel(name = "校内配套")
    private Double internalAmount;

    public void setCourseId(Long courseId) 
    {
        this.courseId = courseId;
    }

    public Long getCourseId() 
    {
        return courseId;
    }
    public void setTeacherId(Long teacherId) 
    {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() 
    {
        return teacherId;
    }
    public void setCourseName(String courseName) 
    {
        this.courseName = courseName;
    }

    public String getCourseName() 
    {
        return courseName;
    }
    public void setLevel(String level) 
    {
        this.level = level;
    }

    public String getLevel() 
    {
        return level;
    }
    public void setCourseType(String courseType) 
    {
        this.courseType = courseType;
    }

    public String getCourseType() 
    {
        return courseType;
    }
    public void setIssueTime(Date issueTime) 
    {
        this.issueTime = issueTime;
    }

    public Date getIssueTime() 
    {
        return issueTime;
    }
    public void setExternalAmount(Double externalAmount)
    {
        this.externalAmount = externalAmount;
    }

    public Double getExternalAmount()
    {
        return externalAmount;
    }
    public void setInternalAmount(Double internalAmount)
    {
        this.internalAmount = internalAmount;
    }

    public Double getInternalAmount()
    {
        return internalAmount;
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
            .append("courseId", getCourseId())
            .append("teacherId", getTeacherId())
            .append("courseName", getCourseName())
            .append("level", getLevel())
            .append("courseType", getCourseType())
            .append("issueTime", getIssueTime())
            .append("externalAmount", getExternalAmount())
            .append("internalAmount", getInternalAmount())
            .toString();
    }
}
