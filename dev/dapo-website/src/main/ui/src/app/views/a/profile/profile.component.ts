import { Component, OnInit, ChangeDetectorRef, TemplateRef } from '@angular/core';
import { ActivatedRoute, Router, Params } from "@angular/router";
import { AuthenticationService } from "../../../modules/core/authentication/authentication.service";
import { environment } from "../../../../environments/environment";
import { BsModalRef, BsModalService } from "ngx-bootstrap";

@Component({
  selector: 'profile-controller',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  private message: string;
  private user;
  modalRef: BsModalRef;

  constructor(private activatedRoute: ActivatedRoute,
              private router: Router,
              private modalService: BsModalService,
              private authService: AuthenticationService) {
  }

  ngOnInit() {
    this.activatedRoute.queryParams.subscribe((params: Params) => {

      if (params.hasOwnProperty("token")) {
        this.authService.setToken(params["token"]).subscribe(
          result => {
            this.user = this.authService.currentUser;
          },
          error => {
            this.router.navigateByUrl(environment.auth.failUrl, params['error']);
          }
        )
      } else {
        if (this.authService.isAuthenticated) {
          this.user = this.authService.currentUser;
        }
      }

      if (params.hasOwnProperty("error")) {
        this.message = params["error"];
      }
    });
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  save(){
    console.log(this.user);
  }

}
