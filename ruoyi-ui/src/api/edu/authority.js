import request from '@/utils/request'

// 查询认证机构列表
export function listAuthority(query) {
  return request({
    url: '/edu/authority/list',
    method: 'get',
    params: query
  })
}

// 查询认证机构详细
export function getAuthority(authorityId) {
  return request({
    url: '/edu/authority/' + authorityId,
    method: 'get'
  })
}

// 新增认证机构
export function addAuthority(data) {
  return request({
    url: '/edu/authority',
    method: 'post',
    data: data
  })
}

// 修改认证机构
export function updateAuthority(data) {
  return request({
    url: '/edu/authority',
    method: 'put',
    data: data
  })
}

// 删除认证机构
export function delAuthority(authorityId) {
  return request({
    url: '/edu/authority/' + authorityId,
    method: 'delete'
  })
}

// 导出认证机构
export function exportAuthority(query) {
  return request({
    url: '/edu/authority/export',
    method: 'get',
    params: query
  })
}