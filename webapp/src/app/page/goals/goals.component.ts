import {Component, OnInit, ViewChild} from '@angular/core';
import {Goal} from "../../models/goal";
import {GoalsGridOrderPipe} from "../../pipes/goals-grid-order.pipe";
import {GoalService} from "../../services/goal.service";

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
}
