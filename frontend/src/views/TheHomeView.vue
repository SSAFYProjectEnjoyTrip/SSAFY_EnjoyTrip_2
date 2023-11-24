<script setup>
const openInNewTab = (url) => {
  window.open(url, '_blank')
}
</script>

<template>
  <div class="main-title">
    <div class="title-text" style="margin-right: 150px">
      Enjoy Trip
      <img
        src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Smilies/Ghost.png"
        alt="Ghost"
        width="150"
        height="150"
      />
    </div>
  </div>
  <div class="gogo" style="margin-left: 130px">최고의 여행을 만들어보세요 :)</div>
  <div class="son">
    Click !
    <a class="nav-link" href="/hotplaceburger">
      <img
        src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Hand%20gestures/Backhand%20Index%20Pointing%20Right%20Light%20Skin%20Tone.png"
        alt="Backhand Index Pointing Right Light Skin Tone"
        width="120"
        height="120"
      />
    </a>
  </div>
  <div
    class="line"
    v-motion
    :initial="{ opacity: 0, y: 100 }"
    :visible="{ opacity: 1, y: 0, scale: 1 }"
    :delay="200"
  >
    <div class="img1">
      <img src=" https://source.unsplash.com/random/?seoul" width="100%" height="300" />
      <a
        href="https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=서울+여행"
        target="_blank"
      >
        <div class="img-info">서울</div></a
      >
      <div class="under">Seoul</div>
    </div>
    <div class="img1">
      <img src=" https://source.unsplash.com/random/?jeju" width="100%" height="300" />
      <a
        href="https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=제주도+여행"
        target="_blank"
      >
        <div class="img-info">제주도</div>
      </a>
      <div class="under">Jeju</div>
    </div>
    <div class="img1">
      <img src=" https://source.unsplash.com/random/?busan" width="100%" height="300" />
      <a
        href="https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=부산+여행"
        target="_blank"
      >
        <div class="img-info">부산</div></a
      >
      <div class="under">Busan</div>
    </div>
  </div>
  <div
    class="line"
    v-motion
    :initial="{ opacity: 0, y: 100 }"
    :visible="{ opacity: 1, y: 0, scale: 1 }"
    :delay="200"
  >
    <div class="img1">
      <img src=" https://source.unsplash.com/random/?eastsea" width="100%" height="300" />
      <a
        href="https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=동해+여행"
        target="_blank"
      >
        <div class="img-info">동해</div></a
      >
      <div class="under">East sea</div>
    </div>
    <div class="img1">
      <img src=" https://source.unsplash.com/random/?gyeongbokgung" width="100%" height="300" />
      <a
        href="https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=경복궁+여행"
        target="_blank"
      >
        <div class="img-info">경복궁</div></a
      >
      <div class="under">Gyeongbokgung</div>
    </div>
    <div class="img1">
      <img src=" https://source.unsplash.com/random/?gangwondo" width="100%" height="300" />
      <a
        href="https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=강원도+여행"
        target="_blank"
      >
        <div class="img-info">강원도</div></a
      >
      <div class="under">Gangwondo</div>
    </div>
  </div>
  <div
    class="line"
    v-motion
    :initial="{ opacity: 0, y: 100 }"
    :visible="{ opacity: 1, y: 0, scale: 1 }"
    :delay="200"
  >
    <div class="img1">
      <img src=" https://source.unsplash.com/random/?danyang" width="100%" height="300" />
      <a
        href="https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=단양+여행"
        target="_blank"
      >
        <div class="img-info">단양</div></a
      >
      <div class="under">Danyang</div>
    </div>
    <div class="img1">
      <img src=" https://source.unsplash.com/random/?gyeongju" width="100%" height="300" />
      <a
        href="https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=경주+여행"
        target="_blank"
      >
        <div class="img-info">경주</div>
      </a>
      <div class="under">Gyeongju</div>
    </div>
    <div class="img1">
      <img src=" https://source.unsplash.com/random/?jeonju" width="100%" height="300" />
      <a
        href="https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=전주+여행"
        target="_blank"
      >
        <div class="img-info">전주</div></a
      >
      <div class="under">Jeonju</div>
    </div>
  </div>

  <!-- <img src=" https://source.unsplash.com/random/?seoul" width="33%" height="400" />
  <img src=" https://source.unsplash.com/random/?jeju" width="33%" height="400" />
  <img src=" https://source.unsplash.com/random/?busan" width="33%" height="400" />
  <img src=" https://source.unsplash.com/random/?eastsea" width="33%" height="400" />
  <img src=" https://source.unsplash.com/random/?gyeongbokgung" width="33%" height="400" />
  <img src=" https://source.unsplash.com/random/?gangwondo" width="33%" height="400" /> -->
  <div class="bottom"></div>
  <div class="bg"></div>
</template>

<style scoped>
.bg {
  width: 100vw;
  height: 100vh;
  position: fixed;
  top: 0;
  left: 0;
  z-index: -10;
  background-color: #ffeed9;
}
.main-title {
  padding-top: 100px;
  display: flex;
  justify-content: center;
  margin-bottom: 100px;
}
.title-text {
  font-size: 100px;
  font-weight: bold;
  color: #ff9130;
  font-family: 'Times New Roman', Times, serif;
  text-shadow: 0 0 5px #fecda6;
}
.line {
  display: flex;
  justify-content: center;
  gap: 2%;
  margin-bottom: 120px;
}
.img1 {
  border: 1px solid gray;
  width: 30%;
  max-width: 300px;
  height: 400px;
  border-radius: 30px;
  overflow: hidden;
  box-shadow: 0 0 2px;
  background-color: white;
}
.img1:hover {
  border: 2px solid rgb(168, 167, 167);
}
.img-info {
  text-decoration: none;
  margin-top: 10px;
  text-align: center;
  font-size: 30px;
  font-weight: bold;
  color: grey;
}
.img-info:hover {
  color: rgb(46, 45, 45);
}
.under {
  text-align: center;
  font-size: 15px;
  font-weight: bold;
  color: rgb(92, 92, 92);
}
.bottom {
  margin-bottom: 100px;
}
a {
  text-decoration: none;
}
/** 최고여행 */
.gogo {
  text-align: center;
  height: 100px;
  font-size: 40px;
  font-weight: bold;
  color: #427d9d;
  margin-bottom: 30px;
}

.son {
  display: flex;
  justify-content: right;
  padding-right: 200px;
  margin-bottom: 100px;

  font-size: 40px;
  font-weight: bold;
  color: #87c4ff;
  font-family: 'Times New Roman', Times, serif;
}
</style>
