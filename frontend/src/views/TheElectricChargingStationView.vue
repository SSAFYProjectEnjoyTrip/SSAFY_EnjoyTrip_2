<script setup>
import { ref, onMounted } from "vue";
import { listStations } from "@/api/estation";
import { listSido, listGugun } from "@/api/map";

import VKakaoMap from "@/components/common/VKakaoMap.vue";
import VSelect from "@/components/common/VSelect.vue";

// const serviceKey = import.meta.env.VITE_OPEN_API_SERVICE_KEY;
const { VITE_OPEN_API_SERVICE_KEY } = import.meta.env;

const sidoList = ref([]);
const gugunList = ref([{ text: "구군선택", value: "" }]);
const chargingStations = ref([]);
const selectStation = ref({});

const param = ref({
  serviceKey: VITE_OPEN_API_SERVICE_KEY,
  pageNo: 1,
  numOfRows: 20,
  zscode: 0,
});

onMounted(() => {
  // getChargingStations();
  getSidoList();
});

const getSidoList = () => {
  listSido(
    ({ data }) => {
      let options = [];
      options.push({ text: "시도선택", value: "" });
      data.forEach((sido) => {
        options.push({ text: sido.sidoName, value: sido.sidoCode });
      });
      sidoList.value = options;
    },
    (err) => {
      console.log(err);
    }
  );
};

const onChangeSido = (val) => {
  listGugun(
    { sido: val },
    ({ data }) => {
      let options = [];
      options.push({ text: "구군선택", value: "" });
      data.forEach((gugun) => {
        options.push({ text: gugun.gugunName, value: gugun.gugunCode });
      });
      gugunList.value = options;
    },
    (err) => {
      console.log(err);
    }
  );
};

const onChangeGugun = (val) => {
  param.value.zscode = val;
  getChargingStations();
};

const getChargingStations = () => {
  listStations(
    param.value,
    ({ data }) => {
      chargingStations.value = data.items[0].item;
    },
    (err) => {
      console.log(err);
    }
  );
};

const viewStation = (station) => {
  selectStation.value = station;
};
</script>

<template>
  <!-- 시도, 구군 선택 드롭다운 -->
  <VSelect :selectOptions="sidoList" @onKeySelect="onChangeSido"></VSelect>
  <VSelect :selectOptions="gugunList" @onKeySelect="onChangeGugun"></VSelect>
  
  <div class="container text-center mt-3">
    <div class="title">
      My Plan
      <img src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Animals/Front-Facing%20Baby%20Chick.png" alt="Front-Facing Baby Chick" width="100" height="100" />
    </div>
    <div class="title-info">나만의 여행계획을 세워보세요 :)</div>
    <div style="display: flex;" >
      <div class="left-side">
        <div class="left-title">
          <img src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Objects/Round%20Pushpin.png" alt="Round Pushpin" width="40" height="40" />
          Where to go
        </div>
        <form>
          <div class="dropdown-box">
            <div class="dropdown">
              <button type="button" class="dropdown-toggle " data-bs-toggle="dropdown">
                시도선택
              </button>
              <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="#">Link 1</a></li>
                <li><a class="dropdown-item" href="#">Link 2</a></li>
                <li><a class="dropdown-item" href="#">Link 3</a></li>
              </ul>
            </div>
            <div class="dropdown">
              <button type="button" class="dropdown-toggle" data-bs-toggle="dropdown">
                구군선택
              </button>
              <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="#">Link 1</a></li>
                <li><a class="dropdown-item" href="#">Link 2</a></li>
                <li><a class="dropdown-item" href="#">Link 3</a></li>
              </ul>
            </div>
          </div>
          <div class="search-box">
            <input type="text" class="input-search" placeholder="Search">
            <button class="btn btn-default" type="submit">
              <img src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Objects/Magnifying%20Glass%20Tilted%20Left.png" alt="Magnifying Glass Tilted Left" width="25" height="25" />
            </button>
          </div>
        </form>
        <hr>
      </div>
      <VKakaoMap :stations="chargingStations" :selectStation="selectStation" />
    </div>
    <div class="down-box" >
      <div class="down-left">
        <img src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Objects/Spiral%20Calendar.png" alt="Spiral Calendar" width="100" height="100" />
      </div>
      <div class="down-right">

      </div>
    </div>
    <div class="info">
      <table class="table table-hover">
        <thead>
          <tr class="text-center">
            <th scope="col">관광지</th>
            <th scope="col">위치</th>
            <th scope="col">위도</th>
            <th scope="col">경도</th>
          </tr>
        </thead>
        <tbody>
          <tr
            class="text-center"
            v-for="station in chargingStations"
            :key="station.statId + station.chgerId"
            @click="viewStation(station)"
          >
            <td>{{ station.stat }}</td>
            <td>{{ station.addr }}</td>
            <td>{{ station.lat }}</td>
            <td>{{ station.lng }}</td>
          </tr>
        </tbody>
      </table>
      필요없음 사실 그냥 내가 보기 위해 만들어놓은 임시
    </div>
  </div>
</template>

<style>
mark.purple {
  background: linear-gradient(to top, #c354ff 20%, transparent 30%);
}
.title {
  height: 100px;
  margin-top: 50px;
  font-size: 50px;
  font-weight: bold;
  font-family: Verdana, Geneva, Tahoma, sans-serif;
  color: #F875AA;
  text-shadow: 1px 1px 2px #F875AA;
}
.title-info {
  margin-top: 10px;
  margin-bottom: 50px;
  color: #a8a8a8;
  font-weight: bold;
  font-size: 14px;
}
.left-side {
  border: 1px solid lightgray;
  width: 40%;
}
.left-title {
  margin-top: 50px;
  margin-bottom: 50px;
  font-size: 30px;
  font-weight: bolder;
  font-family: Verdana, Geneva, Tahoma, sans-serif;
  color: #64CCC5;
  text-shadow: 1px 1px 2px #64CCC5;
}
.down-box {
  display: flex;
  height: 300px;
  border: 2px solid lime;
  margin-top: 20px;
}
.down-left {
  width: 300px;
  border: 2px solid pink;
}
.info {
  height: 200px;
  border: 2px solid blue;
  margin-bottom: 100px;
}
.dropdown {
  width: 160px;
}
.dropdown-toggle {
  background-color: white;
  border: 1px solid lightgray;
  border-radius: 5px;
  width: 100%;
  height: 40px;
}
.dropdown-box {
  display: flex; 
  justify-content: center; 
  gap: 10px;
  margin-bottom: 10px;
}
.input-search {
  border: 1px solid lightgray;
  border-radius: 5px;
  width: 280px;
  height: 40px;
  padding-left: 15px;
  margin-bottom: 20px;
}
</style>
