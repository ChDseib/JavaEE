package com.ruoyi.edu.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.edu.domain.Textbook;
import com.ruoyi.edu.service.ITextbookService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 出版教材Controller
 * 
 * @author ruoyi
 * @date 2024-05-31
 */
@RestController
@RequestMapping("/edu/textbook")
public class TextbookController extends BaseController
{
    @Autowired
    private ITextbookService textbookService;

    /**
     * 查询出版教材列表
     */
    @PreAuthorize("@ss.hasPermi('edu:textbook:list')")
    @GetMapping("/list")
    public TableDataInfo list(Textbook textbook)
    {
        startPage();
        List<Textbook> list = textbookService.selectTextbookList(textbook);
        return getDataTable(list);
    }

    /**
     * 导出出版教材列表
     */
    @PreAuthorize("@ss.hasPermi('edu:textbook:export')")
    @Log(title = "出版教材", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Textbook textbook)
    {
        List<Textbook> list = textbookService.selectTextbookList(textbook);
        ExcelUtil<Textbook> util = new ExcelUtil<Textbook>(Textbook.class);
        return util.exportExcel(list, "出版教材数据");
    }

    /**
     * 获取出版教材详细信息
     */
    @PreAuthorize("@ss.hasPermi('edu:textbook:query')")
    @GetMapping(value = "/{textbookId}")
    public AjaxResult getInfo(@PathVariable("textbookId") Long textbookId)
    {
        return AjaxResult.success(textbookService.selectTextbookByTextbookId(textbookId));
    }

    /**
     * 新增出版教材
     */
    @PreAuthorize("@ss.hasPermi('edu:textbook:add')")
    @Log(title = "出版教材", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Textbook textbook)
    {
        return toAjax(textbookService.insertTextbook(textbook));
    }

    /**
     * 修改出版教材
     */
    @PreAuthorize("@ss.hasPermi('edu:textbook:edit')")
    @Log(title = "出版教材", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Textbook textbook)
    {
        return toAjax(textbookService.updateTextbook(textbook));
    }

    /**
     * 删除出版教材
     */
    @PreAuthorize("@ss.hasPermi('edu:textbook:remove')")
    @Log(title = "出版教材", businessType = BusinessType.DELETE)
	@DeleteMapping("/{textbookIds}")
    public AjaxResult remove(@PathVariable Long[] textbookIds)
    {
        return toAjax(textbookService.deleteTextbookByTextbookIds(textbookIds));
    }
}
