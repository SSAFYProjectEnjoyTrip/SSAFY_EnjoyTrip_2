<script setup>
import { ref, onMounted } from 'vue'
import { storeToRefs } from 'pinia'
import { useRouter } from 'vue-router'
import { useMemberStore } from '@/stores/member'
import { useMenuStore } from '@/stores/menu'
import { findById, deleteUser, logout } from '@/api/user'

const router = useRouter()

const memberStore = useMemberStore()
const { changeMenuState } = useMenuStore()
const { userInfo, isLogin, isValidToken } = storeToRefs(memberStore)
// const { getUserInfo } = memberStore
const isReadonly = ref(true)

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
        changeMenuState()
        // localStorage.removeItem('memberStore')
        sessionStorage.removeItem('accessToken')
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

function moveModify() {
  router.push({ name: 'user-modify' })
}
</script>

<template>
  <main class="container box">
    <div class="login-page mypage" style="margin-top: 100px">
      <div class="title">
        <div class="top">My Page</div>
      </div>
      <div id="client-information">
        <table class="table">
          <thead>
            <tr>
              <div class="info">회원 정보 🥨</div>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td style="padding-top: 32px">Name</td>
              <td style="padding-top: 32px">${loginUser.userName}</td>
            </tr>
            <tr>
              <td style="padding-top: 32px">ID</td>
              <td id="currUser-userId" style="padding-top: 30px; text-align: left">
                ${loginUser.userId}
              </td>
            </tr>
            <tr>
              <td style="padding-top: 32px">PW</td>
              <td id="currUser-userpw" style="padding-top: 30px; text-align: left">
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
              <td style="padding-top: 32px">E-mail</td>
              <td>
                <div class="for-email">
                  <form name="updateEmail-form" id="updateEmail-form" method="POST" action="">
                    <input type="hidden" name="action" value="updateEmail" />
                    <div class="for-email2">
                      <input
                        type="text"
                        class="form-control"
                        id="emailid"
                        name="emailid"
                        style="border-radius: 20px"
                        value="${loginUser.emailId}"
                      />
                      <div style="padding: 7px">@</div>
                      <select
                        class="form-select"
                        style="border-radius: 20px"
                        id="emaildomain"
                        name="emaildomain"
                        aria-label="이메일 도메인 선택"
                      >
                        <option selected>${loginUser.emailDomain}</option>
                        <option value="ssafy.com">ssafy.com</option>
                        <option value="google.com">google.com</option>
                        <option value="naver.com">naver.com</option>
                        <option value="kakao.com">kakao.com</option>
                      </select>
                      <div><button class="email-update btns">변경</button></div>
                    </div>
                  </form>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="form" style="width: 90%">
        <form name="login-form" class="login-form">
          <div>
            <button
              class="delete-btn"
              data-bs-target="#for-delete"
              data-bs-toggle="modal"
              type="button"
            >
              탈퇴하기 😢
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
            <div class="modal1-title">❗ 비밀번호 변경</div>
          </div>
          <form name="updatePwd-form" id="updatePwd-form" method="POST" action="">
            <input type="hidden" name="action" value="updatePwd" />
            <input type="hidden" name="curId" value="${loginUser.userId}" />
            <div class="modal-body make">
              <div>
                <div>현재 비밀번호</div>
                <input type="password" id="curPw" name="curPw" required />
              </div>
              <div>
                <div>변경할 비밀번호</div>
                <input type="password" id="newPw1" name="newPw1" required />
              </div>
              <div>
                <div>변경할 비밀번호 확인</div>
                <input type="password" id="newPw2" name="newPw2" required />
              </div>
              <div class="new-pw-check"></div>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
              <button type="button" onclick="updatePwd()" class="btn btn-primary updatePwdBtn">
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
            <div class="modal2-title">❗</div>
          </div>
          <div class="modal-body">
            <div class="modal2-body">정말 탈퇴하시겠습니까 ?</div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" data-bs-dismiss="modal">취소</button>
            <button type="button" onclick="deleteUser()" class="btn btn-danger">탈퇴</button>
          </div>
        </div>
      </div>
    </div>
    <!-- 모달 end -->
  </main>

  <!-- 수정탈퇴ㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣ -->
  <div>
    <button type="button" class="btn btn-outline-secondary mt-2" @click="moveModify">수정</button>
    <button class="btn btn-outline-primary m-1" @click="onDeleteUser">탈퇴</button>
  </div>
</template>

<style scoped></style>

<!-- 
<div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          <mark class="orange">내정보</mark>
        </h2>
      </div>
      <div class="col-lg-10">
        <div class="card mt-3 m-auto" style="max-width: 700px">
          <div class="row g-0">
            <div class="col-md-4">
              <img
                src="https://source.unsplash.com/random/250x250/?food"
                class="img-fluid rounded-start"
                alt="..."
              />
            </div>
            <div class="col-md-8">
              <div class="card-body text-start">
                <tbody class="list-group list-group-flush">
                  <tr>
                    <td>
                      <input type="text" v-model.lazy="userInfo.userId" readonly="readonly" />
                    </td>
                  </tr>
                  <td>
                    <input type="text" v-model.lazy="userInfo.userName" readonly="isReadonly" />
                  </td>
                  <td>
                    <input type="text" v-model.lazy="userInfo.emailId" readonly="isReadonly" />
                    <input type="text" v-model.lazy="userInfo.emailDomain" readonly="isReadonly" />
                  </td>
                </tbody>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

-->
