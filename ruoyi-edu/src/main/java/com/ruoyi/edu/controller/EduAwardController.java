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
import com.ruoyi.edu.domain.EduAward;
import com.ruoyi.edu.service.IEduAwardService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 奖项Controller
 * 
 * @author ruoyi
 * @date 2024-01-17
 */
@RestController
@RequestMapping("/edu/award")
public class EduAwardController extends BaseController
{
    @Autowired
    private IEduAwardService eduAwardService;

    /**
     * 查询奖项列表
     */
    @PreAuthorize("@ss.hasPermi('edu:award:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduAward eduAward)
    {
        startPage();
        List<EduAward> list = eduAwardService.selectEduAwardList(eduAward);
        return getDataTable(list);
    }

    /**
     * 导出奖项列表
     */
    @PreAuthorize("@ss.hasPermi('edu:award:export')")
    @Log(title = "奖项", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(EduAward eduAward)
    {
        List<EduAward> list = eduAwardService.selectEduAwardList(eduAward);
        ExcelUtil<EduAward> util = new ExcelUtil<EduAward>(EduAward.class);
        return util.exportExcel(list, "奖项数据");
    }

    /**
     * 获取奖项详细信息
     */
    @PreAuthorize("@ss.hasPermi('edu:award:query')")
    @GetMapping(value = "/{awardId}")
    public AjaxResult getInfo(@PathVariable("awardId") Long awardId)
    {
        return AjaxResult.success(eduAwardService.selectEduAwardByAwardId(awardId));
    }

    /**
     * 新增奖项
     */
    @PreAuthorize("@ss.hasPermi('edu:award:add')")
    @Log(title = "奖项", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduAward eduAward)
    {
        return toAjax(eduAwardService.insertEduAward(eduAward));
    }

    /**
     * 修改奖项
     */
    @PreAuthorize("@ss.hasPermi('edu:award:edit')")
    @Log(title = "奖项", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduAward eduAward)
    {
        return toAjax(eduAwardService.updateEduAward(eduAward));
    }

    /**
     * 删除奖项
     */
    @PreAuthorize("@ss.hasPermi('edu:award:remove')")
    @Log(title = "奖项", businessType = BusinessType.DELETE)
	@DeleteMapping("/{awardIds}")
    public AjaxResult remove(@PathVariable Long[] awardIds)
    {
        return toAjax(eduAwardService.deleteEduAwardByAwardIds(awardIds));
    }
}
