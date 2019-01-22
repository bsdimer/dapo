import { Injectable } from "@angular/core";
import { Headers } from "@angular/http";
import { AuthenticationEvent } from "./AuthenticationEvent";
import { LocalStorage } from 'ngx-webstorage';
import { Observable } from "rxjs";
import { Broadcaster } from "../events/broadcaster";
import { HttpClient } from "@angular/common/http";
import { HttpHeaders } from "@angular/common/http";
import { map, catchError } from "rxjs/internal/operators";
import { Oauth2TokenData } from "./oauth2-token-data";
import { ActiveRoleResolver } from "./active-role-resolver";
import { environment } from "../../../../environments/environment";

@Injectable()
export class AuthenticationService {

  private authenticated: boolean = false;
  private tokenExpirationDate: Date = undefined;
  private userData: any = undefined;
  private tokenExpirationTime: any;
  private changeTimestamp: any;
  //private restPrefix: string = environment.mainRestApiPrefix;
  private clientAuthentication: string = environment.auth.clientAuthentication;
  private tokenExpirationDelta: number = environment.auth.tokenExpirationDelta;
  private _activeRoleResolver: ActiveRoleResolver;

  @LocalStorage()
  private activeRole: string;

  @LocalStorage()
  private tokenData: Oauth2TokenData;

  @LocalStorage()
  private token: string;

  public setToken(token:string) {
    this.token = token;
  }

  public static decodeAccessToken(access_token: string) {
    return JSON.parse(AuthenticationService.b64DecodeUnicode(access_token.split('.')[1]));
  }

  public static b64DecodeUnicode(str: string): string {
    return decodeURIComponent(Array.prototype.map.call(atob(str), function (c) {
      return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2)
    }).join(''));
  }

  constructor(public http: HttpClient,
              public broadcaster: Broadcaster) {
    if (this.tokenData && this.tokenData.access_token) {
      this.authenticated = true;
      this.userData = AuthenticationService.decodeAccessToken(this.tokenData.access_token);
      this.tokenExpirationDate = new Date(this.userData.exp * 1000);
      if (this.authenticated && this.tokenExpirationDate < new Date()) {
        this.logout();
      }
    }
    window.onload = () => {
      this.refreshToken();
    };
  }

  /**
   * Check idle state
   * @returns {boolean}
   */
  // ToDo: Fix isIdle check
  public isIdle(): boolean {
    this.tokenExpirationTime = this.tokenData.expires_in * 1000;
    let now: number = Date.now();
    return now - this.changeTimestamp > this.tokenExpirationTime - this.tokenExpirationDelta;
  }

  /**
   * Get change timestamp
   * @returns {number}
   */
  onChange() {
    this.changeTimestamp = Date.now();
  }


  /**
   * Check if user is authenticated
   * @returns {boolean}
   */
  public isAuthenticated(): boolean {
    this.checkTokenExpirationDate();
    return this.authenticated;
  }

  /**
   * Authenticate user
   */
  public authenticate(username: string, password: string, scope: string): Observable<any> {
    /**
     * Broadcast onLogin event with the username as data
     */
    this.broadcaster.$broadcast(AuthenticationEvent[AuthenticationEvent.AUTHENTICATION_START], username);
    let basicAuthHeader = btoa(this.clientAuthentication);
    //basicAuthHeader = basicAuthHeader.slice(0,basicAuthHeader.length-1);
    let headers = new HttpHeaders(
      {
        'Authorization': "Basic " + basicAuthHeader,
        'Accept': 'application/json',
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    );

    let data = 'username=' + encodeURIComponent(username) + '&password='
      + encodeURIComponent(password) + '&grant_type=password' + '&scope=' + scope;

    return this.generateToken(data, headers);

  }

  /**
   * Refresh token
   * If user is authenticated and is not idle - refresh token -> start new token refresh timer (584000ms)
   * If user is not authenticated or is idle - logout() -> redirect to login page
   */
  // ToDo: Fix isIdle check
  public refreshToken() {
    if (this.isAuthenticated()) {
      let basicAuthHeader = btoa(this.clientAuthentication);

      let headers = new HttpHeaders(
        {
          'Authorization': "Basic " + basicAuthHeader,
          'Accept': 'application/json',
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      );

      let data = 'grant_type=refresh_token&refresh_token=' + encodeURIComponent(this.tokenData.refresh_token);

      this.generateToken(data, headers).subscribe();
    } else {
      this.logout();
    }
  }

  private generateToken(tokenData: any, headers: any): Observable<any> {
    return this.http.post('/auth/oauth/token', tokenData, {headers: headers}).pipe(map(data => {
        this.tokenData = <Oauth2TokenData>data;
        this.authenticated = true;
        this.userData = AuthenticationService.decodeAccessToken(this.tokenData.access_token);
        this.tokenExpirationDate = new Date(this.userData.exp * 1000);
        this.tokenExpirationTime = this.tokenData.expires_in * 1000;
        this.broadcaster.$broadcast(AuthenticationEvent.AUTHENTICATION_SUCCESS, this.userData);
        setTimeout(() => {
          this.refreshToken();
        }, this.tokenExpirationTime / 2);
        return this.userData;
      }),
      catchError(err => null));
  }

  public logout(): any {
    this.tokenData = new Oauth2TokenData();
    this.userData = null;
    this.tokenExpirationDate = null;
    this.authenticated = false;
    this.broadcaster.$broadcast(AuthenticationEvent.LOGOUT_COMPLETE);
    //this.appService.clearActiveRole();
  }

  public getUserData(): any {
    return this.userData;
  }

  public getTokenExpirationDate(): Date {
    return this.tokenExpirationDate;
  }

  /**
   * Get user role
   * @returns {string}
   */
  public hasRole(role: string): boolean {
    if (this.isAuthenticated()) {
      return this.getUserData()['authorities'].indexOf(role) >= 0;
    }
    return false;
  }

  public hasAnyRole(roles: string[]): boolean {
    let ok = false;
    roles.forEach(role => {
      if (this.hasRole(role)) {
        ok = true;
      }
    });
    return ok;
  }

  /**
   * Get the authorization Bearer token header
   * @returns {Headers}
   */
  public getAuthorizationHeaders(): HttpHeaders {
    let authorizationHeaders: HttpHeaders;
    if (!!this.authenticated) {
      let t:string = `Bearer ${this.tokenData.access_token}`;
      authorizationHeaders = new HttpHeaders({"Authorization": t});
    }
    return authorizationHeaders;
  }

  private checkTokenExpirationDate() {
    if (this.authenticated && this.tokenExpirationDate < new Date()) {
      this.logout();
    }
  }

  private fetchUserData() {
    this.http.get(environment.authRestApiPrefix + '/user', {headers: this.getAuthorizationHeaders()})
      .subscribe(
        data => {
          this.userData = data;
        },
        err => this.authenticated = false
      );
  }

  public getUser(username: string): Observable<any> {
    let headers = this.getAuthorizationHeaders();
    return this.http.get(window.location.origin + environment.authRestApiPrefix + "/user/" + username, {headers: headers}).pipe(map(res => JSON.stringify(res)))
  }

  public setActiveRoleResolver(activeRoleResolver: ActiveRoleResolver) {
    this._activeRoleResolver = activeRoleResolver;
  }

  public getActiveRole() {
    if (this.activeRole == undefined) return this._activeRoleResolver.resolve(this.getUserData());
    return this.activeRole;
  }

  public setActiveRole(role: string) {
    this.activeRole = role;
  }

}
