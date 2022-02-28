class Auth {
  constructor(public idToken: string = "", public keycloakClient: any = null) {}
}

class AuthFactory {
  private static authInstance = new Auth();

  // Authインスタンスは外部からimportできるようゲッターを定義
  static get auth() {
    return this.authInstance;
  }
}

export default AuthFactory.auth;
