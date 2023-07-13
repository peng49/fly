import request from '@/utils/request'

export function queryComment(query) {
  console.log(query)
  return request({
    url: 'post-comments',
    method: 'get',
    params: query
  })
}

export function deleteComment(comment) {
  return request.delete('post-comments/' + comment.id)
}

export function getComment(id) {
  return request({ url: 'post-comment/' + id, method: 'get' })
}
