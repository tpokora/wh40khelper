import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RoundsComponent} from "./rounds.component";

@NgModule({
  declarations: [RoundsComponent],
  imports: [
    CommonModule,
  ],
  exports: [RoundsComponent],
})
export class RoundsModule {}
