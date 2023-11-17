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
        <div>
          <button type="button" class="btn btn-outline-secondary mt-2" @click="moveModify">
            수정
          </button>
          <button class="btn btn-outline-primary m-1" @click="onDeleteUser">탈퇴</button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
