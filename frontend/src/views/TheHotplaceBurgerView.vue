<script setup>
import { ref, onMounted, defineComponent, reactive, watch } from 'vue'
import { listSido, listGugun } from '@/api/map'
import { storeToRefs } from 'pinia'
import { getZzimList, addZzimList, deleteZzim } from '@/api/zzim'
import { searchAttractionList, searchAttractionListByType, searchByTitle } from '@/api/hotplace'
import VKakaoMap from '@/components/common/VKakaoMap.vue'
import VSelect from '@/components/common/VSelect.vue'
import { VueDraggableNext } from 'vue-draggable-next'
import { Slide } from 'vue3-burger-menu'
import { useMemberStore } from '@/stores/member'
import { useRouter } from 'vue-router';
import planeImage from '@/assets/map/planeImage.png'
import { addPlan } from '@/api/myplan'

import VueDatePicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css'
import { el } from 'date-fns/locale'

const router = useRouter();

const leftMenuOpen = ref(false)
const rightMenuOpen = ref(false)

const toggleLeftMenu = () => {
  leftMenuOpen.value = !leftMenuOpen.value
}

const toggleRightMenu = () => {
  rightMenuOpen.value = !rightMenuOpen.value
}

//탭 전환을 위한 변수
const currentTab = ref('result')

//검색을 위한 변수
const sidoList = ref([])
const gugunList = ref([{ text: '구군선택', value: '' }])

const attractionInfos = ref([])
const selectAttraction = ref({})
const memberStore = useMemberStore()
const { userInfo } = storeToRefs(memberStore)

const selected = ref({
  sido_code: 0,
  gugun_code: 0
})

//찜 목록을 위한 변수
const zzimList = ref([])

//뷰 draggable을 위한 변수
const draggable = defineComponent(VueDraggableNext)

//찜 버튼 하트변환을 위한 변수
const isClickable = reactive(attractionInfos.value.reduce((acc, cur) => ({ ...acc, [cur]: true }), {}));

//일정 제목을 위한 변수
const title = ref('');
const showView = ref(false);

const changeView = () => {
  showView.value = true;
};

const editTitle = () => {
  showView.value = false;
};

const log = (event) => {
  console.log(event)
}


// watch(selectedDate, (newVal, oldVal) => {
//   console.log(newVal);
// });

onMounted(() => {
  getSidoList()
})

const getSidoList = () => {
  listSido(
    ({ data }) => {
      let options = []
      options.push({ text: '시도선택', value: '' })
      data.forEach((sido) => {
        options.push({ text: sido.sido_name, value: sido.sido_code })
      })
      sidoList.value = options
    },
    (err) => {
      console.log(err)
    }
  )
}

const onChangeSido = (val) => {
  selected.value.sido_code = val

  listGugun(
    selected.value.sido_code,
    ({ data }) => {
      let options = []
      options.push({ text: '구군선택', value: '' })
      data.forEach((gugun) => {
        options.push({ value: gugun.gugun_code, text: gugun.gugun_name })
      })
      gugunList.value = options
      console.log('선택한 구군은......', gugunList.value)
    },
    (err) => {
      console.log(err)
    }
  )
}

const onChangeGugun = (val) => {
  selected.value.gugun_code = val
  getAttractionInfo()
}

const getAttractionInfo = () => {
  searchAttractionList(
    selected.value.sido_code,
    selected.value.gugun_code,
    ({ data }) => {
      attractionInfos.value = data
      console.log(attractionInfos.value)
    },
    (err) => {
      console.log(err)
    }
  )
}

const viewAttraction = (attraction) => {
  selectAttraction.value = attraction
}

const myList = ref([])

function deleteAttraction(contentId) {
  const index = myList.value.findIndex((item) => item.contentId === contentId)
  if (index !== -1) {
    myList.value.splice(index, 1)
  }
}

//찜!!!!!!!!!!!!!!!!!목록 관련
function addToZzim(element) {  

  isClickable[element.contentId] = !isClickable[element.contentId];

  const zzimTmp = ref({
    userId: userInfo.value.userId,
    contentId: element.contentId,
    firstImage: element.firstImage,
    addr1: element.addr1,
    title: element.title,
    latitude: element.latitude,
    longitude: element.longitude
  })

  addZzimList(
    zzimTmp.value,
    (response) => {
      let msg = '찜목록 등록 처리 시 문제 발생했습니다.'
      if (response.status == 200) msg = '찜목록 등록 완료'
      alert(msg)
    },
    (error) => {
      console.log(error)
    }
  )
}

function searchZzimList() {
  getZzimList(
    userInfo.value.userId,
    ({ data }) => {
      zzimList.value = data
      console.log(attractionInfos.value)
    },
    (err) => {
      console.log(err)
    }
  )
}

