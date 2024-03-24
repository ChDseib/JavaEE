import request from '@/utils/request'
import { praseStrEmpty } from "@/utils/ruoyi";

// 查询教学成果列表
export function listAchievement(query) {
  return request({
    url: '/edu/achievement/list',
    method: 'get',
    params: query
  })
}

// 查询教学成果详细
export function getAchievement(achievementId) {
  return request({
    url: '/edu/achievement/' + praseStrEmpty(achievementId),
    method: 'get'
  })
}

// 新增教学成果
export function addAchievement(data) {
  return request({
    url: '/edu/achievement',
    method: 'post',
    data: data
  })
}

// 修改教学成果
export function updateAchievement(data) {
  return request({
    url: '/edu/achievement',
    method: 'put',
    data: data
  })
}

// 删除教学成果
export function delAchievement(achievementId) {
  return request({
    url: '/edu/achievement/' + achievementId,
    method: 'delete'
  })
}

// 导出教学成果
export function exportAchievement(query) {
  return request({
    url: '/edu/achievement/export',
    method: 'get',
    params: query
  })
}