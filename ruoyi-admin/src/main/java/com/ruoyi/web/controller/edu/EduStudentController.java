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
import com.ruoyi.edu.domain.EduStudent;
import com.ruoyi.edu.service.IEduStudentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学生Controller
 * 
 * @author ruoyi
 * @date 2024-01-14
 */
@RestController
@RequestMapping("/edu/student")
public class EduStudentController extends BaseController
{
    @Autowired
    private IEduStudentService eduStudentService;

    /**
     * 查询学生列表
     */
    @PreAuthorize("@ss.hasPermi('edu:student:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduStudent eduStudent)
    {
        startPage();
        List<EduStudent> list = eduStudentService.selectEduStudentList(eduStudent);
        return getDataTable(list);
    }

    /**
     * 导出学生列表
     */
    @PreAuthorize("@ss.hasPermi('edu:student:export')")
    @Log(title = "学生", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(EduStudent eduStudent)
    {
        List<EduStudent> list = eduStudentService.selectEduStudentList(eduStudent);
        ExcelUtil<EduStudent> util = new ExcelUtil<EduStudent>(EduStudent.class);
        return util.exportExcel(list, "学生数据");
    }

    /**
     * 获取学生详细信息
     */
    @PreAuthorize("@ss.hasPermi('edu:student:query')")
    @GetMapping(value = "/{studentId}")
    public AjaxResult getInfo(@PathVariable("studentId") Long studentId)
    {
        return AjaxResult.success(eduStudentService.selectEduStudentByStudentId(studentId));
    }

    /**
     * 新增学生
     */
    @PreAuthorize("@ss.hasPermi('edu:student:add')")
    @Log(title = "学生", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduStudent eduStudent)
    {
        return toAjax(eduStudentService.insertEduStudent(eduStudent));
    }

    /**
     * 修改学生
     */
    @PreAuthorize("@ss.hasPermi('edu:student:edit')")
    @Log(title = "学生", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduStudent eduStudent)
    {
        return toAjax(eduStudentService.updateEduStudent(eduStudent));
    }

    /**
     * 删除学生
     */
    @PreAuthorize("@ss.hasPermi('edu:student:remove')")
    @Log(title = "学生", businessType = BusinessType.DELETE)
	@DeleteMapping("/{studentIds}")
    public AjaxResult remove(@PathVariable Long[] studentIds)
    {
        return toAjax(eduStudentService.deleteEduStudentByStudentIds(studentIds));
    }
}
