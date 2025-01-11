import request from '@/utils/request'

export function getPermissions() {
  return request.get('auth/permissions')
}

export function addPermission(params) {
  return request.post('auth/permissions', params)
}

export function editPermission(params) {
  return request.put('auth/permissions/' + params.id, params)
}

export function deletePermission(params) {
  return request.delete('auth/permissions/' + params.id)
}
