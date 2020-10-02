import {Component, ElementRef, Input, OnInit, ViewChild} from '@angular/core';
import {GoalType} from "../../models/goal-type.enum";
import {FormControl, FormGroup, NgForm, Validators} from "@angular/forms";
import {COMMA, ENTER} from "@angular/cdk/keycodes";
import {Observable} from "rxjs";
import {MatAutocomplete, MatAutocompleteSelectedEvent} from "@angular/material/autocomplete";
import {map, startWith} from 'rxjs/operators';
import {MatChipInputEvent} from "@angular/material/chips";
import {Goal} from "../../models/goal";
import {Topic} from "../../models/topic";
import {GoalService} from "../../services/goal.service";

@Component({
  selector: 'app-create-goal',
  templateUrl: './create-goal.component.html',
  styleUrls: ['./create-goal.component.sass']
})
export class CreateGoalComponent implements OnInit {

  selectable = true;
  removable = true;
  separatorKeysCodes: number[] = [ENTER, COMMA];
  topicCtrl = new FormControl();
  filteredTopics: Observable<Topic[]>;
  // topics: string[] = [];
  allTopics: Topic[] = ['sport', 'apartments', 'promotion'].map(s => new Topic(s));

  @Input('goal') goal : Goal

  @ViewChild('topicInput') topicInput: ElementRef<HTMLInputElement>
  @ViewChild('auto') matAutocomplete: MatAutocomplete
  @ViewChild('addButton') addButton: ElementRef<HTMLButtonElement>
  @ViewChild('createForm') createForm: NgForm

  constructor(private goalService: GoalService) {
    this.filteredTopics = this.topicCtrl.valueChanges.pipe(
      startWith(null),
      map((v: Topic | null) => v ? this._filter(v) : this.allTopics.slice()));
  }

  types : string[]

  _reset() : void {
    this.goal = new Goal(
      null,
      "",
      GoalType.DAILY,
      null,
      1,
      0,
      []
    );
  }

  ngOnInit(): void {
    this.types = Object.keys(GoalType)
    if (this.goal == null) this._reset()
  }

  submit(createForm: NgForm) : void {
    if (this.topicInput.nativeElement.value !== '') {
      this.goal.topics.push(new Topic(this.topicInput.nativeElement.value))
    }
    console.log(this.goal)
    this.goalService.addGoal(this.goal).subscribe(
      _ => this._reset()
    )
  }

  add(event: MatChipInputEvent): void {
    const input = event.input;
    const value = event.value;

    // Add topic
    if ((value || '').trim()) {
      this.goal.topics.push(new Topic(value.trim()));
    }

    // Reset the input value
    if (input) {
      input.value = '';
    }

    this.topicCtrl.setValue(null);
  }

  remove(topic: Topic): void {
    const index = this.goal.topics.indexOf(topic);

    if (index >= 0) {
      this.goal.topics.splice(index, 1);
    }
  }

  selected(event: MatAutocompleteSelectedEvent): void {
    this.goal.topics.push(new Topic(event.option.viewValue));
    this.topicInput.nativeElement.value = '';
    this.topicCtrl.setValue(null);
  }


  private _filter(value: Topic): Topic[] {
    const filterValue = value.text.toLowerCase();

    return this.allTopics.filter(v => v.text.toLowerCase().indexOf(filterValue) === 0);
  }
}
