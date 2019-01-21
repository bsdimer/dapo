import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './views/home/home.component';
import { AgmCoreModule } from '@agm/core';
import { AgmSnazzyInfoWindowModule } from '@agm/snazzy-info-window';
import { AgmJsMarkerClustererModule } from '@agm/js-marker-clusterer';
import { LoggerModule, NgxLoggerLevel } from "ngx-logger";
import { environment } from "../environments/environment";
import { CoreModule } from "./modules/core/core.module";
import { NgxWebstorageModule } from "ngx-webstorage";
import { DapoModule } from "./modules/dapo/dapo.module";
import { HttpClient } from "@angular/common/http";
import { TranslateHttpLoader } from "@ngx-translate/http-loader";
import { TranslateLoader, TranslateModule } from "@ngx-translate/core";

export function createTranslateLoader(http: HttpClient): TranslateHttpLoader {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AgmCoreModule.forRoot({
      apiKey: 'GMAPKEY'
    }),
    AgmJsMarkerClustererModule,
    NgxWebstorageModule.forRoot(),
    LoggerModule.forRoot({
      serverLoggingUrl: environment.loggerUri,
      level: NgxLoggerLevel.DEBUG,
      serverLogLevel: NgxLoggerLevel.ERROR
    }),
    AgmSnazzyInfoWindowModule,
    CoreModule,
    DapoModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: (createTranslateLoader),
        deps: [HttpClient]
      }
    }),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
