import request from '@/utils/request'

export function queryColumn(query) {
  return request({ url: 'columns', method: 'get', params: query })
}

export function getColumn(id) {
  return request.get('columns/' + id)
}

export function addColumn(params) {
  return request.post('columns', params)
}

export function editColumn(params) {
  return request.put('columns/' + params.id, params)
}

export function deleteColumn(params) {
  return request.delete('columns/' + params.id)
}
