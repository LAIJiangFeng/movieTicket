package com.ruoyi.web.controller.film;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.entity.SysRole;
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
import com.ruoyi.film.domain.FCollection;
import com.ruoyi.film.service.IFCollectionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 收藏Controller
 * 
 * @author wyx
 * @date 2022-12-24
 */
@RestController
@RequestMapping("/film/collection")
public class FCollectionController extends BaseController
{
    @Autowired
    private IFCollectionService fCollectionService;

    /**
     * 查询收藏列表
     */
    @GetMapping("/list")
    public TableDataInfo list(FCollection fCollection)
    {
        //判断是否是管理员
        boolean admin=false;
        List<SysRole> roles = getLoginUser().getUser().getRoles();
        for(SysRole role:roles){
            if(role.getRoleId()==1)
                admin=true;
        }
        startPage();
        if(!admin){
            fCollection.setUserName(getUsername());
        }
        List<FCollection> list = fCollectionService.selectFCollectionList(fCollection);
        return getDataTable(list);
    }

    /**
     * 导出收藏列表
     */
    @Log(title = "收藏", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FCollection fCollection)
    {
        List<FCollection> list = fCollectionService.selectFCollectionList(fCollection);
        ExcelUtil<FCollection> util = new ExcelUtil<FCollection>(FCollection.class);
        util.exportExcel(response, list, "收藏数据");
    }

    /**
     * 获取收藏详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(fCollectionService.selectFCollectionById(id));
    }

    /**
     * 新增收藏
     */
    @Log(title = "收藏", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FCollection fCollection)
    {
        if(fCollection.getUserName() == null || "".equals(fCollection.getUserName())){
            fCollection.setUserName(getUsername());
            fCollection.setNickName(getLoginUser().getUser().getNickName());
        }
        return toAjax(fCollectionService.insertFCollection(fCollection));
    }

    /**
     * 修改收藏
     */
    @Log(title = "收藏", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FCollection fCollection)
    {
        if(fCollection.getUserName() == null || "".equals(fCollection.getUserName())){
            fCollection.setUserName(getUsername());
            fCollection.setNickName(getLoginUser().getUser().getNickName());
        }
        return toAjax(fCollectionService.updateFCollection(fCollection));
    }

    /**
     * 删除收藏
     */
    @Log(title = "收藏", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(fCollectionService.deleteFCollectionByIds(ids));
    }


    /**
     * 删除收藏通过电影id
     */
    @Log(title = "收藏", businessType = BusinessType.DELETE)
    @DeleteMapping("removeFCollectionByFilmIdAndUserName")
    public AjaxResult removeFCollectionByFilmIdAndUserName(@RequestBody FCollection fCollection)
    {
        if(fCollection.getUserName() == null || "".equals(fCollection.getUserName())){
            fCollection.setUserName(getUsername());
        }
        return toAjax(fCollectionService.deleteFCollectionByFilmIdAndUserName(fCollection));
    }

}
