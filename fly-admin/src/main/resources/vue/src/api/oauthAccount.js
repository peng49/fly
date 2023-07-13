import request from '@/utils/request'

export function queryAccounts(query) {
  console.log(query)
  return request({
    url: 'oauth-accounts',
    method: 'get',
    params: query
  })
}
