<template>
    <div>
        <div
                  class="list-group-item bg-gray-300 m-1 p-3 rounded-md text-center"
                  v-for="element in myPlanList"
                  :key="element.contentId"
                >
                <div class="left-item-title" style="color: #265073">{{ element.title }}</div>
                  <div>
                    <img
                      v-if="element.firstImage"
                      class="rounded"
                      :src="element.firstImage"
                      alt="Spiral Calendar"
                      width="200"
                      height="150"
                    />
                    <img v-else :src="planeImage" alt="보이나요?" width="100" height="100" />
                  </div>
                  <div style="font-size: 15px; width: 200px; display: block; margin: 0 auto">
                    {{ element.addr1 }}
                  </div>
                  <div style="font-size: 15px; width: 200px; display: block; margin: 0 auto">
                    {{ element.travelTime }}
                  </div>
    
                </div>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue';
  import { storeToRefs } from 'pinia'
  import { useMemberStore } from '@/stores/member'
  import { getPlanList } from '@/api/myplan'


  const memberStore = useMemberStore()
  const { userInfo } = storeToRefs(memberStore)

  const myPlanList = ref([])

  onMounted(() => {
    // console.log(props.data.title)
    getPlan()
  })

  const getPlan = () => {
    getPlanList(
        userInfo.value.userId,
        ({ data }) => {
            console.log(data)
            myPlanList.value = data
    },
    (err) => {
      console.log(err)
    }
    )
  }
  </script>

  <style>
.list-group-item {
    border: 1px solid #000; /* 테두리 설정 */
    box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2); /* 그림자 설정 */
}</style>