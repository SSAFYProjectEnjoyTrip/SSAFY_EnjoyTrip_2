<script setup>
import { ref, onMounted, defineComponent } from 'vue'
import { listSido, listGugun } from '@/api/map'
import { searchAttractionList, searchAttractionListByType, searchByTitle } from '@/api/hotplace'
import VKakaoMap from '@/components/common/VKakaoMap.vue'
import VSelect from '@/components/common/VSelect.vue'
import { VueDraggableNext } from 'vue-draggable-next'
import { Slide } from 'vue3-burger-menu'

const leftMenuOpen = ref(false)
const rightMenuOpen = ref(false)

const toggleLeftMenu = () => {
  leftMenuOpen.value = !leftMenuOpen.value
}

const toggleRightMenu = () => {
  rightMenuOpen.value = !rightMenuOpen.value
}

// const { VITE_OPEN_API_SERVICE_KEY } = import.meta.env

const sidoList = ref([])
const gugunList = ref([{ text: '구군선택', value: '' }])

const attractionInfos = ref([])
const selectAttraction = ref({})

const selected = ref({
  sido_code: 0,
  gugun_code: 0
})

const draggable = defineComponent(VueDraggableNext)

const log = (event) => {
  console.log(event)
}

onMounted(() => {
  // getChargingStations();
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
      // console.log("선택한 시도는......", sidoList.value)
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
            <table class="table table-hover">
              <!-- <tbody>
              <tr
                class="text-center"
                v-for="attraction in attractionInfos"
                :key="attraction.contentId + attraction.title"
                @click="viewAttraction(attraction)"
              >
                <td>{{ attraction.title }}</td>
                <img :src="attraction.firstImage" alt="Spiral Calendar" width="100" height="100" />
                <td>{{ attraction.addr1 }}</td>
                <button>찜</button>
              </tr>
            </tbody> -->

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
                >
                  <div class="left-item-title" style="color: #265073">{{ element.title }}</div>
                  <div v-if="element.firstImage">
                    <img
                      class="rounded"
                      :src="element.firstImage"
                      alt="Spiral Calendar"
                      width="200"
                      height="150"
                    />
                  </div>
                  <div style="font-size: 15px; width: 200px; display: block; margin: 0 auto">
                    {{ element.addr1 }}
                  </div>
                </div>
              </draggable>
            </table>
          </div>
        </div>
      </Slide>
      <VKakaoMap :attractions="attractionInfos" :selectAttraction="selectAttraction" class="map" />
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
          <div v-if="element.firstImage">
            <img
              class="rounded"
              :src="element.firstImage"
              alt="Spiral Calendar"
              width="200"
              height="150"
            />
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
          </div>
        </div>
      </draggable>
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
</style>
