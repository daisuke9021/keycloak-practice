<template>
  <v-container>
    <v-row>
      <h1>HELLO NUXT APP</h1>
    </v-row>
    <v-row>
      <h2><v-btn @click="moveToLoginPage">ログインページへ移動</v-btn></h2>
    </v-row>
  </v-container>
</template>
<script lang="ts">
import { Vue, Component } from "nuxt-property-decorator";
import Auth from "../infras/Auth";
@Component
export default class Top extends Vue {
  moveToLoginPage() {
    this.$router.push("/login");
  }
}
</script>
