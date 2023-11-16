<script setup>
import { ref, watch, onMounted } from 'vue'
import { storeToRefs } from 'pinia'
import { useRouter } from 'vue-router'
import { useMemberStore } from '@/stores/member'
import { registUser, updateUser } from '@/api/user'

const router = useRouter()

const props = defineProps({ type: String })

const memberStore = useMemberStore()

const user = ref({
  userId: '',
  userName: '',
  userPwd: '',
  emailId: '',
  emailDomain: ''
})

const { userInfo, isLogin } = storeToRefs(memberStore)
const isUseId = ref(false)

onMounted(() => {
  if (props.type === 'update') {
    user.value = userInfo.value
  }
}),
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
    props.type === 'update' ? userUpdate() : userRegist()
    // }
  }

function userUpdate() {
  if (isLogin) {
    console.log('회원 수정 진입!!')
    updateUser(user.value, (response) => {
      let msg = '회원 수정 시 문제 발생'
      if (response.status == 200) msg = '회원 수정 완료'
      alert(msg)
      moveDetail()
    })
  } else {
    ;(error) => console.log(error)
  }
}

function userRegist() {
  console.log('회원 등록 진입!!')
  registUser(
    user.value,
    (response) => {
      let msg = '회원 등록 처리시 문제 발생했습니다.'
      if (response.status == 200) msg = '회원 등록이 완료되었습니다.'
      alert(msg)
      moveMain()
    },
    (error) => console.log(error)
  )
}

function moveMain() {
  router.push({ name: 'main' })
}

function moveDetail() {
  router.push({ name: 'user-mypage' })
}
</script>

<template>
  <form @submit.prevent="onSubmit">
    <div class="mb-3">
      <label for="userid" class="form-label">작성자 ID : </label>
      <input
        type="text"
        class="form-control"
        v-model="user.userId"
        :disabled="isUseId"
        placeholder="작성자ID..."
      />
    </div>
    <div class="mb-3">
      <label for="userName" class="form-label">이름 : </label>
      <input type="text" class="form-control" v-model="user.userName" />
    </div>
    <!-- <div class="mb-3">
      <label for="userId" class="form-label">아이디 : </label>
      <input type="text" class="form-control" v-model="userInfo.userId" placeholder="아이디..." />
    </div> -->
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
      <button
        type="submit"
        class="btn btn-outline-primary mb-3"
        v-if="type === 'register'"
        @click="userRegist"
      >
        회원가입
      </button>
      <button type="submit" class="btn btn-outline-success mb-3" v-else @click="userUpdate">
        회원 정보 수정
      </button>
      <button type="button" class="btn btn-outline-success ms-1 mb-3" @click="clear">초기화</button>
    </div>
  </form>
</template>

<style scoped></style>
