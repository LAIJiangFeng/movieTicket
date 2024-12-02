package com.ruoyi.film.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.film.mapper.FHealthyMapper;
import com.ruoyi.film.domain.FHealthy;
import com.ruoyi.film.service.IFHealthyService;

/**
 * 健康码管理Service业务层处理
 * 
 * @author wyx
 * @date 2022-12-25
 */
@Service
public class FHealthyServiceImpl implements IFHealthyService 
{
    @Autowired
    private FHealthyMapper fHealthyMapper;

    /**
     * 查询健康码管理
     * 
     * @param id 健康码管理主键
     * @return 健康码管理
     */
    @Override
    public FHealthy selectFHealthyById(Long id)
    {
        return fHealthyMapper.selectFHealthyById(id);
    }

    /**
     * 查询健康码管理列表
     * 
     * @param fHealthy 健康码管理
     * @return 健康码管理
     */
    @Override
    public List<FHealthy> selectFHealthyList(FHealthy fHealthy)
    {
        return fHealthyMapper.selectFHealthyList(fHealthy);
    }

    /**
     * 新增健康码管理
     * 
     * @param fHealthy 健康码管理
     * @return 结果
     */
    @Override
    public int insertFHealthy(FHealthy fHealthy)
    {
        return fHealthyMapper.insertFHealthy(fHealthy);
    }

    /**
     * 修改健康码管理
     * 
     * @param fHealthy 健康码管理
     * @return 结果
     */
    @Override
    public int updateFHealthy(FHealthy fHealthy)
    {
        return fHealthyMapper.updateFHealthy(fHealthy);
    }

    /**
     * 批量删除健康码管理
     * 
     * @param ids 需要删除的健康码管理主键
     * @return 结果
     */
    @Override
    public int deleteFHealthyByIds(Long[] ids)
    {
        return fHealthyMapper.deleteFHealthyByIds(ids);
    }

    /**
     * 删除健康码管理信息
     * 
     * @param id 健康码管理主键
     * @return 结果
     */
    @Override
    public int deleteFHealthyById(Long id)
    {
        return fHealthyMapper.deleteFHealthyById(id);
    }

    /**
     * 查询健康码在今天上传数据
     * @param userName 用户名
     * @return 健康码信息
     */
    @Override
    public FHealthy selectUploadFHealthy(String userName) {
        return fHealthyMapper.selectUploadFHealthy(userName);
    }
}
