<template>
  <div class="row">
    <div class="col-3">
      <h3>Draggable 1</h3>
      <draggable class="dragArea list-group" :list="list1" :group="{ name: 'people', pull: 'clone', put: false }" @change="log" item-key="name">
        <div
        class="list-group-item bg-gray-300 m-1 p-3 rounded-md text-center"
        v-for="element in list1"
        :key="element.name"
      >
        {{ element.name }}
      </div>
      </draggable>
    </div>

    <div class="col-3">
      <h3>Draggable 2</h3>
      <draggable class="dragArea list-group" :list="list2" group="people" @change="log" item-key="name">
        <div
        class="list-group-item bg-gray-300 m-1 p-3 rounded-md text-center"
        v-for="element in list2"
        :key="element.name"
      >
        {{ element.name }}
      </div>
      </draggable>
    </div>
  </div>
</template>

<script setup>
import { ref, defineComponent } from 'vue';
import { VueDraggableNext } from 'vue-draggable-next'
const draggable = defineComponent(VueDraggableNext);
const list1 = ref([
  { name: "John", id: 1 }, 
  { name: "Joao", id: 2 }, 
  { name: "Jean", id: 3 }, 
  { name: "Gerard", id: 4 }
]);

const list2 = ref([
  { name: "Juan", id: 5 }, 
  { name: "Edgard", id: 6 }, 
  { name: "Johnson", id: 7 }
]);

const log = (evt) => {
  window.console.log(evt);
};
</script>

<style scoped>
pre {
  text-align: start;
}
</style>
