import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute, Router, Params } from "@angular/router";
import { AuthenticationService } from "../../../modules/core/authentication/authentication.service";
import { environment } from "../../../../environments/environment";

@Component({
  selector: 'app-token-set',
  templateUrl: './token-set.component.html',
  styleUrls: ['./token-set.component.scss']
})
export class TokenSetComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute,
              private router: Router,
              private authService: AuthenticationService) {
  }

  ngOnInit() {
    this.activatedRoute.queryParams.subscribe((params: Params) => {
      if (params.hasOwnProperty("token")) {
        this.authService.setToken(params["token"]);
        //this.router.navigateByUrl(environment.auth.successUrl);
        window.location.href = window.location.origin + environment.auth.successUrl;
      } else {
        this.router.navigateByUrl(environment.auth.failUrl, params['error']);
      }
    });
  }

  test() {
    console.log(this.authService.isAuthenticated);
    this.authService.authenticate();
  }

}
