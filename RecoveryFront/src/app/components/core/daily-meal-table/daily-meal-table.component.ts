import { Component, Input, OnInit } from '@angular/core';
import { Meal } from 'src/app/model/meal.model';

@Component({
  selector: 'app-daily-meal-table',
  templateUrl: './daily-meal-table.component.html',
  styleUrls: ['./daily-meal-table.component.sass']
})
export class DailyMealTableComponent implements OnInit {

  displayedColumns: string[] = ['dayMeal', 'mealName'];
  @Input() dataSource: Meal[] = [];
  dayMeal: Date = new Date();

  constructor() { }

  ngOnInit(): void {
  }

}
