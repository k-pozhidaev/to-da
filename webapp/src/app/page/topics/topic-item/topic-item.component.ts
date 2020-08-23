import { Component, OnInit } from '@angular/core';
import {Topic} from "../../../models/topic";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-topic-item',
  templateUrl: './topic-item.component.html',
  styleUrls: ['./topic-item.component.sass']
})
export class TopicItemComponent implements OnInit {

  constructor(private route: ActivatedRoute) { }

  topic: Topic

  ngOnInit(): void {
    const text = this.route.snapshot.paramMap.get('text');
    this.topic = new Topic(text)
  }

}
