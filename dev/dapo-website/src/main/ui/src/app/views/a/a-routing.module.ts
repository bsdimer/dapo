import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from "./login/login.component";
import { RegisterComponent } from "./register/register.component";
import { TranslateModule } from "@ngx-translate/core";
import { CoreModule } from "../../modules/core/core.module";

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: '',
    component: LoginComponent
  }
];

@NgModule({
  declarations: [
    LoginComponent,
    RegisterComponent],
  imports: [
    RouterModule.forChild(routes),
    TranslateModule,
    CoreModule
  ],
  exports: [RouterModule]
})
export class ARoutingModule {
}
