import { Component, OnInit } from '@angular/core';
import { AppliedTherapy } from 'src/app/model/applied-therapy.model';
import { Meal } from 'src/app/model/meal.model';
import { DailyMealService } from 'src/app/services/daily-meal.service';
import { TherapyService } from 'src/app/services/therapy.service';

@Component({
  selector: 'app-patient-todo',
  templateUrl: './patient-todo.component.html',
  styleUrls: ['./patient-todo.component.sass']
})
export class PatientTodoComponent implements OnInit {

  dataSourceMeals: Meal[] = [];
  dataSourceTherapies: AppliedTherapy[] = [];
  dayMeal: Date = new Date();

  constructor(
    private dailyMealService: DailyMealService,
    private therapyService: TherapyService
  ) { }

  ngOnInit(): void {
    this.dailyMealService.getAllDailyMealsCurrent().subscribe(
      response => {
        this.dataSourceMeals = response.meals;
        this.dayMeal = response.day;
      },
      error => {
        console.error()
      }
    )
    this.therapyService.getAllDailyTherapiesCurrent().subscribe(
      response => {
        this.dataSourceTherapies = response;
      },
      error => {
        console.error()
      }
    )
  }

}
