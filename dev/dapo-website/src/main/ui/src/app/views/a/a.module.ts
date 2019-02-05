import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ARoutingModule } from './a-routing.module';
import { TokenSetComponent } from './token-set/token-set.component';
import { CoreModule } from "../../modules/core/core.module";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { RegisterComponent } from "./register/register.component";
import { LoginComponent } from "./login/login.component";
import { TranslateModule } from "@ngx-translate/core";

@NgModule({
  declarations: [TokenSetComponent, RegisterComponent, LoginComponent],
  imports: [
    CommonModule,
    ARoutingModule,
    CoreModule,
    FormsModule
  ]
})
export class AModule { }
