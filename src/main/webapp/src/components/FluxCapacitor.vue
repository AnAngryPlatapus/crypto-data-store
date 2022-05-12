<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <div>
      <Splitter>
        <SplitterPanel>
        <AutoCompleteBox></AutoCompleteBox>
        </SplitterPanel>
        <SplitterPanel>
        <DataTabSelector></DataTabSelector>
        </SplitterPanel>
      </Splitter>
      <p>
        "TODO Create console component here"
      </p>
    </div>

  </div>
</template>

<script lang="ts">
import SplitterPanel from 'primevue/splitterpanel';
import Splitter from 'primevue/splitter';
import AutoCompleteBox from "./AutoCompleteBox.vue";
import DataTabSelector from "./DataTabSelector.vue";
import {defineComponent} from "vue";

export  default defineComponent({
  name: 'FluxCapacitor',
  components: {DataTabSelector, AutoCompleteBox, Splitter, SplitterPanel},
  props: {
    msg: String
  }
})
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
