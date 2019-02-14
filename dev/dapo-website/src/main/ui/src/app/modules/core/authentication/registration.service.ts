import { Injectable } from '@angular/core';
import { SignupRequest } from "./signup-request";
import { HttpClient } from "@angular/common/http";
import { environment } from "../../../../environments/environment";
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(public http:HttpClient) { }

  public signUp(request:SignupRequest):Observable<any> {
    return this.http.post(environment.auth.signUpUrl, request);
  }
}
