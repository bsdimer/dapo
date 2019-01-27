import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from "./login/login.component";
import { RegisterComponent } from "./register/register.component";
import { TranslateModule } from "@ngx-translate/core";
import { CoreModule } from "../../modules/core/core.module";
import { TokenSetComponent } from "./token-set/token-set.component";
import { NoAuthGuard } from "../../modules/core/guard/noauth.guard";

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
    path: 'token',
    component: TokenSetComponent,
    data: { error: "/a/login" }
  },
  {
    path: '',
    component: LoginComponent
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
  ],
  exports: [RouterModule]
})
export class ARoutingModule {
}
