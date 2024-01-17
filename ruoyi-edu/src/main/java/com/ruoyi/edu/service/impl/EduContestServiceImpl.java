package com.ruoyi.edu.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.edu.mapper.EduContestMapper;
import com.ruoyi.edu.domain.EduContest;
import com.ruoyi.edu.service.IEduContestService;

/**
 * 竞赛管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-01-17
 */
@Service
public class EduContestServiceImpl implements IEduContestService 
{
    @Autowired
    private EduContestMapper eduContestMapper;

    /**
     * 查询竞赛管理
     * 
     * @param contestId 竞赛管理主键
     * @return 竞赛管理
     */
    @Override
    public EduContest selectEduContestByContestId(Long contestId)
    {
        return eduContestMapper.selectEduContestByContestId(contestId);
    }

    /**
     * 查询竞赛管理列表
     * 
     * @param eduContest 竞赛管理
     * @return 竞赛管理
     */
    @Override
    public List<EduContest> selectEduContestList(EduContest eduContest)
    {
        return eduContestMapper.selectEduContestList(eduContest);
    }

    /**
     * 新增竞赛管理
     * 
     * @param eduContest 竞赛管理
     * @return 结果
     */
    @Override
    public int insertEduContest(EduContest eduContest)
    {
        return eduContestMapper.insertEduContest(eduContest);
    }

    /**
     * 修改竞赛管理
     * 
     * @param eduContest 竞赛管理
     * @return 结果
     */
    @Override
    public int updateEduContest(EduContest eduContest)
    {
        return eduContestMapper.updateEduContest(eduContest);
    }

    /**
     * 批量删除竞赛管理
     * 
     * @param contestIds 需要删除的竞赛管理主键
     * @return 结果
     */
    @Override
    public int deleteEduContestByContestIds(Long[] contestIds)
    {
        return eduContestMapper.deleteEduContestByContestIds(contestIds);
    }

    /**
     * 删除竞赛管理信息
     * 
     * @param contestId 竞赛管理主键
     * @return 结果
     */
    @Override
    public int deleteEduContestByContestId(Long contestId)
    {
        return eduContestMapper.deleteEduContestByContestId(contestId);
    }
}
