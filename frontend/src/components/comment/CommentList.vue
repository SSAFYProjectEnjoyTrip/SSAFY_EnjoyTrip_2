<!-- CommentList.vue -->
<script setup>
import { ref, onBeforeMount, onMounted } from 'vue';
import { useRouter, useRoute } from 'vue-router'
import CommentListItem from './item/CommentListItem.vue';
import { listComment } from '@/api/comment';
const route = useRoute()

const props = defineProps(['postId']);
const commentList = ref({
  comment: [],
});
// const { articleNo } = route.params

// const commentList = ref({
//   commentNo: '',
//   articleNo: '',
//   userId: '',
//   commentContend: '',
//   registerTime: ''
// })

console.log('CommentList.setup......')
// commentList.value = searchList()
// console.log(commentList.value)
onMounted(() => {
  // searchList()
  commentList.value = searchList()
  console.log(commentList.value)
})

function searchList() {
  console.log('commentSearch params............: ', typeof(props.postId))

  listComment(props.postId)
  .then((comments) => {
    // 성공적으로 댓글을 받아왔을 때의 처리
    console.log('댓글 목록:', comments);
    console.log('댓글 목록:', comments.value);
    return comments;
  })
  .catch((error) => {
    // 댓글을 받아오는 중 에러가 발생했을 때의 처리
    console.error('댓글 목록을 받아오는 중 에러 발생:', error);
  });

  // listComment(
  //   props.postId,
  //   ({ response }) => {
  //     console.log('commentSearch......result: ', response.data)
  //     commentList.value = response.data
  //   },
  //   (error) => {
  //     console.log(error)
  //   }
  // )
}
</script>

<template>
  <div>
    <h3>댓글</h3>
    <CommentListItem v-for="comment in comments" :key="comment.commentNo" :comment="comment" />
  </div>
</template>
