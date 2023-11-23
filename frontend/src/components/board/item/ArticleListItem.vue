<script setup>
defineProps({ article: Object })
</script>

<template>
  <tr class="text-center">
    <th scope="row">{{ article.articleNo }}</th>
    <td scope="row">
      <span v-if="article.star == 5"
        ><img
          src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Travel%20and%20places/Star.png"
          alt="Star"
          width="20"
          height="20" /><img
          src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Travel%20and%20places/Star.png"
          alt="Star"
          width="20"
          height="20" /><img
          src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Travel%20and%20places/Star.png"
          alt="Star"
          width="20"
          height="20" /><img
          src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Travel%20and%20places/Star.png"
          alt="Star"
          width="20"
          height="20" /><img
          src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Travel%20and%20places/Star.png"
          alt="Star"
          width="20"
          height="20"
      /></span>
      <span v-else-if="article.star == 4"
        ><img
          src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Travel%20and%20places/Star.png"
          alt="Star"
          width="20"
          height="20" /><img
          src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Travel%20and%20places/Star.png"
          alt="Star"
          width="20"
          height="20" /><img
          src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Travel%20and%20places/Star.png"
          alt="Star"
          width="20"
          height="20" /><img
          src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Travel%20and%20places/Star.png"
          alt="Star"
          width="20"
          height="20"
      /></span>
      <span v-else-if="article.star == 3"
        ><img
          src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Travel%20and%20places/Star.png"
          alt="Star"
          width="20"
          height="20" /><img
          src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Travel%20and%20places/Star.png"
          alt="Star"
          width="20"
          height="20" /><img
          src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Travel%20and%20places/Star.png"
          alt="Star"
          width="20"
          height="20"
      /></span>
      <span v-else-if="article.star == 2"
        ><img
          src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Travel%20and%20places/Star.png"
          alt="Star"
          width="20"
          height="20" /><img
          src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Travel%20and%20places/Star.png"
          alt="Star"
          width="20"
          height="20"
      /></span>
      <span v-else-if="article.star == 1"
        ><img
          src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Travel%20and%20places/Star.png"
          alt="Star"
          width="20"
          height="20"
      /></span>
      <span v-else></span>
    </td>
    <td class="text-start">
      <router-link
        :to="{ name: 'view', params: { articleNo: article.articleNo } }"
        class="article-title link-dark"
      >
        {{ article.subject }}
      </router-link>
    </td>
    <td>{{ article.userId }}</td>
    <td>{{ article.hit }}</td>
  </tr>
</template>

<style scoped>
a {
  text-decoration: none;
}
</style>
