import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthenticationService } from "./authentication/authentication.service";
import { ConfigurationService } from "./configuration/configuration.service";
import { Broadcaster } from "./events/broadcaster";

@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ],
  providers: [
    AuthenticationService,
    ConfigurationService,
    Broadcaster
  ]
})
export class CoreModule { }
