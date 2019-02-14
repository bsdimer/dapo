import { Component, OnInit } from '@angular/core';
import { Broadcaster } from "./modules/core/events/broadcaster";
import { ConfigurationService } from "./modules/core/configuration/configuration.service";
import { TranslateService } from "@ngx-translate/core";
import { LocalStorage } from "ngx-webstorage";
import { ConfigEvent } from "./modules/core";
import { Config } from "./modules/dapo/model/v1/config";
import { AuthenticationService } from "./modules/core/authentication/authentication.service";
import { AuthenticationEvent } from "./modules/core/authentication/AuthenticationEvent";
import { RouterOutlet } from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  constructor(public configService: ConfigurationService,
              public authService: AuthenticationService) {
  }

  ngOnInit(): void {
    this.configService.loadConfig().subscribe();
    this.authService.authenticate().subscribe();
  }

  prepareRoute(outlet: RouterOutlet) {
    return outlet && outlet.activatedRouteData && outlet.activatedRouteData['animation'];
  }

}
