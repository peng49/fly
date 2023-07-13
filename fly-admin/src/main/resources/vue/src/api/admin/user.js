import request from '@/utils/request'

export function login(params) {
  const form = new FormData()
  form.append('username', params.username)
  form.append('password', params.password)

  // const url = process.env.VUE_APP_BASE_API.replace(/api\/?$/, '') + 'login'
  return request.post('login', form)
}

export function getUsers() {
  return request.get('auth/users')
}

export function addUser(params) {
  return request.post('auth/users', params)
}

export function editUser(params) {
  return request.put('auth/users/' + params.id, params)
}

export function deleteUser(params) {
  return request.delete('auth/users/' + params.id)
}
