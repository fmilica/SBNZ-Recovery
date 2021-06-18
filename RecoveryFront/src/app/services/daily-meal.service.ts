import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from 'rxjs';
import { environment } from "src/environments/environment";
import { DailyMeal } from "../model/daily-meal.model";

@Injectable({
  providedIn: 'root',
})
export class DailyMealService {

  constructor(
    private http: HttpClient
  ) { }

  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });

  getAllDailyMeals(patientId: number): Observable<DailyMeal> {
    return this.http.get<DailyMeal>(environment.apiEndpoint + 'doctor/' + patientId + '/get-daily-meals');
  }

  getAllDailyMealsCurrent(): Observable<DailyMeal> {
    return this.http.get<DailyMeal>(environment.apiEndpoint + 'doctor/get-current-daily-meals');
  }
}