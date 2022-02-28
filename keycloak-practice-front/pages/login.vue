<template>
  <v-container>
    <v-row>
      <h1>ログイン画面</h1>
    </v-row>
    <v-row>
      <h2><v-btn @click="moveToTopPage">TOPページへ移動</v-btn></h2>
    </v-row>
    <v-row>
      <h2><v-btn @click="checkIdToken">IDトークン表示</v-btn></h2>
    </v-row>
    <v-row>
      <h2><v-btn @click="callFreeAPI">誰でも呼び出せるAPI</v-btn></h2>
    </v-row>
    <v-row>
      <h2><v-btn @click="callAuthAPI">認証ありのAPI</v-btn></h2>
    </v-row>
  </v-container>
</template>
<script lang="ts">
import { Vue, Component } from "nuxt-property-decorator";
import { KeycloakInstance, KeycloakConfig } from "keycloak-js";
import Auth from "../infras/Auth";
import Axios from "../infras/AxiosFactory";
@Component
export default class Top extends Vue {
  // この画面を表示する前にkeycloak認証
  async fetch() {
    // Keycloakクライアント作成
    let initOptions = {
      url: "http://localhost:8180/auth",
      realm: "user",
      clientId: "nuxt-app",
    };
    let keycloakClient = await this.createKeycloakClient(initOptions);
    // https://wjw465150.gitbooks.io/keycloak-documentation/content/securing_apps/topics/oidc/javascript-adapter.html
    // init ... adapterのinitialize
    // initでlogin-requiredを指定していれば、自動でadapterにログイン処理をさせられる
    await keycloakClient.init({
      onLoad: "login-required",
    });
    Auth.idToken = keycloakClient.idToken as string;
    Axios.defaults.baseURL = "http://localhost:8080/";
    Axios.defaults.headers.common.Authorization = `Bearer ${keycloakClient.idToken}`;
    // axios.defaults.headers.common['Authorization'] = this.AuthToken;
  }

  async createKeycloakClient(
    _config: KeycloakConfig
  ): Promise<KeycloakInstance> {
    // keycloak-jsは動的IMPORTする必要がある
    const { default: Keycloak } = await import("keycloak-js");
    return Keycloak(_config);
  }

  async callFreeAPI() {
    let data = (await Axios.get("/user/hello")).data;

    alert(data);
  }

  async callAuthAPI() {
    let data = (await Axios.get("/admin/hello")).data;
    alert(data);
  }

  checkIdToken() {
    if (Auth.idToken == "") {
      alert("keycloakClientはnullです");
    } else {
      console.log(Auth.idToken);
      alert(Auth.idToken);
    }
  }

  moveToTopPage() {
    this.$router.push("/");
  }
}
</script>
