import { Pipe, PipeTransform } from '@angular/core';
import {Goal} from "../models/goal";

@Pipe({
  name: 'goalsGridOrder'
})
export class GoalsGridOrderPipe implements PipeTransform {

  transform(value: Goal[], ...args: unknown[]): Goal[] {
    return value.sort((a,b) => a.status - b.status );
  }

}
