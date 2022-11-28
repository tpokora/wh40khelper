import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {GameComponent} from "./game.component";
import {PlayersModule} from "./players/players.module";
import {RoundsModule} from "./rounds/rounds.module";
import {GameRoutingModule} from "./game-routing.module";
import {MatGridListModule} from "@angular/material/grid-list";

@NgModule({
  declarations: [GameComponent],
  imports: [
    CommonModule,
    GameRoutingModule,
    PlayersModule,
    RoundsModule,
    MatGridListModule,
  ],
  exports: [GameComponent],
  providers: [],
})
export class GameModule {}
