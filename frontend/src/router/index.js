import { createRouter, createWebHistory } from 'vue-router'
import TheHomeView from '@/views/TheHomeView.vue'
import TheDraggableView from '@/views/TheDraggableView.vue'
import TheHotplaceView from '@/views/TheHotplaceView.vue'
import TheHotplaceBurgerView from '@/views/TheHotplaceBurgerView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/', // 처음 실행 시 보여줄 main
      name: 'main',
      component: TheHomeView
    },
    {
      path: '/hotplace',
      name: 'hotplace',
      component: TheHotplaceView
    },
    {
      path: '/hotplaceburger',
      name: 'hotplaceburger',
      // beforeEnter: onlyAuthUser,
      component: TheHotplaceBurgerView
    },
    {
      path: '/estations',
      name: 'estations',
      // beforeEnter: onlyAuthUser,
      component: TheDraggableView
    },
    {
      path: '/user',
      name: 'user',
      component: () => import('@/views/TheUserView.vue'),
      children: [
        {
          path: 'login',
          name: 'user-login',
          component: () => import('@/components/users/UserLogin.vue')
        },
        {
          path: 'join',
          name: 'user-join',
          component: () => import('@/components/users/UserRegister.vue')
        },
        {
          path: 'mypage',
          name: 'user-mypage',
          component: () => import('@/components/users/UserMyPage.vue')
        },
        {
          path: 'modify',
          name: 'user-modify',
          component: () => import('@/components/users/UserModify.vue')
        }
      ]
    },
    {
      path: '/board',
      name: 'board',
      component: () => import('@/views/TheBoardView.vue'),
      redirect: { name: 'list' },
      children: [
        {
          path: 'list', // children에서는 '/'가 자동으로 붙기 때문에
          name: 'list',
          component: () => import('@/components/board/ArticleList.vue')
        },
        {
          path: 'view/:articleNo?', // children에서는 '/'가 자동으로 붙기 때문에
          name: 'view',
          component: () => import('@/components/board/ArticleDetail.vue')
        },
        {
          path: 'modify/:articleNo?', // children에서는 '/'가 자동으로 붙기 때문에
          name: 'modify',
          component: () => import('@/components/board/ArticleModify.vue')
        },
        {
          path: 'regist', // children에서는 '/'가 자동으로 붙기 때문에
          name: 'regist',
          component: () => import('@/components/board/ArticleRegist.vue')
        }
      ]
    },
    // Notice 공지사항 게시판 ▼▼▼▼▼▼▼▼▼▼▼▼
    {
      path: '/notice',
      name: 'notice',
      component: () => import('@/views/TheNoticeView.vue'),
      redirect: { name: 'noticeList' },
      children: [
        {
          path: 'noticeList', // children에서는 '/'가 자동으로 붙기 때문에
          name: 'noticeList',
          component: () => import('@/components/notice/NoticeList.vue')
        },
        {
          path: 'detail/:articleNo?',
          name: 'noticeDetail',
          component: () => import('@/components/notice/NoticeDetail.vue')
        },
        {
          path: 'modify/:articleNo?',
          name: 'noticeModify',
          component: () => import('@/components/notice/NoticeModify.vue')
        },
        {
          path: 'regist',
          name: 'noticeRegist',
          component: () => import('@/components/notice/NoticeRegist.vue')
        }
      ]
    }
  ]
})

export default router
