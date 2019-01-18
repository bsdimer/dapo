import { Component, OnInit } from '@angular/core';
import { Broadcaster } from "./modules/core/events/broadcaster";
import { ConfigurationService } from "./modules/core/configuration/configuration.service";
import { TranslateService } from "@ngx-translate/core";
import { LocalStorage } from "ngx-webstorage";
import { ConfigEvent } from "./modules/core";
import { Config } from "./modules/dapo/model/v1/config";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  private currentLang = "en";
  @LocalStorage()
  private defaultLang;
  private config: Config;

  constructor(private configService: ConfigurationService,
              private broadcaster: Broadcaster,
              private translate: TranslateService) {
  }

  ngOnInit(): void {
    this.broadcaster.$on(ConfigEvent.CONFIG_LOADED_SUCCESSFULLY).subscribe((result) => {
      this.config = result.data;
    });
    this.configService.getConfig();
    if (!this.currentLang) this.currentLang = this.defaultLang ? this.defaultLang : this.config.defaultLang;
    this.translate.use(this.currentLang);
  }

  setLanguage(lang) {
    this.translate.use(lang);
    this.defaultLang = lang;
  }

}
