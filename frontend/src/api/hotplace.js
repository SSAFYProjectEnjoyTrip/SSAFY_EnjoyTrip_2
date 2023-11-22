import { localAxios } from '@/utils/http-commons'

const local = localAxios()

function searchAttractionList(sidoCode, gugunCode, success, fail) {
  console.log(typeof(sidoCode));
  local.get(`/hotplace/${sidoCode}/${gugunCode}`).then(success).catch(fail)
}

function searchAttractionListByType(areaCode, sigunguCode, contentTypeId, success, fail) {
    local.get(`/hotplace/${areaCode}/${sigunguCode}/${contentTypeId}`).then(success).catch(fail)
}
  
function searchByTitle(areaCode, sigunguCode, title, success, fail) {
    local.get(`/hotplace/${areaCode}/${sigunguCode}/${title}`).then(success).catch(fail)
  }

export { searchAttractionList, searchAttractionListByType, searchByTitle }
