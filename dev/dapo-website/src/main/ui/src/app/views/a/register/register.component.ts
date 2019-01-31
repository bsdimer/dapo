import { Component, OnInit } from '@angular/core';
import { SignupRequest } from "../../../modules/core/authentication/signup-request";
import { RegistrationService } from "../../../modules/core/authentication/registration.service";
import { catchError } from "rxjs/internal/operators";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  private model: SignupRequest = new SignupRequest();
  private state: string = "default";
  private message: string = "";

  constructor(private registrationService: RegistrationService) {
  }

  ngOnInit() {
  }

  signup() {
    this.registrationService.signUp(this.model)
      .subscribe(result => {
          this.state = "success";
          this.message = result.message;
          debugger;
        },
        error => {
          this.state = "default";
          this.message = error.error.message;
        }
      );
  }

}
