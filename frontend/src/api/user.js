import { localAxios } from '@/utils/http-commons'

const local = localAxios()

async function userConfirm(param, success, fail) {
  console.log('param', param)
  await local.post(`/user/login`, param).then(success).catch(fail)
  console.log('userConfirm ok??????')
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
  // console.log("로그아웃 잘 호출 되었나요??>> ", userid)
  await local.get(`/user/logout/${userid}`).then(success).catch(fail)
}

async function updateUser(user, success, fail) {
  local.post(`/user/update`, JSON.stringify(user)).then(success).catch(fail)
}

async function deleteUser(userId, success, fail) {
  await local.delete(`/user/${userId}`).then(success).catch(fail)
}

async function updatePwd(param, success, fail) {
  console.log('param', param)
  await local.put(`/user/updatePwd`, param).then(success).catch(fail)
  console.log('updatePwd가 await까지 했긴했음')
}

export {
  userConfirm,
  findById,
  registUser,
  tokenRegeneration,
  logout,
  updateUser,
  deleteUser,
  updatePwd
}
