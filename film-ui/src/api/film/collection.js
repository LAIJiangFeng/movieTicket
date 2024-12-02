import request from '@/utils/request'

// 查询收藏列表
export function listCollection(query) {
  return request({
    url: '/film/collection/list',
    method: 'get',
    params: query
  })
}

// 查询收藏详细
export function getCollection(id) {
  return request({
    url: '/film/collection/' + id,
    method: 'get'
  })
}

// 新增收藏
export function addCollection(data) {
  return request({
    url: '/film/collection',
    method: 'post',
    data: data
  })
}

// 修改收藏
export function updateCollection(data) {
  return request({
    url: '/film/collection',
    method: 'put',
    data: data
  })
}

// 删除收藏
export function delCollection(id) {
  return request({
    url: '/film/collection/' + id,
    method: 'delete'
  })
}
