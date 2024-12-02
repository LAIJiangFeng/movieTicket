package com.ruoyi.film.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 健康码管理对象 f_healthy
 * 
 * @author wyx
 * @date 2022-12-25
 */
public class FHealthy extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String userName;

    /** 用户昵称 */
    @Excel(name = "用户昵称")
    private String nickName;

    /** 手机 */
    @Excel(name = "手机")
    private String phone;

    /** 体温状态 正常 异常 */
    @Excel(name = "体温状态")
    private String temperatureStatus;

    /** 健康码颜色:绿色、黄色、红色 */
    @Excel(name = "健康码颜色")
    private String healthCodeColor;

    /** 身体状态:无异常 有异常 */
    @Excel(name = "身体状态")
    private String bodyStatus;

    /** 接种状态:无 一针 二针 加强针 */
    @Excel(name = "接种状态")
    private String inoculationStatus;

    /** 健康码截图 */
    @Excel(name = "健康码截图")
    private String img;

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
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setImg(String img) 
    {
        this.img = img;
    }

    public String getImg() 
    {
        return img;
    }

    public String getTemperatureStatus() {
        return temperatureStatus;
    }

    public void setTemperatureStatus(String temperatureStatus) {
        this.temperatureStatus = temperatureStatus;
    }

    public String getHealthCodeColor() {
        return healthCodeColor;
    }

    public void setHealthCodeColor(String healthCodeColor) {
        this.healthCodeColor = healthCodeColor;
    }

    public String getBodyStatus() {
        return bodyStatus;
    }

    public void setBodyStatus(String bodyStatus) {
        this.bodyStatus = bodyStatus;
    }

    public String getInoculationStatus() {
        return inoculationStatus;
    }

    public void setInoculationStatus(String inoculationStatus) {
        this.inoculationStatus = inoculationStatus;
    }

    @Override
    public String toString() {
        return "FHealthy{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", phone='" + phone + '\'' +
                ", temperatureStatus='" + temperatureStatus + '\'' +
                ", healthCodeColor='" + healthCodeColor + '\'' +
                ", bodyStatus='" + bodyStatus + '\'' +
                ", inoculationStatus='" + inoculationStatus + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
