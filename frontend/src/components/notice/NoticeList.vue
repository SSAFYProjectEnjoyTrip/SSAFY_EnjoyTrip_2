<script setup>
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import VSelect from '@/components/common/VSelect.vue'
import { listNotice } from '@/api/notice'
import NoticeListItem from './item/NoticeListItem.vue'
import PageNavigation from '../common/PageNavigation.vue'

const router = useRouter()

// 목록을 표시할 notices 반응형으로 선언
const notices = ref([])

// 서버에 보낼 조회 조건을 위한 반응형 변수 선언
const params = reactive({
  pageNo: 1,
  key: 'all',
  word: ''
})

// PageNavigation을 위한 반응형 변수
const currentPage = ref(1)
const totalPage = ref(1)

// VSelect 컴포넌트의 props
const selectOptions = ref([
  { text: '---선택하세요---', value: 'all' },
  { text: '제목', value: 'content' },
  { text: '저자', value: 'userId' }
])

function searchNoticeList() {
  console.log('searchNoticeList params............: ', params)
  listNotice(
    params,
    ({ data }) => {
      console.log('noticeSearch......result: ', data)
      notices.value = data.boards
      currentPage.value = data.page.pageNo
      totalPage.value = data.page.total
    },
    (error) => {
      console.log(error)
    }
  )
}

console.log('NoticeList.setup......')
searchNoticeList()

const changeKey = (val) => {
  console.log('NoticeList.search........changeKey:', val)
  params.key.value = val
}

function moveHandler() {
  router.push({ name: 'noticeRegist' })
}

function onPageChange(value) {
  console.log('searchNoticeList.onPageChange....pageNo', value)
  params.pageNo = value
  searchNoticeList()
}

// // 로그인한사람이 admin인지 확인
const loginUser = JSON.parse(sessionStorage.getItem('loginUser'))

var isAdmin = false
if (loginUser == 'admin') {
  isAdmin = true
}
</script>

<template>
  <div v-if="notices.length > 0">
    <div class="board-title">Notice</div>
    <div class="title-info">공지사항입니다 :)</div>
    <div class="regi-btn-box" v-if="isAdmin">
      <button class="btn btn-success" @click="moveHandler">등록</button>
    </div>
    <div class="row search-box">
      <div class="col-6">
        <div class="input-group">
          <span class="input-group-text">검색조건</span>
          <v-select :selectOptions="selectOptions" @on-key-select="changeKey" />
          <input
            type="text"
            class="input-control"
            placeholder="검색어를 입력하세요"
            v-model="params.word"
            @keyup.enter="searchList"
          />
          <button class="btn btn-dark" @click="searchNoticeList">검색</button>
        </div>
      </div>
    </div>
    <table class="table table-hover">
      <thead>
        <colgroup>
          <col width="33%" />
          <col width="33%" />
          <col width="33%" />
        </colgroup>
        <tr class="text-center">
          <th>게시글 번호</th>
          <th>제목</th>
          <th>글쓴이</th>
        </tr>
      </thead>
      <tbody>
        <NoticeListItem
          v-for="notice in notices"
          :key="notice.articleNo"
          :notice="notice"
        ></NoticeListItem>
      </tbody>
    </table>
  </div>
  <div v-else>
    <h3>등록된 게시물 정보가 없습니다</h3>
  </div>
  <div style="height: 50px"></div>
  <PageNavigation :currentPage="currentPage" :totalPage="totalPage" @page-change="onPageChange" />
</template>

<style scoped>
.board-title {
  margin-top: 50px;
  font-size: 50px;
  font-weight: bold;
  font-family: 'Times New Roman', Times, serif;
  text-shadow: 0 0 5px gray;
}
.title-info {
  margin-top: 10px;
  margin-bottom: 50px;
  color: #a8a8a8;
  font-weight: bold;
  font-size: 14px;
}
.regi-btn-box {
  display: flex;
  justify-content: right;
  margin-top: 20px;
}
.input-group {
  margin-bottom: 40px;
}
</style>

<!-- <tbody>
  searchNoticeList
  <NoticeListItem
    v-for="notice in notices"
    :key="notice.articleNo"
    :notice="notice"
  ></NoticeListItem>
</tbody> -->
