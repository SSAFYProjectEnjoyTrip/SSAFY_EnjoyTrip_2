<script setup>
import { ref, onMounted } from 'vue'
import { storeToRefs } from 'pinia'
import { useRouter } from 'vue-router'
import { useMemberStore } from '@/stores/member'
import { useMenuStore } from '@/stores/menu'
import { findById, deleteUser, logout, updatePwd, updateEmail } from '@/api/user'

const router = useRouter()

const memberStore = useMemberStore()
const { userLogout } = memberStore
const { changeMenuState } = useMenuStore()
const { userInfo, isLogin, isValidToken } = storeToRefs(memberStore)
// const { getUserInfo } = memberStore
const isReadonly = ref(true)

console.log(userInfo.value.userId);

// 기본 상태를 정의하는 함수
const getDefaultState = () => ({
  userId: '',
  userName: '',
  userPwd: '',
  emailId: '',
  emailDomain: ''
})

// 폼 상태
const user = ref(getDefaultState())

onMounted(() => {
  find()
})

function onDeleteUser() {
  console.log('userInfo.remove.................')
  console.log(userInfo.value.userId + ' 삭제하러 가자!!!')
  logout(
    userInfo.value.userId,
    (response) => {
      if (response.status == 200) {
        //토큰 삭제를 위해 logout 함수 호출
        deleteUser(userInfo.value.userId)
        //메인으로 이동
        moveMain()

        // isLogin.value = false
        // userInfo.value = null
        // isValidToken.value = false

        //메뉴바 변경
        // changeMenuState()
        // localStorage.removeItem('memberStore')
        sessionStorage.removeItem('accessToken')
        userLogout(userInfo.userId)
        // Clear session storage items
        sessionStorage.removeItem('accessToken')
        sessionStorage.removeItem('refreshToken')
        sessionStorage.removeItem('loginUser')
        console.log('로그아웃!!!!')
        alert(userInfo.value.userId + ' 탈퇴 완료되었습니다.')
        console.log('삭제 완료?')
      }
    },
    (error) => {
      console.log(error)
    }
  )
}

const find = () => {
  console.log('USER 가져오기!!!')
  // getUserInfo(sessionStorage.getItem('token'))
  console.log('엥???????????', userInfo)
  console.log(userInfo.value)
}

function moveMain() {
  router.push({ name: 'main' })
}

function modifyPassword() {

  // 입력 값 가져오기 user.emailId
  const userId = userInfo.value.userId
  const curPw = document.getElementById('curPw').value
  const newPw1 = document.getElementById('newPw1').value
  const newPw2 = document.getElementById('newPw2').value

  // 새 비밀번호 일치 여부 확인
  if (newPw1 === newPw2) {
    const param = {
      userId: userId,
      curPw: curPw,
      newPw1: newPw1
    }

    updatePwd(
      param,
      (response) => {
        if (response.data === 'SUCCESS') {
          // 비밀번호 변경 성공 처리
          alert('비밀번호가 변경되었습니다. 로그아웃됩니다.')
          userLogout(userInfo.userId)
          // Clear session storage items
          sessionStorage.removeItem('accessToken')
          sessionStorage.removeItem('refreshToken')
          sessionStorage.removeItem('loginUser')
          moveMain()
        } else {
          alert('현재 비밀번호가 일치하지 않습니다.')
        }
      },
      (error) => {
        // 비밀번호 변경 실패 처리
        console.error('비밀번호 변경 중 에러 발생: ', error)
        alert('비밀번호 변경에 실패했습니다.')
      }
    )
  } else {
    alert('비밀번호 확인을 체크해주세요.')
  }
}

function modifyEmail() {

  // 입력 값 가져오기
  const userId = userInfo.value.userId
  const curPw = document.getElementById('curPw').value
  const emailid = document.getElementById('emailid').value
  const emaildomain = document.getElementById('emaildomain').value
  // 현재아이디랑 비밀번호 같으면 이메일 바꿔주기

  const param = {
    userId: userId,
    curPw: curPw,
    emailId: emailid,
    emailDomain: emaildomain
  }

  updateEmail (
    param,
    (response) => {
      if (response.data === 'SUCCESS') {
        alert('이메일 정보가 변경되었습니다. 로그아웃됩니다.')
        userLogout(userInfo.userId)
        // Clear session storage items
        sessionStorage.removeItem('accessToken')
        sessionStorage.removeItem('refreshToken')
        sessionStorage.removeItem('loginUser')
        moveMain()
      } else {
        alert('비밀번호가 일치하지 않습니다.')
      }
    },
    (error) => {
      console.error('이메일 정보 변경 중 에러 발생: ', error)
      alert('이메일 정보 변경에 실패했습니다.')
    }
  )
  
}
</script>

