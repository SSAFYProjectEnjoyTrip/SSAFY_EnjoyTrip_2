<!-- CommentList.vue -->
<script setup>
import { ref, onBeforeMount, onMounted } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import CommentListItem from './item/CommentListItem.vue'
import CommentFormItem from './item/CommentFormItem.vue'
import { listComment } from '@/api/comment'

const props = defineProps(['postId'])
const commentList = ref({})

console.log('CommentList.setup......')
onMounted(() => {
  searchList()
  console.log(commentList.value)
})

function searchList() {
  console.log('commentSearch params............: ', props.postId)

  listComment(props.postId)
    .then((result) => {
      // 성공적으로 댓글을 받아왔을 때의 처리
      console.log('댓글 목록:', result.data)
      commentList.value = result.data
    })
    .catch((error) => {
      // 댓글을 받아오는 중 에러가 발생했을 때의 처리
      console.error('댓글 목록을 받아오는 중 에러 발생:', error)
    })
}

//댓글 화면만 갱신하고 싶은데..
</script>

<template>
  <div>
    <div class="re">댓글</div>
    <hr />
    <CommentListItem v-for="comment in commentList" :key="comment.commentNo" :comment="comment" />
  </div>
  <div>
    <hr />
    <CommentFormItem type="regist"></CommentFormItem>
  </div>
</template>

<style>
.re {
  color: gray;
  font-weight: bold;
  text-align: left;
  margin-top: 40px;
}
</style>
