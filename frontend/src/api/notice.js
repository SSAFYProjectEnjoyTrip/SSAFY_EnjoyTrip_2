import { localAxios } from '@/utils/http-commons'

const local = localAxios()

function listNotice(params, success, fail) {
  local.get('/notice').then(success).catch(fail)
}

function insertNotice(notice, success, fail) {
  local.post(`/notice/insertNotice`, JSON.stringify(notice)).then(success).catch(fail)
}

function modifyNotice(article, success, fail) {
  local.put(`/notice`, JSON.stringify(article)).then(success).catch(fail)
}

function deleteNotice(articleNo, success, fail) {
  local.delete(`/notice/${articleNo}`).then(success).catch(fail)
}

function detailNotice(articleNo, success, fail) {
  local.get(`/notice/${articleNo}`).then(success).catch(fail)
}

export { listNotice, insertNotice, modifyNotice, deleteNotice, detailNotice }
