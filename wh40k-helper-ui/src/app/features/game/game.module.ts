import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {GameComponent} from "./game.component";
import {PlayersModule} from "./players/players.module";
import {RoundsModule} from "./rounds/rounds.module";
import {GameRoutingModule} from "./game-routing.module";

@NgModule({
  declarations: [GameComponent],
  imports: [
    CommonModule,
    GameRoutingModule,
    PlayersModule,
    RoundsModule
  ],
  exports: [GameComponent],
  providers: [],
})
export class GameModule {}
