import request from '@/utils/request'

export function addPost(body) {
  return request.post('posts', body)
}

export function getPost(id) {
  return request.get('posts/' + id)
}

export function updatePost(post) {
  return request.put('posts/' + post.id, post)
}

export function queryPosts(query) {
  console.log(query)
  return request({
    url: 'posts',
    method: 'get',
    params: query
  })
}
