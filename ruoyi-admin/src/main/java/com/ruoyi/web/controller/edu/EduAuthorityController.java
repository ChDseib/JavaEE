package com.ruoyi.web.controller.edu;

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
import com.ruoyi.edu.domain.EduAuthority;
import com.ruoyi.edu.service.IEduAuthorityService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 认证机构Controller
 * 
 * @author ruoyi
 * @date 2024-01-14
 */
@RestController
@RequestMapping("/edu/authority")
public class EduAuthorityController extends BaseController
{
    @Autowired
    private IEduAuthorityService eduAuthorityService;

    /**
     * 查询认证机构列表
     */
    @PreAuthorize("@ss.hasPermi('edu:authority:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduAuthority eduAuthority)
    {
        startPage();
        List<EduAuthority> list = eduAuthorityService.selectEduAuthorityList(eduAuthority);
        return getDataTable(list);
    }

    /**
     * 导出认证机构列表
     */
    @PreAuthorize("@ss.hasPermi('edu:authority:export')")
    @Log(title = "认证机构", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(EduAuthority eduAuthority)
    {
        List<EduAuthority> list = eduAuthorityService.selectEduAuthorityList(eduAuthority);
        ExcelUtil<EduAuthority> util = new ExcelUtil<EduAuthority>(EduAuthority.class);
        return util.exportExcel(list, "认证机构数据");
    }

    /**
     * 获取认证机构详细信息
     */
    @PreAuthorize("@ss.hasPermi('edu:authority:query')")
    @GetMapping(value = "/{authorityId}")
    public AjaxResult getInfo(@PathVariable("authorityId") Long authorityId)
    {
        return AjaxResult.success(eduAuthorityService.selectEduAuthorityByAuthorityId(authorityId));
    }

    /**
     * 新增认证机构
     */
    @PreAuthorize("@ss.hasPermi('edu:authority:add')")
    @Log(title = "认证机构", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduAuthority eduAuthority)
    {
        return toAjax(eduAuthorityService.insertEduAuthority(eduAuthority));
    }

    /**
     * 修改认证机构
     */
    @PreAuthorize("@ss.hasPermi('edu:authority:edit')")
    @Log(title = "认证机构", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduAuthority eduAuthority)
    {
        return toAjax(eduAuthorityService.updateEduAuthority(eduAuthority));
    }

    /**
     * 删除认证机构
     */
    @PreAuthorize("@ss.hasPermi('edu:authority:remove')")
    @Log(title = "认证机构", businessType = BusinessType.DELETE)
	@DeleteMapping("/{authorityIds}")
    public AjaxResult remove(@PathVariable Long[] authorityIds)
    {
        return toAjax(eduAuthorityService.deleteEduAuthorityByAuthorityIds(authorityIds));
    }
}
