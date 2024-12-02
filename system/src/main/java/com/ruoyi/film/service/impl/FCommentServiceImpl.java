package com.ruoyi.film.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.film.mapper.FCommentMapper;
import com.ruoyi.film.domain.FComment;
import com.ruoyi.film.service.IFCommentService;

/**
 * 评论管理Service业务层处理
 * 
 * @author wyx
 * @date 2022-12-25
 */
@Service
public class FCommentServiceImpl implements IFCommentService 
{
    @Autowired
    private FCommentMapper fCommentMapper;

    /**
     * 查询评论管理
     * 
     * @param id 评论管理主键
     * @return 评论管理
     */
    @Override
    public FComment selectFCommentById(Long id)
    {
        return fCommentMapper.selectFCommentById(id);
    }

    /**
     * 查询评论管理列表
     * 
     * @param fComment 评论管理
     * @return 评论管理
     */
    @Override
    public List<FComment> selectFCommentList(FComment fComment)
    {
        return fCommentMapper.selectFCommentList(fComment);
    }

    /**
     * 新增评论管理
     * 
     * @param fComment 评论管理
     * @return 结果
     */
    @Override
    public int insertFComment(FComment fComment)
    {
        fComment.setCreateTime(DateUtils.getNowDate());
        return fCommentMapper.insertFComment(fComment);
    }

    /**
     * 修改评论管理
     * 
     * @param fComment 评论管理
     * @return 结果
     */
    @Override
    public int updateFComment(FComment fComment)
    {
        return fCommentMapper.updateFComment(fComment);
    }

    /**
     * 批量删除评论管理
     * 
     * @param ids 需要删除的评论管理主键
     * @return 结果
     */
    @Override
    public int deleteFCommentByIds(Long[] ids)
    {
        return fCommentMapper.deleteFCommentByIds(ids);
    }

    /**
     * 删除评论管理信息
     * 
     * @param id 评论管理主键
     * @return 结果
     */
    @Override
    public int deleteFCommentById(Long id)
    {
        return fCommentMapper.deleteFCommentById(id);
    }
}
