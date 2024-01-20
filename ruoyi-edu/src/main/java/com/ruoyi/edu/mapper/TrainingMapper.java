package com.ruoyi.edu.mapper;

import java.util.List;
import com.ruoyi.edu.domain.Training;

/**
 * 教师培训Mapper接口
 * 
 * @author ruoyi
 * @date 2024-01-20
 */
public interface TrainingMapper 
{
    /**
     * 查询教师培训
     * 
     * @param trainingId 教师培训主键
     * @return 教师培训
     */
    public Training selectTrainingByTrainingId(Long trainingId);

    /**
     * 查询教师培训列表
     * 
     * @param training 教师培训
     * @return 教师培训集合
     */
    public List<Training> selectTrainingList(Training training);

    /**
     * 新增教师培训
     * 
     * @param training 教师培训
     * @return 结果
     */
    public int insertTraining(Training training);

    /**
     * 修改教师培训
     * 
     * @param training 教师培训
     * @return 结果
     */
    public int updateTraining(Training training);

    /**
     * 删除教师培训
     * 
     * @param trainingId 教师培训主键
     * @return 结果
     */
    public int deleteTrainingByTrainingId(Long trainingId);

    /**
     * 批量删除教师培训
     * 
     * @param trainingIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTrainingByTrainingIds(Long[] trainingIds);
}
