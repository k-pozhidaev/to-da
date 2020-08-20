import {Component, OnInit} from '@angular/core';
import {Goal} from "../../models/goal";
import {GoalType} from "../../models/goal-type.enum";
import {GoalStatus} from "../../models/goal-status.enum";

@Component({
  selector: 'app-goals',
  templateUrl: './goals.component.html',
  styleUrls: ['./goals.component.sass'],

})
export class GoalsComponent implements OnInit {

  constructor() { }

  items : Goal[]

  ngOnInit(): void {
    this.items = [
      new Goal(1, "text", GoalType.DAILY, GoalStatus.IN_PROGRESS, 0, 3),
      new Goal(2, "demo", GoalType.DAILY, GoalStatus.IN_PROGRESS, 0, 1),
      new Goal(3, "local", GoalType.DAILY, GoalStatus.DONE, 0, 1),
    ]
  }

}
