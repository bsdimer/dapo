import { Component, OnInit } from '@angular/core';
import { environment } from "../../../../environments/environment";
import { LoginRequest } from "../../../modules/core/authentication/login-request";
import { AuthenticationService } from "../../../modules/core/authentication/authentication.service";
import { Router } from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  public environment = environment;
  public req: LoginRequest = new LoginRequest();

  constructor(public authService: AuthenticationService, public router: Router) {
  }

  ngOnInit() {
  }

  login() {
    this.authService.login(this.req).subscribe(result => {
      this.authService.setToken(result).subscribe(
        success => {
          // ToDo: should fix this
          this.router.navigate([environment.auth.successUrl]);
        }
      )
    })
  }

}
