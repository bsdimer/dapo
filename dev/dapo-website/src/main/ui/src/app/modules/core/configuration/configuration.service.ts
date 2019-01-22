import { Injectable, OnDestroy } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Broadcaster } from "../events/broadcaster";
import { ConfigEvent } from "./config-event.enum";
import { config } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ConfigurationService implements OnDestroy{

  private configUrl = 'assets/config.json';
  private _config;
  isResolved: boolean = false;
  asd: Date = new Date();

  constructor(private http: HttpClient, private broadcaster:Broadcaster) {
  }

  loadConfig() {
    this.isResolved = false;
    return this.http.get(this.configUrl)
      .subscribe(result => {
        this.config = result;
        this.isResolved = true;
        this.broadcaster.$broadcast(ConfigEvent.CONFIG_LOADED_SUCCESSFULLY, this.config);
      });
  }


  get config() {
    return this._config;
  }


  set config(value) {
    this._config = value;
  }

  ngOnDestroy(): void {
    console.log("DESTROY");
  }
}
