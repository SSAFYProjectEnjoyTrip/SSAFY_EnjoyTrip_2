// comment.js
import { localAxios } from '@/utils/http-commons'
const local = localAxios()

async function listComment(articleNo) {
  try {
    const result = await local.get(`/comment/${articleNo}`)
    return result
  } catch (error) {
    console.error('Error fetching data:', error)
    throw error
  }
}

function registComment(comment, success, fail) {
  local.post(`/comment`, JSON.stringify(comment)).then(success).catch(fail)
}

function modifyComment(comment, success, fail) {
  local.put(`/comment`, JSON.stringify(comment)).then(success).catch(fail)
}

function deleteComment(comment, success, fail) {
  console.log('아니 보냈잖아 : ', comment)
  //Proxy 객체 해제해서 데이터 꺼내오면??
  // comment = {
  //   commentNo: comment.commentNo,
  //   articleNo: comment.articleNo
  //   // registerTime: comment.registerTime,
  //   // userId: comment.userId,
  //   // commentContent: comment.commentContent
  // }
  // local
  //   .delete(`/comment`, { data: { comment: comment } })
  //   .then(success)
  //   .catch(fail)

  //1.
  // local
  //   .delete(`/comment`, {
  //     data: {
  //       commentNo: comment.commentNo,
  //       articleNo: comment.articleNo,
  //       registerTime: comment.registerTime,
  //       userId: comment.userId,
  //       commentContent: comment.commentContent
  //     }
  //   })
  //   .then(success)
  //   .catch(fail)

  //2.
  local
    .delete(`/comment`, { data: { ...comment } })
    .then(success)
    .catch(fail)
}

function searchComment(articleNo, commentNo, success, fail) {
  local.get(`/comment/${articleNo}/${commentNo}`).then(success).catch(fail)
}

export { listComment, registComment, modifyComment, deleteComment, searchComment }
