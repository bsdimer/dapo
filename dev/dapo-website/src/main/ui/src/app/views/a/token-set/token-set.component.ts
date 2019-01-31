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

  private message: string;

  constructor(private activatedRoute: ActivatedRoute,
              private router: Router,
              private authService: AuthenticationService) {
  }

  ngOnInit() {
    this.activatedRoute.queryParams.subscribe((params: Params) => {

      if (params.hasOwnProperty("token")) {
        this.authService.setToken(params["token"]).subscribe(
          result => {
            //this.router.navigateByUrl(environment.auth.successUrl);
            // ToDo: should fix this
            window.location.href = window.location.origin + environment.auth.successUrl;
          },
          error => {
            this.router.navigateByUrl(environment.auth.failUrl, params['error']);
          }
        )
      }

      if (params.hasOwnProperty("error")) {
        this.message = params["error"];
        //this.router.navigateByUrl(environment.auth.failUrl, params['error']);
      }
    });
  }

}
