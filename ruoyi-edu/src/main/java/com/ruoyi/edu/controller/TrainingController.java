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
import com.ruoyi.edu.domain.Training;
import com.ruoyi.edu.service.ITrainingService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 教师培训Controller
 * 
 * @author ruoyi
 * @date 2024-01-20
 */
@RestController
@RequestMapping("/edu/training")
public class TrainingController extends BaseController
{
    @Autowired
    private ITrainingService trainingService;

    /**
     * 查询教师培训列表
     */
    @PreAuthorize("@ss.hasPermi('edu:training:list')")
    @GetMapping("/list")
    public TableDataInfo list(Training training)
    {
        startPage();
        List<Training> list = trainingService.selectTrainingList(training);
        return getDataTable(list);
    }

    /**
     * 导出教师培训列表
     */
    @PreAuthorize("@ss.hasPermi('edu:training:export')")
    @Log(title = "教师培训", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Training training)
    {
        List<Training> list = trainingService.selectTrainingList(training);
        ExcelUtil<Training> util = new ExcelUtil<Training>(Training.class);
        return util.exportExcel(list, "教师培训数据");
    }

    /**
     * 获取教师培训详细信息
     */
    @PreAuthorize("@ss.hasPermi('edu:training:query')")
    @GetMapping(value = "/{trainingId}")
    public AjaxResult getInfo(@PathVariable("trainingId") Long trainingId)
    {
        return AjaxResult.success(trainingService.selectTrainingByTrainingId(trainingId));
    }

    /**
     * 新增教师培训
     */
    @PreAuthorize("@ss.hasPermi('edu:training:add')")
    @Log(title = "教师培训", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Training training)
    {
        return toAjax(trainingService.insertTraining(training));
    }

    /**
     * 修改教师培训
     */
    @PreAuthorize("@ss.hasPermi('edu:training:edit')")
    @Log(title = "教师培训", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Training training)
    {
        return toAjax(trainingService.updateTraining(training));
    }

    /**
     * 删除教师培训
     */
    @PreAuthorize("@ss.hasPermi('edu:training:remove')")
    @Log(title = "教师培训", businessType = BusinessType.DELETE)
	@DeleteMapping("/{trainingIds}")
    public AjaxResult remove(@PathVariable Long[] trainingIds)
    {
        return toAjax(trainingService.deleteTrainingByTrainingIds(trainingIds));
    }
}
