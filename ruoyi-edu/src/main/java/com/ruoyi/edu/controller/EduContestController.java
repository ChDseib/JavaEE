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
import com.ruoyi.edu.domain.EduContest;
import com.ruoyi.edu.service.IEduContestService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 竞赛管理Controller
 * 
 * @author ruoyi
 * @date 2024-01-17
 */
@RestController
@RequestMapping("/edu/contest")
public class EduContestController extends BaseController
{
    @Autowired
    private IEduContestService eduContestService;

    /**
     * 查询竞赛管理列表
     */
    @GetMapping("/list")
    public TableDataInfo list(EduContest eduContest)
    {
        startPage();
        List<EduContest> list = eduContestService.selectEduContestList(eduContest);
        return getDataTable(list);
    }

    /**
     * 导出竞赛管理列表
     */
    @PreAuthorize("@ss.hasPermi('edu:contest:export')")
    @Log(title = "竞赛管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(EduContest eduContest)
    {
        List<EduContest> list = eduContestService.selectEduContestList(eduContest);
        ExcelUtil<EduContest> util = new ExcelUtil<EduContest>(EduContest.class);
        return util.exportExcel(list, "竞赛管理数据");
    }

    /**
     * 获取竞赛管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('edu:contest:query')")
    @GetMapping(value = "/{contestId}")
    public AjaxResult getInfo(@PathVariable("contestId") Long contestId)
    {
        return AjaxResult.success(eduContestService.selectEduContestByContestId(contestId));
    }

    /**
     * 新增竞赛管理
     */
    @PreAuthorize("@ss.hasPermi('edu:contest:add')")
    @Log(title = "竞赛管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduContest eduContest)
    {
        return toAjax(eduContestService.insertEduContest(eduContest));
    }

    /**
     * 修改竞赛管理
     */
    @PreAuthorize("@ss.hasPermi('edu:contest:edit')")
    @Log(title = "竞赛管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduContest eduContest)
    {
        return toAjax(eduContestService.updateEduContest(eduContest));
    }

    /**
     * 删除竞赛管理
     */
    @PreAuthorize("@ss.hasPermi('edu:contest:remove')")
    @Log(title = "竞赛管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{contestIds}")
    public AjaxResult remove(@PathVariable Long[] contestIds)
    {
        return toAjax(eduContestService.deleteEduContestByContestIds(contestIds));
    }
}
