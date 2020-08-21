import {Component, OnInit} from '@angular/core';
import {Goal} from "../../models/goal";
import {GoalType} from "../../models/goal-type.enum";
import {GoalStatus} from "../../models/goal-status.enum";
import {Topic} from "../../models/topic";

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
      new Goal(1, "add money to acc", GoalType.MONTHLY, GoalStatus.IN_PROGRESS, 0, 1, [new Topic(1, "apartments")]),
      new Goal(3, "find lawyer", GoalType.SINGLE, GoalStatus.DONE, 0, 1, [new Topic(1, "apartments")]),
      new Goal(2, "подтягивания", GoalType.DAILY, GoalStatus.IN_PROGRESS, 0, 10, [new Topic(2, "sport")]),
      new Goal(4, "no topic")
    ]
  }

  acceptTrial($event: MouseEvent, item: Goal) {
    console.log($event)
    item.increaseTrial()
  }
}
