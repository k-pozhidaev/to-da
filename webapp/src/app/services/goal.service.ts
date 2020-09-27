import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Goal} from "../models/goal";
import {catchError, map} from "rxjs/operators";
import {Observable, of} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class GoalService {

  constructor(private http: HttpClient) {
  }


  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(operation + error);
      return of(result as T);
    };
  }

  getList(): Observable<Goal[]> {
    return this.http.get<Array<any>>("/api/goal/date").pipe(
      catchError(this.handleError('getAllGoals', [])),
      map(v =>
          v.map(g => {
            let goal = new Goal()
            Object.assign(goal, g)
            return goal
          })

      )
    )
  }

  addGoal(goal: Goal) : Observable<Goal> {
    return this.http.post("/api/goal", goal).pipe(
      map(v =>
        {
          let goal = new Goal()
          Object.assign(goal, v)
          return goal
        }

      )
    )
  }

  addApproach(goal: Goal, date: Date) : Observable<Number>{
    return this.http.patch<Number>(`/api/goal/${goal.id}/${GoalService.dateToString(date)}`, null)
  }

  getOne() {

  }

  private static dateToString(date: Date) : String {
    let month = '' + (date.getMonth() + 1),
    day = '' + date.getDate(),
    year = date.getFullYear();

    if (month.length < 2)
      month = '0' + month;
    if (day.length < 2)
      day = '0' + day;

    return [year, month, day].join('-');
  }
}
