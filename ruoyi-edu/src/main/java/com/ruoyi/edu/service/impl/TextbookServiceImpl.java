package com.ruoyi.edu.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.edu.mapper.TextbookMapper;
import com.ruoyi.edu.domain.Textbook;
import com.ruoyi.edu.service.ITextbookService;

/**
 * 出版教材Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-05-31
 */
@Service
public class TextbookServiceImpl implements ITextbookService 
{
    @Autowired
    private TextbookMapper textbookMapper;

    /**
     * 查询出版教材
     * 
     * @param textbookId 出版教材主键
     * @return 出版教材
     */
    @Override
    public Textbook selectTextbookByTextbookId(Long textbookId)
    {
        return textbookMapper.selectTextbookByTextbookId(textbookId);
    }

    /**
     * 查询出版教材列表
     * 
     * @param textbook 出版教材
     * @return 出版教材
     */
    @Override
    public List<Textbook> selectTextbookList(Textbook textbook)
    {
        return textbookMapper.selectTextbookList(textbook);
    }

    /**
     * 新增出版教材
     * 
     * @param textbook 出版教材
     * @return 结果
     */
    @Override
    public int insertTextbook(Textbook textbook)
    {
        return textbookMapper.insertTextbook(textbook);
    }

    /**
     * 修改出版教材
     * 
     * @param textbook 出版教材
     * @return 结果
     */
    @Override
    public int updateTextbook(Textbook textbook)
    {
        return textbookMapper.updateTextbook(textbook);
    }

    /**
     * 批量删除出版教材
     * 
     * @param textbookIds 需要删除的出版教材主键
     * @return 结果
     */
    @Override
    public int deleteTextbookByTextbookIds(Long[] textbookIds)
    {
        return textbookMapper.deleteTextbookByTextbookIds(textbookIds);
    }

    /**
     * 删除出版教材信息
     * 
     * @param textbookId 出版教材主键
     * @return 结果
     */
    @Override
    public int deleteTextbookByTextbookId(Long textbookId)
    {
        return textbookMapper.deleteTextbookByTextbookId(textbookId);
    }
}
