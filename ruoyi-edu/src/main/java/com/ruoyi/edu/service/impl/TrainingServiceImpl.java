package com.ruoyi.edu.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.edu.mapper.TrainingMapper;
import com.ruoyi.edu.domain.Training;
import com.ruoyi.edu.service.ITrainingService;

/**
 * 教师培训Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-01-20
 */
@Service
public class TrainingServiceImpl implements ITrainingService 
{
    @Autowired
    private TrainingMapper trainingMapper;

    /**
     * 查询教师培训
     * 
     * @param trainingId 教师培训主键
     * @return 教师培训
     */
    @Override
    public Training selectTrainingByTrainingId(Long trainingId)
    {
        return trainingMapper.selectTrainingByTrainingId(trainingId);
    }

    /**
     * 查询教师培训列表
     * 
     * @param training 教师培训
     * @return 教师培训
     */
    @Override
    public List<Training> selectTrainingList(Training training)
    {
        return trainingMapper.selectTrainingList(training);
    }

    /**
     * 新增教师培训
     * 
     * @param training 教师培训
     * @return 结果
     */
    @Override
    public int insertTraining(Training training)
    {
        training.setCreateTime(DateUtils.getNowDate());
        return trainingMapper.insertTraining(training);
    }

    /**
     * 修改教师培训
     * 
     * @param training 教师培训
     * @return 结果
     */
    @Override
    public int updateTraining(Training training)
    {
        training.setUpdateTime(DateUtils.getNowDate());
        return trainingMapper.updateTraining(training);
    }

    /**
     * 批量删除教师培训
     * 
     * @param trainingIds 需要删除的教师培训主键
     * @return 结果
     */
    @Override
    public int deleteTrainingByTrainingIds(Long[] trainingIds)
    {
        return trainingMapper.deleteTrainingByTrainingIds(trainingIds);
    }

    /**
     * 删除教师培训信息
     * 
     * @param trainingId 教师培训主键
     * @return 结果
     */
    @Override
    public int deleteTrainingByTrainingId(Long trainingId)
    {
        return trainingMapper.deleteTrainingByTrainingId(trainingId);
    }
}
