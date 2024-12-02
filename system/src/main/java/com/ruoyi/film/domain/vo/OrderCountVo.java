package com.ruoyi.film.domain.vo;

import java.math.BigDecimal;

/**
 * @date 2023/1/8 15:55
 */
public class OrderCountVo {
    /** 订单数量 */
    private Integer count;

    /** 订单金额总计 */
    private BigDecimal priceTotal;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(BigDecimal priceTotal) {
        this.priceTotal = priceTotal;
    }
}
