import { Component, OnInit } from '@angular/core';
import { TranslateService } from "@ngx-translate/core";
import { Broadcaster } from "../../modules/core/events/broadcaster";
import { AuthenticationService } from "../../modules/core/authentication/authentication.service";
import { LocalStorage } from "ngx-webstorage";
import { ConfigEvent } from "../../modules/core/configuration/config-event.enum";
import { Config } from "../../modules/dapo/model/v1/config";
import { Router } from "@angular/router";

@Component({
  selector: 'app-top-header',
  templateUrl: './top-header.component.html',
  styleUrls: ['./top-header.component.scss']
})
export class TopHeaderComponent implements OnInit {

  @LocalStorage()
  public defaultLang;
  public config: Config;


  constructor(public broadcaster: Broadcaster,
              public authService: AuthenticationService,
              public router:Router,
              public translate: TranslateService) {
  }

  ngOnInit() {
    this.broadcaster.$on(ConfigEvent.CONFIG_LOADED_SUCCESSFULLY).subscribe((result) => {
      this.config = result.data;
    });
    if (!this.defaultLang) this.defaultLang = "en";
    this.translate.use(this.defaultLang);
  }

  setLanguage(lang) {
    this.translate.use(lang);
    this.defaultLang = lang;
  }

  logout() {
    this.authService.logout().subscribe(() => {
      this.router.navigate(['/']);
    })
  }

}
