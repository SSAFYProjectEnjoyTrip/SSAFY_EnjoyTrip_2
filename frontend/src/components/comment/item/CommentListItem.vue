<script setup>
import { ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { deleteComment } from '@/api/comment'
import CommentFormItem from './CommentFormItem.vue'

// const { comment } = defineProps({ comment: Object })
const props = defineProps({ comment: Object })
const router = useRouter()
const route = useRoute()
const isUseId = ref(true)

function editComment() {
  console.log(props.commentNo)
  isUseId.value = false
}

function removeComment() {
  // console.log('삭제할 Comment : ' + props.comment)
  console.log('삭제할 Comment : ' + props.comment.commentNo)
  const comment = {
    commentNo: props.comment.commentNo,
    articleNo: props.comment.articleNo,
    commentContent: props.comment.commentContent,
    registerTime: props.comment.registerTime,
    userId: props.comment.userId
  }

  console.log('제발 .. ' + comment)
  deleteComment(comment, (response) => {
    let msg = '댓글 삭제 시 문제 발생했습니다.'
    if (response.status == 200) msg = '댓글 삭제가 완료되었습니다.'
    alert(msg)
    location.reload()
  })
}
</script>

<template>
  <CommentFormItem v-show="!isUseId" type="modify" :commentNo="comment.commentNo"></CommentFormItem>
  <div class="comment-container" v-show="isUseId">
    <div class="comment">
      <div class="comment-author">{{ comment.userId }}</div>
      <div class="comment-date">{{ comment.registerTime }}</div>
      <div class="comment-content">{{ comment.commentContent }}</div>
    </div>
    <tr>
      <td colspan="2" class="text-center">
        <button @click="editComment" v-if="isUseId">수정</button>
        <button @click="removeComment" v-if="isUseId">삭제</button>
      </td>
    </tr>
  </div>
</template>

<style scoped>
body {
  font-family: 'Arial', sans-serif;
  background-color: #f4f4f4;
  margin: 0;
  padding: 0;
}

.comment-container {
  max-width: 600px;
  margin: 20px auto;
  background-color: #fff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.comment {
  margin-bottom: 15px;
}

.comment-author {
  font-weight: bold;
  color: #333;
}

.comment-date {
  color: #777;
  font-size: 0.8em;
}

.comment-content {
  margin-top: 10px;
  color: #555;
}
</style>
