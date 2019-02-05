import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MustMatchDirective } from './directives/mustMatch/must-match.directive';
import { RegistrationService } from "./authentication/registration.service";

@NgModule({
  declarations: [MustMatchDirective],
  exports: [MustMatchDirective],
  imports: [
    CommonModule
  ],
  providers: [
    RegistrationService
  ]
})
export class CoreModule { }
