<script setup></script>

<template>
  <div class="main-title">
    <div class="title-text">
      어디로 !!!!!!!!!! 떠나시겠슴니까!!!!!!
      <img src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Smilies/Ghost.png" alt="Ghost" width="150" height="150" />
    </div>
  </div>
  <div class="line">
    <div class="img1">
      <img src=" https://source.unsplash.com/random/?seoul" width="100%" height="300" />
      <div class="img-info">서울</div>
    </div>
    <div class="img1">
      <img src=" https://source.unsplash.com/random/?jeju" width="100%" height="300" />
      <div class="img-info">제주도</div>
    </div>
    <div class="img1">
      <img src=" https://source.unsplash.com/random/?busan" width="100%" height="300" />
      <div class="img-info">부산</div>
    </div>
  </div>
  <div class="line">
    <div class="img1">
      <img src=" https://source.unsplash.com/random/?eastsea" width="100%" height="300" />
      <div class="img-info">동해</div>
    </div>
    <div class="img1">
      <img src=" https://source.unsplash.com/random/?gyeongbokgung" width="100%" height="300" />
      <div class="img-info">경복궁</div>
    </div>
    <div class="img1">
      <img src=" https://source.unsplash.com/random/?gangwondo" width="100%" height="300" />
      <div class="img-info">강원도</div>
    </div>
  </div>

  <!-- <img src=" https://source.unsplash.com/random/?seoul" width="33%" height="400" />
  <img src=" https://source.unsplash.com/random/?jeju" width="33%" height="400" />
  <img src=" https://source.unsplash.com/random/?busan" width="33%" height="400" />
  <img src=" https://source.unsplash.com/random/?eastsea" width="33%" height="400" />
  <img src=" https://source.unsplash.com/random/?gyeongbokgung" width="33%" height="400" />
  <img src=" https://source.unsplash.com/random/?gangwondo" width="33%" height="400" /> -->
    <div class="bottom"></div>
</template>

<style scoped>
.main-title {
  padding-top: 100px;
  display: flex;
  justify-content: center;
  margin-bottom: 100px;
}
.title-text {
  font-size: 40px;
  font-weight: bold;
  color: #80B3FF;
}
.line {
  display: flex;
  justify-content: center;
  gap: 2%;
  margin-bottom: 50px;
}
.img1 {
  border: 1px solid gray;
  width: 25%;
  height: 400px;
  border-radius: 30px;
  overflow: hidden;
  box-shadow: 0 0 2px;
}
.img1:hover {
  border: 2px solid gray;
}
.img-info {
  padding: 10px;
  padding-top: 24px;
  text-align: center;
  font-size: 30px;
  font-weight: bold;
  color: grey;
}
.bottom {
  margin-bottom: 100px;
}

</style>
