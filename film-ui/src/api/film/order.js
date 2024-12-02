import request from '@/utils/request'

// 查询订单管理列表
export function listOrder(query) {
  return request({
    url: '/film/order/list',
    method: 'get',
    params: query
  })
}

// 查询订单管理详细
export function getOrder(id) {
  return request({
    url: '/film/order/' + id,
    method: 'get'
  })
}

// 新增订单管理
export function addOrder(data) {
  return request({
    url: '/film/order',
    method: 'post',
    data: data
  })
}

// 修改订单管理
export function updateOrder(data) {
  return request({
    url: '/film/order',
    method: 'put',
    data: data
  })
}

// 删除订单管理
export function delOrder(id) {
  return request({
    url: '/film/order/' + id,
    method: 'delete'
  })
}

// 查询订单总数和盈利
export function getOrderTotal() {
  return request({
    url: '/film/order/getOrderTotal',
    method: 'get',
  })
}


// 查询近7天盈利情况
export function getOderTotalPrice7Day() {
  return request({
    url: '/film/order/getOderTotalPrice7Day',
    method: 'get',
  })
}
