import { animate, state, style, transition, trigger } from '@angular/animations';
import { Component, Input, OnInit } from '@angular/core';
import { IngredientAmount } from 'src/app/model/ingredient-amount.model';
import { Meal } from 'src/app/model/meal.model';

@Component({
  selector: 'app-meal-table',
  templateUrl: './meal-table.component.html',
  styleUrls: ['./meal-table.component.sass'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({ height: '0px', minHeight: '0' })),
      state('expanded', style({ height: '*' })),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ]
})
export class MealTableComponent implements OnInit {

  displayedColumns: string[] = ['name', 'mealDescription', 'totalCalories', 'assignMeal'];
  displayedIngredientColumns: string[] = ['ingredientName', 'amount'];
  @Input() dataSource: Meal[] = [];
  @Input() assignPatientMeal: boolean = false;
  expandedElement: any | null;
  chosenIngredients: IngredientAmount[] = [];

  constructor() { }

  ngOnInit(): void {
    if (this.assignPatientMeal) {
      this.displayedColumns = ['name', 'mealDescription', 'totalCalories', 'assignMeal'];
    } else {
      this.displayedColumns = ['name', 'mealDescription', 'totalCalories'];
    }
  }

  getIngredientAmounts(id: number) {
    this.dataSource.forEach((element, index) => {
      if (element.id === id) {
        this.chosenIngredients = element.ingredients;
      }
    })
  }
}
