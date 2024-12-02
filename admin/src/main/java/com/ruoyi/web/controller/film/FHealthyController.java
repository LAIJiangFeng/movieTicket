package com.ruoyi.web.controller.film;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.film.domain.FHealthy;
import com.ruoyi.film.service.IFHealthyService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 健康码管理Controller
 * 
 * @author wyx
 * @date 2022-12-25
 */
@RestController
@RequestMapping("/film/healthy")
public class FHealthyController extends BaseController
{
    @Autowired
    private IFHealthyService fHealthyService;

    /**
     * 查询健康码管理列表
     */
    @GetMapping("/list")
    public TableDataInfo list(FHealthy fHealthy)
    {
        startPage();
        List<FHealthy> list = fHealthyService.selectFHealthyList(fHealthy);
        return getDataTable(list);
    }

    /**
     * 导出健康码管理列表
     */
    @PreAuthorize("@ss.hasPermi('film:healthy:export')")
    @Log(title = "健康码管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FHealthy fHealthy)
    {
        List<FHealthy> list = fHealthyService.selectFHealthyList(fHealthy);
        ExcelUtil<FHealthy> util = new ExcelUtil<FHealthy>(FHealthy.class);
        util.exportExcel(response, list, "健康码管理数据");
    }

    /**
     * 获取健康码管理详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(fHealthyService.selectFHealthyById(id));
    }

    /**
     * 新增健康码管理
     */
    @Log(title = "健康码管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FHealthy fHealthy)
    {
        if(fHealthy.getUserName() == null || "".equals(fHealthy.getUserName()) ){
            fHealthy.setUserName(getUsername());
            fHealthy.setNickName(getLoginUser().getUser().getNickName());
        }
        return toAjax(fHealthyService.insertFHealthy(fHealthy));
    }

    /**
     * 修改健康码管理
     */
    @Log(title = "健康码管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FHealthy fHealthy)
    {
        if(fHealthy.getUserName() == null || "".equals(fHealthy.getUserName())){
            fHealthy.setUserName(getUsername());
            fHealthy.setNickName(getLoginUser().getUser().getNickName());
        }

        return toAjax(fHealthyService.updateFHealthy(fHealthy));
    }

    /**
     * 删除健康码管理
     */
    @PreAuthorize("@ss.hasPermi('film:healthy:remove')")
    @Log(title = "健康码管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(fHealthyService.deleteFHealthyByIds(ids));
    }


    /**
     * 查询今天健康码上传数据
     */
    @GetMapping("/selectUploadFHealthy")
    public FHealthy selectUploadFHealthy() {
        return fHealthyService.selectUploadFHealthy(getUsername());
    }
}
