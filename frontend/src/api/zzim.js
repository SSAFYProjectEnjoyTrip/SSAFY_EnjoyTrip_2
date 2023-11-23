import { localAxios } from '@/utils/http-commons'

const local = localAxios()

function getZzimList(userId, success, fail) {
  local.get(`/zzim/${userId}`).then(success).catch(fail)
}

function addZzimList(zzim, success, fail) {
  local.post(`/zzim`, JSON.stringify(zzim)).then(success).catch(fail)
}

function deleteZzim(userId, contentId, success, fail) {
  local.delete(`/zzim/${userId}/${contentId}`).then(success).catch(fail)
}

export { getZzimList, addZzimList, deleteZzim }
