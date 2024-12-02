package com.ruoyi.film.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.film.mapper.FCollectionMapper;
import com.ruoyi.film.domain.FCollection;
import com.ruoyi.film.service.IFCollectionService;

/**
 * 收藏Service业务层处理
 * 
 * @author wyx
 * @date 2022-12-24
 */
@Service
public class FCollectionServiceImpl implements IFCollectionService 
{
    @Autowired
    private FCollectionMapper fCollectionMapper;

    /**
     * 查询收藏
     * 
     * @param id 收藏主键
     * @return 收藏
     */
    @Override
    public FCollection selectFCollectionById(Long id)
    {
        return fCollectionMapper.selectFCollectionById(id);
    }

    /**
     * 查询收藏列表
     * 
     * @param fCollection 收藏
     * @return 收藏
     */
    @Override
    public List<FCollection> selectFCollectionList(FCollection fCollection)
    {
        return fCollectionMapper.selectFCollectionList(fCollection);
    }

    /**
     * 新增收藏
     * 
     * @param fCollection 收藏
     * @return 结果
     */
    @Override
    public int insertFCollection(FCollection fCollection)
    {
        return fCollectionMapper.insertFCollection(fCollection);
    }

    /**
     * 修改收藏
     * 
     * @param fCollection 收藏
     * @return 结果
     */
    @Override
    public int updateFCollection(FCollection fCollection)
    {
        return fCollectionMapper.updateFCollection(fCollection);
    }

    /**
     * 批量删除收藏
     * 
     * @param ids 需要删除的收藏主键
     * @return 结果
     */
    @Override
    public int deleteFCollectionByIds(Long[] ids)
    {
        return fCollectionMapper.deleteFCollectionByIds(ids);
    }

    /**
     * 删除收藏信息
     * 
     * @param id 收藏主键
     * @return 结果
     */
    @Override
    public int deleteFCollectionById(Long id)
    {
        return fCollectionMapper.deleteFCollectionById(id);
    }


    /**
     * 删除收藏
     *
     * @param fCollection username and filmId
     * @return 结果
     */
    @Override
    public int deleteFCollectionByFilmIdAndUserName(FCollection fCollection) {
        return fCollectionMapper.deleteFCollectionByFilmIdAndUserName(fCollection);
    }


}
