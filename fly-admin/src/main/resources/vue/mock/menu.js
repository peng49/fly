const Mock = require('mockjs')

const data = Mock.mock({
  'menus|30': [{
    path: 'test-path@integer(300, 5000)',
    name: '@sentence(10, 20)',
    meta: {},
    component: () => import('@/views/nested/menu2/index')
  }]
})

module.exports = [
  {
    url: '/vue-admin-template/menus',
    type: 'get',
    response: config => {
      const menus = data.menus
      return {
        code: 20000,
        data: {
          total: menus.length,
          items: menus
        }
      }
    }
  }
]
