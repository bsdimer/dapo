import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from "./views/home/home.component";
import {AdvancedSearchComponent} from "./views/advanced-search/advanced-search.component";

const routes: Routes = [
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'as',
    component: AdvancedSearchComponent
  },
  {
    path: 'a',
    loadChildren: './views/a/a.module#AModule'
  },
  {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full'
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
