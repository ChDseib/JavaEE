import request from '@/utils/request'

// 查询出版教材列表
export function listTextbook(query) {
  return request({
    url: '/edu/textbook/list',
    method: 'get',
    params: query
  })
}

// 查询出版教材详细
export function getTextbook(textbookId) {
  return request({
    url: '/edu/textbook/' + textbookId,
    method: 'get'
  })
}

// 新增出版教材
export function addTextbook(data) {
  return request({
    url: '/edu/textbook',
    method: 'post',
    data: data
  })
}

// 修改出版教材
export function updateTextbook(data) {
  return request({
    url: '/edu/textbook',
    method: 'put',
    data: data
  })
}

// 删除出版教材
export function delTextbook(textbookId) {
  return request({
    url: '/edu/textbook/' + textbookId,
    method: 'delete'
  })
}

// 导出出版教材
export function exportTextbook(query) {
  return request({
    url: '/edu/textbook/export',
    method: 'get',
    params: query
  })
}