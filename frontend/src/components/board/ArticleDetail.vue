<script setup>
import { ref, onMounted } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { detailArticle, deleteArticle } from '@/api/board'

// router로 이동 시 전달한 데이터를 받기 위해
const route = useRoute()
// router로 화면 이동하기 위해
const router = useRouter()

// :to={name:'', query:{}}로 전달한 데이터를 받기 위해
console.log(route)
const { articleNo } = route.params

const article = ref({})

onMounted(() => {
  getArticle()
})

const getArticle = () => {
  // const { articleno } = route.params;
  console.log('ArticleDetail.setup..............데이터 불러오기')
  console.log(articleNo + '번글 얻으러 가자!!!')
  detailArticle(
    articleNo,
    ({ data }) => {
      console.log('ArticleDetail.setup.....데이터 전송 완료 article:', data)
      article.value = data
    },
    (error) => {
      console.log(error)
    }
  )
}

// 초기에는 Detail 화면 표시하기 위해 readonly='readonly'로 표시
// 수정일 경우 readonly='' 표시하기 위해 isReadonly=false로
const isReadonly = ref(true)

function onDeleteArticle() {
  console.log('ArticleDetail.remove.................')
  console.log(articleNo + '번글 삭제하러 가자!!!')
  deleteArticle(
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
  router.push({ name: 'list' })
}

function moveModify() {
  router.push({ name: 'modify', params: { articleNo } })
}
</script>

<template>
  <div class="container">
	  <div class="review">Review&nbsp;<img src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Hand%20gestures/Writing%20Hand%20Medium-Light%20Skin%20Tone.png" alt="Writing Hand Medium-Light Skin Tone" width="50" height="50" /></div>
		<div class="big-box">
			<div class="who">{{article.userId}} 님의 후기 </div>
			<div>
				<span v-if="article.star == 5"
					><img
					src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Travel%20and%20places/Star.png"
					alt="Star"
					width="50"
					height="50" /><img
					src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Travel%20and%20places/Star.png"
					alt="Star"
					width="50"
					height="50" /><img
					src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Travel%20and%20places/Star.png"
					alt="Star"
					width="50"
					height="50" /><img
					src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Travel%20and%20places/Star.png"
					alt="Star"
					width="50"
					height="50" /><img
					src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Travel%20and%20places/Star.png"
					alt="Star"
					width="50"
					height="50"
					/></span>
				<span v-else-if="article.star == 4"
					><img
					src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Travel%20and%20places/Star.png"
					alt="Star"
					width="50"
					height="50" /><img
					src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Travel%20and%20places/Star.png"
					alt="Star"
					width="50"
					height="50" /><img
					src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Travel%20and%20places/Star.png"
					alt="Star"
					width="50"
					height="50" /><img
					src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Travel%20and%20places/Star.png"
					alt="Star"
					width="50"
					height="50"
				/></span>
				<span v-else-if="article.star == 3"
					><img
					src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Travel%20and%20places/Star.png"
					alt="Star"
					width="50"
					height="50" /><img
					src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Travel%20and%20places/Star.png"
					alt="Star"
					width="50"
					height="50" /><img
					src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Travel%20and%20places/Star.png"
					alt="Star"
					width="50"
					height="50"
				/></span>
				<span v-else-if="article.star == 2"
					><img
					src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Travel%20and%20places/Star.png"
					alt="Star"
					width="50"
					height="50" /><img
					src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Travel%20and%20places/Star.png"
					alt="Star"
					width="50"
					height="50"
				/></span>
				<span v-else-if="article.star == 1"
					><img
					src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Travel%20and%20places/Star.png"
					alt="Star"
					width="50"
					height="50"
				/></span>
				<span v-else></span>
			</div>
			<div class="row chart">
				<div class="col-sm-4">
					<div v-if="article.rfile">
						<img :src="article.rfile" alt="Uploaded Image" class="rounded" />
					</div>
				</div>
				<div class="col-sm-8">
					<div class="row row1">"{{ article.subject }}"</div>
					<div class="row row2">{{ article.content }} Lorem ipsum dolor sit amet consectetur adipisicing elit. Sequi adipisci asperiores voluptates excepturi nemo iste molestiae sint deserunt nesciunt accusamus ea esse dolore, nostrum iure voluptate delectus doloremque corporis laboriosam.</div>
				</div>
			</div>
			<div class="date">작성일 : {{article.registerTime}} (조회수 : {{article.hit}}) </div>
			</div>
		</div>
		<div>
			<button class="btn btn-outline-success m-1" @click="moveModify">수정</button>
            <button class="btn btn-outline-success m-1" @click="moveList">목록</button>
            <button class="btn btn-outline-success m-1" @click="onDeleteArticle">삭제</button>
		</div>
	<!-- 컨테이너 -->
</template>

<style scoped>
input:read-only {
  background-color: skyblue;
}
.uploaded-image {
  max-width: 50%;
  height: auto;
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
	background-color: #ECF8F9;
	border-radius: 20px;
	border: 2px solid #B8DFD8;
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

.who {
	font-size: 35px;
	font-weight: bold;
	color: #265073;
	margin-bottom: 10px;
}

.row {
	width: 95%;
	height: auto;
}
/* 제목 */
.row1 {
	font-size: 20px;
	font-weight: bold;
	display: flex;
	justify-content: center;
}
/* 내용 */
.row2 {
	margin-top: 20px;
}
.chart {
	margin-top: 40px;
	height: auto;
	width: 90%;
}
.rounded {
	width: 100%;
}
.date {
	font-weight: bold;
	margin-top: 10px;
	margin-bottom: 10px;
}
</style>
