import { Component, OnInit } from '@angular/core';
import {Topic} from "../../models/topic";

@Component({
  selector: 'app-topics',
  templateUrl: './topics.component.html',
  styleUrls: ['./topics.component.sass']
})
export class TopicsComponent implements OnInit {

  topics: Topic[]

  constructor() { }

  ngOnInit(): void {
    this.topics = []
  }

}
