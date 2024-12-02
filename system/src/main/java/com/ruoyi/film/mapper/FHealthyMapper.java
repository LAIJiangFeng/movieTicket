package com.ruoyi.film.mapper;

import java.util.List;
import com.ruoyi.film.domain.FHealthy;

/**
 * 健康码管理Mapper接口
 * 
 * @author wyx
 * @date 2022-12-25
 */
public interface FHealthyMapper 
{
    /**
     * 查询健康码管理
     * 
     * @param id 健康码管理主键
     * @return 健康码管理
     */
    public FHealthy selectFHealthyById(Long id);

    /**
     * 查询健康码管理列表
     * 
     * @param fHealthy 健康码管理
     * @return 健康码管理集合
     */
    public List<FHealthy> selectFHealthyList(FHealthy fHealthy);

    /**
     * 新增健康码管理
     * 
     * @param fHealthy 健康码管理
     * @return 结果
     */
    public int insertFHealthy(FHealthy fHealthy);

    /**
     * 修改健康码管理
     * 
     * @param fHealthy 健康码管理
     * @return 结果
     */
    public int updateFHealthy(FHealthy fHealthy);

    /**
     * 删除健康码管理
     * 
     * @param id 健康码管理主键
     * @return 结果
     */
    public int deleteFHealthyById(Long id);

    /**
     * 批量删除健康码管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFHealthyByIds(Long[] ids);

    /**
     * 查询健康码在今天上传数据
     * @param userName 用户名
     * @return 健康码信息
     */
    public FHealthy selectUploadFHealthy(String userName);
}
