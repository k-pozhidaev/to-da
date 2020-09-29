import {Component, OnInit, ViewChild} from '@angular/core';
import {Goal} from "../../models/goal";
import {GoalsGridOrderPipe} from "../../pipes/goals-grid-order.pipe";
import {GoalService} from "../../services/goal.service";
import {GoalType} from "../../models/goal-type.enum";

@Component({
  selector: 'app-goals',
  templateUrl: './goals.component.html',
  styleUrls: ['./goals.component.sass'],

})
export class GoalsComponent implements OnInit {


  constructor(private goalService : GoalService) { }

  currentDate : Date = new Date()
  items : Goal[] = []

  ngOnInit(): void {
    this.goalService.getList().subscribe(
      value => {
        console.log(value)
        this.items = value
      }
    )
  }

  acceptApproach($event: MouseEvent, item: Goal) {
    let target = ($event.currentTarget as HTMLButtonElement)
    target.disabled = true;
    this.goalService.addApproach(item, this.currentDate).subscribe(
      value => {
        target.disabled = false
        item.approachesCount = value.valueOf()
        item.doneStatusCheck()
        new GoalsGridOrderPipe().transform(this.items)
      },
      error => { target.disabled = false }
    )
  }

  getIconStyleNameFromGoal(item:Goal) : String {
    let style
    switch (item.type){
      case GoalType.DAILY:
        style = "fa-calendar-day"
        break
      case GoalType.WEEKLY:
        style = "fa-calendar-week"
        break
      case GoalType.MONTHLY:
        style = "fa-calendar-alt"
        break
      case GoalType.SINGLE:
        style = "fa-calendar-plus"
        break
    }
    return style
  }
}
