import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthenticationService } from "./authentication/authentication.service";
import { ConfigurationService } from "./configuration/configuration.service";
import { Broadcaster } from "./events/broadcaster";
import { AuthGuard } from "./guard/auth.guard";
import { NoAuthGuard } from "./guard/noauth.guard";
import { MustMatchDirective } from './directives/mustMatch/must-match.directive';
import { RegistrationService } from "./authentication/registration.service";

@NgModule({
  declarations: [MustMatchDirective],
  exports: [MustMatchDirective],
  imports: [
    CommonModule
  ],
  providers: [
    AuthenticationService,
    ConfigurationService,
    Broadcaster,
    RegistrationService
  ]
})
export class CoreModule { }
