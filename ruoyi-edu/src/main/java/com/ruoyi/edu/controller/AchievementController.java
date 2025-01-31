package com.ruoyi.edu.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.edu.domain.Teacher;
import com.ruoyi.edu.service.ITeacherService;
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
import com.ruoyi.edu.domain.Achievement;
import com.ruoyi.edu.service.IAchievementService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 教学成果Controller
 * 
 * @author ruoyi
 * @date 2024-03-24
 */
@RestController
@RequestMapping("/edu/achievement")
public class AchievementController extends BaseController
{
    @Autowired
    private IAchievementService achievementService;

    @Autowired
    private ITeacherService teacherService;

    /**
     * 查询教学成果列表
     */
    @PreAuthorize("@ss.hasPermi('edu:achievement:list')")
    @GetMapping("/list")
    public TableDataInfo list(Achievement achievement)
    {
        startPage();
        List<Achievement> list = achievementService.selectAchievementList(achievement);
        for (Achievement a: list) {
            a.setTeachers(teacherService.selectTeacherListByAchievementId(a.getAchievementId()));
        }
        return getDataTable(list);
    }

    /**
     * 导出教学成果列表
     */
    @PreAuthorize("@ss.hasPermi('edu:achievement:export')")
    @Log(title = "教学成果", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Achievement achievement)
    {
        List<Achievement> list = achievementService.selectAchievementList(achievement);
        ExcelUtil<Achievement> util = new ExcelUtil<Achievement>(Achievement.class);
        return util.exportExcel(list, "教学成果数据");
    }

    /**
     * 获取教学成果详细信息
     */
    @PreAuthorize("@ss.hasPermi('edu:achievement:query')")
    @GetMapping(value = { "/", "/{achievementId}" })
    public AjaxResult getInfo(@PathVariable(value = "achievementId", required = false) Long achievementId)
    {
        AjaxResult ajax = AjaxResult.success();
        ajax.put("teachers", teacherService.selectTeacherAll());
        if (StringUtils.isNotNull(achievementId))
        {
            ajax.put(AjaxResult.DATA_TAG, achievementService.selectAchievementByAchievementId(achievementId));
            List<Teacher> teachers = teacherService.selectTeacherListByAchievementId(achievementId);
            List<Long> teacherIds = teachers.stream()
                    .map(Teacher::getTeacherId)
                    .collect(Collectors.toList());
            ajax.put("teacherIds", teacherIds);
        }
        return ajax;
    }

    /**
     * 新增教学成果
     */
    @PreAuthorize("@ss.hasPermi('edu:achievement:add')")
    @Log(title = "教学成果", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Achievement achievement)
    {
        return toAjax(achievementService.insertAchievement(achievement));
    }

    /**
     * 修改教学成果
     */
    @PreAuthorize("@ss.hasPermi('edu:achievement:edit')")
    @Log(title = "教学成果", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Achievement achievement)
    {
        return toAjax(achievementService.updateAchievement(achievement));
    }

    /**
     * 删除教学成果
     */
    @PreAuthorize("@ss.hasPermi('edu:achievement:remove')")
    @Log(title = "教学成果", businessType = BusinessType.DELETE)
	@DeleteMapping("/{achievementIds}")
    public AjaxResult remove(@PathVariable Long[] achievementIds)
    {
        return toAjax(achievementService.deleteAchievementByAchievementIds(achievementIds));
    }
}
