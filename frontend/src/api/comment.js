import { localAxios } from '@/utils/http-commons'

const local = localAxios()

function listComment(articleNo, success, fail) {
  local.get(`/comment/${articleNo}`).then(success).catch(fail)
}
export { listComment }
