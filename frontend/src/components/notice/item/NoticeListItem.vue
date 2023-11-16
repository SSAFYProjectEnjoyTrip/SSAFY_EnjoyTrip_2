<script setup>
defineProps({ notice: Object })
</script>

<template>
  <tr class="text-center">
    <th scope="row">{{ notice.articleNo }}</th>
    <td class="text-start">
      <router-link
        :to="{ name: 'noticeDetail', params: { articleNo: notice.articleNo } }"
        class="article-title link-dark"
      >
        {{ notice.subject }}
      </router-link>
    </td>
    <td>{{ notice.userId }}</td>
    <td>{{ notice.hit }}</td>
  </tr>
</template>

<style scoped>
a {
  text-decoration: none;
}
</style>
