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
    if (response.status == 200) {
      msg = '댓글 삭제가 완료되었습니다.'
      alert(msg)
      window.location.reload()
    }
  })
}
// // 로그인한사람있으면 확인
const loginUser = JSON.parse(sessionStorage.getItem('loginUser'))

const isSameUser = props.comment.userId === loginUser
</script>

<template>
  <CommentFormItem v-show="!isUseId" type="modify" :commentNo="comment.commentNo"></CommentFormItem>
  <div class="comment-container" v-show="isUseId">
    <div class="comment">
      <div class="comment-author">{{ comment.userId }}</div>
      <div class="comment-date">{{ comment.registerTime }}</div>
      <div class="comment-content">{{ comment.commentContent }}</div>
    </div>
    <tr v-if="isSameUser">
      <td colspan="2" class="text-center">
        <button class="submit-button" @click="editComment" v-if="isUseId">수정</button>
        <button class="submit-button" @click="removeComment" v-if="isUseId">삭제</button>
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
  width: 100%;
  max-width: 800px;
  margin: 20px auto;
  background-color: #fff;
  padding: 20px;
  border-radius: 20px;
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

.submit-button {
  border: 2px solid #eef5ff;
  border-radius: 10px;
  background-color: #9ad0c2;
  font-weight: bold;
  width: 80px;
  height: 35px;
}
.submit-button:hover {
  background-color: #f2ffe9;
}
</style>
