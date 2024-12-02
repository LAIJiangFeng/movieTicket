package com.ruoyi.film.service;

import java.util.List;
import com.ruoyi.film.domain.FCollection;

/**
 * 收藏Service接口
 * 
 * @author wyx
 * @date 2022-12-24
 */
public interface IFCollectionService 
{
    /**
     * 查询收藏
     * 
     * @param id 收藏主键
     * @return 收藏
     */
    public FCollection selectFCollectionById(Long id);

    /**
     * 查询收藏列表
     * 
     * @param fCollection 收藏
     * @return 收藏集合
     */
    public List<FCollection> selectFCollectionList(FCollection fCollection);

    /**
     * 新增收藏
     * 
     * @param fCollection 收藏
     * @return 结果
     */
    public int insertFCollection(FCollection fCollection);

    /**
     * 修改收藏
     * 
     * @param fCollection 收藏
     * @return 结果
     */
    public int updateFCollection(FCollection fCollection);

    /**
     * 批量删除收藏
     * 
     * @param ids 需要删除的收藏主键集合
     * @return 结果
     */
    public int deleteFCollectionByIds(Long[] ids);

    /**
     * 删除收藏信息
     * 
     * @param id 收藏主键
     * @return 结果
     */
    public int deleteFCollectionById(Long id);


    /**
     * 删除收藏
     *
     * @param fCollection username and filmId
     * @return 结果
     */
    public int deleteFCollectionByFilmIdAndUserName(FCollection fCollection);
}