function deleteZzimList(element) {
  isClickable[element.contentId] = !isClickable[element.contentId];

  deleteZzim(
    userInfo.value.userId,
    element.contentId,
    (response) => {
      let msg = '찜목록 삭제 처리 시 문제 발생했습니다.'
      if (response.status == 200) msg = '찜목록 삭제 완료'
      alert(msg)
      searchZzimList()
    },
    (error) => {
      console.log(error)
    }
  )
}

//마이 플랜 관련!!!!!!!!!!!!!!!!!!!!
const goToTheMyPlanView = () => {
  if (!myList.value || myList.value.length === 0) {
    alert("마이플랜이 비어있습니다");
  } else {
    insertPlan();
    router.push({ name: 'myplan' });
  }
};

const planTmp = ref({
    userId: userInfo.value.userId,
    contentId: 0,
    firstImage: '',
    addr1: '',
    title: '',
    latitude: 0.0,
    longitude: 0.0,
    travelTime: ''
  })

const insertPlan = () => {
  myList.value.forEach((element) => {
  console.log("어케 나오는건데!!!", element); // 예시: 콘솔에 원소 출력
  // planTmp.value.contentId = element.contentId;
  // planTmp.value.firstImage = element.firstImage;
  // planTmp.value.addr1 = element.addr1;
  // planTmp.value.title = element.title;
  // planTmp.value.latitude = element.latitude;
  // planTmp.value.longitude = element.longitude;
  // planTmp.value.travelTime = element.selectedDate;

  // console.log("엥??", planTmp);

  addPlan(
    element,
        (response) => {
      let msg = '마이플랜 등록 시 문제 발생했습니다.'
      if (response.status == 200) msg = '마이플랜 등록 완료'
      alert(msg)
    },
    (error) => {
      console.log(error)
    }
    )
}

);
  }
</script>

<template>
  <div class="container text-center mt-3">
    <div class="menu-btn-container">
      <img src="@/assets/menu.png" @click="toggleLeftMenu" alt="" width="40" />
      <div class="small-info">
        나만의 여행계획을 세워보세요
        <img
          src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Animals/Bear.png"
          alt="Bear"
          width="40"
          height="40"
        />
      </div>
      <img src="@/assets/menu.png" @click="toggleRightMenu" alt="" width="40" />
    </div>
    <div>
      <Slide
        class="left-sidebar"
        width="420"
        :isOpen="leftMenuOpen"
        :noOverlay="true"
        disableOutsideClick
        left
      >
        <div class="for-left-menu">
          <!-- <button @click="toggleLeftMenu" class="menu-button right">ㅇ</button> -->
          <img src="@/assets/menu.png" @click="toggleLeftMenu" alt="" width="40" />
        </div>
        <div>
          <div class="left-title" style="margin-top: 0px; a; margin-right: 30px;">
            <img
              src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Objects/Round%20Pushpin.png"
              alt="Round Pushpin"
              width="40"
              height="40"
            />
            where to go
          </div>
          <form>
            <div class="dropdown-box">
              <!-- 시도, 구군 선택 드롭다운 -->
              <VSelect :selectOptions="sidoList" @onKeySelect="onChangeSido"></VSelect>
              <VSelect :selectOptions="gugunList" @onKeySelect="onChangeGugun"></VSelect>
            </div>
            <div class="search-box" style="display: flex">
              <input type="text" class="input-search" placeholder="Search" />
              <button class="btn btn-default" type="submit" style="height: 40px">
                <img
                  src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Objects/Magnifying%20Glass%20Tilted%20Left.png"
                  alt="Magnifying Glass Tilted Left"
                  width="25"
                  height="25"
                />
              </button>
            </div>
          </form>
          <hr />
          <div>
            <ul class="nav-tabs">
              <li
                @click="currentTab = 'result'"
                :class="{ active: currentTab === 'zzim' }"
                active-class="active"
              >
                조회 결과
              </li>
              <li
                @click="(currentTab = 'zzim'), searchZzimList()"
                :class="{ active: currentTab === 'result' }"
                active-class="active"
              >
                찜 목록
              </li>
            </ul>
            <!-- 조회 결과 -->
            <table v-if="currentTab === 'result'" class="table table-hover">
              <draggable
                class="dragArea list-group"
                :list="attractionInfos"
                :group="{ name: 'people', pull: 'clone', put: false }"
                @change="log"
                item-key="contentId"
              >
                <div
                  class="list-group-item bg-gray-300 m-1 p-3 rounded-md text-center"
                  v-for="element in attractionInfos"
                  :key="element.contentId"
                  @click="viewAttraction(element)"
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
                  <button @click="addToZzim(element)">
                    <img v-if="!isClickable[element.contentId]" src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Smilies/Black%20Heart.png" alt="Black Heart" width="25" height="25" />
                    <img v-else src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Smilies/Beating%20Heart.png" alt="Beating Heart" width="25" height="25" >
                    찜
                  </button>
                </div>
              </draggable>
            </table>
            <!-- 찜목록 -->
            <table v-else>
              <draggable
                class="dragArea list-group"
                :list="zzimList"
                :group="{ name: 'people', pull: 'clone', put: false }"
                @change="log"
                item-key="contentId"
              >
              <div
                  class="list-group-item bg-gray-300 m-1 p-3 rounded-md text-center"
                  v-for="element in zzimList"
                  :key="element.contentId"
                  @click="viewAttraction(element)"
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
                  <button @click="deleteZzimList(element)">삭제</button>
                </div>
              </draggable>
            </table>
          </div>
        </div>
      </Slide>
      <VKakaoMap
        :attractions="attractionInfos"
        :selectAttraction="selectAttraction"
        :myList="myList"
        class="map"
      />
    </div>

    <Slide
      class="right-sidebar"
      width="400"
      :isOpen="rightMenuOpen"
      :noOverlay="true"
      disableOutsideClick
      right
    >
      <!-- Right side menu content -->
      <div class="for-right-menu" style="margin-top: 80px">
        <img src="@/assets/menu.png" @click="toggleRightMenu" alt="" width="40" />
      </div>
      <div class="right-top">
        <img
          src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Objects/Spiral%20Calendar.png"
          alt="Spiral Calendar"
          width="100"
          height="100"
        />
      </div>
      <div>
    <input
      v-model="title"
      type="text"
      id="myListTitleInput"
      placeholder="제목을 입력하세요"
      @keyup.enter="changeView"
      v-if="!showView"
    >
    <div id="myListTitleDisplay" v-if="showView" @click="editTitle">{{ title }}</div>
  </div>

      <hr style="margin-right: 20px" />
      <draggable
        class="dragArea list-group"
        :list="myList"
        group="people"
        @change="log"
        :direction="horizontal"
        item-key="contentId"
      >
        <div
          class="list-group-item"
          v-for="element in myList"
          :key="element.contentId"
          style="margin-bottom: 10px"
          @click="viewAttraction(element)"
        >
          <button
            @click="deleteAttraction(element.contentId)"
            style="border: none; background-color: white; margin-left: 90%"
          >
            <img
            src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Symbols/Cross%20Mark.png"
              alt="Cross Mark"
              width="20"
              height="20"
            />
          </button>
          <div style="color: #265073; margin-bottom: 10px">
            <b>{{ element.title }}</b>
          </div>
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
          <div
            style="
              font-size: 15px;
              width: 200px;
              display: block;
              margin: 0 auto;
              margin-top: 4px;
              margin-bottom: 20px;
            "
          >
            {{ element.addr1 }}
            <!-- 여기가 바로 날짜!!!!!!!!!!!!!!!!!!!!!!!!1 -->
          <VueDatePicker v-model="element.selectedDate" :enable-time-picker="false"></VueDatePicker>
          </div>
        </div>
      </draggable>
      <button id="myListSubmitBtn" @click="goToTheMyPlanView(myList)">계획 완성</button>
    </Slide>
  </div>
