import { Injectable, OnDestroy } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Broadcaster } from "../events/broadcaster";
import { ConfigEvent } from "./config-event.enum";
import { config, Observable, of } from "rxjs";
import { map, catchError } from "rxjs/internal/operators";

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

  loadConfig():Observable<any> {
    this.isResolved = false;
    return this.http.get(this.configUrl).pipe(
      map(result => {
        this.config = result;
        this.isResolved = true;
        this.broadcaster.$broadcast(ConfigEvent.CONFIG_LOADED_SUCCESSFULLY, this.config);
        return result;
      }),
      catchError((error:any) => {
        this.broadcaster.$broadcast(ConfigEvent.CONFIG_LOADING_FAILED, error);
        return error;
      })
    )
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
