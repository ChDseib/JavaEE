package com.ruoyi.edu.controller;

import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
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
import com.ruoyi.edu.domain.Parttime;
import com.ruoyi.edu.service.IParttimeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 社会兼职Controller
 *
 * @author lennon
 * @date 2024-02-06
 */
@RestController
@RequestMapping("/edu/parttime")
public class ParttimeController extends BaseController {
    @Autowired
    private IParttimeService parttimeService;

    /**
     * 查询社会兼职列表
     */
    @PreAuthorize("@ss.hasPermi('edu:parttime:list')")
    @GetMapping("/list")
    public TableDataInfo list(Parttime parttime) {
        startPage();
        List<Parttime> list = parttimeService.selectParttimeList(parttime);
        return getDataTable(list);
    }

    /**
     * 导出社会兼职列表
     */
    @PreAuthorize("@ss.hasPermi('edu:parttime:export')")
    @Log(title = "社会兼职", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Parttime parttime) {
        List<Parttime> list = parttimeService.selectParttimeList(parttime);
        ExcelUtil<Parttime> util = new ExcelUtil<Parttime>(Parttime.class);
        return util.exportExcel(list, "社会兼职数据");
    }

    /**
     * 获取社会兼职详细信息
     */
    @PreAuthorize("@ss.hasPermi('edu:parttime:query')")
    @GetMapping(value = "/{parttimeId}")
    public AjaxResult getInfo(@PathVariable("parttimeId") Long parttimeId) {
        return AjaxResult.success(parttimeService.selectParttimeByParttimeId(parttimeId));
    }

    /**
     * 新增社会兼职
     */
    @PreAuthorize("@ss.hasPermi('edu:parttime:add')")
    @Log(title = "社会兼职", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Parttime parttime) {
        return toAjax(parttimeService.insertParttime(parttime));
    }

    /**
     * 修改社会兼职
     */
    @PreAuthorize("@ss.hasPermi('edu:parttime:edit')")
    @Log(title = "社会兼职", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Parttime parttime)
    {
        return toAjax(parttimeService.updateParttime(parttime));
    }

    /**
     * 删除社会兼职
     */
    @PreAuthorize("@ss.hasPermi('edu:parttime:remove')")
    @Log(title = "社会兼职", businessType = BusinessType.DELETE)
    @DeleteMapping("/{parttimeIds}")
    public AjaxResult remove(@PathVariable Long[] parttimeIds) {


        String userName = getLoginUser().getUser().getUserName();
        if (!SecurityUtils.isAdmin(getUserId())) {
            //判断该数据是否是自己添加
            for (Long parttimeId : parttimeIds) {
                Parttime parttime = parttimeService.selectParttimeByParttimeId(parttimeId);
                if (!userName.equals(parttime.getCreateBy())) {
                    return AjaxResult.error("有且仅勾选本人添加的数据");
                }

            }
            //说明勾选的数据没有异常
            return toAjax(parttimeService.deleteParttimeByParttimeIds(parttimeIds));


        } else return toAjax(parttimeService.deleteParttimeByParttimeIds(parttimeIds));

    }
}
