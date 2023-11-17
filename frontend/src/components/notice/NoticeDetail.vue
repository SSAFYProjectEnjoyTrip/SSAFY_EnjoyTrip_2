<script setup>
import { ref, onMounted } from 'vue'
import CommentListItem from '@/components/comment/item/CommentListItem.vue';
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
const comments = ref([])
const notice = ref({})

onMounted(() => {
  getNotice()
  getCommentList()
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

function getCommentList() {
  console.log('CommentList params............: ', articleNo)
  listComment(
    articleNo,
    ({ data }) => {
      console.log('comment search......result: ', data)
      comments.value = data.comments
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
</script>

<template>
  <div>
    <table class="table table-bordered">
      <tbody>
        <tr>
          <th>게시글 번호</th>
          <td><input type="text" v-model.lazy="notice.articleNo" readonly="readonly" /></td>
        </tr>
        <tr>
          <th>게시글 작성자</th>
          <td><input type="text" v-model.lazy="notice.userId" :readonly="isReadonly" /></td>
        </tr>
        <tr>
          <th>게시글 제목</th>
          <td><input type="text" v-model.lazy="notice.subject" :readonly="isReadonly" /></td>
        </tr>
        <tr>
          <th>게시글 조회수</th>
          <td><input type="text" v-model.lazy="notice.hit" :readonly="isReadonly" /></td>
        </tr>
        <tr>
          <th>게시글 등록 시간</th>
          <td><input type="text" v-model.lazy="notice.registerTime" :readonly="isReadonly" /></td>
        </tr>
        <tr>
          <th colspan="2">게시글 내용</th>
        </tr>
        <tr>
          <td colspan="2">
            <textarea
              cols="45"
              rows="10"
              v-model.lazy="notice.content"
              :readonly="isReadonly"
            ></textarea>
          </td>
        </tr>
        <tbody>
        <CommentListItem :postId="notice.articleNo"></CommentListItem>
      </tbody>
        <tr>
          <td colspan="2" class="text-center">
            <button class="btn btn-outline-primary m-1" @click="moveModify">수정</button>
            <button class="btn btn-outline-primary m-1" @click="moveList">목록</button>
            <button class="btn btn-outline-primary m-1" @click="onDeleteNotice">삭제</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<style scoped>
input:read-only {
  background-color: skyblue;
}
</style>
