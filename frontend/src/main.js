// import "./assets/main.css";

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'

import App from './App.vue'
import router from './router'
import VueDatePicker from '@vuepic/vue-datepicker';

import '@vuepic/vue-datepicker/dist/main.css'

import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap'

import { MotionPlugin } from '@vueuse/motion'

const app = createApp(App)
const pinia = createPinia()

pinia.use(piniaPluginPersistedstate)

app.component('VueDatePicker', VueDatePicker);

// VueUse Motion 플러그인을 애플리케이션에 설치
app.use(MotionPlugin)
app.use(pinia)
// app.use(createPinia().use(piniaPluginPersistedstate));
app.use(router)

app.mount('#app')
