<script setup>
import { useMenuStore } from '@/stores/menu'
import { storeToRefs } from 'pinia'

const menuStore = useMenuStore()

// 반응형을 유지하면서 스토어에서 속성을 추출하려면, storeToRefs()를 사용
// https://pinia.vuejs.kr/core-concepts/
const { menuList } = storeToRefs(menuStore)
const { changeMenuState } = menuStore

const logout = () => {
  console.log('로그아웃!!!!')
  changeMenuState()
}
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
          <template v-for="menu in menuList" :key="menu.routeName">
            <template v-if="menu.show">
              <template v-if="menu.routeName === 'user-logout'">
                <li class="nav-item">
                  <router-link to="/" @click.prevent="logout" class="nav-link">
                  {{
                    menu.name
                  }}</router-link>
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
          </template>
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
