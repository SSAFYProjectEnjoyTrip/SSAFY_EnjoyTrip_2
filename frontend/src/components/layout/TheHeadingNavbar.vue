<script setup>
import { ref, onMounted } from 'vue'
import { useMenuStore } from '@/stores/menu'
import { useMemberStore } from '@/stores/member'
import { storeToRefs } from 'pinia'

const menuStore = useMenuStore()
const memberStore = useMemberStore()

// 반응형을 유지하면서 스토어에서 속성을 추출하려면, storeToRefs()를 사용
// https://pinia.vuejs.kr/core-concepts/
const { menuList } = storeToRefs(menuStore)
const { userLogout } = memberStore
const { userInfo } = storeToRefs(memberStore)

const logout = () => {
  userLogout(userInfo.userId)
  // Clear session storage items
  sessionStorage.removeItem('accessToken')
  sessionStorage.removeItem('refreshToken')
  sessionStorage.removeItem('loginUser')

  console.log('로그아웃!!!!')
}

const loginUser = ref(null)

function checkSession() {
  const userSession = sessionStorage.getItem('loginUser') // or localStorage
  // console.log(sessionStorage.getItem('loginUser'))
  // console.log(userSession)
  // console.log('loginUser 이거슨 그냥: ', loginUser)
  loginUser.value = userSession ? JSON.parse(userSession) : null
}

onMounted(() => {
  checkSession()
})
</script>

<template>
  <!-- 상단 navbar start -->
  <nav class="navbar navbar-expand-lg navbar-light bg-white shadow fixed-top">
    <div class="container-fluid">
      <router-link :to="{ name: 'main' }" class="navbar-brand">
        <img src="@/assets/et.png" alt="" width="100" />
      </router-link>
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarScroll"
        aria-controls="navbarScroll"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarScroll">
        <ul
          class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll"
          style="--bs-scroll-height: 100px"
        >
          <li class="nav-item">
            <a class="nav-link" href="/notice"><b>공지사항</b></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/estations"><b>전기차충전소</b></a>
          </li>
        </ul>
        <ul
          class="navbar-nav ms-auto my-2 my-lg-0 navbar-nav-scroll"
          style="--bs-scroll-height: 100px"
        >
          <li class="nav-item">
            <a class="nav-link" href="/hotplaceburger"><b>버거버거</b></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/hotplace"><b>계획세우기</b></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/board/list"><b>여행리뷰✨</b></a>
          </li>
          <li class="nav-item" v-if="loginUser">
            <a class="nav-link" href="/user/mypage"><b>마이페이지</b></a>
          </li>
          <li class="nav-item" v-if="loginUser">
            <!-- <a class="nav-link" href="/user/logout" @click.prevent="logout()"><b>로그아웃</b></a> -->
            <router-link to="/" @click.prevent="logout()" class="nav-link"
              ><b>로그아웃</b></router-link
            >
          </li>
          <li class="nav-item" v-if="!loginUser">
            <a class="nav-link" href="/user/join"><b>회원가입✍🏻</b></a>
          </li>
          <li class="nav-item" v-if="!loginUser">
            <a class="nav-link" href="/user/login"><b>로그인</b></a>
          </li>

          <!-- <template v-for="menu in menuList" :key="menu.routeName">
            <template v-if="menu.show">
              <template v-if="menu.routeName === 'user-logout'">
                <li class="nav-item">
                  <router-link to="/" @click.prevent="logout()" class="nav-link">
                    {{ menu.name }}</router-link
                  >
                </li>
              </template>
              <template v-else>
                <li class="nav-item">
                  <router-link :to="{ name: menu.routeName }" class="nav-link">{{
                    menu.name
                  }}</router-link>
                </li>
              </template>
            </template>
          </template> -->
        </ul>
      </div>
    </div>
  </nav>
  <!-- 상단 navbar end -->
  <div class="navbar-bottom"></div>
</template>

<style scoped>
.navbar-bottom {
  height: 100px;
}
</style>
