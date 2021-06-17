import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from 'rxjs';
import { environment } from "src/environments/environment";
import { Meal } from "../model/meal.model";
import { Patient } from "../model/patient.model";

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

    filterMeals(illnessId: number): Observable<Meal[]> {
        return this.http.get<Meal[]>(environment.apiEndpoint + 'doctor/filter-meals/' + illnessId);
    }

    createMeal(mealDto: Meal): Observable<Meal> {
        return this.http.post<Meal>(environment.apiEndpoint + 'doctor/create-meal', mealDto, {
            headers: this.headers
        });
    }

    getRankMeals(patientUsername: string): Observable<Meal[]> {
        return this.http.get<Meal[]>(environment.apiEndpoint + 'doctor/patient-rank-meals/' + patientUsername);
    }

    addMeal(mealDto: Meal, patientId: number): Observable<Meal> {
        return this.http.post<Meal>(environment.apiEndpoint + 'doctor/' + patientId + "/add-meal", mealDto, {
            headers: this.headers
        });
    }
}