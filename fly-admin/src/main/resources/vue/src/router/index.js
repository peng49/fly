import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '首页', icon: 'dashboard' }
    }]
  },
  {
    path: '/users',
    component: Layout,
    meta: { title: '用户管理', icon: 'peoples' },
    children: [
      {
        path: '',
        name: 'Users',
        component: () => import('@/views/user/index'),
        meta: { title: '用户管理' }
      },
      {
        path: 'view/:id',
        name: 'user.view',
        component: () => import('@/views/user/view'),
        hidden: true
      },
      {
        path: 'oauth',
        name: 'Oauth',
        component: () => import('@/views/user/oauth'),
        meta: { title: '第三方账户管理' }
      }
    ]
  },
  {
    path: '/posts',
    component: Layout,
    meta: { title: '内容管理', icon: 'education' },
    children: [
      {
        path: 'navigation',
        name: 'Navigation',
        component: () => import('@/views/theme/navigation'),
        meta: { title: '导航管理' }
      },
      {
        path: 'column',
        name: 'Column',
        component: () => import('@/views/theme/column'),
        meta: { title: '栏目管理' }
      },
      {
        path: 'index',
        name: 'Themes',
        component: () => import('@/views/theme/index'),
        meta: { title: '文章管理' }
      },
      {
        path: 'create',
        name: 'post.create',
        component: () => import('@/views/theme/create'),
        hidden: true
      },
      {
        path: 'edit/:id',
        name: 'post.edit',
        component: () => import('@/views/theme/edit'),
        hidden: true
      },
      {
        path: 'comments',
        name: 'Comment',
        component: () => import('@/views/theme/comment'),
        meta: { title: '评论管理' }
      }
    ]
  },
  {
    path: '/system',
    component: Layout,
    meta: { title: '系统管理', icon: 'el-icon-s-tools' },
    alwaysShow: true,
    children: [
      {
        path: 'system-config',
        name: 'system.system-config',
        component: () => import('@/views/system/config'),
        meta: { title: '系统设置' }
      }, {
        path: 'friend-link',
        name: 'system.friend-link',
        component: () => import('@/views/system/friendLink'),
        meta: { title: '友链管理' }
      }
    ]
  },
  {
    path: '/admin/auth',
    component: Layout,
    meta: { title: '后台管理', icon: 'tree' },
    children: [
      {
        path: 'menus',
        name: 'Menus',
        component: () => import('@/views/auth/menu/index'),
        meta: { title: '菜单管理' }
      },
      {
        path: 'users',
        name: 'Users',
        component: () => import('@/views/auth/user/index'),
        meta: { title: '用户管理' }
      },
      {
        path: 'roles',
        name: 'Roles',
        component: () => import('@/views/auth/role/index'),
        meta: { title: '角色管理' }
      },
      {
        path: 'permissions',
        name: 'Permissions',
        component: () => import('@/views/auth/permission/index'),
        meta: { title: '权限管理' }
      }
    ]
  }
]

const leftMenus = sessionStorage.getItem('LEFT_MENUS')
const allMenu = JSON.parse(leftMenus)
console.log(allMenu)

// const hasChild = (menu) => {
//   for (const i in allMenu) {
//     if (allMenu[i].parentId === menu.id) {
//       return true
//     }
//   }
//   return false
// }

// const getChildren = (parent, result) => {
//   for (const i in allMenu) {
//     const menu = allMenu[i]
//     if (menu.parentId === parent.id) {
//       if (menu.component === null || menu.component === '') {
//         continue
//       }

//       const route = {
//         path: menu.uri,
//         name: menu.title,
//         component: () => import('@/views/auth/menu/index'),
//         meta: { title: menu.title, icon: menu.icon }
//       }
//       if (hasChild(menu)) {
//         console.log('hasChild', menu)
//         route.children = getChildren(menu, [])
//       }
//       result.push(route)
//     }
//   }
//   return result
// }

// allMenu.forEach(menu => {
//   if (menu.parentId === 0) {
//     let route = {
//       path: menu.uri,
//       component: Layout,
//       name: menu.title + 'parent',
//       meta: { title: menu.title, icon: 'el-icon-s-help' },
//     }
//     if (hasChild(menu)) {
//       route.children = getChildren(menu, [])
//     }
//     constantRoutes.push(route)
//   }
// })

console.log(constantRoutes)

console.log('route debug')

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
