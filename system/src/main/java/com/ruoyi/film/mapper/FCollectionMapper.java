package com.ruoyi.film.mapper;

import java.util.List;
import com.ruoyi.film.domain.FCollection;

/**
 * 收藏Mapper接口
 * 
 * @author wyx
 * @date 2022-12-24
 */
public interface FCollectionMapper 
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
     * 删除收藏
     * 
     * @param id 收藏主键
     * @return 结果
     */
    public int deleteFCollectionById(Long id);

    /**
     * 批量删除收藏
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFCollectionByIds(Long[] ids);

    /**
     * 删除收藏
     *
     * @param fCollection username and filmId
     * @return 结果
     */
    public int deleteFCollectionByFilmIdAndUserName(FCollection fCollection);
}
