package com.ruoyi.film.service.impl;

import com.ruoyi.film.domain.Film;
import com.ruoyi.film.domain.vo.PieChartVo;
import com.ruoyi.film.domain.vo.SelectVo;
import com.ruoyi.film.mapper.FilmMapper;
import com.ruoyi.film.service.FilmService;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 电影信息-impl
 * @date 2022/12/19 13:55
 */
@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    private FilmMapper filmMapper;

    /**
     * 查询电影信息
     *
     * @param id 主键
     * @return 电影信息
     */
    @Override
    public Film selectFilmById(Long id) {
        return filmMapper.selectFilmById(id);
    }

    /**
     * 查询电影信息列表
     *
     * @param film 电影信息对象
     * @return 电影信息列表
     */
    @Override
    public List<Film> selectFilmList(Film film) {
        return filmMapper.selectFilmList(film);
    }

    /**
     * 新增电影
     *
     * @param film 电影
     * @return 新增条数
     */
    @Override
    public int insertFilm(Film film) {
        return filmMapper.insertFilm(film);
    }

    /**
     * 修改电影
     *
     * @param film 电影
     * @return 修改条数
     */
    @Override
    public int updateFilm(Film film) {
        return filmMapper.updateFilm(film);
    }

    /**
     * 删除电影
     *
     * @param id 主键
     * @return 删除条数
     */
    @Override
    public int deleteFilmById(Long id) {
        return filmMapper.deleteFilmById(id);
    }

    /**
     * 批量删除电影
     *
     * @param ids 需要删除的数据主键集合
     * @return 删除条数
     */
    @Override
    public int deleteFilmByIds(Long[] ids) {
        return filmMapper.deleteFilmByIds(ids);
    }

    /**
     * 查询所有电影的选项
     * @return  所有电影的选项
     */
    @Override
    public List<SelectVo> selectAllFilmBuildOption() {
        return filmMapper.selectAllFilmBuildOption();
    }

    /**
     * 查询电影分类数量集
     * @return 电影分类数量集
     */
    @Override
    public List<PieChartVo> selectFilmTypeCount() {
        return filmMapper.selectFilmTypeCount();
    }
}
