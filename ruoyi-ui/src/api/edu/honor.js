import request from '@/utils/request'

// 查询教师奖项列表
export function listHonor(query) {
  return request({
    url: '/edu/honor/list',
    method: 'get',
    params: query
  })
}

// 查询教师奖项详细
export function getHonor(honorId) {
  return request({
    url: '/edu/honor/' + honorId,
    method: 'get'
  })
}

// 新增教师奖项
export function addHonor(data) {
  return request({
    url: '/edu/honor',
    method: 'post',
    data: data
  })
}

// 修改教师奖项
export function updateHonor(data) {
  return request({
    url: '/edu/honor',
    method: 'put',
    data: data
  })
}

// 删除教师奖项
export function delHonor(honorId) {
  return request({
    url: '/edu/honor/' + honorId,
    method: 'delete'
  })
}

// 导出教师奖项
export function exportHonor(query) {
  return request({
    url: '/edu/honor/export',
    method: 'get',
    params: query
  })
}