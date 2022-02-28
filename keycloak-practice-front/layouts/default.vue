<template>
  <v-app>
    <v-app-bar absolute> </v-app-bar>
    <v-main class="mt-15">
      <v-container>
        <Nuxt />
      </v-container>
    </v-main>
  </v-app>
</template>
<script lang="ts">
import { Vue, Component } from "nuxt-property-decorator";
@Component
export default class Default extends Vue {}
</script>
