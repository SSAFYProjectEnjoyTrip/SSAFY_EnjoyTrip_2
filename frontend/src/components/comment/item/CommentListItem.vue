<script setup>
const comment = defineProps({ comment: Object })
console.log("여기까지 들어오기는 하는건가 : ", comment.value)
</script>

<!-- <script>
export default {
  props: {
    comment: Object
  }
}
</script> -->

<template>
  <tr class="text-center">
    <th scope="row">{{ comment.commentNo }}</th>
    <td class="text-start">{{ comment.commentContent }}</td>
    <td>{{ comment.userId }}</td>
    <td>{{ comment.registerTime }}</td>
  </tr>
</template>

<style scoped>
a {
  text-decoration: none;
}
</style>
