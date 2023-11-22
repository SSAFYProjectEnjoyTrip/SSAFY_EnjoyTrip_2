import { localAxios } from '@/utils/http-commons'

const local = localAxios()

function listSido(success, fail) {
  local.get(`/map/getSido`).then(success).catch(fail)
}

function listGugun(sidoCode, success, fail) {
  local.get(`/map/getGugun/${sidoCode}`).then(success).catch(fail)
}

export { listSido, listGugun }
