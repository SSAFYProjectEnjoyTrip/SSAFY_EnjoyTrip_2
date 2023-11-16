<script setup></script>

<template>
  <nav class="navbar navbar-expand-lg bg-body-tertiary sticky-top">
    <div class="container-fluid">
      <router-link class="navbar-brand" :to="{ name: 'main' }">
        <!-- <img src="@/assets/ssafy_logo.png" class="rounded mx-auto d-block" alt="..." /> -->
        <!-- <img src="@/assets/qq.png" alt="" width="60" /> -->
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
            <a class="nav-link" href="#">핫플찾기🚗</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">여행계획🎈</a>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" :to="{ name: 'list' }">여행후기✨</router-link>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">회원가입✍🏻</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">로그인</a>
          </li>
          <li class="nav-item dropdown">
            <a
              class="nav-link dropdown-toggle"
              href="#"
              role="button"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              HELP DESK
            </a>
            <ul class="dropdown-menu">
              <li>
                <!-- <a class="dropdown-item" href="#">공지사항</a> -->
                <router-link class="dropdown-item" :to="{ name: 'noticeList' }"
                  >공지사항💡</router-link
                >
              </li>
              <li><a class="dropdown-item" href="#">FAQ</a></li>
              <li><hr class="dropdown-divider" /></li>
            </ul>
          </li>
        </ul>
        <!-- <form class="d-flex" role="search">
          <input
            class="form-control me-2"
            type="search"
            placeholder="검색..."
            aria-label="Search"
          />
          <button class="btn btn-outline-success" type="button">search</button>
        </form> -->
      </div>
    </div>
  </nav>
</template>

<style scoped></style>
