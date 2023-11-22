<script setup>
import UserModifyFormItem from './item/UserModifyFormItem.vue'
</script>

<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          <mark class="sky">회원 정보 수정</mark>
        </h2>
      </div>
      <div class="col-lg-10 text-start">
        <UserModifyFormItem type="update" />
      </div>
    </div>
  </div>
</template>

<style scoped></style>
