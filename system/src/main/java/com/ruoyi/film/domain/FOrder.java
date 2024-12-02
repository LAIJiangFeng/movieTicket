package com.ruoyi.film.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.poi.hpsf.Decimal;

import java.math.BigDecimal;

/**
 * 订单管理对象 f_order
 * 
 * @author wyx
 * @date 2022-12-24
 */
public class FOrder extends Film
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 用户名 */
    @Excel(name = "用户名")
    private String userName;

    /** 用户昵称 */
    @Excel(name = "用户昵称")
    private String nickName;

    /** 影片id */
    @Excel(name = "影片id")
    private Long filmId;

    /** 购票数量 */
    @Excel(name = "购票数量")
    private Integer count;

    /** 总价 */
    @Excel(name = "总价")
    private BigDecimal totalPrice;

    /** 座位 */
    @Excel(name = "座位")
    private String seat;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setNickName(String nickName) 
    {
        this.nickName = nickName;
    }

    public String getNickName() 
    {
        return nickName;
    }
    public void setFilmId(Long filmId) 
    {
        this.filmId = filmId;
    }

    public Long getFilmId() 
    {
        return filmId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return "FOrder{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", filmId=" + filmId +
                ", count=" + count +
                ", totalPrice=" + totalPrice +
                ", seat='" + seat + '\'' +
                '}';
    }
}
