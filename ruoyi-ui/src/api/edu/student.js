import request from '@/utils/request'

// 查询学生列表
export function listStudent(query) {
  return request({
    url: '/edu/student/list',
    method: 'get',
    params: query
  })
}

// 查询学生详细
export function getStudent(studentId) {
  return request({
    url: '/edu/student/' + studentId,
    method: 'get'
  })
}

// 新增学生
export function addStudent(data) {
  return request({
    url: '/edu/student',
    method: 'post',
    data: data
  })
}

// 修改学生
export function updateStudent(data) {
  return request({
    url: '/edu/student',
    method: 'put',
    data: data
  })
}

// 删除学生
export function delStudent(studentId) {
  return request({
    url: '/edu/student/' + studentId,
    method: 'delete'
  })
}
// 下载用户导入模板
export function importTemplateStu() {
  return request({
    url: '/edu/student/importTemplate',
    method: 'get'
  })
}
// 导出学生
export function exportStudent(query) {
  return request({
    url: '/edu/student/export',
    method: 'get',
    params: query
  })
}
