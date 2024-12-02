package com.ruoyi.web.controller.film;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.film.domain.vo.OrderCountVo;
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
import com.ruoyi.film.domain.FOrder;
import com.ruoyi.film.service.IFOrderService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 订单管理Controller
 * 
 * @author ruoyi
 * @date 2022-12-24
 */
@RestController
@RequestMapping("/film/order")
public class FOrderController extends BaseController
{
    @Autowired
    private IFOrderService fOrderService;

    /**
     * 查询订单管理列表
     */
    @GetMapping("/list")
    public TableDataInfo list(FOrder fOrder)
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
            fOrder.setUserName(getUsername());
        }
        List<FOrder> list = fOrderService.selectFOrderList(fOrder);
        return getDataTable(list);
    }

    /**
     * 导出订单管理列表
     */
    @PreAuthorize("@ss.hasPermi('film:order:export')")
    @Log(title = "订单管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FOrder fOrder)
    {
        List<FOrder> list = fOrderService.selectFOrderList(fOrder);
        ExcelUtil<FOrder> util = new ExcelUtil<FOrder>(FOrder.class);
        util.exportExcel(response, list, "订单管理数据");
    }

    /**
     * 获取订单管理详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(fOrderService.selectFOrderById(id));
    }

    /**
     * 新增订单管理
     */
    @Log(title = "订单管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FOrder fOrder)
    {
        if(fOrder.getUserName() == null || "".equals(fOrder.getUserName()) ){
            fOrder.setUserName(getUsername());
            fOrder.setNickName(getLoginUser().getUser().getNickName());
        }
        return toAjax(fOrderService.insertFOrder(fOrder));
    }

    /**
     * 修改订单管理
     */
    @Log(title = "订单管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FOrder fOrder)
    {
        return toAjax(fOrderService.updateFOrder(fOrder));
    }

    /**
     * 删除订单管理
     */
    @Log(title = "订单管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(fOrderService.deleteFOrderByIds(ids));
    }

    /**
     *查询座位
     */
    @GetMapping(value = "getSeat/{filmId}")
    public List<JSONObject> getSeat(@PathVariable Long filmId) {
        List<FOrder> list = fOrderService.selectOrderSeat(filmId);
        HashSet<String> selectSeat = new HashSet<>();
        for (FOrder f:list){
            String[] split = f.getSeat().split(",");
            selectSeat.addAll(Arrays.asList(split));
        }

        ArrayList<JSONObject> seatList = new ArrayList<>();
        //总70个座位
        for (int i=1;i<=70;i++){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("number",i);
            jsonObject.put("select",false);
            jsonObject.put("disabled",!selectSeat.add(i+""));
            seatList.add(jsonObject);
        }
        return seatList;
    }

    /**
     * 查询订单总数和盈利
     */
    @GetMapping(value = "/getOrderTotal")
    public OrderCountVo getOrderTotal() {
        return fOrderService.selectOrderTotal();
    }

    /**
     * 获取近7天盈利
     */
    @GetMapping(value = "/getOderTotalPrice7Day")
    public List<BigDecimal> getOderTotalPrice7Day() {
        ArrayList<BigDecimal> list = new ArrayList<>();
        for(int i=1;i<=7;i++){
            BigDecimal decimal = fOrderService.selectOderTotalPriceByDay(i);
            list.add(0,decimal);
        }
        return list;
    }
}
