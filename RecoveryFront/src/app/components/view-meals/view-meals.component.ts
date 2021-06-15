import { animate, state, style, transition, trigger } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { IngredientAmount } from 'src/app/model/ingredient-amount.model';
import { Meal } from 'src/app/model/meal.model';
import { MealService } from 'src/app/services/meal.service';

@Component({
  selector: 'app-view-meals',
  templateUrl: './view-meals.component.html',
  styleUrls: ['./view-meals.component.sass']
})
export class ViewMealsComponent implements OnInit {

  dataSource: Meal[] = [];

  constructor(
    private mealService: MealService
  ) { }

  ngOnInit(): void {
    this.mealService
      .getMeals().subscribe(
        response => {
          this.dataSource = response;
        },
        error => {
        });
  }

}
