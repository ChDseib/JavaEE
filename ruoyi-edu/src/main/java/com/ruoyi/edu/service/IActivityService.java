package com.ruoyi.edu.service;

import java.util.List;
import com.ruoyi.edu.domain.Activity;

/**
 * 社会活动Service接口
 * 
 * @author sanda
 * @date 2024-10-12
 */
public interface IActivityService 
{
    /**
     * 查询社会活动
     * 
     * @param activityId 社会活动主键
     * @return 社会活动
     */
    public Activity selectActivityByActivityId(Long activityId);

    /**
     * 查询社会活动列表
     * 
     * @param activity 社会活动
     * @return 社会活动集合
     */
    public List<Activity> selectActivityList(Activity activity);

    /**
     * 新增社会活动
     * 
     * @param activity 社会活动
     * @return 结果
     */
    public int insertActivity(Activity activity);

    /**
     * 修改社会活动
     * 
     * @param activity 社会活动
     * @return 结果
     */
    public int updateActivity(Activity activity);

    /**
     * 批量删除社会活动
     * 
     * @param activityIds 需要删除的社会活动主键集合
     * @return 结果
     */
    public int deleteActivityByActivityIds(Long[] activityIds);

    /**
     * 删除社会活动信息
     * 
     * @param activityId 社会活动主键
     * @return 结果
     */
    public int deleteActivityByActivityId(Long activityId);
}
