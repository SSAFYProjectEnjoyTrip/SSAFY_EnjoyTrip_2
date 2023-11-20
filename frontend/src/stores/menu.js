import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useMenuStore = defineStore('menuStore', () => {
  const menuList = ref([
    //TODO : 여행계획 루트 경로 수정 필요
    //TODO : 로그인 이후 내정보랑 로그아웃만 뜨는거 수정 필요
    // { name: '공지사항💡', show: true, routeName: 'noticeList' },
    { name: '계획세우기🚗', show: true, routeName: 'hotplace' },
    // { name: '여행계획🎈', show: true, routeName: 'myplan' },
    { name: '여행리뷰✨', show: true, routeName: 'list' },
    // { name: '전기차충전소⚡', show: true, routeName: 'estations' },
    { name: '회원가입✍🏻', show: true, routeName: 'user-join' },
    { name: '로그인', show: true, routeName: 'user-login' },
    { name: '내정보', show: false, routeName: 'user-mypage' },
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
