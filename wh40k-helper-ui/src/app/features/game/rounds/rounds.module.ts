import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RoundsComponent} from "./rounds.component";
import {MatCardModule} from "@angular/material/card";

@NgModule({
  declarations: [RoundsComponent],
  imports: [
    CommonModule,
    MatCardModule
  ],
  exports: [RoundsComponent],
})
export class RoundsModule {}
