import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useMenuStore = defineStore('menuStore', () => {
  const menuList = ref([
    //TODO : 핫플찾기, 여행계획 루트 경로 수정 필요
    { name: '회원가입✍🏻', show: true, routeName: 'user-join' },
    { name: '로그인', show: true, routeName: 'user-login' },
    { name: '내정보', show: false, routeName: 'user-mypage' },
    { name: '여행계획🎈', show: true, routeName: 'main' },
    { name: '핫플찾기🚗', show: true, routeName: 'main' },
    { name: '여행후기✨', show: true, routeName: 'list' },
    { name: '공지사항💡', show: true, routeName: 'noticeList' },
    { name: '로그아웃', show: false, routeName: 'user-logout' }
  ])

  const changeMenuState = () => {
    menuList.value = menuList.value.map((item) => ({ ...item, show: !item.show }))
  }
  return {
    menuList,
    changeMenuState
  }
})
