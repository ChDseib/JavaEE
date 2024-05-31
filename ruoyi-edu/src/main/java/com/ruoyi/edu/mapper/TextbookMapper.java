package com.ruoyi.edu.mapper;

import java.util.List;
import com.ruoyi.edu.domain.Textbook;

/**
 * 出版教材Mapper接口
 * 
 * @author ruoyi
 * @date 2024-05-31
 */
public interface TextbookMapper 
{
    /**
     * 查询出版教材
     * 
     * @param textbookId 出版教材主键
     * @return 出版教材
     */
    public Textbook selectTextbookByTextbookId(Long textbookId);

    /**
     * 查询出版教材列表
     * 
     * @param textbook 出版教材
     * @return 出版教材集合
     */
    public List<Textbook> selectTextbookList(Textbook textbook);

    /**
     * 新增出版教材
     * 
     * @param textbook 出版教材
     * @return 结果
     */
    public int insertTextbook(Textbook textbook);

    /**
     * 修改出版教材
     * 
     * @param textbook 出版教材
     * @return 结果
     */
    public int updateTextbook(Textbook textbook);

    /**
     * 删除出版教材
     * 
     * @param textbookId 出版教材主键
     * @return 结果
     */
    public int deleteTextbookByTextbookId(Long textbookId);

    /**
     * 批量删除出版教材
     * 
     * @param textbookIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTextbookByTextbookIds(Long[] textbookIds);
}
