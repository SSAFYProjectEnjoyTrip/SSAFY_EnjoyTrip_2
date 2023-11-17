<!-- CommentList.vue -->
<template>
  <div>
    <h3>댓글</h3>
    <CommentListItem v-for="comment in comments" :key="comment.commentNo" :comment="comment" />
  </div>
</template>

<script>
import CommentListItem from './CommentListItem.vue'
import { listComment } from '@/api/user'
export default {
  props: {
    postId: String
  },
  data() {
    return {
      comments: []
    }
  },
  mounted() {
    // postId를 기반으로 서버에서 댓글 데이터를 가져옴
    this.fetchComments()
  },
  methods: {
    async fetchComments() {
      console.log('?????????????????????/')
      try {
        listComment(this.postId, ({ data }) => {
          console.log('댓글을 제대로 불러왔나용~ : ', data)
          this.comments = data.comments
        })
      } catch (error) {
        console.error('Error fetching comments:', error)
      }
    }
  },
  components: {
    CommentListItem
  }
}
</script>
