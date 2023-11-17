<script setup>
import ArticleFormItem from './item/ArticleFormItem.vue'
</script>

<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          <mark class="sky">글쓰기</mark>
        </h2>
      </div>
      <div class="col-lg-10 text-start">
        <ArticleFormItem type="regist" />
      </div>
    </div>
  </div>

  <div class="big-box">
    <div class="title">
      여행 후기를 남겨주세요
      <img
        src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Hand%20gestures/Heart%20Hands%20Medium-Light%20Skin%20Tone.png"
        alt="Heart Hands Medium-Light Skin Tone"
        width="100"
        height="100"
      />
    </div>

    <form action="#" name="rb" id="rb" method="post" enctype="multipart/form-data">
      <input type="hidden" name="ino" value="#" />
      <div class="stars">만족하셨나요?</div>
      <div class="form-group">
        <select class="form-control" id="sel1" name="star">
          <option value="5">★★★★★</option>
          <option value="4">★★★★</option>
          <option value="3">★★★</option>
          <option value="2">★★</option>
          <option value="1">★</option>
        </select>
      </div>

      <div class="share">사진을 공유해주세요</div>
      <!-- <div class="image">
			<img class="rounded" id="preview" src="#" alt="Uploaded Image">
		</div>

		<div class="input-div">
			<input type="file" name="mfilename" id="filename" class="file" onchange="showPreview(event)" required>
		</div> -->

      <input type="text" id="title" name="title" placeholder="Title" required />

      <textarea id="content" name="content" placeholder="Content" required></textarea>

      <div class="btn-div">
        <button type="submit" onclick="review_check()" class="submit-button">리뷰 작성</button>
      </div>
    </form>
  </div>
</template>

<style scoped>
.big-box {
  justify-content: center;
  align-items: center;
  margin: 0 auto;
  margin-top: 100px;
  margin-bottom: 100px;
  width: 60%;
  height: auto;
  padding-top: 50px;
  background-color: #ecf8f9;
  border-radius: 30px;
  border: 3px solid #b8dfd8;
}

input,
textarea {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0 auto;
  width: 50%;
  padding: 10px;
  margin-bottom: 40px;
  border: 3px solid pink;
  border-radius: 6px;
}

textarea {
  height: 200px;
}

.btn-div,
.input-div {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0 auto;
}

.submit-button {
  padding: 10px 20px;
  text-align: center;
  background-color: #64ccc5;
  color: #fff;
  border: none;
  border-radius: 6px;
  margin-top: 50px;
  margin-bottom: 70px;
}

.submit-button:hover {
  background-color: #dafffb;
}
.title {
  width: 100%;
  text-align: center;
  margin-top: 30px;
  margin-bottom: 50px;
  font-size: 35px;
  font-weight: bold;
  color: #64ccc5;
}

.share,
.stars {
  display: flex;
  justify-content: center;
  color: #e966a0;
  font-size: 12px;
  font-weight: bold;
  margin-bottom: 5px;
}
.form-control {
  width: 110px;
  border: 3px solid #ffd89c;
  border-radius: 4px;
}
.form-group {
  display: flex;
  justify-content: center;
  margin-bottom: 40px;
}
</style>
