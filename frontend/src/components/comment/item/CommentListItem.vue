<script setup>
import { useRoute, useRouter } from 'vue-router'

const { comment } = defineProps({ comment: Object })
const router = useRouter()
const route = useRoute()

//수정, 삭제 처리..
//버튼 누르면 modify 값을 가지고 commentFormItem으로 이동해서 수정??
//그럼 수정은 CommentFormITem으로 넘기자! 똑같이
//삭제는 여기서 해도 되니까
//그럼 여기서 해야할ㄱ건.. 로그인한 사용자일때만 아래 버튼들이 떠야곘네 ㅇㅋㅇㅋ
//event emit을 통해 넘기기?

moveList()

function moveList() {
  router.push({ name: 'list' })
}

function editComment(commentNo) {
  console.log(commentNo);
  // <CommentFormItem type="register"></CommentFormItem>

}

function deleteComment(commentNo) {
  console.log(commentNo);
  
}
</script>

<template>
  <div class="comment-container">
    <div class="comment" v-if="type == 'register'">
      <div class="comment-author">{{ comment.userId }}</div>
      <div class="comment-date">{{ comment.registerTime }}</div>
      <div class="comment-content">{{ comment.commentContent }}</div>
    </div>
    <tr>
      <td colspan="2" class="text-center">
        <button @click="editComment(index)">수정</button>
      <button @click="deleteComment(index)">삭제</button>
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
