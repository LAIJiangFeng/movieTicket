package com.ruoyi.film.mapper;

import java.math.BigDecimal;
import java.util.List;
import com.ruoyi.film.domain.FOrder;
import com.ruoyi.film.domain.vo.OrderCountVo;

/**
 * 订单管理Mapper接口
 * 
 * @author ruoyi
 * @date 2022-12-24
 */
public interface FOrderMapper 
{
    /**
     * 查询订单管理
     * 
     * @param id 订单管理主键
     * @return 订单管理
     */
    public FOrder selectFOrderById(Long id);

    /**
     * 查询订单管理列表
     * 
     * @param fOrder 订单管理
     * @return 订单管理集合
     */
    public List<FOrder> selectFOrderList(FOrder fOrder);

    /**
     * 新增订单管理
     * 
     * @param fOrder 订单管理
     * @return 结果
     */
    public int insertFOrder(FOrder fOrder);

    /**
     * 修改订单管理
     * 
     * @param fOrder 订单管理
     * @return 结果
     */
    public int updateFOrder(FOrder fOrder);

    /**
     * 删除订单管理
     * 
     * @param id 订单管理主键
     * @return 结果
     */
    public int deleteFOrderById(Long id);

    /**
     * 批量删除订单管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFOrderByIds(Long[] ids);

    /**
     * 查询当天电影订单座位
     * @param filmId 电影id
     * @return 订单数据集
     */
    public List<FOrder> selectOrderSeat(Long filmId);

    /**
     * 查询订单数量和盈利
     * @return 订单数量和盈利
     */
    public OrderCountVo selectOrderTotal();

    /**
     * 查询近day天盈利
     * @param day
     * @return 盈利
     */
    public BigDecimal selectOderTotalPriceByDay(Integer day);
}
