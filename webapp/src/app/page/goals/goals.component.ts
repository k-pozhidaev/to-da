import { Component, OnInit } from '@angular/core';
import {Goal} from "../../models/goal";

@Component({
  selector: 'app-goals',
  templateUrl: './goals.component.html',
  styleUrls: ['./goals.component.sass']
})
export class GoalsComponent implements OnInit {

  constructor() { }

  items : Goal[]

  ngOnInit(): void {
    this.items = [
      new Goal(1, "text"),
      new Goal(2, "demo"),
      new Goal(3, "local"),
    ]
  }

}
