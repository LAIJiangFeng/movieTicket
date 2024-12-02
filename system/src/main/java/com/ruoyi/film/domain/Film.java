package com.ruoyi.film.domain;


import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;


/**
 * 电影对象
 * @date 2022/12/19 10:43
 */
public class Film extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id ;

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

    /** 评分 */
    @Excel(name = "评分")
    private Double score;

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

    private String price;

    /** 影片状态 1待映 2预售 3上映 */
    private Integer status;

    /** 是否收藏 */
    private Boolean collect;


    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String getvImg() {
        return vImg;
    }

    public void setvImg(String vImg) {
        this.vImg = vImg;
    }

    public Integer getpNumber() {
        return pNumber;
    }

    public void setpNumber(Integer pNumber) {
        this.pNumber = pNumber;
    }

    public String getpImg() {
        return pImg;
    }

    public void setpImg(String pImg) {
        this.pImg = pImg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
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

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Boolean getCollect() {
        return collect;
    }

    public void setCollect(Boolean collect) {
        this.collect = collect;
    }
}
