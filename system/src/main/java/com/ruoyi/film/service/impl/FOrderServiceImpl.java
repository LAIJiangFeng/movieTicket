package com.ruoyi.film.service.impl;

import java.math.BigDecimal;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.film.domain.vo.OrderCountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.film.mapper.FOrderMapper;
import com.ruoyi.film.domain.FOrder;
import com.ruoyi.film.service.IFOrderService;

/**
 * 订单管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-12-24
 */
@Service
public class FOrderServiceImpl implements IFOrderService 
{
    @Autowired
    private FOrderMapper fOrderMapper;

    /**
     * 查询订单管理
     * 
     * @param id 订单管理主键
     * @return 订单管理
     */
    @Override
    public FOrder selectFOrderById(Long id)
    {
        return fOrderMapper.selectFOrderById(id);
    }

    /**
     * 查询订单管理列表
     * 
     * @param fOrder 订单管理
     * @return 订单管理
     */
    @Override
    public List<FOrder> selectFOrderList(FOrder fOrder)
    {
        return fOrderMapper.selectFOrderList(fOrder);
    }

    /**
     * 新增订单管理
     * 
     * @param fOrder 订单管理
     * @return 结果
     */
    @Override
    public int insertFOrder(FOrder fOrder)
    {
        fOrder.setCreateTime(DateUtils.getNowDate());
        return fOrderMapper.insertFOrder(fOrder);
    }

    /**
     * 修改订单管理
     * 
     * @param fOrder 订单管理
     * @return 结果
     */
    @Override
    public int updateFOrder(FOrder fOrder)
    {
        return fOrderMapper.updateFOrder(fOrder);
    }

    /**
     * 批量删除订单管理
     * 
     * @param ids 需要删除的订单管理主键
     * @return 结果
     */
    @Override
    public int deleteFOrderByIds(Long[] ids)
    {
        return fOrderMapper.deleteFOrderByIds(ids);
    }

    /**
     * 删除订单管理信息
     * 
     * @param id 订单管理主键
     * @return 结果
     */
    @Override
    public int deleteFOrderById(Long id)
    {
        return fOrderMapper.deleteFOrderById(id);
    }


    /**
     * 查询当天电影订单座位
     * @param filmId 电影id
     * @return 订单数据集
     */
    @Override
    public List<FOrder> selectOrderSeat(Long filmId) {
        return fOrderMapper.selectOrderSeat(filmId);
    }

    /**
     * 查询订单数量和盈利
     * @return 订单数量和盈利
     */
    @Override
    public OrderCountVo selectOrderTotal() {
        return fOrderMapper.selectOrderTotal();
    }

    /**
     * 查询近day天盈利
     * @param day
     * @return 盈利
     */
    @Override
    public BigDecimal selectOderTotalPriceByDay(Integer day) {
        return  fOrderMapper.selectOderTotalPriceByDay(day);
    }
}
