<script setup>
import { ref, watch, onMounted } from 'vue'
import { storeToRefs } from 'pinia'
import { useRouter } from 'vue-router'
import { useMemberStore } from '@/stores/member'
import { registUser, updateUser } from '@/api/user'

const router = useRouter()

const props = defineProps({ type: String })

const memberStore = useMemberStore()

// 기본 상태를 정의하는 함수
const getDefaultState = () => ({
  userId: '',
  userName: '',
  userPwd: '',
  emailId: '',
  emailDomain: ''
});

// 폼 상태
const user = ref(getDefaultState())

// 폼을 리셋하는 함수
const clear = () => {
  user.value = getDefaultState();
};

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
    <div class="register-page">
      <div class="title">
        <img src="@/assets/et.png" class="login-logo"/>
      </div>
      <div class="form">
        <div> 
          <input id="name" type="text" class="form-control" v-model="user.userName" placeholder="name" required />
        </div>
        <input id="userId" type="text" class="form-control" v-model="user.userId" :disabled="isUseId" placeholder="id" required />
        <div id="result-view" class="mb-3"></div><!--아이디 사용가능한지 보여주는 곳-->
        <div>
          <input type="password" id="password" class="form-control" v-model="user.userPwd" placeholder="password" required />
        </div>
        <input type="password" id="pwdcheck" class="form-control" placeholder="password check" />
        <div class="mb-3">
          <div class="input-group">
            <input type="text" class="form-control" id="emailid" v-model="user.emailId" style="border-radius: 20px;" placeholder="email id" /> 
            <div style="padding: 7px;">@</div>
            <select class="form-select" style="border-radius: 20px; height: 42px; padding-bottom: 9px;"
              id="emaildomain" v-model="user.emailDomain" aria-label="이메일 도메인 선택">
              <option selected>선택</option>
              <option value="ssafy.com">ssafy.com</option>
              <option value="google.com">google.com</option>
              <option value="naver.com">naver.com</option>
              <option value="kakao.com">kakao.com</option>
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
      </div>
    </div>
  </form>
</template>

<style scoped>
.login-logo {
  width: 150px;
  display: block;
  margin: 0 auto;
}
.register-page {
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin: 0 auto;
  border-radius: 30px;
  background-color: white;
  width: 90%;
  max-width: 500px;
  padding-top: 10px;
  padding-bottom: 50px;
  box-shadow: 0 0 10px #adc4ce;
  margin-top: 100px;
  margin-bottom: 140px;
}
input {
  border: 1px solid #a1ccd1;
  border-radius: 20px;
  height: 42px;
  margin-bottom: 20px;
  padding-left: 15px;
  padding-bottom: 9px;
}
.form {
  width: 90%;
  max-width: 300px;
  margin: 0 auto;
}
button {
  width: 49%;
  border-radius: 20px;
}
</style>
