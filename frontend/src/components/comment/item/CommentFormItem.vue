<script setup>
import { ref, watch, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { searchComment, modifyComment, registComment } from '@/api/comment'

const route = useRoute()

const props = defineProps({ type: String, commentNo: Number })
const { articleNo } = route.params
const isUseId = ref(false)

const comment = ref({
  articleno: '',
  commentno: '',
  userId: '',
  commentContent: '',
  registerTime: ''
})

onMounted(() => {
  if (props.type == 'modify') {
    const commentNo = props.commentNo
    // console.log(props.commentNo)

    console.log(commentNo + '번 댓글 얻어와서 수정할거야')
    searchComment(
      articleNo,
      commentNo,
      ({ data }) => {
        console.log(data)
        comment.value = data
        isUseId.value = true
        console.log('가져온 comment는 ?? ' + comment.value)
      },
      (error) => {
        console.log(error)
      }
    )
    // isUseId.value = true
  }
})

const contentErrMsg = ref('')
watch(
  () => comment.value.commentContent,
  (value) => {
    let len = value.length
    if (len == 0 || len > 500) {
      contentErrMsg.value = '내용을 확인해 주세요!!!'
    } else contentErrMsg.value = ''
  },
  { immediate: true }
)

function onSubmit() {
  // event.preventDefault();
  if (contentErrMsg.value) {
    alert(contentErrMsg.value)
  } else {
    props.type === 'regist' ? writeComment() : updateComment()
  }
}

function writeComment() {
  console.log('댓글 등록하자!!', comment.value)
  comment.value.articleNo = articleNo
  comment.value.userId = JSON.parse(sessionStorage.getItem('loginUser'))
  registComment(
    comment.value,
    (response) => {
      let msg = '댓글 등록 처리 시 문제 발생했습니다.'
      if (response.status == 200) msg = '댓글 등록이 완료되었습니다.'
      alert(msg)
      window.location.reload()
      // moveList()
    },
    (error) => console.log(error)
  )
}

function updateComment() {
  console.log(
    comment.value.articleno + '번글의 ' + comment.value.commentno + ' 번 댓글을 수정하자!!',
    comment.value
  )
  modifyComment(
    comment.value,
    (response) => {
      let msg = '댓글 수정 시 문제 발생했습니다.'
      if (response.status == 200) msg = '댓글 수정이 완료되었습니다.'
      alert(msg)
      window.location.reload()

      // moveList()
      // moveReload()
    },
    (error) => console.log(error)
  )
}

// // 로그인한사람있으면 확인
const loginUser = JSON.parse(sessionStorage.getItem('loginUser'))
function moveReload() {
  location.reload()
}
</script>

<template>
  <form @submit.prevent="onSubmit">
    <div class="comment-container" v-if="loginUser">
      <div class="comment">
        <div class="comment-author">{{ comment.userId }}</div>
        <div class="comment-date">{{ comment.registerTime }}</div>
        <div class="comment-content">
          <div class="re-author">
            <b>{{ loginUser }}</b>
          </div>
          <input type="hidden" v-model="comment.userId" />
          <input type="text" class="form-control" v-model="comment.commentContent" />
        </div>
      </div>
      <div class="btn-div">
        <button type="submit" class="submit-button" v-if="type == 'regist'">등록</button>
        <button type="submit" class="submit-button" v-else>수정</button>
      </div>
    </div>
    <div class="comment-container" v-else>
      <div>로그인해야 이용하실 수 있습니다.</div>
    </div>
  </form>
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
.re-author {
  text-align: left;
  font-size: 20px;
  margin-left: 10px;
  margin-bottom: 10px;
}
.form-control {
  height: 100px;
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
