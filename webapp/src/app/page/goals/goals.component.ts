import {Component, OnInit} from '@angular/core';
import {Goal} from "../../models/goal";
import {GoalType} from "../../models/goal-type.enum";
import {GoalStatus} from "../../models/goal-status.enum";
import {Topic} from "../../models/topic";
import {GoalsGridOrderPipe} from "../../pipes/goals-grid-order.pipe";
import {GoalService} from "../../services/goal.service";

@Component({
  selector: 'app-goals',
  templateUrl: './goals.component.html',
  styleUrls: ['./goals.component.sass'],

})
export class GoalsComponent implements OnInit {

  constructor(private goalService : GoalService) { }

  items : Goal[]

  ngOnInit(): void {
    this.goalService.getList().subscribe(
      value => {
        console.log(value)
        this.items = value
      }
    )
  }

  acceptApproach($event: MouseEvent, item: Goal) {
    item.increaseTrial()
    new GoalsGridOrderPipe().transform(this.items)
  }
}
