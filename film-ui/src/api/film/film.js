import request from '@/utils/request'

// 查询电影列表
export function filmList(query) {
  return request({
    url: '/film/film/list',
    method: 'get',
    params: query
  })
}

// 查询电影信息详细
export function getFilm(id) {
  return request({
    url: '/film/film/' + id,
    method: 'get'
  })
}

// 新增电影
export function addFilm(data) {
  return request({
    url: '/film/film',
    method: 'post',
    data: data
  })
}

// 修改电影信息
export function uodateFilm(data) {
  return request({
    url: '/film/film',
    method: 'put',
    data: data
  })
}


// 删除电影信息
export function deleteFilm(id) {
  return request({
    url: '/film/film/' + id,
    method: 'delete'
  })
}


// 查询所有电影信选项
export function getFilmBuildOption() {
  return request({
    url: '/film/film/selectAllFilmBuildOption',
    method: 'get'
  })
}


// 查询电影分类数据集
export function getFilmTypeCount() {
  return request({
    url: '/film/film/getFilmTypeCount',
    method: 'get'
  })
}