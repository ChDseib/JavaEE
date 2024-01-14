package com.ruoyi.edu.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 认证机构对象 edu_authority
 * 
 * @author ruoyi
 * @date 2024-01-14
 */
public class EduAuthority extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 机构ID */
    private Long authorityId;

    /** 机构名称 */
    @Excel(name = "机构名称")
    private String authorityName;

    public void setAuthorityId(Long authorityId) 
    {
        this.authorityId = authorityId;
    }

    public Long getAuthorityId() 
    {
        return authorityId;
    }
    public void setAuthorityName(String authorityName) 
    {
        this.authorityName = authorityName;
    }

    public String getAuthorityName() 
    {
        return authorityName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("authorityId", getAuthorityId())
            .append("authorityName", getAuthorityName())
            .toString();
    }
}
