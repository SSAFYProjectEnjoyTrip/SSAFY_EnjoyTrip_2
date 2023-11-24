<script setup>
import { listArticle } from '@/api/board'
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import ArticleListItem from './item/ArticleListItem.vue'
import VSelect from '@/components/common/VSelect.vue'
import PageNavigation from '../common/PageNavigation.vue'

const router = useRouter()

// 목록을 표시할 articles를 반응형으로 선언
const articles = ref([])

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

function searchList() {
  console.log('ArticleList params............: ', params)
  listArticle(
    params,
    ({ data }) => {
      console.log('search......result: ', data)
      articles.value = data.boards
      currentPage.value = data.page.pageNo
      totalPage.value = data.page.total
    },
    (error) => {
      console.log(error)
    }
  )
}

console.log('ArticleList.setup......')
searchList()

const changeKey = (val) => {
  console.log('ArticleList.search........changeKey:', val)
  params.key.value = val
}

function moveHandler() {
  router.push({ name: 'regist' })
}

function onPageChange(value) {
  console.log('ArticleList.onPageChange....pageNo', value)
  params.pageNo = value
  searchList()
}

// // 로그인한사람있으면 확인
const loginUser = JSON.parse(sessionStorage.getItem('loginUser'))
</script>

<template>
  <div class="bg">
    <div v-if="articles.length > 0">
      <div class="board-title">Review Board</div>
      <div class="title-info">여러분의 여행을 공유해주세요 :)</div>
      <div class="regi-btn-box" v-if="loginUser">
        <button class="btn btn-success" @click="moveHandler">등록</button>
      </div>
      <div class="regi-btn-box" v-else>
        <div style="font-size: 13px; font-weight: bold">로그인해야 작성하실 수 있습니다 !</div>
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
            <button class="btn btn-dark" @click="searchList">검색</button>
          </div>
        </div>
      </div>
      <table class="table table-hover">
        <thead>
          <colgroup>
            <col width="25%" />
            <col width="25%" />
            <col width="25%" />
            <col width="25%" />
          </colgroup>
          <tr class="text-center">
            <th width="25%">리뷰번호</th>
            <th width="25%">별점</th>
            <th width="25%">제목</th>
            <th width="25%">글쓴이</th>
          </tr>
        </thead>
        <tbody>
          <ArticleListItem
            v-for="article in articles"
            :key="article.articleNo"
            :article="article"
          ></ArticleListItem>
        </tbody>
      </table>
    </div>
    <div v-else>
      <h3>등록된 게시물 정보가 없습니다</h3>
    </div>
    <div style="height: 50px"></div>
    <PageNavigation :currentPage="currentPage" :totalPage="totalPage" @page-change="onPageChange" />
  </div>
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
