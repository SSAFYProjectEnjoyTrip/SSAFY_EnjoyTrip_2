import { localAxios } from '@/utils/http-commons'

const local = localAxios()

async function userConfirm(param, success, fail) {
  console.log('param', param)
  await local.post(`/user/login`, param).then(success).catch(fail)
  console.log('userConfirm ok')
}

async function findById(userId, success, fail) {
  local.defaults.headers['Authorization'] = sessionStorage.getItem('accessToken')
  await local.get(`/user/info/${userId}`).then(success).catch(fail)
}

async function tokenRegeneration(user, success, fail) {
  local.defaults.headers['refreshToken'] = sessionStorage.getItem('refreshToken') //axios header에 refresh-token 셋팅
  await local.post(`/user/refresh`, user).then(success).catch(fail)
}

async function registUser(user, success, fail) {
  local.post(`/user/register`, JSON.stringify(user)).then(success).catch(fail)
}

async function logout(userid, success, fail) {
  await local.get(`/user/logout/${userid}`).then(success).catch(fail)
}

export { userConfirm, findById, registUser, tokenRegeneration, logout }
