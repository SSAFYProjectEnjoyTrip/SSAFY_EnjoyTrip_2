<script setup>
import { ref, watch } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { registArticle, detailArticle, modifyArticle } from '@/api/board'
import axios from 'axios'

const router = useRouter()
const route = useRoute()

const props = defineProps({ type: String })

const isUseId = ref(false)

const article = ref({
  articleNo: 0,
  rfile: '', // 파일
  star: '', // 별점
  userId: '',
  subject: '',
  content: '',
  hit: 0,
  registerTime: ''
})

function showPreview(event) {
  var input = event.target
  // var preview = document.getElementById('preview') 밑으로 옮김

  if (input.files && input.files[0]) {
    var reader = new FileReader()
    // article.value.rfile = rfile // 사용자가 선택한 파일을 article 객체에 추가
    article.value.rfile = input.files[0] // 파일 객체 저장

    reader.onload = function (e) {
      // 파일 읽기가 완료되었을 때 실행될 함수
      // preview.src = e.target.result
      var preview = document.getElementById('preview')
      preview.src = e.target.result
      preview.style.display = 'block'
    }
    reader.readAsDataURL(input.files[0])
  }
}

async function onSubmit() {
  // event.preventDefault();

  if (subjectErrMsg.value) {
    alert(subjectErrMsg.value)
    return
  } else if (contentErrMsg.value) {
    alert(contentErrMsg.value)
    return
  } else {
    if (article.value.rfile) {
      let formData = new FormData()
      formData.append('image', article.value.rfile)

      // ImgBB API endpoint with your API key
      const imgbbAPIEndpoint = `https://api.imgbb.com/1/upload?key=8d48b6e9b684c58e2f0d596e58a37d97`

      try {
        // Upload the image to ImgBB
        const imgResponse = await axios.post(imgbbAPIEndpoint, formData, {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        })

        // Set the ImgBB URL to the rfile property of your article object
        if (imgResponse.data.success) {
          article.value.rfile = imgResponse.data.data.url
        }
      } catch (error) {
        console.error('Error uploading the image:', error)
        return
      }
    }

    // Continue with your existing logic to write or update the article
    props.type === 'regist' ? writeArticle() : updateArticle()
  }
}

if (props.type === 'modify') {
  let { articleNo } = route.params
  console.log(articleNo + '번글 얻어와서 수정할거야')
  detailArticle(
    articleNo,
    ({ data }) => {
      article.value = data
      isUseId.value = true
      console.log(article.value)
      if (data.rfile) {
        // If there is an image URL, display it in the preview
        var preview = document.getElementById('preview')
        preview.src = data.rfile
        preview.style.display = 'block'
      }
    },
    (error) => {
      console.log(error)
    }
  )
  isUseId.value = true
}

const subjectErrMsg = ref('')
const contentErrMsg = ref('')
watch(
  () => article.value.subject,
  (value) => {
    let len = value.length
    if (len == 0 || len > 30) {
      subjectErrMsg.value = '제목을 확인해 주세요!!!'
    } else subjectErrMsg.value = ''
  },
  { immediate: true }
)
watch(
  () => article.value.content,
  (value) => {
    let len = value.length
    if (len == 0 || len > 500) {
      contentErrMsg.value = '내용을 확인해 주세요!!!'
    } else contentErrMsg.value = ''
  },
  { immediate: true }
)

function writeArticle() {
  // console.log('글등록하자!!', sessionStorage.getItem("loginUser"))
  console.log('글등록하자!!', article.value.subject)
  article.value.userId = JSON.parse(sessionStorage.getItem('loginUser'))
  // 서버로 formData 전송하는 로직
  registArticle(
    article.value,
    (response) => {
      let msg = '글등록 처리시 문제 발생했습니다.'
      if (response.status == 200) msg = '글등록이 완료되었습니다.'
      alert(msg)
      moveList()
    },
    (error) => console.log(error)
  )
}

