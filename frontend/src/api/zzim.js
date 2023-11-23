import { localAxios } from '@/utils/http-commons'

const local = localAxios()

function getZzimList(user_id, success, fail) {
  local.get(`/zzim/${user_id}`).then(success).catch(fail)
}

function addZzimList(zzim, success, fail) {
  local.post(`/zzim`, JSON.stringify(zzim)).then(success).catch(fail)
}

function deleteZzim(user_id, content_id, success, fail) {
  local.delete(`/zzim/${user_id}/${content_id}`).then(success).catch(fail)
}

export { getZzimList, addZzimList, deleteZzim }
