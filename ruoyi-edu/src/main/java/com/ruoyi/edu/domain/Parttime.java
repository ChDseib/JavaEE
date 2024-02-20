package com.ruoyi.edu.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 社会兼职对象 parttime
 *
 * @author lennon
 * @date 2024-02-06
 */
public class Parttime extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 社会兼职ID
     */
    private Long parttimeId;


    /**
     * 教师ID
     */
    @Excel(name = "教师ID")
    private Long teacherId;

    /**
     * 教师
     */
    @Excel(name = "教师姓名", targetAttr = "teacherName")
    private Teacher teacher;

    /**
     * 类别
     */

    @Excel(name = "级别", dictType = "edu_parttime_category")
    private String category;

    /**
     * 兼职单位
     */
    @Excel(name = "兼职单位")
    private String employer;

    /**
     * 兼职职务
     */
    @Excel(name = "兼职职务")
    private String position;




    public void setParttimeId(Long parttimeId) {
        this.parttimeId = parttimeId;
    }

    public Long getParttimeId() {
        return parttimeId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getEmployer() {
        return employer;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("parttimeId", getParttimeId())
                .append("teacherId", getTeacherId())
                .append("category", getCategory())
                .append("employer", getEmployer())
                .append("position", getPosition())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
