<script setup>
import { ref, watch } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { registUser } from '@/api/user'

//TODO : 수정, 삭제 추가 필ㅛ

const router = useRouter()
const route = useRoute()

const props = defineProps({ type: String })

const isUseId = ref(false)

const user = ref({
  userId: '',
  userName: '',
  userPwd: '',
  emailId: '',
  emailDomain: '',
  joinDate: '',
  token: ''
})

// if (props.type === 'modify') {
//   let { articleNo } = route.params
//   console.log(articleNo + '번글 얻어와서 수정할거야')
//   detailArticle(
//     articleNo,
//     ({ data }) => {
//       article.value = data
//       isUseId.value = true
//       console.log(article.value)
//     },
//     (error) => {
//       console.log(error)
//     }
//   )
//   isUseId.value = true
// }

// const nameErrMsg = ref('')
// const pwdErrMsg = ref('')
// watch(
//   () => user.value.userName,
//   (value) => {
//     let len = value.length
//     if (len == 0 || len > 30) {
//       nameErrMsg.value = '아이디를 확인해 주세요!!!'
//     } else nameErrMsg.value = ''
//   },
//   { immediate: true }
// )
// watch(
//   () => user.value.userPwd,
//   (value) => {
//     let len = value.length
//     if (len == 0 || len > 500) {
//       pwdErrMsg.value = '비밀번호를 확인해 주세요!!!'
//     } else pwdErrMsg.value = ''
//   },
//   { immediate: true }
// )

function onSubmit() {
  // event.preventDefault();

  // if (nameErrMsg.value) {
  //   alert(nameErrMsg.value)
  // } else if (pwdErrMsg.value) {
  //   alert(pwdErrMsg.value)
  // } else {
  props.type === 'regist' ? userRegister() : userUpdate()
  // }
}

function userRegister() {
  console.log('회원 등록!!', user.value)
  registUser(
    user.value,
    (response) => {
      let msg = '회원 등록 처리시 문제 발생했습니다.'
      if (response.status == 200) msg = '회원 등록이 완료되었습니다.'
      alert(msg)
      moveList()
    },
    (error) => console.log(error)
  )
}

function userUpdate() {
//   console.log(article.value.articleNo + '번글 수정하자!!', article.value)
//   modifyArticle(
//     article.value,
//     (response) => {
//       let msg = '글수정 처리시 문제 발생했습니다.'
//       if (response.status == 200) msg = '글정보 수정이 완료되었습니다.'
//       alert(msg)
//       moveList()
//     },
//     (error) => console.log(error)
//   )
}

//이거 이름 싹 다 바꿔줘야하나? board-list 형식으로?
function moveList() {
  router.push({ name: 'list' })
}
</script>

<template>
  <form @submit.prevent="onSubmit">
    <!-- <div class="mb-3">
      <label for="userid" class="form-label">작성자 ID : </label>
      <input
        type="text"
        class="form-control"
        v-model="article.userId"
        :disabled="isUseId"
        placeholder="작성자ID..."
      />
    </div> -->
    <div class="mb-3">
            <label for="userName" class="form-label">이름 : </label>
            <input type="text" class="form-control" v-model="user.userName" placeholder="이름..." />
          </div>
    <div class="mb-3">
            <label for="userId" class="form-label">아이디 : </label>
            <input type="text" class="form-control" v-model="user.userId" placeholder="아이디..." />
    </div>
    <div class="mb-3">
            <label for="userPwd" class="form-label">비밀번호 : </label>
            <input type="text" class="form-control" v-model="user.userPwd" placeholder="비밀번호..." />
    </div>
    <!-- <div class="mb-3">
            <label for="pwdCheck" class="form-label">비밀번호확인 : </label>
            <input type="text" class="form-control" v-model="user.userPwd" id="pwdCheck" placeholder="비밀번호확인..." />
    </div> -->
    <div class="mb-3">
            <label for="emailid" class="form-label">이메일 : </label>
            <div class="input-group">
              <input type="text" class="form-control" v-model="user.emailId" placeholder="이메일아이디" />
              <span class="input-group-text">@</span>
              <select class="form-select" v-model="user.emailDomain" aria-label="이메일 도메인 선택">
                <option selected>선택</option>
                <option value="ssafy.com">싸피</option>
                <option value="google.com">구글</option>
                <option value="naver.com">네이버</option>
                <option value="kakao.com">카카오</option>
              </select>
            </div>
    </div>
    <div class="col-auto text-center">
            <button type="submit" class="btn btn-outline-primary mb-3" v-if="type === 'regist'">회원가입</button>
            <button type="submit" class="btn btn-outline-success mb-3" v-else>회원 정보 수정</button>
            <button type="button" class="btn btn-outline-success ms-1 mb-3" @click="moveList" >초기화</button>
    </div>
  </form>
</template>

<style scoped></style>
