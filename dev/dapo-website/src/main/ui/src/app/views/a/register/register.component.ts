import { Component, OnInit } from '@angular/core';
import { SignupRequest } from "../../../modules/core/authentication/signup-request";
import { RegistrationService } from "../../../modules/core/authentication/registration.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  private model:SignupRequest = new SignupRequest();

  constructor(private registrationService: RegistrationService) { }

  ngOnInit() {
  }

  signup() {
    this.registrationService.signUp(this.model).subscribe(result => {
      console.log(result);
      debugger;
    })
  }

  public test(){
    debugger;
  }


  private isValid(){
    return true;
  }

}
