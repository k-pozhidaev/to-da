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
    this.goalService.addApproach(item).subscribe(
      value => {
        target.disabled = false
        item.approachesCount = value.valueOf()
      },
      error => { target.disabled = false }
    )
  }
}
