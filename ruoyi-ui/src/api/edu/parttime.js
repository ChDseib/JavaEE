import request from '@/utils/request'

// 查询社会兼职列表
export function listParttime(query) {
  return request({
    url: '/edu/parttime/list',
    method: 'get',
    params: query
  })
}

// 查询社会兼职详细
export function getParttime(parttimeId) {
  return request({
    url: '/edu/parttime/' + parttimeId,
    method: 'get'
  })
}

// 新增社会兼职
export function addParttime(data) {
  return request({
    url: '/edu/parttime',
    method: 'post',
    data: data
  })
}

// 修改社会兼职
export function updateParttime(data) {
  return request({
    url: '/edu/parttime',
    method: 'put',
    data: data
  })
}

// 删除社会兼职
export function delParttime(parttimeId) {
  return request({
    url: '/edu/parttime/' + parttimeId,
    method: 'delete'
  })
}

// 导出社会兼职
export function exportParttime(query) {
  return request({
    url: '/edu/parttime/export',
    method: 'get',
    params: query
  })
}