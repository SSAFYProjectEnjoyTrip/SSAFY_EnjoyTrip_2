<script setup>
import { ref, onMounted } from 'vue'
// import CommentListItem from '@/components/comment/item/CommentListItem.vue';
import CommentList from '@/components/comment/CommentList.vue'
import { listComment } from '@/api/comment'
import { useRouter, useRoute } from 'vue-router'
import { detailNotice, deleteNotice } from '@/api/notice'

// router로 이동 시 전달한 데이터를 받기 위해
const route = useRoute()
// router로 화면 이동하기 위해
const router = useRouter()

// :to={name:'', query:{}}로 전달한 데이터를 받기 위해
console.log(route)
const { articleNo } = route.params
// 목록을 표시할 comments 반응형으로 선언
// const comments = ref([])
const notice = ref({})

onMounted(() => {
  getNotice()
  // getCommentList()
})

const getNotice = () => {
  console.log('NoticeDetail.setup..............데이터 불러오기')
  console.log(articleNo + '번글 얻으러 가자!!!')
  detailNotice(
    articleNo,
    ({ data }) => {
      console.log('detailNotice.setup.....데이터 전송 완료 detailNotice:', data)
      notice.value = data
    },
    (error) => {
      console.log(error)
    }
  )
}

// 초기에는 Detail 화면 표시하기 위해 readonly='readonly'로 표시
// 수정일 경우 readonly='' 표시하기 위해 isReadonly=false로
const isReadonly = ref(true)

function onDeleteNotice() {
  console.log('deleteNotice.remove.................')
  console.log(articleNo + '번글 삭제하러 가자!!!')
  deleteNotice(
    articleNo,
    (response) => {
      if (response.status == 200) moveList()
    },
    (error) => {
      console.log(error)
    }
  )
}

function moveList() {
  router.push({ name: 'noticeList' })
}

function moveModify() {
  router.push({ name: 'noticeModify', params: { articleNo } })
}

// // 로그인한사람이 admin인지 확인
const loginUser = JSON.parse(sessionStorage.getItem('loginUser'))

var isAdmin = false
if (loginUser == 'admin') {
  isAdmin = true
}
</script>

<template>
  <div>
    <div class="container">
      <div class="review">Notice</div>
      <div class="big-box">
        <div class="row chart">
          <div>
            <div>
              <h1>
                <b>"{{ notice.subject }}"</b>
              </h1>
            </div>
            <div class="admin-content">
              {{ notice.content }} Lorem ipsum dolor sit amet consectetur adipisicing elit. Sequi
              adipisci asperiores voluptates excepturi nemo iste molestiae sint deserunt nesciunt
              accusamus ea esse dolore, nostrum iure voluptate delectus doloremque corporis
              laboriosam.
            </div>
          </div>
        </div>
        <div class="date">작성일 : {{ notice.registerTime }}</div>
      </div>
    </div>
    <CommentList :postId="String(articleNo)"></CommentList>
    <div v-if="isAdmin">
      <button class="btn btn-outline-primary m-1" @click="moveModify">수정</button>
      <button class="btn btn-outline-primary m-1" @click="moveList">목록</button>
      <button class="btn btn-outline-primary m-1" @click="onDeleteNotice">삭제</button>
    </div>
  </div>
</template>

<style scoped>
input:read-only {
  background-color: skyblue;
}
/**review big */
.container {
  display: grid;
  place-items: center;
}

.big-box {
  margin-top: 20px;
  padding-top: 20px;
  display: grid;
  place-items: center;
  width: 80%;
  background-color: #ecf8f9;
  border-radius: 20px;
  border: 2px solid #b8dfd8;
  margin-bottom: 20px;
}

.review {
  margin-top: 50px;
  margin-bottom: 10px;
  font-size: 50px;
  font-weight: bold;
  font-family: 'Times New Roman', Times, serif;
  text-shadow: 0 0 5px gray;
}

.row {
  width: 95%;
  height: auto;
}
/* 제목 */

/* 내용 */
.chart {
  margin-top: 40px;
  height: auto;
  width: 90%;
}
.date {
  font-weight: bold;
  margin-top: 10px;
  margin-bottom: 10px;
}
.admin-content {
  margin-top: 20px;
  margin-bottom: 20px;
}
</style>
