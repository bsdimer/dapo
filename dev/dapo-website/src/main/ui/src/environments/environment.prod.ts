export const environment = {
  production: false,
  loggerUri: "/logger",
  apiPrefix: "/api/v1",
  authRestApiPrefix: "/auth",
  auth: {
    clientAuthentication: "dapoweb:dapo123**",
    tokenExpirationDelta: 15000,
    facebookLoginUri: "/oauth2/authorize/facebook?redirect_uri=http://localhost:4200/a/profile",
    googleLoginUri: "/oauth2/authorize/google?redirect_uri=http://localhost:4200/a/profile",
    successUrl: "/a/profile",
    failUrl: "/a/login",
    userInfoUri: "/user/me",
    signUpUrl: "/auth/signup",
    loginUrl: "/auth/login"
  },
  view: {
    default: "/",
    defaultLang: "en",
    langs: ["bg", "en"]
  }
};
