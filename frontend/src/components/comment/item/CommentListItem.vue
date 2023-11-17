<!-- <script setup>
defineProps({ comment: Object })

</script> -->

<script>
export default {
  props: {
    comment: Object
  }
}
</script>

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
