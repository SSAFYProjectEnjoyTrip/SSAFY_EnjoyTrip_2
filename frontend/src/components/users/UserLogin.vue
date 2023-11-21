<script setup>
import { ref } from 'vue'
import { storeToRefs } from 'pinia'
import { useRouter } from 'vue-router'
import { useMemberStore } from '@/stores/member'
// import { useMenuStore } from '@/stores/menu'

const router = useRouter()

const memberStore = useMemberStore()

const { userInfo, isLogin } = storeToRefs(memberStore)
const { userLogin, getUserInfo } = memberStore
// const { changeMenuState } = useMenuStore()

const loginUser = ref({
  userId: '',
  userPwd: ''
})

const login = async () => {
  console.log('login ing!!!! !!!')
  await userLogin(loginUser.value)
  let token = sessionStorage.getItem('accessToken')
  console.log('111. ', token)
  console.log('isLogin: ', isLogin.value)
  if (isLogin.value === true) {
    console.log('로그인 정보가 있음 ㅇㅇㅇ 로그인 성공 아니 로그인이 안됐는데 왜 뜨는거임?')
    getUserInfo(token)

    // changeMenuState()
    router.push('/').then(() => {
      // Once navigation is confirmed, reload the page
      window.location.reload()
    })
  }
  console.log(userInfo)
}
</script>

<template>
  <main class="container box">
    <div class="login-page" style="margin-top: 100px">
      <div class="title">
        <img src="@/assets/et.png" class="login-logo" />
      </div>
      <div class="form">
        <form name="login-form" class="login-form" method="POST" action="">
          <input type="hidden" name="action" value="login" />
          <div>
            <input
              type="text"
              class="form-control"
              v-model="loginUser.userId"
              placeholder="ID"
              required
            />
          </div>
          <div>
            <input
              type="password"
              class="form-control"
              v-model="loginUser.userPwd"
              placeholder="Password"
            />
          </div>
          <div class="for-saveid">
            <input class="form-check-input" type="checkbox" value="ok" id="saveid" name="saveid" />
            <label class="form-check-label" for="saveid">&nbsp;&nbsp;아이디저장 </label>
          </div>
          <div>
            <button type="button" class="btn btn-outline-primary mb-3 login-btn" @click="login">
              login
            </button>
          </div>
          <div class="go">
            <a href="/user/join" class="last">회원가입</a>&nbsp;|
            <a href="#" class="last">&nbsp;아이디 찾기&nbsp;</a>|
            <a href="#" class="last">&nbsp;비밀번호 찾기</a>
          </div>
        </form>
      </div>
    </div>
  </main>
</template>

<style scoped>
.login-page {
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin: 0 auto;
  border-radius: 30px;
  background-color: white;
  width: 95%;
  max-width: 500px;
  padding-bottom: 50px;
  box-shadow: 0 0 10px #adc4ce;
  margin-bottom: 140px;
}
.form {
  width: 300px;
  display: flex;
  margin: 0 auto;
}
.login-logo {
  width: 150px;
  margin-top: 20px;
}

.last {
  text-decoration: none;
  color: gray;
  font-weight: bold;
  font-size: 14px;
}
.last:hover {
  color: black;
}
.form-control {
  border: 1px solid #a1ccd1;
  border-radius: 20px;
  height: 42px;
  margin-bottom: 20px;
  padding-left: 15px;
}
.for-saveid {
  display: flex;
  justify-content: left;
}
.login-btn {
  border: none;
  font-size: 20px;
  font-weight: bold;
  font-family: 'Times New Roman', Times, serif;
  color: #0c356a;
  border-radius: 20px;
  width: 90px;
  height: 40px;
  margin-bottom: 20px;
  background-color: #aed2ff;
  width: 300px;
  margin-top: 20px;
}
.login-btn:hover,
.update-btn:hover,
.delete-btn:hover {
  color: white;
  box-shadow: 0 0 3px #aed2ff;
}
</style>
