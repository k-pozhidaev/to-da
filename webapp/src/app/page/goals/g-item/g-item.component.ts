import {Component, Input, OnInit} from '@angular/core';
import {Goal} from "../../../models/goal";

@Component({
  selector: 'app-g-item',
  templateUrl: './g-item.component.html',
  styleUrls: ['./g-item.component.sass']
})
export class GItemComponent implements OnInit {


  @Input() item : Goal

  ngOnInit(): void {
  }

}
