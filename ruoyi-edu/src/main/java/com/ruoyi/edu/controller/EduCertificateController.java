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
import com.ruoyi.edu.domain.EduCertificate;
import com.ruoyi.edu.service.IEduCertificateService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学生证书Controller
 * 
 * @author ruoyi
 * @date 2024-01-15
 */
@RestController
@RequestMapping("/edu/certificate")
public class EduCertificateController extends BaseController
{
    @Autowired
    private IEduCertificateService eduCertificateService;

    /**
     * 查询学生证书列表
     */
    @PreAuthorize("@ss.hasPermi('edu:certificate:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduCertificate eduCertificate)
    {
        startPage();
        List<EduCertificate> list = eduCertificateService.selectEduCertificateList(eduCertificate);
        return getDataTable(list);
    }

    /**
     * 导出学生证书列表
     */
    @PreAuthorize("@ss.hasPermi('edu:certificate:export')")
    @Log(title = "学生证书", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(EduCertificate eduCertificate)
    {
        List<EduCertificate> list = eduCertificateService.selectEduCertificateList(eduCertificate);
        ExcelUtil<EduCertificate> util = new ExcelUtil<EduCertificate>(EduCertificate.class);
        return util.exportExcel(list, "学生证书数据");
    }

    /**
     * 获取学生证书详细信息
     */
    @PreAuthorize("@ss.hasPermi('edu:certificate:query')")
    @GetMapping(value = "/{certificateId}")
    public AjaxResult getInfo(@PathVariable("certificateId") Long certificateId)
    {
        return AjaxResult.success(eduCertificateService.selectEduCertificateByCertificateId(certificateId));
    }

    /**
     * 新增学生证书
     */
    @PreAuthorize("@ss.hasPermi('edu:certificate:add')")
    @Log(title = "学生证书", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduCertificate eduCertificate)
    {
        return toAjax(eduCertificateService.insertEduCertificate(eduCertificate));
    }

    /**
     * 修改学生证书
     */
    @PreAuthorize("@ss.hasPermi('edu:certificate:edit')")
    @Log(title = "学生证书", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduCertificate eduCertificate)
    {
        return toAjax(eduCertificateService.updateEduCertificate(eduCertificate));
    }

    /**
     * 删除学生证书
     */
    @PreAuthorize("@ss.hasPermi('edu:certificate:remove')")
    @Log(title = "学生证书", businessType = BusinessType.DELETE)
	@DeleteMapping("/{certificateIds}")
    public AjaxResult remove(@PathVariable Long[] certificateIds)
    {
        return toAjax(eduCertificateService.deleteEduCertificateByCertificateIds(certificateIds));
    }
}
