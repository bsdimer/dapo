import { Injectable } from '@angular/core';
import { Broadcaster } from "../events/broadcaster";
import { LocalStorage } from "ngx-webstorage";
import { HttpClient } from "@angular/common/http";
import { environment } from "../../../../environments/environment";
import { map, catchError, tap } from "rxjs/internal/operators";
import { AuthenticationEvent } from "./AuthenticationEvent";
import { of, Observable } from "rxjs";

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {

  @LocalStorage()
  private token;
  private _currentUser: any;
  private _isAuthenticated: boolean;

  constructor(private broadcaster: Broadcaster, private http: HttpClient) {
  }

  private validateToken(token: string): boolean {
    return true;
  }

  public setToken(token: any): Observable<any> {
    this.token = token;
    return this.authenticate();
  }

  private refreshToken(): void {
  }

  public authenticate(): Observable<any> {
    this.http.get(environment.auth.userInfoUri, {headers: {Authorization: `Bearer ${this.token}`}}).pipe(
      catchError((error: any) => {
        this.broadcaster.$broadcast(AuthenticationEvent.AUTHENTICATION_FAIL, error);
        return of(error);
      })
    ).subscribe(user => {
      this._currentUser = user;
      this._isAuthenticated = true;
      this.broadcaster.$broadcast(AuthenticationEvent.AUTHENTICATION_SUCCESS, user);
    });
    return of(this._currentUser);
  }

  public logout() {
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
