import axios, { AxiosError } from "axios";

// MEMO：「import axios from "axios"」で取得したaxiosにdefaults.baseUR等の設定をまず行う。
//       この「初期設定」を行ったインスタンスは★シングルトン★として使い回したい。
// https://stackoverflow.com/questions/30174078/how-to-define-singleton-in-typescript
//
// ポイント：importする度に「axios」が初期化されるから【import axios from "axios"】を書くのはAxiosFactory内のみ。
//          他クラスからこの「axios」を参照できるようにexportする。
class AxiosFactory {
  private static initialized = false;

  // MEMO
  // ・staticの理由：最終行で「AxiosFactory.axios」でstatic参照するため
  // ・staticにする...毎回インスタンス化するのではなく、ずっと同じ値がそこにある＝シングルトン的な扱いにできる
  static get axios() {
    if (!this.initialized) {
      // 各APIのレスポンスに対して実施する処理
      // axios.interceptors.response.use(
      //    第１引数：正常終了時に行う処理
      //    第２引数：異常終了時に行う処理
      axios.interceptors.response.use(
        // 正常終了時...そのままレスポンスを返す
        (response) => response,
        // 異常終了時...エラーログ出力＆NuxtJSヘルパーを使ってエラー画面遷移
        // ★「$nuxt ＝＝ Context」のこと。
        // context($nuxt).error({messagge,statusCode})でNuxtエラーページに誘導できる
        // https://nuxtjs.org/docs/concepts/context-helpers/
        async (error: AxiosError) => {
          window.$nuxt.error({
            message: error.response?.data.console.error,
            statusCode: error.response?.status,
          });
          return false;
        }
      );
    }
    return axios;
  }
}

// MEMO：JS/TSでは、ゲッターを「instance.getXxxx()」と書かない。「instance.xxxx」と書く。
//       なので↓は「AxiosFactoryクラス内の"static get axios()」を呼び出している。
export default AxiosFactory.axios;
