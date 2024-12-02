import request from '@/utils/request'

// 查询健康码管理列表
export function listHealthy(query) {
  return request({
    url: '/film/healthy/list',
    method: 'get',
    params: query
  })
}

// 查询健康码管理详细
export function getHealthy(id) {
  return request({
    url: '/film/healthy/' + id,
    method: 'get'
  })
}

// 新增健康码管理
export function addHealthy(data) {
  return request({
    url: '/film/healthy',
    method: 'post',
    data: data
  })
}

// 修改健康码管理
export function updateHealthy(data) {
  return request({
    url: '/film/healthy',
    method: 'put',
    data: data
  })
}

// 删除健康码管理
export function delHealthy(id) {
  return request({
    url: '/film/healthy/' + id,
    method: 'delete'
  })
}
