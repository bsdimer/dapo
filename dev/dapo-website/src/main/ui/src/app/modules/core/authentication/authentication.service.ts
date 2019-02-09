import { Injectable, ChangeDetectorRef } from '@angular/core';
import { Broadcaster } from "../events/broadcaster";
import { LocalStorage } from "ngx-webstorage";
import { HttpClient } from "@angular/common/http";
import { environment } from "../../../../environments/environment";
import { map, catchError, tap } from "rxjs/internal/operators";
import { of, Observable } from "rxjs";
import { AuthenticationEvent } from "./AuthenticationEvent";
import { LoginRequest } from "./login-request";

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {

  @LocalStorage()
  private token;
  private _currentUser: any;
  private _isAuthenticated: boolean;

  constructor(private http: HttpClient,
              private broadcaster: Broadcaster) {
  }

  private validateToken(token: string): boolean {
    return true;
  }

  public setToken(token: any) {
    if (token.hasOwnProperty("accessToken"))  {
      this.token = token["accessToken"];
    } else {
      this.token = token;
    }
    return this.authenticate();
  }

  private refreshToken(): void {
  }

  public authenticate() {
    if (!this.token) return of(null);
    return this.http.get(environment.auth.userInfoUri, {headers: {Authorization: `Bearer ${this.token}`}})
      .pipe(
        map(user => {
          this._currentUser = user;
          this._isAuthenticated = true;
          return user;
        }),
        catchError(error => {
          this.clearAuthentication();
          return error;
        })
      );
  }

  public login(request: LoginRequest) {
    return this.http.post(environment.auth.loginUrl, request);
  }

  public logout():Observable<boolean> {
    this.clearAuthentication();
    this.broadcaster.$broadcast(AuthenticationEvent.AUTHENTICATION_COMPLETE);
    return of(true);
  }

  private clearAuthentication() {
    this.token = null;
    this._currentUser = null;
    this._isAuthenticated = false;
  }

  get currentUser(): any {
    return this._currentUser;
  }

  get isAuthenticated(): boolean {
    return this._isAuthenticated;
  }
}
