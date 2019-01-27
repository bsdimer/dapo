import { Component, OnInit } from '@angular/core';
import { SignupRequest } from "../../../modules/core/authentication/signup-request";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  private model:SignupRequest = new SignupRequest();

  constructor() { }

  ngOnInit() {
  }

  register() {
    debugger;
  }

  public test(){
    debugger;
  }

  public onSubmit(){
    debugger;
  }

  private isValid(){
    return true;
  }

}
