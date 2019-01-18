import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Broadcaster } from "../events/broadcaster";
import { ConfigEvent } from "./config-event.enum";

@Injectable({
  providedIn: 'root'
})
export class ConfigurationService {

  private configUrl = 'assets/config.json';
  private config;

  constructor(private http: HttpClient, private broadcaster:Broadcaster) {
  }

  getConfig() {
    return this.http.get(this.configUrl)
      .subscribe(result => {
        this.config = result;
        this.broadcaster.$broadcast(ConfigEvent.CONFIG_LOADED_SUCCESSFULLY, this.config);
      });
  }
}