<template>
  <main class="container box">
    <div class="login-page mypage" style="margin-top: 100px">
      <div class="title">
        <div class="mypage-title">My Page</div>
      </div>
      <div id="client-information">
        <table class="table">
          <thead>
            <tr>
              <div class="user-info">
                회원 정보
                <img
                  src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Food/French%20Fries.png"
                  alt="French Fries"
                  width="35"
                  height="35"
                />
              </div>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td style="padding-top: 32px">Name</td>
              <td style="padding-top: 30px; text-align: left">{{ userInfo.userName }}</td>
            </tr>
            <tr>
              <td style="padding-top: 15px">ID</td>
              <td id="currUser-userId" style="padding-top: 10px; text-align: left">
                {{ userInfo.userId }}
              </td>
            </tr>
            <tr>
              <td style="padding-top: 15px">PW</td>
              <td id="currUser-userpw" style="padding-top: 10px; text-align: left">
                <button
                  data-bs-target="#for-update"
                  data-bs-toggle="modal"
                  id="update-user-btn"
                  class="btns"
                >
                  변경
                </button>
              </td>
            </tr>
            <tr>
              <td style="padding-top: 18px">E-mail</td>
              <td>
                <div class="for-email">
                  <div class="for-email2">
                    {{ userInfo.emailId }} @ {{ userInfo.emailDomain }}
                    <!-- <div><button class="email-update btns" @click="moveModify">변경</button></div> -->
                    <div>
                      <button
                        class="email-update btns"
                        data-bs-target="#for-email-update"
                        data-bs-toggle="modal"
                        id="update-user-btn"
                      >
                        변경
                      </button>
                    </div>
                  </div>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="form">
        <form name="login-form" class="login-form">
          <div>
            <button
              class="delete-btn"
              data-bs-target="#for-delete"
              data-bs-toggle="modal"
              type="button"
            >
              탈퇴하기&nbsp;<img
                src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Smilies/Face%20Holding%20Back%20Tears.png"
                alt="Face Holding Back Tears"
                width="25"
                height="25"
              />
            </button>
          </div>
        </form>
      </div>
    </div>
    <!-- 모달 start -->
    <div class="modal fade" id="for-update" tabindex="-1">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <div class="modal1-title">
              <img
                src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Symbols/Triangular%20Flag.png"
                alt="Triangular Flag"
                width="35"
                height="35"
              />
              비밀번호 변경
            </div>
          </div>
          <form name="updatePwd-form" id="updatePwd-form" method="POST" action="">
            <div class="modal-body make">
              <div>
                <div>현재 비밀번호</div>
                <input type="password" id="curPw" v-model="user.userPwd" required />
              </div>
              <div>
                <div>변경할 비밀번호</div>
                <input type="password" id="newPw1" required />
              </div>
              <div>
                <div>변경할 비밀번호 확인</div>
                <input type="password" id="newPw2" required />
              </div>
              <div class="new-pw-check"></div>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
              <button type="button" @click="modifyPassword" class="btn btn-primary updatePwdBtn">
                변경하기
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
    <!-- 모달 end -->
    <!-- 모달 start -->
    <div class="modal fade" id="for-email-update" tabindex="-1">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <div class="modal1-title">
              <img
                src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Symbols/Triangular%20Flag.png"
                alt="Triangular Flag"
                width="35"
                height="35"
              />
              이메일 변경
            </div>
          </div>
          <form>
            <div class="mb-3">
              <div class="modal-body make">
                <div style="width: 100%; padding-right: 50px">
                  <div>현재 비밀번호</div>
                  <input
                    type="password"
                    id="curPw"
                    v-model="user.userPwd"
                    style="border-radius: 20px; height: 40px"
                    required
                  />
                </div>
                <div>
                  <div class="input-group" style="width: 100%">
                    <div style="margin-right: 20px; padding-top: 8px">변경할 이메일</div>
                    <input
                      type="text"
                      id="emailid"
                      v-model="user.emailId"
                      style="border-radius: 20px"
                      placeholder="email id"
                    />
                    <div style="width: 30px; padding-top: 5px">@</div>
                    <select
                      id="emaildomain"
                      v-model="user.emailDomain"
                      aria-label="이메일 도메인 선택"
                      style="border-radius: 20px"
                    >
                      <option selected>선택</option>
                      <option value="ssafy.com">ssafy.com</option>
                      <option value="google.com">google.com</option>
                      <option value="naver.com">naver.com</option>
                      <option value="kakao.com">kakao.com</option>
                    </select>
                  </div>
                </div>
              </div>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
              <button type="button" @click="modifyEmail" class="btn btn-primary updatePwdBtn">
                변경하기
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
    <!-- 모달 end -->
    <!-- 모달 start -->
    <div class="modal fade" id="for-delete" tabindex="-1">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <div class="modal2-title">
              <img
                src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Symbols/Triangular%20Flag.png"
                alt="Triangular Flag"
                width="35"
                height="35"
              />
            </div>
          </div>
          <div class="modal-body">
            <div class="modal2-body" style="margin-top: 20px; margin-bottom: 20px">
              <h4>
                <b
                  >정말 탈퇴하시겠습니까 ?
                  <img
                    src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Smilies/Face%20Holding%20Back%20Tears.png"
                    alt="Face Holding Back Tears"
                    width="35"
                    height="35"
                /></b>
              </h4>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" data-bs-dismiss="modal">취소</button>
            <button type="button" class="btn btn-danger" @click="onDeleteUser">탈퇴</button>
          </div>
        </div>
      </div>
    </div>
    <!-- 모달 end -->
  </main>

  <!-- 수정탈퇴ㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣ -->
  <!-- <div>
    <button type="button" class="btn btn-outline-secondary mt-2" @click="moveModify">수정</button>
    <button class="btn btn-outline-primary m-1" @click="onDeleteUser">탈퇴</button>
  </div> -->
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
.mypage-title {
  margin-top: 25px;
  margin-bottom: 80px;
  text-align: center;
  font-size: 40px;
  font-family: 'Times New Roman', Times, serif;
  font-weight: bold;
  color: #213555;
  text-shadow: 0 0 3px gray;
}
#client-information {
  width: 92%;
  display: block;
  margin: 0 auto;
}
.user-info {
  font-size: 20px;
  font-weight: bold;
  padding-left: 20px;
  margin-bottom: 20px;
  color: #213555;
}
.form-control {
  border: 1px solid #a1ccd1;
  border-radius: 20px;
  height: 40px;
  padding-left: 15px;
}
.modal1-title {
  font-size: 20px;
  font-weight: bold;
}
.modal {
  margin-top: 100px;
}
.modal2-body {
  font-size: 17px;
}
#currUser-email {
  width: 70%;
}
.for-email {
  display: flex;
  flex-direction: row;
}

