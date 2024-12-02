package com.ruoyi.film.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 评论管理对象 f_comment
 * 
 * @author wyx
 * @date 2022-12-25
 */
public class FComment extends BaseEntity
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

    /** 电影id */
    @Excel(name = "电影id")
    private Long filmId;

    /** 评论内容 */
    @Excel(name = "评论内容")
    private String content;

    /** 评分 */
    @Excel(name = "评分")
    private BigDecimal score;


    /** 电影名称 */
    @Excel(name = "电影名称")
    private String name;

    /** 英文名称 */
    @Excel(name = "英文名称")
    private String eName;

    /** 电影类型(科幻、喜剧、爱情等) */
    @Excel(name = "电影类型")
    private String type;

    /** 导演 */
    @Excel(name = "导演")
    private String director;

    /** 电影图片 */
    private String vImg;

    /** 电影海报图 */
    private String poster;

    /** 电影宣传图 */
    private String pImg;

    /** 明星演员 */
    @Excel(name = "明星演员")
    private String star;

    /** 版本(3D 2D) */
    @Excel(name = "版本")
    private String version;

    /** 描述 */
    @Excel(name = "描述")
    private String describe;

    /** 想看人数 */
    private Integer pNumber;

    /** 片源 (大陆、欧美、其他) */
    @Excel(name = "片源")
    private String src;

    /** 语言 */
    @Excel(name = "语言")
    private String language;

    /** 时长 */
    @Excel(name = "时长")
    private Integer duration;

    /** 是否上映 */
    private Integer released;

    /** 上映时间 */
    @Excel(name = "上映时间")
    private String releaseDate;

    /** 价格 */
    private String price;

    /** 影片状态 1待映 2预售 3上映 */
    private Integer status;

    /** 头像 */
    private String avatar;

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
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setScore(BigDecimal score) 
    {
        this.score = score;
    }

    public BigDecimal getScore()
    {
        return score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getvImg() {
        return vImg;
    }

    public void setvImg(String vImg) {
        this.vImg = vImg;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getpImg() {
        return pImg;
    }

    public void setpImg(String pImg) {
        this.pImg = pImg;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Integer getpNumber() {
        return pNumber;
    }

    public void setpNumber(Integer pNumber) {
        this.pNumber = pNumber;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getReleased() {
        return released;
    }

    public void setReleased(Integer released) {
        this.released = released;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userName", getUserName())
            .append("nickName", getNickName())
            .append("filmId", getFilmId())
            .append("content", getContent())
            .append("score", getScore())
            .append("createTime", getCreateTime())
            .toString();
    }
}
