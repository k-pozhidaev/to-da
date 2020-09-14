import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class GoalServiceService {

  constructor(private http: HttpClient) { }

  getList(){
    this.http.get("/api/goal")
  }
}
