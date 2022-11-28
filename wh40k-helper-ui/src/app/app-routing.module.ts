import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";

const routes: Routes = [
  {
    path: 'game',
    loadChildren: () =>
      import('./features/game/game.module').then(
        (m) => m.GameModule
      ),
  },
  { path: '', pathMatch: 'full', redirectTo: '/game' },
  { path: '**', redirectTo: '/game' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
