import request from '@/utils/request'

// 查询奖项列表
export function listAward(query) {
  return request({
    url: '/edu/award/list',
    method: 'get',
    params: query
  })
}

// 查询奖项详细
export function getAward(awardId) {
  return request({
    url: '/edu/award/' + awardId,
    method: 'get'
  })
}

// 新增奖项
export function addAward(data) {
  return request({
    url: '/edu/award',
    method: 'post',
    data: data
  })
}

// 修改奖项
export function updateAward(data) {
  return request({
    url: '/edu/award',
    method: 'put',
    data: data
  })
}

// 删除奖项
export function delAward(awardId) {
  return request({
    url: '/edu/award/' + awardId,
    method: 'delete'
  })
}

// 导出奖项
export function exportAward(query) {
  return request({
    url: '/edu/award/export',
    method: 'get',
    params: query
  })
}