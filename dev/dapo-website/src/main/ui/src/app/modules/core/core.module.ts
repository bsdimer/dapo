import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MustMatchDirective } from './directives/mustMatch/must-match.directive';
import { RegistrationService } from "./authentication/registration.service";
import { CapitalizePipe } from "./pipes/capitalize.pipe";

@NgModule({
  declarations: [MustMatchDirective, CapitalizePipe],
  exports: [MustMatchDirective],
  imports: [
    CommonModule
  ],
  providers: [
    RegistrationService
  ]
})
export class CoreModule { }
