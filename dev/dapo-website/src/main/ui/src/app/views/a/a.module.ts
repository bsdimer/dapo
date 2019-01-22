import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ARoutingModule } from './a-routing.module';
import { TokenSetComponent } from './token-set/token-set.component';

@NgModule({
  declarations: [TokenSetComponent],
  imports: [
    CommonModule,
    ARoutingModule
  ]
})
export class AModule { }
