import request from '@/utils/request'

export function getAllMenu() {
  return request.get('auth/menus')
}

export function addMenu(params) {
  return request.post('auth/menus', params)
}

export function editMenu(params) {
  return request.put('auth/menus/' + params.id, params)
}

export function deleteMenu(params) {
  return request.delete('auth/menus/' + params.id)
}
