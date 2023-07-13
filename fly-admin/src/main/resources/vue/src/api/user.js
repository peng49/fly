import request from '@/utils/request'

export function addUser(body) {
  return request.post('users', body)
}

export function getUser(id) {
  return request.get('users/' + id)
}

export function editUser(user) {
  return request.put('users/' + user.id, user)
}

export function queryUser(query) {
  return request.get('users', { params: query })
}

export function getInfo(token) {
  return request({
    url: 'http://localhost:9528/dev-api/vue-admin-template/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: 'http://localhost:9528/dev-api/vue-admin-template/user/logout',
    method: 'post'
  })
}