function updateArticle() {
  console.log(article.value.articleNo + '번글 수정하자!!', article.value)
  modifyArticle(
    article.value,
    (response) => {
      let msg = '글수정 처리시 문제 발생했습니다.'
      if (response.status == 200) msg = '글정보 수정이 완료되었습니다.'
      alert(msg)
      moveList()
    },
    (error) => console.log(error)
  )
}

function moveList() {
  router.push({ name: 'list' })
}
</script>

<template>
  <form @submit.prevent="onSubmit">
    <div class="big-box">
      <div class="title">
        여행 후기를 남겨주세요
        <img
          src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Hand%20gestures/Heart%20Hands%20Medium-Light%20Skin%20Tone.png"
          alt="Heart Hands Medium-Light Skin Tone"
          width="100"
          height="100"
        />
      </div>
      <div class="stars">만족하셨나요?</div>
      <div class="form-group">
        <select class="form-control" id="sel1" name="star" v-model="article.star">
          <option value="5">★★★★★</option>
          <option value="4">★★★★</option>
          <option value="3">★★★</option>
          <option value="2">★★</option>
          <option value="1">★</option>
        </select>
      </div>

      <input type="hidden" v-model="article.userId" />

      <div class="share">사진을 공유해주세요</div>
      <div class="image">
        <img
          class="rounded"
          id="preview"
          :src="article.rfile"
          alt="Uploaded Image"
          v-if="article.rfile"
        />
      </div>
      <div class="input-div">
        <input type="file" name="rfile" id="rfile" class="rfile" @change="showPreview" />
      </div>

      <input
        type="text"
        id="title"
        name="title"
        v-model="article.subject"
        placeholder="Title"
        required
      />

      <textarea
        id="content"
        name="content"
        v-model="article.content"
        placeholder="Content"
        required
      ></textarea>

      <div class="btn-div">
        <button type="submit" class="submit-button" v-if="type === 'regist'">리뷰등록</button>
        <button type="submit" class="submit-button" v-else>리뷰수정</button>
        <button type="button" class="list-button" @click="moveList">목록으로</button>
      </div>
    </div>
  </form>
</template>

<style scoped>
.big-box {
  justify-content: center;
  align-items: center;
  margin: 0 auto;
  margin-top: 100px;
  margin-bottom: 200px;
  width: 90%;
  max-width: 700px;
  height: auto;
  padding-top: 50px;
  background-color: #ecf8f9;
  border-radius: 30px;
  border: 3px solid #b8dfd8;
}

input,
textarea {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0 auto;
  width: 80%;
  max-width: 400px;
  padding: 10px;
  margin-bottom: 40px;
  border: 3px solid pink;
  border-radius: 6px;
}

textarea {
  height: 200px;
}

.btn-div,
.input-div {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0 auto;
}

.submit-button {
  padding: 10px 20px;
  text-align: center;
  background-color: #64ccc5;
  color: #fff;
  border: none;
  border-radius: 6px;
  margin-top: 50px;
  margin-bottom: 70px;
  margin-right: 10px;
}

.list-button {
  padding: 10px 20px;
  text-align: center;
  background-color: #64ccc5;
  color: #fff;
  border: none;
  border-radius: 6px;
  margin-top: 50px;
  margin-bottom: 70px;
}
.submit-button:hover {
  background-color: #dafffb;
}
.title {
  width: 100%;
  text-align: center;
  margin-top: 30px;
  font-size: 35px;
  font-weight: bold;
  color: #64ccc5;
  text-shadow: 0 0 2px #64ccc5;
}

.share,
.stars {
  display: flex;
  justify-content: center;
  color: #e966a0;
  font-size: 12px;
  font-weight: bold;
  margin-bottom: 5px;
}
.form-control {
  width: 110px;
  border: 3px solid #ffd89c;
  border-radius: 4px;
}
.form-group {
  display: flex;
  justify-content: center;
  margin-bottom: 40px;
}
.image {
  width: 300px;
  height: 300px;
  border: 3px dashed pink;
  border-radius: 6px;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0 auto;
  margin-bottom: 10px;
  background-color: white;
}
.rounded {
  width: 200px;
}
</style>
