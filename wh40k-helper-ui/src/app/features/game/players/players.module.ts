import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {PlayersComponent} from "./players.component";
import {MatCardModule} from "@angular/material/card";
import {ReactiveFormsModule} from "@angular/forms";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatDividerModule} from "@angular/material/divider";
import {MatExpansionModule} from "@angular/material/expansion";
import {MatListModule} from "@angular/material/list";
import {MatButtonModule} from "@angular/material/button";

@NgModule({
  declarations: [PlayersComponent],
  imports: [
    CommonModule,
    MatCardModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatDividerModule,
    MatExpansionModule,
    MatListModule,
  ],
  exports: [PlayersComponent],
})
export class PlayersModule {}
