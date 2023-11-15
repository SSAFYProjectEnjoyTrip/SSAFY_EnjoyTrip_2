<script setup>
import { RouterView } from 'vue-router'
import TheHeadingNavBar from '@/components/layout/TheHeadingNavbar.vue'
</script>

<template>
  <div>
    <TheHeadingNavBar></TheHeadingNavBar>
    <router-view></router-view>
  </div>
</template>

<style scoped></style>
