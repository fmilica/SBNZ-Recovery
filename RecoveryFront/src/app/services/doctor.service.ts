import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Ingredient } from "../model/ingredient.model";
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from "src/environments/environment";
import { Meal } from "../model/meal.model";

@Injectable({
    providedIn: 'root',
  })
  export class DoctorService {
      
    constructor(
        private http: HttpClient
    ) { }

    private headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    createIngredient(ingredientDto : Ingredient): Observable<Ingredient>{
        return this.http.post<Ingredient>(environment.apiEndpoint + 'doctor/create-ingredient', ingredientDto, {
            headers: this.headers
        });
    }

    getIngredients(): Observable<Ingredient[]> {
        return this.http.get<Ingredient[]>(environment.apiEndpoint + 'doctor/get-ingredients');
    }

    createMeal(mealDto : Meal): Observable<Meal>{
        return this.http.post<Meal>(environment.apiEndpoint + 'doctor/create-meal', mealDto, {
            headers: this.headers
        });
    }
  }