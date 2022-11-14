import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-players',
  templateUrl: './players.component.html',
  styleUrls: ['./players.component.css']
})
export class PlayersComponent implements OnInit {

  playersForm = new FormGroup({
    playerOneName: new FormControl('', [Validators.required]),
    playerOneCp: new FormControl('', [Validators.required]),
    playerTwoName: new FormControl('', [Validators.required]),
    playerTwoCp: new FormControl('', [Validators.required])
  });

  constructor() { }

  ngOnInit(): void {
  }

  get playersFormControl() {
    return this.playersForm.controls;
  }

}
