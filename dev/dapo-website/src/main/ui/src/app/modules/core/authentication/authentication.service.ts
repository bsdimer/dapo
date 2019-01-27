import { Injectable, ChangeDetectorRef } from '@angular/core';
import { Broadcaster } from "../events/broadcaster";
import { LocalStorage } from "ngx-webstorage";
import { HttpClient } from "@angular/common/http";
import { environment } from "../../../../environments/environment";
import { map, catchError, tap } from "rxjs/internal/operators";
import { of, Observable } from "rxjs";
import { AuthenticationEvent } from "./AuthenticationEvent";

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {

  @LocalStorage()
  private token;
  private _currentUser: any;
  private _isAuthenticated: boolean;

  constructor(private http: HttpClient,
              private broadcaster:Broadcaster) {
  }

  private validateToken(token: string): boolean {
    return true;
  }

  public setToken(token: any) {
    this.token = token;
    this.authenticate();
  }

  private refreshToken(): void {
  }

  public authenticate() {
    if (!!this.token) {
      this.http.get(environment.auth.userInfoUri, {headers: {Authorization: `Bearer ${this.token}`}})
        .pipe(
          catchError(err => {
            this.clearAuthentication();
            return of(err)
          })
        )
        .subscribe(
          user => {
            this._currentUser = user;
            this._isAuthenticated = true;
          },
          error => {
            this.clearAuthentication();
          },
          () => {
            this.broadcaster.$broadcast(AuthenticationEvent.AUTHENTICATION_COMPLETE);
          }
        );
    } else {
      this.clearAuthentication();
    }
  }

  public logout() {
    this.clearAuthentication();
    this.broadcaster.$broadcast(AuthenticationEvent.AUTHENTICATION_COMPLETE);
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
