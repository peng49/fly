import request from '@/utils/request'

export function getRoles() {
  return request.get('auth/roles')
}

export function addRole(params) {
  return request.post('auth/roles', params)
}

export function editRole(params) {
  return request.put('auth/roles/' + params.id, params)
}

export function deleteRole(params) {
  return request.delete('auth/roles/' + params.id)
}
