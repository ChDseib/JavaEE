package com.ruoyi.edu.mapper;

import java.util.List;
import com.ruoyi.edu.domain.EduContest;

/**
 * 竞赛管理Mapper接口
 * 
 * @author ruoyi
 * @date 2024-01-17
 */
public interface EduContestMapper 
{
    /**
     * 查询竞赛管理
     * 
     * @param contestId 竞赛管理主键
     * @return 竞赛管理
     */
    public EduContest selectEduContestByContestId(Long contestId);

    /**
     * 查询竞赛管理列表
     * 
     * @param eduContest 竞赛管理
     * @return 竞赛管理集合
     */
    public List<EduContest> selectEduContestList(EduContest eduContest);

    /**
     * 新增竞赛管理
     * 
     * @param eduContest 竞赛管理
     * @return 结果
     */
    public int insertEduContest(EduContest eduContest);

    /**
     * 修改竞赛管理
     * 
     * @param eduContest 竞赛管理
     * @return 结果
     */
    public int updateEduContest(EduContest eduContest);

    /**
     * 删除竞赛管理
     * 
     * @param contestId 竞赛管理主键
     * @return 结果
     */
    public int deleteEduContestByContestId(Long contestId);

    /**
     * 批量删除竞赛管理
     * 
     * @param contestIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduContestByContestIds(Long[] contestIds);
}
