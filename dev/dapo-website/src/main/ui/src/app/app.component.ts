import { Component, OnInit } from '@angular/core';
import { Broadcaster } from "./modules/core/events/broadcaster";
import { ConfigurationService } from "./modules/core/configuration/configuration.service";
import { TranslateService } from "@ngx-translate/core";
import { LocalStorage } from "ngx-webstorage";
import { ConfigEvent } from "./modules/core";
import { Config } from "./modules/dapo/model/v1/config";
import { AuthenticationService } from "./modules/core/authentication/authentication.service";
import { AuthenticationEvent } from "./modules/core/authentication/AuthenticationEvent";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  constructor(private configService: ConfigurationService,
              private authService: AuthenticationService) {
  }

  ngOnInit(): void {
    this.configService.loadConfig().subscribe();
    this.authService.authenticate().subscribe();
  }

}