</template>

<style>
.small-info {
  font-weight: bold;
  font-size: 20px;
  color: #265073;
  margin-top: 5px;
}
.small-info > img {
  margin-bottom: 5px;
}
.right-sidebar .bm-menu,
.left-sidebar .bm-menu {
  background-color: #eef5ff;
  padding-top: 10px;
}
.right-sidebar .bm-item-list > *,
.left-sidebar .bm-item-list > * {
  display: flex;
  flex-direction: column;
  text-decoration: none;
  margin-bottom: 10px;
}
.list-group-item {
  border-radius: 10px;
}
.list-group-item:hover {
  border: 2px solid lightgrey;
}
.left-item-title {
  font-weight: bold;
}
/* <!-- right --> */

.right-top > img {
  display: block;
  margin: 0 auto;
}

/* 메뉴버튼 */
.menu-btn-container {
  display: flex;
  justify-content: space-between;
  height: 50px;
}
.for-left-menu {
  margin-top: 80px;
}
.for-left-menu > img,
.for-right-menu > img {
  display: block;
  margin: 0 auto;
}

/* 탭 두개로 나누기? */
.nav-tabs {
  list-style-type: none;
  display: flex;
  justify-content: space-around;
  margin: 0;
  padding: 0;
}

.nav-link {
  padding: 10px 20px;
  text-decoration: none;
  color: black;
  border-radius: 4px;
}

.nav-link:hover {
  background-color: #eee;
}

.active {
  background-color: #ddd;
}

#myListTitleInput {
  width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    box-sizing: border-box;
    border: 2px solid #ccc;
    border-radius: 4px;
    background-color: #f8f8f8;
    font-size: 16px;
}

#myListTitleDisplay {
  padding: 10px;
    margin-top: 10px;
    border: 1px solid #ddd;
    border-radius: 5px;
    background-color: #f9f9f9;
    font-size: 18px;
    color: #333;
}

#myListSubmitBtn {
  display: inline-block;
    padding: 10px 20px;
    margin: 10px 0;
    font-size: 18px;
    color: #fff;
    background-color: #5CCF50;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    text-align: center;
    text-decoration: none;
}
</style>
