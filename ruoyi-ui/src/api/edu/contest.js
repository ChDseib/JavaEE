import request from '@/utils/request'

// 查询竞赛管理列表
export function listContest(query) {
  return request({
    url: '/edu/contest/list',
    method: 'get',
    params: query
  })
}

// 查询竞赛管理详细
export function getContest(contestId) {
  return request({
    url: '/edu/contest/' + contestId,
    method: 'get'
  })
}

// 新增竞赛管理
export function addContest(data) {
  return request({
    url: '/edu/contest',
    method: 'post',
    data: data
  })
}

// 修改竞赛管理
export function updateContest(data) {
  return request({
    url: '/edu/contest',
    method: 'put',
    data: data
  })
}

// 删除竞赛管理
export function delContest(contestId) {
  return request({
    url: '/edu/contest/' + contestId,
    method: 'delete'
  })
}

// 导出竞赛管理
export function exportContest(query) {
  return request({
    url: '/edu/contest/export',
    method: 'get',
    params: query
  })
}