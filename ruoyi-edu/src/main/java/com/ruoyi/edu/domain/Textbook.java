package com.ruoyi.edu.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 出版教材对象 edu_textbook
 * 
 * @author ruoyi
 * @date 2024-05-31
 */
public class Textbook extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 教材ID */
    private Long textbookId;

    /** 教师ID */
    @Excel(name = "教师ID")
    private Long teacherId;

    @Excel(name = "教师姓名", targetAttr = "teacherName")
    private Teacher teacher;

    /** 教材名称 */
    @Excel(name = "教材名称")
    private String textbookName;

    /** 作者次序 */
    @Excel(name = "作者次序")
    private String authorOrder;

    /** 出版时间 */
    @JsonFormat(pattern = "yyyy-MM")
    @Excel(name = "出版时间", width = 30, dateFormat = "yyyy-MM")
    private Date issueTime;

    /** 出版社 */
    @Excel(name = "出版社")
    private String publisher;

    /** 附件 */
    @Excel(name = "附件")
    private String fileUrl;

    public void setTextbookId(Long textbookId) 
    {
        this.textbookId = textbookId;
    }

    public Long getTextbookId() 
    {
        return textbookId;
    }
    public void setTeacherId(Long teacherId) 
    {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() 
    {
        return teacherId;
    }
    public void setTextbookName(String textbookName) 
    {
        this.textbookName = textbookName;
    }

    public String getTextbookName() 
    {
        return textbookName;
    }
    public void setAuthorOrder(String authorOrder) 
    {
        this.authorOrder = authorOrder;
    }

    public String getAuthorOrder() 
    {
        return authorOrder;
    }
    public void setIssueTime(Date issueTime) 
    {
        this.issueTime = issueTime;
    }

    public Date getIssueTime() 
    {
        return issueTime;
    }
    public void setPublisher(String publisher) 
    {
        this.publisher = publisher;
    }

    public String getPublisher() 
    {
        return publisher;
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
            .append("textbookId", getTextbookId())
            .append("teacherId", getTeacherId())
            .append("textbookName", getTextbookName())
            .append("authorOrder", getAuthorOrder())
            .append("issueTime", getIssueTime())
            .append("publisher", getPublisher())
            .append("remark", getRemark())
            .append("fileUrl", getFileUrl())
            .toString();
    }
}
