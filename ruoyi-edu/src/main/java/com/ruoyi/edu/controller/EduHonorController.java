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
import com.ruoyi.edu.domain.EduHonor;
import com.ruoyi.edu.service.IEduHonorService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 教师奖项Controller
 * 
 * @author Lennon
 * @date 2024-01-31
 */
@RestController
@RequestMapping("/edu/honor")
public class EduHonorController extends BaseController
{
    @Autowired
    private IEduHonorService eduHonorService;

    /**
     * 查询教师奖项列表
     */
    @PreAuthorize("@ss.hasPermi('edu:honor:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduHonor eduHonor)
    {
        startPage();
        List<EduHonor> list = eduHonorService.selectEduHonorList(eduHonor);
        return getDataTable(list);
    }

    /**
     * 导出教师奖项列表
     */
    @PreAuthorize("@ss.hasPermi('edu:honor:export')")
    @Log(title = "教师奖项", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(EduHonor eduHonor)
    {
        List<EduHonor> list = eduHonorService.selectEduHonorList(eduHonor);
        ExcelUtil<EduHonor> util = new ExcelUtil<EduHonor>(EduHonor.class);
        return util.exportExcel(list, "教师奖项数据");
    }

    /**
     * 获取教师奖项详细信息
     */
    @PreAuthorize("@ss.hasPermi('edu:honor:query')")
    @GetMapping(value = "/{honorId}")
    public AjaxResult getInfo(@PathVariable("honorId") Long honorId)
    {
        return AjaxResult.success(eduHonorService.selectEduHonorByHonorId(honorId));
    }

    /**
     * 新增教师奖项
     */
    @PreAuthorize("@ss.hasPermi('edu:honor:add')")
    @Log(title = "教师奖项", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduHonor eduHonor)
    {
        return toAjax(eduHonorService.insertEduHonor(eduHonor));
    }

    /**
     * 修改教师奖项
     */
    @PreAuthorize("@ss.hasPermi('edu:honor:edit')")
    @Log(title = "教师奖项", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduHonor eduHonor)
    {
        return toAjax(eduHonorService.updateEduHonor(eduHonor));
    }

    /**
     * 删除教师奖项
     */
    @PreAuthorize("@ss.hasPermi('edu:honor:remove')")
    @Log(title = "教师奖项", businessType = BusinessType.DELETE)
	@DeleteMapping("/{honorIds}")
    public AjaxResult remove(@PathVariable Long[] honorIds)
    {
        return toAjax(eduHonorService.deleteEduHonorByHonorIds(honorIds));
    }
}
