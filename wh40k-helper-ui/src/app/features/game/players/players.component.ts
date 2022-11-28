import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {PlayerModel} from "./shared/player.model";

@Component({
  selector: 'app-players',
  templateUrl: './players.component.html',
  styleUrls: ['./players.component.css']
})
export class PlayersComponent implements OnInit {

  playerOneForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    cp: new FormControl('', [Validators.required]),
  });

  playerTwoForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    cp: new FormControl('', [Validators.required]),
  });

  playerOneSecondaries = [];
  playerOntSecondaryToAdd = '';
  playerTwoSecondaries = [];

  playerOne = null;
  playerTwo = null;

  constructor() {
  }

  ngOnInit(): void {
  }

  get playerOneFormControl() {
    return this.playerOneForm.controls;
  }

  get playerTwoFormControl() {
    return this.playerTwoForm.controls;
  }

  createPlayerOne() {
    this.playerOne = new PlayerModel(this.playerOneForm.value.name, this.playerOneForm.value.cp);
    console.log(this.playerOne);
  }

  createPlayerTwo() {
    this.playerTwo = new PlayerModel(this.playerTwoForm.value.name, this.playerTwoForm.value.cp);
    console.log(this.playerTwo);
  }
}
