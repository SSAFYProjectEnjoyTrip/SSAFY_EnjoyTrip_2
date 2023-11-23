<script setup>
import { ref, watch, onMounted } from 'vue'
import defaultImage from '@/assets/map/marker.png'
import planeImage from '@/assets/map/planeImage.png'
import departureImg from '@/assets/departure.png'
import arrivalImg from '@/assets/arrival.png'

var map
const positions = ref([])
const markers = ref([])

const props = defineProps({ attractions: Array, selectAttraction: Object, myList: Array })

watch(
  () => props.selectAttraction.value,
  () => {
    // 이동할 위도 경도 위치를 생성합니다
    var moveLatLon = new kakao.maps.LatLng(
      props.selectAttraction.latitude,
      props.selectAttraction.longitude
    )

    // 지도 중심을 부드럽게 이동시킵니다
    // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
    map.panTo(moveLatLon)
  },
  { deep: true }
)

onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    initMap()
    drawLine()
  } else {
    const script = document.createElement('script')
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${
      import.meta.env.VITE_KAKAO_MAP_SERVICE_KEY
    }&libraries=services,clusterer`
    /* global kakao */
    script.onload = () => kakao.maps.load(() => initMap())
    document.head.appendChild(script)
  }
})

watch(
  () => props.attractions.value,
  () => {
    positions.value = []
    props.attractions.forEach((attraction) => {
      let obj = {}
      obj.latlng = new kakao.maps.LatLng(attraction.latitude, attraction.longitude)
      obj.title = attraction.title
      obj.firstImage = attraction.firstImage

      positions.value.push(obj)
    })
    loadMarkers()
  },
  { deep: true }
)

watch(
  () => props.myList,
  () => {
    console.log(props.myList)
    deleteClickLine()
    drawLine(props.myList)
  },
  { deep: true }
)

const initMap = () => {
  const container = document.getElementById('map')
  const options = {
    center: new kakao.maps.LatLng(33.450701, 126.570667),
    level: 3
  }
  map = new kakao.maps.Map(container, options)

  // loadMarkers();
}

const loadMarkers = () => {
  // 현재 표시되어있는 marker들이 있다면 map에 등록된 marker를 제거한다.
  if (markers.value.length > 0) deleteMarkers()

  // 마커 이미지의 이미지 크기 입니다
  const imgSize = new kakao.maps.Size(24, 35)
  const markerImage = new kakao.maps.MarkerImage(defaultImage, imgSize)

  // 마커를 생성합니다
  markers.value = []
  positions.value.forEach((position) => {
    const marker = new kakao.maps.Marker({
      map: map, // 마커를 표시할 지도
      position: position.latlng, // 마커를 표시할 위치
      title: position.title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됨.
      // clickable: true, // // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
      image: markerImage // 마커의 이미지
    })

    kakao.maps.event.addListener(marker, 'click', function () {
      // 인포 윈도우를 생성합니다.
      // const content = ref('하이하이!')
      // const removeable = ref(true)
      console.log(position)

      const infoWindow = new kakao.maps.InfoWindow({
        content: `<div style="width:200px; height: 100px; padding:20px; border-radius: 10px; box-shadow: 0px 0px 10px rgba(0,0,0,0.1);">
                <div style="margin-bottom: 10px; font-weight: bold;">${position.title}</div>
                <img src="${
                  position.firstImage ? position.firstImage : planeImage
                }" alt="Spiral Calendar" style="max-width:50px; height:auto; border-radius: 5px;" />
            </div>`,
        removable: true
      })

      infoWindow.open(map, marker)
    })

    markers.value.push(marker)
  })

  // 4. 지도를 이동시켜주기
  // 배열.reduce( (누적값, 현재값, 인덱스, 요소)=>{ return 결과값}, 초기값);
  const bounds = positions.value.reduce(
    (bounds, position) => bounds.extend(position.latlng),
    new kakao.maps.LatLngBounds()
  )

  map.setBounds(bounds)
}

const deleteMarkers = () => {
  if (markers.value.length > 0) {
    markers.value.forEach((marker) => marker.setMap(null))
  }
}

var drawingFlag = false // 선이 그려지고 있는 상태를 가지고 있을 변수입니다
var clickLine // 마우스로 클릭한 좌표로 그려질 선 객체입니다
var distanceOverlay // 선의 거리정보를 표시할 커스텀오버레이 입니다
var dots = {} // 선이 그려지고 있을때 클릭할 때마다 클릭 지점과 거리를 표시하는 커스텀 오버레이 배열입니다.
var distance

function drawLine(myList) {
  // myList를 기준으로 선을 그립니다
  myList.forEach(function (item, index) {
    var clickPosition = new kakao.maps.LatLng(item.latitude, item.longitude) // myList의 각 항목을 위도와 경도로 변환합니다
    // console.log(clickPosition)

    var imgContent

    if (index == 0) {
      //출발 마커?
      imgContent = departureImg
    } else if (index == myList.length - 1) {
      //마지막 마커?
      imgContent = arrivalImg
    } else {
      imgContent = defaultImage
    }
    if (!drawingFlag) {
      drawingFlag = true

      deleteClickLine()
      deleteDistnce()
      deleteCircleDot()

      clickLine = new kakao.maps.Polyline({
        map: map,
        path: [clickPosition],
        strokeWeight: 3,
        strokeColor: '#db4040',
        strokeOpacity: 1,
        strokeStyle: 'solid'
      })

      displayCircleDot(clickPosition, 0, departureImg)
    } else {
      var path = clickLine.getPath()
      path.push(clickPosition)
      clickLine.setPath(path)

      distance = Math.round(clickLine.getLength())
      displayCircleDot(clickPosition, distance, imgContent)
    }

    if (index === myList.length - 1) {
      // 마지막 항목이면 선 그리기를 종료합니다
      if (drawingFlag) {
        if (dots[dots.length - 1].distance) {
          dots[dots.length - 1].distance.setMap(null)
          dots[dots.length - 1].distance = null
        }

        if (clickLine) {
          // clickLine이 정의되었는지 확인
          path = clickLine.getPath()
          if (path.length > 0) {
            // path가 존재하는지 확인
            distance = Math.round(clickLine.getLength())
            var content = getTimeHTML(distance)

            showDistance(content, path[path.length - 1])
          }
        }
        drawingFlag = false
      }
    }
  })

  // 마우스 드래그로 그려지고 있는 선의 총거리 정보를 표시하거
  // 마우스 오른쪽 클릭으로 선 그리가 종료됐을 때 선의 정보를 표시하는 커스텀 오버레이를 생성하고 지도에 표시하는 함수입니다
  function showDistance(content, position) {
    if (distanceOverlay) {
      // 커스텀오버레이가 생성된 상태이면

      // 커스텀 오버레이의 위치와 표시할 내용을 설정합니다
      distanceOverlay.setPosition(position)
      distanceOverlay.setContent(content)
    } else {
      // 커스텀 오버레이가 생성되지 않은 상태이면

      // 커스텀 오버레이를 생성하고 지도에 표시합니다
      distanceOverlay = new kakao.maps.CustomOverlay({
        map: map, // 커스텀오버레이를 표시할 지도입니다
        content: content, // 커스텀오버레이에 표시할 내용입니다
        position: position, // 커스텀오버레이를 표시할 위치입니다.
        xAnchor: 0,
        yAnchor: 0,
        zIndex: 3
      })
    }
  }

  // 그려지고 있는 선의 총거리 정보와
  // 선 그리가 종료됐을 때 선의 정보를 표시하는 커스텀 오버레이를 삭제하는 함수입니다
  function deleteDistnce() {
    if (distanceOverlay) {
      distanceOverlay.setMap(null)
      distanceOverlay = null
    }
  }

  // 선이 그려지고 있는 상태일 때 지도를 클릭하면 호출하여
  // 클릭 지점에 대한 정보 (동그라미와 클릭 지점까지의 총거리)를 표출하는 함수입니다
  function displayCircleDot(position, distance, imageSrc) {
    // 클릭 지점을 표시할 빨간 동그라미 커스텀오버레이를 생성합니다
    var circleOverlay = new kakao.maps.CustomOverlay({
      content: `<span style="background-image: url(${imageSrc})"></span>`,
      position: position,
      zIndex: 1
    })

    // 지도에 표시합니다
    circleOverlay.setMap(map)

    if (distance > 0) {
      // 클릭한 지점까지의 그려진 선의 총 거리를 표시할 커스텀 오버레이를 생성합니다
      var distanceOverlay = new kakao.maps.CustomOverlay({
        content: '<div class="dotOverlay">거리 <span class="number">' + distance + '</span>m</div>',
        position: position,
        yAnchor: 1,
        zIndex: 2
      })

      // 지도에 표시합니다
      distanceOverlay.setMap(map)
    }

    // 배열에 추가합니다
    dots.push({ circle: circleOverlay, distance: distanceOverlay })
  }

  // 클릭 지점에 대한 정보 (동그라미와 클릭 지점까지의 총거리)를 지도에서 모두 제거하는 함수입니다
  function deleteCircleDot() {
    var i

    for (i = 0; i < dots.length; i++) {
      if (dots[i].circle) {
        dots[i].circle.setMap(null)
      }

      if (dots[i].distance) {
        dots[i].distance.setMap(null)
      }
    }

    dots = []
  }

  // 마우스 우클릭 하여 선 그리기가 종료됐을 때 호출하여
  // 그려진 선의 총거리 정보와 거리에 대한 도보, 자전거 시간을 계산하여
  // HTML Content를 만들어 리턴하는 함수입니다
  function getTimeHTML(distance) {
    // 도보의 시속은 평균 4km/h 이고 도보의 분속은 67m/min입니다
    var walkkTime = (distance / 67) | 0
    var walkHour = '',
      walkMin = ''

    // 계산한 도보 시간이 60분 보다 크면 시간으로 표시합니다
    if (walkkTime > 60) {
      walkHour = '<span class="number">' + Math.floor(walkkTime / 60) + '</span>시간 '
    }
    walkMin = '<span class="number">' + (walkkTime % 60) + '</span>분'

    // 자전거의 평균 시속은 16km/h 이고 이것을 기준으로 자전거의 분속은 267m/min입니다
    var bycicleTime = (distance / 227) | 0
    var bycicleHour = '',
      bycicleMin = ''

    // 계산한 자전거 시간이 60분 보다 크면 시간으로 표출합니다
    if (bycicleTime > 60) {
      bycicleHour = '<span class="number">' + Math.floor(bycicleTime / 60) + '</span>시간 '
    }
    bycicleMin = '<span class="number">' + (bycicleTime % 60) + '</span>분'

    // 거리와 도보 시간, 자전거 시간을 가지고 HTML Content를 만들어 리턴합니다
    var content = '<ul class="dotOverlay distanceInfo">'
    content += '    <li>'
    content +=
      '        <span class="label">총거리</span><span class="number">' + distance + '</span>m'
    content += '    </li>'
    content += '    <li>'
    content += '        <span class="label">도보</span>' + walkHour + walkMin
    content += '    </li>'
    content += '    <li>'
    content += '        <span class="label">자전거</span>' + bycicleHour + bycicleMin
    content += '    </li>'
    content += '</ul>'

    return content
  }
}

// 클릭으로 그려진 선을 지도에서 제거하는 함수입니다
function deleteClickLine() {
  if (clickLine) {
    clickLine.setMap(null)
    clickLine = null
  }
}
</script>

<template>
  <div id="map"></div>
</template>

<style>
#map {
  width: 100%;
  height: 800px;
}

.dot {
  overflow: hidden;
  float: left;
  width: 12px;
  height: 12px;
  background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/mini_circle.png');
}
.dotOverlay {
  position: relative;
  bottom: 10px;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  float: left;
  font-size: 12px;
  padding: 5px;
  background: #fff;
}
.dotOverlay:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.number {
  font-weight: bold;
  color: #ee6152;
}
.dotOverlay:after {
  content: '';
  position: absolute;
  margin-left: -6px;
  left: 50%;
  bottom: -8px;
  width: 11px;
  height: 8px;
  background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white_small.png');
}
.distanceInfo {
  position: relative;
  top: 5px;
  left: 5px;
  list-style: none;
  margin: 0;
}
.distanceInfo .label {
  display: inline-block;
  width: 50px;
}
.distanceInfo:after {
  content: none;
}
</style>
