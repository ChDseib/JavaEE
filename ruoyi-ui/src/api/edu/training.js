import request from '@/utils/request'

// 查询教师培训列表
export function listTraining(query) {
  return request({
    url: '/edu/training/list',
    method: 'get',
    params: query
  })
}

// 查询教师培训详细
export function getTraining(trainingId) {
  return request({
    url: '/edu/training/' + trainingId,
    method: 'get'
  })
}

// 新增教师培训
export function addTraining(data) {
  return request({
    url: '/edu/training',
    method: 'post',
    data: data
  })
}

// 修改教师培训
export function updateTraining(data) {
  return request({
    url: '/edu/training',
    method: 'put',
    data: data
  })
}

// 删除教师培训
export function delTraining(trainingId) {
  return request({
    url: '/edu/training/' + trainingId,
    method: 'delete'
  })
}

// 导出教师培训
export function exportTraining(query) {
  return request({
    url: '/edu/training/export',
    method: 'get',
    params: query
  })
}