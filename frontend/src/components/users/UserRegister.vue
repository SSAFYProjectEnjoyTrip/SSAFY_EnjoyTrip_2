<script setup>
import UserFormItem from './item/UserFormItem.vue'
</script>

<template>
  <div
    class="container"
    v-motion
    :initial="{ opacity: 0, y: 100 }"
    :enter="{ opacity: 1, y: 0, scale: 1 }"
    :variants="{ custom: { scale: 1 } }"
    :hovered="{ scale: 1.1 }"
    :delay="200"
  >
    <div class="row justify-content-center">
      <div class="col-lg-10 text-start">
        <UserFormItem type="register" />
      </div>
    </div>
  </div>
</template>

<style scoped></style>
