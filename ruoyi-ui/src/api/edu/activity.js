import request from '@/utils/request'

// 查询社会活动列表
export function listActivity(query) {
  return request({
    url: '/edu/activity/list',
    method: 'get',
    params: query
  })
}

// 查询社会活动详细
export function getActivity(activityId) {
  return request({
    url: '/edu/activity/' + activityId,
    method: 'get'
  })
}

// 新增社会活动
export function addActivity(data) {
  return request({
    url: '/edu/activity',
    method: 'post',
    data: data
  })
}

// 修改社会活动
export function updateActivity(data) {
  return request({
    url: '/edu/activity',
    method: 'put',
    data: data
  })
}

// 删除社会活动
export function delActivity(activityId) {
  return request({
    url: '/edu/activity/' + activityId,
    method: 'delete'
  })
}

// 导出社会活动
export function exportActivity(query) {
  return request({
    url: '/edu/activity/export',
    method: 'get',
    params: query
  })
}