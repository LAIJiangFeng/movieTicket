package com.ruoyi.web.controller.film;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
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
import com.ruoyi.film.domain.FComment;
import com.ruoyi.film.service.IFCommentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 评论管理Controller
 * 
 * @author wyx
 * @date 2022-12-25
 */
@RestController
@RequestMapping("/film/comment")
public class FCommentController extends BaseController
{
    @Autowired
    private IFCommentService fCommentService;

    /**
     * 查询评论管理列表
     */
    @GetMapping("/list")
    public TableDataInfo list(FComment fComment)
    {
        startPage();
        List<FComment> list = fCommentService.selectFCommentList(fComment);
        return getDataTable(list);
    }

    /**
     * 导出评论管理列表
     */
    @Log(title = "评论管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FComment fComment)
    {
        List<FComment> list = fCommentService.selectFCommentList(fComment);
        ExcelUtil<FComment> util = new ExcelUtil<FComment>(FComment.class);
        util.exportExcel(response, list, "评论管理数据");
    }

    /**
     * 获取评论管理详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(fCommentService.selectFCommentById(id));
    }

    /**
     * 新增评论管理
     */
    @Log(title = "评论管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FComment fComment)
    {
        if(fComment.getUserName() == null || "".equals(fComment.getUserName())){
            fComment.setUserName(getUsername());
            fComment.setNickName(getLoginUser().getUser().getNickName());
        }
        return toAjax(fCommentService.insertFComment(fComment));
    }

    /**
     * 修改评论管理
     */
    @Log(title = "评论管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FComment fComment)
    {
        if(fComment.getUserName() == null || "".equals(fComment.getUserName())){
            fComment.setUserName(getUsername());
            fComment.setNickName(getLoginUser().getUser().getNickName());
        }
        return toAjax(fCommentService.updateFComment(fComment));
    }

    /**
     * 删除评论管理
     */
    @Log(title = "评论管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(fCommentService.deleteFCommentByIds(ids));
    }
}
