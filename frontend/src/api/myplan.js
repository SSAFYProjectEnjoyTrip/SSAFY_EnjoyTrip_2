import { localAxios } from '@/utils/http-commons'

const local = localAxios()

function getPlanList(userId, success, fail) {
  local.get('/myplan/{userId}', userId).then(success).catch(fail)
}

function addPlan(myplan, success, fail) {
  local.post(`/myplan`, JSON.stringify(myplan)).then(success).catch(fail)
}

export { getPlanList, addPlan }
