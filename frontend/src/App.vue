<script setup>
import { ref, onMounted, provide } from 'vue';
import { RouterView } from 'vue-router';
import TheHeadingNavBar from '@/components/layout/TheHeadingNavbar.vue';
import TheFooter from '@/components/layout/TheFooter.vue';


// 채널톡을 위한......... 근데 너무 길다
;(function () {
  var w = window
  if (w.ChannelIO) {
    return w.console.error('ChannelIO script included twice.')
  }
  var ch = function () {
    ch.c(arguments)
  }
  ch.q = []
  ch.c = function (args) {
    ch.q.push(args)
  }
  w.ChannelIO = ch
  function l() {
    if (w.ChannelIOInitialized) {
      return
    }
    w.ChannelIOInitialized = true
    var s = document.createElement('script')
    s.type = 'text/javascript'
    s.async = true
    s.src = 'https://cdn.channel.io/plugin/ch-plugin-web.js'
    var x = document.getElementsByTagName('script')[0]
    if (x.parentNode) {
      x.parentNode.insertBefore(s, x)
    }
  }
  if (document.readyState === 'complete') {
    l()
  } else {
    w.addEventListener('DOMContentLoaded', l)
    w.addEventListener('load', l)
  }
})()

ChannelIO('boot', {
  pluginKey: '5c78b295-da5d-453b-87a8-e336385d6e44'
})


</script>

<template>
  <div>
    <TheHeadingNavBar></TheHeadingNavBar>
    <router-view></router-view>
    <TheFooter></TheFooter>
  </div>
</template>

<style scoped></style>
