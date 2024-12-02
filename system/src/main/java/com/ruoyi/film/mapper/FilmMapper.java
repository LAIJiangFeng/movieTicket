package com.ruoyi.film.mapper;

import com.ruoyi.film.domain.Film;
import com.ruoyi.film.domain.vo.PieChartVo;
import com.ruoyi.film.domain.vo.SelectVo;

import java.util.List;

/**
 * 电影-mapper接口
 * @date 2022/12/19 13:48
 */

public interface FilmMapper {
    /**
     * 查询电影信息
     *
     * @param id 主键
     * @return 电影信息
     */
    public Film selectFilmById(Long id);

    /**
     * 查询电影信息列表
     *
     * @param film 电影信息对象
     * @return 电影信息列表
     */
    public List<Film> selectFilmList(Film film);

    /**
     * 新增电影
     *
     * @param film 电影
     * @return 新增条数
     */
    public int insertFilm(Film film);

    /**
     * 修改电影
     *
     * @param film 电影
     * @return 修改条数
     */
    public int updateFilm(Film film);

    /**
     * 删除电影
     *
     * @param id 主键
     * @return 删除条数
     */
    public int deleteFilmById(Long id);

    /**
     * 批量删除电影
     *
     * @param ids 需要删除的数据主键集合
     * @return 删除条数
     */
    public int deleteFilmByIds(Long[] ids);

    /**
     * 查询所有电影的选项
     * @return  所有电影的选项
     */
    List<SelectVo> selectAllFilmBuildOption();

    /**
     * 查询电影分类数量集
     * @return 电影分类数量集
     */
    List<PieChartVo> selectFilmTypeCount();
}
