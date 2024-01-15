import request from '@/utils/request'

// 查询学生证书列表
export function listCertificate(query) {
  return request({
    url: '/edu/certificate/list',
    method: 'get',
    params: query
  })
}

// 查询学生证书详细
export function getCertificate(certificateId) {
  return request({
    url: '/edu/certificate/' + certificateId,
    method: 'get'
  })
}

// 新增学生证书
export function addCertificate(data) {
  return request({
    url: '/edu/certificate',
    method: 'post',
    data: data
  })
}

// 修改学生证书
export function updateCertificate(data) {
  return request({
    url: '/edu/certificate',
    method: 'put',
    data: data
  })
}

// 删除学生证书
export function delCertificate(certificateId) {
  return request({
    url: '/edu/certificate/' + certificateId,
    method: 'delete'
  })
}

// 导出学生证书
export function exportCertificate(query) {
  return request({
    url: '/edu/certificate/export',
    method: 'get',
    params: query
  })
}