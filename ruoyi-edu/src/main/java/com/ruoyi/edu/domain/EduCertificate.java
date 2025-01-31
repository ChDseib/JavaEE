package com.ruoyi.edu.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excels;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学生证书对象 edu_certificate
 * 
 * @author ruoyi
 * @date 2024-01-15
 */
public class EduCertificate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学生证书ID */
    private Long certificateId;

    /** 学生ID */
    private Long studentId;

    @Excels({
            @Excel(name = "班级", targetAttr = "className", type = Excel.Type.EXPORT),
            @Excel(name = "学号", targetAttr = "studentCode", type = Excel.Type.EXPORT),
            @Excel(name = "姓名", targetAttr = "studentName", type = Excel.Type.EXPORT)
    })
    private EduStudent student;

    /** 认证机构ID */
    private Long authorityId;

    @Excels({
            @Excel(name = "机构", targetAttr = "authorityName", type = Excel.Type.EXPORT)
    })
    private EduAuthority authority;

    /** 证书名称 */
    @Excel(name = "证书名称")
    private String certificateName;

    /** 认证时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "认证时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date issueTime;

    /** 附件 */
    private String fileUrl;

    public void setCertificateId(Long certificateId) 
    {
        this.certificateId = certificateId;
    }

    public Long getCertificateId() 
    {
        return certificateId;
    }
    public void setStudentId(Long studentId) 
    {
        this.studentId = studentId;
    }

    public Long getStudentId() 
    {
        return studentId;
    }
    public void setAuthorityId(Long authorityId) 
    {
        this.authorityId = authorityId;
    }

    public Long getAuthorityId() 
    {
        return authorityId;
    }
    public void setCertificateName(String certificateName) 
    {
        this.certificateName = certificateName;
    }

    public String getCertificateName() 
    {
        return certificateName;
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

    public EduStudent getStudent() {
        return student;
    }

    public void setStudent(EduStudent student) {
        this.student = student;
    }

    public EduAuthority getAuthority() {
        return authority;
    }

    public void setAuthority(EduAuthority authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("certificateId", getCertificateId())
            .append("studentId", getStudentId())
            .append("authorityId", getAuthorityId())
            .append("certificateName", getCertificateName())
            .append("issueTime", getIssueTime())
            .append("fileUrl", getFileUrl())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
