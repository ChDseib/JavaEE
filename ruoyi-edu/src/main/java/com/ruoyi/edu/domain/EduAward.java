package com.ruoyi.edu.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excels;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 奖项对象 edu_award
 * 
 * @author ruoyi
 * @date 2024-01-17
 */
public class EduAward extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 奖项ID */
    private Long awardId;

    /** 学生ID */
    private Long studentId;

    @Excels({
            @Excel(name = "班级", targetAttr = "className", type = Excel.Type.EXPORT),
            @Excel(name = "学号", targetAttr = "studentCode", type = Excel.Type.EXPORT),
            @Excel(name = "姓名", targetAttr = "studentName", type = Excel.Type.EXPORT)
    })
    private EduStudent student;

    /** 等级 */
    @Excel(name = "等级", dictType = "edu_award_grade")
    private String grade;

    /** 竞赛ID */
    private Long contestId;

    @Excels({
            @Excel(name = "竞赛名称", targetAttr = "contestName", type = Excel.Type.EXPORT, width = 30),
            @Excel(name = "竞赛级别", targetAttr = "level", type = Excel.Type.EXPORT, dictType = "edu_contest_level"),
            @Excel(name = "组织者", targetAttr = "organizer", type = Excel.Type.EXPORT, width = 30)
    })
    private EduContest contest;

    /** 获奖时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "获奖时间", dateFormat = "yyyy-MM-dd")
    private Date issueTime;

    /** 指导老师 */
    @Excel(name = "指导老师")
    private String teacherName;

    /** 附件 */
    private String fileUrl;

    /** 团队名称 */
    @Excel(name = "团队名称")
    private String teamName;

    public void setAwardId(Long awardId) 
    {
        this.awardId = awardId;
    }

    public Long getAwardId() 
    {
        return awardId;
    }
    public void setStudentId(Long studentId) 
    {
        this.studentId = studentId;
    }

    public Long getStudentId() 
    {
        return studentId;
    }
    public void setContestId(Long contestId) 
    {
        this.contestId = contestId;
    }

    public Long getContestId() 
    {
        return contestId;
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
    public void setTeacherName(String teacherName) 
    {
        this.teacherName = teacherName;
    }

    public String getTeacherName() 
    {
        return teacherName;
    }
    public void setFileUrl(String fileUrl) 
    {
        this.fileUrl = fileUrl;
    }

    public String getFileUrl() 
    {
        return fileUrl;
    }
    public void setTeamName(String teamName) 
    {
        this.teamName = teamName;
    }

    public String getTeamName() 
    {
        return teamName;
    }

    public EduStudent getStudent() {
        return student;
    }

    public void setStudent(EduStudent student) {
        this.student = student;
    }

    public EduContest getContest() {
        return contest;
    }

    public void setContest(EduContest contest) {
        this.contest = contest;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("awardId", getAwardId())
            .append("studentId", getStudentId())
            .append("contestId", getContestId())
            .append("grade", getGrade())
            .append("issueTime", getIssueTime())
            .append("teacherName", getTeacherName())
            .append("fileUrl", getFileUrl())
            .append("teamName", getTeamName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
