// comment.js
import { localAxios } from '@/utils/http-commons'
const local = localAxios()

async function listComment(articleNo) {
  try {
    const result = await local.get(`/comment/${articleNo}`);
    return result;
  } catch (error) {
    console.error('Error fetching data:', error);
    throw error;
  }
}
export { listComment }