.email-update {
  height: 30px;
  display: block;
  margin-left: 5px;
}
#curPw,
#newPw1,
#newPw2 {
  width: 50%;
  border: 1px solid #a1ccd1;
  border-radius: 5px;
  padding: 5px;
}
.make > div {
  margin-top: 10px;
  display: flex;
  justify-content: center;
}
.make > div > div {
  margin-top: 10px;
  width: 40%;
}
.btns {
  width: 70px;
  height: 35px;
  border: 2px solid #f1efef;
  border-radius: 20px;
}
.delete-btn {
  width: 40%;
  height: 40px;
  background-color: #eeeeee;
  border: none;
  border-radius: 20px;
  margin-top: 50px;
}
.btns:hover {
  transition:
    background-color 0.3s ease,
    color 0.3s ease;
  background-color: #d0e7d2;
  color: white;
}
.delete-btn:hover {
  transition:
    background-color 0.3s ease,
    color 0.3s ease;
  background-color: #b4b4b3;
  color: white;
}
#emailid {
  width: 130px;
  border: 1px solid #a1ccd1;
  padding: 7px;
}
#emaildomain {
  padding: 7px;
}
.for-email2 {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  margin-top: 6px;
}
.login-page {
  max-width: 600px;
}
.input-group {
  display: flex;
  justify-content: center;
}
</style>
