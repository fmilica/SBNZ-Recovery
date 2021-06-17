import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit } from '@angular/core';
import { Meal } from 'src/app/model/meal.model';
import { Patient } from 'src/app/model/patient.model';
import { DailyMealService } from 'src/app/services/daily-meal.service';

@Component({
  selector: 'app-assign-meal',
  templateUrl: './assign-meal.component.html',
  styleUrls: ['./assign-meal.component.sass']
})
export class AssignMealComponent implements OnInit {

  assignDisplayedColumns: string[] = ['dayMeal', 'mealName'];
  assignDataSource: Meal[] = [];
  patient: Patient = {} as Patient;
  remainingCalories: number = 0;
  dayMeal: Date = new Date();
  
  constructor(
    private dailyMealService: DailyMealService
  ) { }

  ngOnInit(): void {
    this.patient = history.state.data.patient;
    this.loadDailyMeals();
  }

  loadDailyMeals() {
    this.remainingCalories = this.patient.dailyCaloryIntakeAfterInjury;
    this.dailyMealService.getAllDailyMeals(this.patient.id).subscribe(
      response => {
        this.assignDataSource = response.meals;
        this.assignDataSource.forEach((element) => {
          this.remainingCalories = this.remainingCalories - element.totalCalories
        })
        this.dayMeal = response.day
      },
      error => {
        console.error()
      }
    )
  }

  getFullName(): string {
    return this.patient.name + " " + this.patient.surname; 
  }

  getUsedCalories(): number {
    return this.remainingCalories;
  }

  calculateCalories(value: number) {
    this.loadDailyMeals();
  }

}
