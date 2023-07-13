import request from '@/utils/request'

export function querySystemConfig(query) {
  return request({ url: 'system-config', method: 'get', params: query })
}

export function getSystemConfig(id) {
  return request.get('system-config/' + id)
}

export function addSystemConfig(params) {
  return request.post('system-config', params)
}

export function editSystemConfig(params) {
  return request.put('system-config/' + params.id, params)
}

export function deleteSystemConfig(id) {
  return request.delete('system-config/' + id)
}
