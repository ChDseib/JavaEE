package com.ruoyi.edu.mapper;

import java.util.List;
import com.ruoyi.edu.domain.EduAward;

/**
 * 奖项Mapper接口
 * 
 * @author ruoyi
 * @date 2024-01-17
 */
public interface EduAwardMapper 
{
    /**
     * 查询奖项
     * 
     * @param awardId 奖项主键
     * @return 奖项
     */
    public EduAward selectEduAwardByAwardId(Long awardId);

    /**
     * 查询奖项列表
     * 
     * @param eduAward 奖项
     * @return 奖项集合
     */
    public List<EduAward> selectEduAwardList(EduAward eduAward);

    /**
     * 新增奖项
     * 
     * @param eduAward 奖项
     * @return 结果
     */
    public int insertEduAward(EduAward eduAward);

    /**
     * 修改奖项
     * 
     * @param eduAward 奖项
     * @return 结果
     */
    public int updateEduAward(EduAward eduAward);

    /**
     * 删除奖项
     * 
     * @param awardId 奖项主键
     * @return 结果
     */
    public int deleteEduAwardByAwardId(Long awardId);

    /**
     * 批量删除奖项
     * 
     * @param awardIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduAwardByAwardIds(Long[] awardIds);
}
