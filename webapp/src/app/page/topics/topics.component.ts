import { Component, OnInit } from '@angular/core';
import {Topic} from "../../models/topic";

@Component({
  selector: 'app-topics',
  templateUrl: './topics.component.html',
  styleUrls: ['./topics.component.sass']
})
export class TopicsComponent implements OnInit {

  topics: Map<Topic, number>


  constructor() { }

  ngOnInit(): void {
    this.topics = new Map()
    this.topics.set(new Topic("sport"), 1)
    this.topics.set(new Topic("apartments"), 3)
    this.topics.set(new Topic("promotion"), 5)
  }

}
