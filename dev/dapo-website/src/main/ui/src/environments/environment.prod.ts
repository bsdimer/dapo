// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  loggerUri: "/logger",
  apiPrefix: "/api/v1",
  authRestApiPrefix: "/auth",
  auth: {
    clientAuthentication: "dapoweb:dapo123**",
    tokenExpirationDelta: 15000,
    facebookLoginUri: "/oauth2/authorize/facebook?redirect_uri=http://dapo.com/a/profile",
    googleLoginUri: "/oauth2/authorize/google?redirect_uri=http://dapo.com/a/profile",
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

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
