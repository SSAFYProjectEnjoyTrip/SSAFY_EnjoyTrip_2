import { localAxios } from '@/utils/http-commons'

const local = localAxios()

function searchAttractionList(String areaCode, String sigunguCode, success, fail) {
  local.get(`/hotplace/${areaCode}/${sigunguCode}`).then(success).catch(fail)
}

function searchAttractionListByType(String areaCode, String sigunguCode, String contentTypeId, success, fail) {
    local.get(`/hotplace/${areaCode}/${sigunguCode}/${contentTypeId}`).then(success).catch(fail)
}
  
function searchByTitle(String areaCode, String sigunguCode, String title, success, fail) {
    local.get(`/hotplace/${areaCode}/${sigunguCode}/${title}`).then(success).catch(fail)
  }

export { listAttraction }
