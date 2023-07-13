import request from '@/utils/request'

export function addLink(params) {
  return request.post('friend-links', params)
}

export function deleteLink(id) {
  return request.delete('friend-links/' + id)
}

export function updateLink(params) {
  return request.put('friend-links/' + params.id, params)
}

export function queryLinks(query) {
  console.log(query)
  return request({
    url: 'friend-links',
    method: 'get',
    params: query
  })
}

export function getLink(id) {
  return request({ url: 'friend-links/' + id, method: 'get' })
}
