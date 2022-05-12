<template>
  <li v-for="item in items" :key="item.message">
    {{ item in items }}

    <Panel :header="item.header" :toggleble="true">
      TODO Make toggle remove from local cache
        {{ item.body }}
    </Panel>
  </li>
</template>
<script lang="ts">
import Panel from 'primevue/panel'
import {defineComponent} from "vue";
export default defineComponent({
  name: "DataTabSelector",
  components: {Panel},
  data() {

    return {
      items: [
        {
          header: "api-header-1",
          body: "api-body-1"
        },
        {
          header: "api-header-2",
          body: "api-body-2"
        }
      ]
    }
  }
})
</script>

<style scoped>

</style>