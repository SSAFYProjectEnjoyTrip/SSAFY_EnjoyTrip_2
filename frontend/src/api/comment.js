// comment.js
import { localAxios } from '@/utils/http-commons'
const local = localAxios()

// function listComment(articleNo, success, fail) {
//   // local.get(`/comment/${articleNo}`).then(success).catch(fail)
//   comments.value = local.get(`/comment/${articleNo}`).then(success).catch(fail)
//   console.log("왜 안나올까?", comments.value)
//   return comments;
// }

async function listComment(articleNo) {
  await local.get(`/comment/${articleNo}`).then((result) => console.log("then에서 찍어봤다 : ",result.data));
  // try {
  //   await local.get(`/comment/${articleNo}`).then((result) => console.log("then에서 찍어봤다 : ",result));
  //   // const response = await local.get(`/comment/${articleNo}`).then((result) => console.log(result));
  // //   const data = response.data;

  // //   return data.comments;
  // } catch (error) {
  // //   console.error('댓글 목록을 불러오는 중 에러 발생:', error);
  //   throw error; // 실패한 경우 예외를 다시 던져서 상위 수준에서 처리할 수 있도록 함
  // }
}
export { listComment }
