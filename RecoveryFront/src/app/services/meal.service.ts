import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from 'rxjs';
import { environment } from "src/environments/environment";
import { Meal } from "../model/meal.model";

@Injectable({
    providedIn: 'root',
  })
  export class MealService {
      
    constructor(
        private http: HttpClient
    ) { }

    private headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    getMeals(): Observable<Meal[]> {
        return this.http.get<Meal[]>(environment.apiEndpoint + 'doctor/get-meals');
    }

    createMeal(mealDto : Meal): Observable<Meal>{
        return this.http.post<Meal>(environment.apiEndpoint + 'doctor/create-meal', mealDto, {
            headers: this.headers
        });
    }
  }