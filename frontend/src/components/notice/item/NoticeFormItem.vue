<script setup>
import { ref, watch } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { insertNotice, modifyNotice, detailNotice } from '@/api/notice'

const router = useRouter()
const route = useRoute()

const props = defineProps({ type: String })

const isUseId = ref(false)

const notice = ref({
  articleNo: 0,
  userId: '',
  subject: '',
  content: '',
  hit: 0,
  registerTime: ''
})

if (props.type === 'modify') {
  let { articleNo } = route.params
  console.log(articleNo + '번글 얻어와서 수정할거야')
  detailNotice(
    articleNo,
    ({ data }) => {
      notice.value = data
      isUseId.value = true
      console.log(notice.value)
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
  () => notice.value.subject,
  (value) => {
    let len = value.length
    if (len == 0 || len > 30) {
      subjectErrMsg.value = '제목을 확인해 주세요!!!'
    } else subjectErrMsg.value = ''
  },
  { immediate: true }
)
watch(
  () => notice.value.content,
  (value) => {
    let len = value.length
    if (len == 0 || len > 500) {
      contentErrMsg.value = '내용을 확인해 주세요!!!'
    } else contentErrMsg.value = ''
  },
  { immediate: true }
)

function onSubmit() {
  // event.preventDefault();

  if (subjectErrMsg.value) {
    alert(subjectErrMsg.value)
  } else if (contentErrMsg.value) {
    alert(contentErrMsg.value)
  } else {
    props.type === 'regist' ? writeNotice() : updateNotice()
  }
}

function writeNotice() {
  console.log('글등록하자!!', notice.value)
  insertNotice(
    notice.value,
    (response) => {
      let msg = '글등록 처리시 문제 발생했습니다.'
      if (response.status == 200) msg = '글등록이 완료되었습니다.'
      alert(msg)
      moveList()
    },
    (error) => console.log(error)
  )
}

function updateNotice() {
  console.log(notice.value.articleNo + '번글 수정하자!!', notice.value)
  modifyNotice(
    notice.value,
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
  router.push({ name: 'noticeList' })
}
</script>

<template>
  <form @submit.prevent="onSubmit">
    <div class="mb-3">
      <label for="subject" class="form-label">제목 : </label>
      <input type="text" class="form-control" v-model="notice.subject" placeholder="제목..." />
    </div>
    <div class="mb-3">
      <label for="content" class="form-label">내용 : </label>
      <textarea class="form-control" v-model="notice.content" rows="10"></textarea>
    </div>
    <div class="col-auto text-center">
      <button type="submit" class="btn btn-outline-primary mb-3" v-if="type === 'regist'">
        글작성
      </button>
      <button type="submit" class="btn btn-outline-success mb-3" v-else>글수정</button>
      <button type="button" class="btn btn-outline-danger mb-3 ms-1" @click="moveList">
        목록으로이동...
      </button>
    </div>
  </form>
</template>

<style scoped></style>
