import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Ingredient } from "../model/ingredient.model";
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from "src/environments/environment";
import { Meal } from "../model/meal.model";
import { Patient } from "../model/patient.model";
import { InjuryCount } from "../model/injury-count.model";
import { GenderCount } from "../model/gender-count.model";
import { TemplateDTO } from "../model/template-dto.model";

@Injectable({
    providedIn: 'root',
})
export class DoctorService {

    constructor(
        private http: HttpClient
    ) { }

    private headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    createIngredient(ingredientDto: Ingredient): Observable<Ingredient> {
        return this.http.post<Ingredient>(environment.apiEndpoint + 'doctor/create-ingredient', ingredientDto, {
            headers: this.headers
        });
    }

    getIngredients(): Observable<Ingredient[]> {
        return this.http.get<Ingredient[]>(environment.apiEndpoint + 'doctor/get-ingredients');
    }

    filterIngredients(illnessId: number): Observable<Ingredient[]> {
        return this.http.get<Ingredient[]>(environment.apiEndpoint + 'doctor/filter-ingredients/' + illnessId);
    }

    createMeal(mealDto: Meal): Observable<Meal> {
        return this.http.post<Meal>(environment.apiEndpoint + 'doctor/create-meal', mealDto, {
            headers: this.headers
        });
    }

    getAbuseReport(): Observable<Patient[]> {
        return this.http.get<Patient[]>(environment.apiEndpoint + 'doctor/potential-abuse');
    }

    getAtrophyReport(): Observable<Patient[]> {
        return this.http.get<Patient[]>(environment.apiEndpoint + 'doctor/potential-atrophy');
    }

    getEatingDisorderReport(): Observable<Patient[]> {
        return this.http.get<Patient[]>(environment.apiEndpoint + 'doctor/potential-eating-disorder');
    }

    getInjuryCountByAgeGroup(injuryCount: InjuryCount): Observable<number> {
        return this.http.post<number>(environment.apiEndpoint + 'doctor/injury-count-by-age', injuryCount, {
            headers: this.headers
        });
    }

    getInjuryCountByInjuryType(injuryCount: InjuryCount): Observable<number> {
        return this.http.post<number>(environment.apiEndpoint + 'doctor/injury-count-by-type', injuryCount, {
            headers: this.headers
        });
    }

    getInjuryCountByGender(interval: GenderCount): Observable<TemplateDTO[]> {
        return this.http.post<TemplateDTO[]>(environment.apiEndpoint + 'doctor/injury-count-by-gender', interval, {
            headers: this.headers
        });
    }
}