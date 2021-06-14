import { animate, state, style, transition, trigger } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { IngredientAmount } from 'src/app/model/ingredient-amount.model';
import { Meal } from 'src/app/model/meal.model';
import { MealService } from 'src/app/services/meal.service';

@Component({
  selector: 'app-view-meals',
  templateUrl: './view-meals.component.html',
  styleUrls: ['./view-meals.component.sass'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({ height: '0px', minHeight: '0' })),
      state('expanded', style({ height: '*' })),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class ViewMealsComponent implements OnInit {

  displayedColumns: string[] = ['name', 'mealDescription'];
  displayedIngredientColumns: string[] = ['ingredientName', 'amount'];
  dataSource: Meal[] = [];
  expandedElement: any | null;
  chosenIngredients: IngredientAmount[] = [];

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

  getIngredientAmounts(id: number){
    this.dataSource.forEach((element, index) => {
      if(element.id === id){
        this.chosenIngredients = element.ingredients;
      }
    })
  }

}
