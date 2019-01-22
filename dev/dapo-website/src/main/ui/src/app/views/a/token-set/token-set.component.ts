import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, Params } from "@angular/router";
import { AuthenticationService } from "../../../modules/core/authentication/authentication.service";

@Component({
  selector: 'app-token-set',
  templateUrl: './token-set.component.html',
  styleUrls: ['./token-set.component.scss']
})
export class TokenSetComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute, private router: Router, authService: AuthenticationService) {
    this.activatedRoute.queryParams.subscribe((params: Params) => {
      if (params.hasOwnProperty("token"))  {
        authService.setToken(params["token"]);
        this.router.navigateByUrl("/home");
      } else {
        this.router.navigateByUrl("/a/login", params['error']);
      }
    });
  }

  ngOnInit() {
  }

}
