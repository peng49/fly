import request from '@/utils/request'

export function queryNavigation(query) {
  return request({ url: 'navigations', method: 'get', params: query })
}

export function getNavigation(id) {
  return request.get('navigations/' + id)
}

export function addNavigation(params) {
  return request.post('navigations', params)
}

export function editNavigation(params) {
  return request.put('navigations/' + params.id, params)
}

export function deleteNavigation(params) {
  return request.delete('navigations/' + params.id)
}
