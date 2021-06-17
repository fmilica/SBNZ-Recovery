import { animate, state, style, transition, trigger } from '@angular/animations';
import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Illness } from 'src/app/model/illness.model';
import { IngredientAmount } from 'src/app/model/ingredient-amount.model';
import { Meal } from 'src/app/model/meal.model';
import { MealService } from 'src/app/services/meal.service';

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
  displayedIngredientColumns: string[] = ['ingredientName', 'amount', 'illnesses'];
  @Input() dataSource: Meal[] = [];
  @Input() assignPatientMeal: boolean = false;
  @Input() patientId: number = 0;
  expandedElement: any | null;
  chosenIngredients: IngredientAmount[] = [];

  constructor(
    private mealService: MealService,
    private toastr: ToastrService,
    private router: Router
  ) { }

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

  addMeal(meal: Meal) {

    this.mealService.addMeal(meal, this.patientId).subscribe(
      response => {
        this.toastr.success('Successfully added meal!');
        this.router.navigate(['homepage/view-patients']);
      },
      error => {
        this.toastr.error(error.error.message)
      });
  }

  formatIllnesses(illnesses: Illness[]) {
    let illnessesString = ""
    illnesses.forEach((element, index) => {
      illnessesString += element.name;
      if (index != illnesses.length - 1) {
        illnessesString += ", ";
      }
    })
    return illnessesString;
  }
}
