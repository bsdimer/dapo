import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HomeComponent} from './views/home/home.component';
import {AgmCoreModule} from '@agm/core';
import {AgmSnazzyInfoWindowModule} from '@agm/snazzy-info-window';
import {AgmJsMarkerClustererModule} from '@agm/js-marker-clusterer';
import {LoggerModule, NgxLoggerLevel} from "ngx-logger";
import {environment} from "../environments/environment";
import {CoreModule} from "./modules/core/core.module";
import {NgxWebstorageModule} from "ngx-webstorage";
import {DapoModule} from "./modules/dapo/dapo.module";
import {HttpClient} from "@angular/common/http";
import {TranslateHttpLoader} from "@ngx-translate/http-loader";
import {TranslateLoader, TranslateModule} from "@ngx-translate/core";
import {TopHeaderComponent} from './views/top-header/top-header.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ModalModule} from "ngx-bootstrap";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {
  MatInputModule,
  MatSelectModule,
  MatFormFieldModule,
  MatAutocompleteModule,
  MatTabsModule, MatButtonModule, MatIconModule
} from "@angular/material";
import {AdvancedSearchComponent} from './views/advanced-search/advanced-search.component';
import {MatCheckboxModule} from '@angular/material/checkbox';


export function createTranslateLoader(http: HttpClient): TranslateHttpLoader {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    TopHeaderComponent,
    AdvancedSearchComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBMGKHXLeizvrfckXbFFt8SfeaSBHi8xd4'
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
    FormsModule,
    ReactiveFormsModule,
    DapoModule,
    MatButtonModule,
    MatInputModule,
    MatIconModule,
    MatTabsModule,
    MatSelectModule,
    MatCheckboxModule,
    MatFormFieldModule,
    MatAutocompleteModule,
    ModalModule.forRoot(),
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
