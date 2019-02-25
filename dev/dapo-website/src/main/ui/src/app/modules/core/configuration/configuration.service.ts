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

  configUrl = 'assets/config.json';
  config;
  isResolved: boolean = false;

  constructor(public http: HttpClient, public broadcaster:Broadcaster) {
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

  ngOnDestroy(): void {
    console.log("DESTROY");
  }
}
