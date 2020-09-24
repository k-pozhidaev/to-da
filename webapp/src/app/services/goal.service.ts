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
    return this.http.get<Array<any>>("/api/goal").pipe(
      catchError(this.handleError('getAllBrands', [])),
      map(v =>
          v.map(g => {
            let goal = new Goal()
            Object.assign(goal, g)
            return goal
          })

      )
    )
  }

  addGoal(goal: Goal) {
    return this.http.post("/api/goal", goal).subscribe(
      goal => console.log(goal),
      err => console.error(err)
    )
  }

  getOne() {

  }
}
