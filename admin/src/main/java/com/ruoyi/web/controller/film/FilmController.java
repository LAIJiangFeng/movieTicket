package com.ruoyi.web.controller.film;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.film.domain.FCollection;
import com.ruoyi.film.domain.Film;
import com.ruoyi.film.domain.vo.PieChartVo;
import com.ruoyi.film.domain.vo.SelectVo;
import com.ruoyi.film.mapper.FilmMapper;
import com.ruoyi.film.service.FilmService;
import com.ruoyi.film.service.IFCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

/**
 * 电影信息-controller
 * @date 2022/12/19 14:00
 */
@RestController
@RequestMapping("/film/film")
public class FilmController extends BaseController {

    @Autowired
    private FilmService filmService;

    @Autowired
    private IFCollectionService ifCollectionService;

    /**
     * 查询电影信息列表
     */
    @PreAuthorize("@ss.hasPermi('film:film:list')")
    @GetMapping("/list")
    public TableDataInfo list(Film film)
    {
        //获取所有收藏
        FCollection fCollection = new FCollection();
        fCollection.setUserName(getUsername());
        List<FCollection> collectionList = ifCollectionService.selectFCollectionList(fCollection);
        HashSet<Long> set = new HashSet<>();
        for (FCollection collection:collectionList){
            set.add(collection.getFilmId());
        }

        startPage();
        List<Film> list = filmService.selectFilmList(film);
        for(Film f:list){
            //判断是否收藏
            boolean b = set.add(f.getId());
            if(b){
                f.setCollect(false);
            }else {
                f.setCollect(true);
            }
        }
        return getDataTable(list);
    }

    /**
     * 导出电影信息列表
     */
    @PreAuthorize("@ss.hasPermi('film:film:list')")
    @Log(title = "电影信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response,Film film)
    {
        List<Film> list = filmService.selectFilmList(film);
        ExcelUtil<Film> util = new ExcelUtil<Film>(Film.class);
        util.exportExcel(response, list, "电影信息数据");
    }

    /**
     * 获取电影详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        //查询是否收藏
        FCollection fCollection = new FCollection();
        fCollection.setUserName(getUsername());
        fCollection.setFilmId(id);
        List<FCollection> collectionList = ifCollectionService.selectFCollectionList(fCollection);
        Film film = filmService.selectFilmById(id);
        if(collectionList.size()>0){
            film.setCollect(true);
        }else {
            film.setCollect(false);
        }
        return AjaxResult.success(film);
    }

    /**
     * 新增电影信息
     */
    @PreAuthorize("@ss.hasPermi('film:film:add')")
    @Log(title = "电影信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Film film)
    {
        return toAjax(filmService.insertFilm(film));
    }

    /**
     * 修改抄电影信息
     */
    @PreAuthorize("@ss.hasPermi('film:film:edit')")
    @Log(title = "电影信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Film film)
    {
        return toAjax(filmService.updateFilm(film));
    }

    /**
     * 删除电影信息
     */
    @PreAuthorize("@ss.hasPermi('film:film:remove')")
    @Log(title = "电影信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(filmService.deleteFilmByIds(ids));
    }


    /**
     * 获取所有电影的选项
     */
    @GetMapping("/selectAllFilmBuildOption")
    public List<SelectVo> selectAllFilmBuildOption()
    {
        return filmService.selectAllFilmBuildOption();
    }


    /**
     * 获取所有电影
     */
    @GetMapping("/selectFilmIndex")
    public JSONObject selectFilmIndex(Film film)
    {
        //获取所有收藏
        FCollection fCollection = new FCollection();
        fCollection.setUserName(getUsername());
        List<FCollection> collectionList = ifCollectionService.selectFCollectionList(fCollection);
        HashSet<Long> set = new HashSet<>();
        for (FCollection collection:collectionList){
            set.add(collection.getFilmId());
        }

        JSONObject data = new JSONObject();
        ArrayList<Film> shownList = new ArrayList<>();
        ArrayList<Film> notShownList = new ArrayList<>();
        //轮播展示图
        ArrayList<Film> swiperImgList = new ArrayList<>();
        //猜你喜欢
        ArrayList<Film> likeList = new ArrayList<>();
        //轮播图7张
        int count=7;
        //猜你喜欢5部
        int like=5;
        List<Film> films = filmService.selectFilmList(film);
        for (Film f:films){
            //1已上映 0未上映
            if(f.getReleased()==1){
                shownList.add(f);
                //猜你喜欢
                Random random = new Random();
                if(random.nextInt(10)>4 && like>0){
                    likeList.add(f);
                    like--;
                }
            }else {
                //判断是否收藏
                boolean b = set.add(f.getId());
                if(b){
                   f.setCollect(false);
                }else {
                    f.setCollect(true);
                }
                notShownList.add(f);
            }
            //轮播图添加
            if(count>0){
                swiperImgList.add(f);
                count--;
            }
        }
        data.put("shownList",shownList);
        data.put("notShownList",notShownList);
        data.put("swiperImgList",swiperImgList);
        data.put("likeList",likeList);
        return data;
    }


    /**
     * 获取电影分类数据集
     */
    @GetMapping("/getFilmTypeCount")
    public List<PieChartVo> getFilmTypeCount() {
        return filmService.selectFilmTypeCount();
    }
}
