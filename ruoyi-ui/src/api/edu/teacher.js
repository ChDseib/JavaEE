import request from '@/utils/request'

// 查询教师列表
export function listTeacher(query) {
  return request({
    url: '/edu/teacher/list',
    method: 'get',
    params: query
  })
}

// 查询教师详细
export function getTeacher(teacherId) {
  return request({
    url: '/edu/teacher/' + teacherId,
    method: 'get'
  })
}

// 新增教师
export function addTeacher(data) {
  return request({
    url: '/edu/teacher',
    method: 'post',
    data: data
  })
}

// 修改教师
export function updateTeacher(data) {
  return request({
    url: '/edu/teacher',
    method: 'put',
    data: data
  })
}

// 删除教师
export function delTeacher(teacherId) {
  return request({
    url: '/edu/teacher/' + teacherId,
    method: 'delete'
  })
}

// 导出教师
export function exportTeacher(query) {
  return request({
    url: '/edu/teacher/export',
    method: 'get',
    params: query
  })
}