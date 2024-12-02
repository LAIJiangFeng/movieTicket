package com.ruoyi.film.mapper;

import java.util.List;
import com.ruoyi.film.domain.FComment;

/**
 * 评论管理Mapper接口
 * 
 * @author wyx
 * @date 2022-12-25
 */
public interface FCommentMapper 
{
    /**
     * 查询评论管理
     * 
     * @param id 评论管理主键
     * @return 评论管理
     */
    public FComment selectFCommentById(Long id);

    /**
     * 查询评论管理列表
     * 
     * @param fComment 评论管理
     * @return 评论管理集合
     */
    public List<FComment> selectFCommentList(FComment fComment);

    /**
     * 新增评论管理
     * 
     * @param fComment 评论管理
     * @return 结果
     */
    public int insertFComment(FComment fComment);

    /**
     * 修改评论管理
     * 
     * @param fComment 评论管理
     * @return 结果
     */
    public int updateFComment(FComment fComment);

    /**
     * 删除评论管理
     * 
     * @param id 评论管理主键
     * @return 结果
     */
    public int deleteFCommentById(Long id);

    /**
     * 批量删除评论管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFCommentByIds(Long[] ids);
}
