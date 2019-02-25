import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ARoutingModule } from './a-routing.module';
import { CoreModule } from "../../modules/core/core.module";
import { FormsModule } from "@angular/forms";
import { RegisterComponent } from "./register/register.component";
import { LoginComponent } from "./login/login.component";
import { TranslateModule } from "@ngx-translate/core";
import { ProfileComponent } from "./profile/profile.component";
import { ModalModule } from "ngx-bootstrap";

@NgModule({
  declarations: [ProfileComponent, RegisterComponent, LoginComponent],
  imports: [
    CommonModule,
    ARoutingModule,
    CoreModule,
    FormsModule,
    TranslateModule
  ]
})
export class AModule { }
