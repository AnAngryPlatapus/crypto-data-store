<template>
  <div>
    <p>Enter "date" to display the current date, "greet {0}" for a message and "random" to get a random number.</p>
    <Terminal welcomeMessage="auto-complete-box" prompt="primevue $" />
  </div>
</template>

<script lang="ts">

import Terminal from 'primevue/terminal';


import TerminalService from 'primevue/terminalservice';
import {onMounted} from "vue";
import {onBeforeUnmount} from "vue";
import {defineComponent} from "vue";

export default defineComponent({
  name: "AutoCompleteBox",
  components: {Terminal},
  setup() {
    onMounted(() => {
      TerminalService.on('command', commandHandler);
    })

    onBeforeUnmount(() => {
      TerminalService.off('command', commandHandler);
    })

    const commandHandler = (text: string) => {
      let response;
      let argsIndex = text.indexOf(' ');
      let command = argsIndex !== -1 ? text.substring(0, argsIndex) : text;

      switch (command) {
        case "date":
          response = 'Today is ' + new Date().toDateString();
          break;

        case "greet":
          response = 'Hola ' + text.substring(argsIndex + 1);
          break;

        case "random":
          response = Math.floor(Math.random() * 100);
          break;

        default:
          response = "Unknown command: " + command;
      }

      TerminalService.emit('response', response);
    }

    return {commandHandler}
  }
})
</script>

<style scoped>

</style>