<template>
    <div>
        <h4>하이</h4>
    </div>
  </template>
  
  <script setup>
  import { computed, onMounted } from 'vue';
  import { useRoute } from 'vue-router';
  import { defineProps } from 'vue';

const props = defineProps(['myList']);

  const route = useRoute();
//   const myList = route.params.myList;
console.log(props)
console.log(props.value)
console.log(props.myList)
// console.log(props.myList.value)

const sortedList = computed(() => {
  if (!props.myList) {
    return [];
  }
  return props.myList.slice().sort((a, b) => new Date(a.date) - new Date(b.date));
});
  </script>