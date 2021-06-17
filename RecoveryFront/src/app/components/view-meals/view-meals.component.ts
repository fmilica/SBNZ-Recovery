import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Illness } from 'src/app/model/illness.model';
import { Meal } from 'src/app/model/meal.model';
import { IllnessService } from 'src/app/services/illness.service';
import { MealService } from 'src/app/services/meal.service';

@Component({
  selector: 'app-view-meals',
  templateUrl: './view-meals.component.html',
  styleUrls: ['./view-meals.component.sass']
})
export class ViewMealsComponent implements OnInit {

  dataSource: Meal[] = [];

  allIllnesses: Illness[] = [];
  filterForm: FormGroup;

  constructor(
    private mealService: MealService,
    private illnessService: IllnessService
  ) {
    this.filterForm = new FormGroup({
      illness: new FormControl(''),
    })
  }

  ngOnInit(): void {
    this.mealService
      .getMeals().subscribe(
        response => {
          this.dataSource = response;
        },
        error => {
        });
    this.illnessService.findAll()
      .subscribe(
        response => {
          this.allIllnesses = response;
          this.allIllnesses.unshift({ "id": -1, "name": "" })
        }
      );
  }

  onFilter(): void {
    const illnessId = this.filterForm.value.illness;
    if (illnessId == -1) {
      this.mealService
        .getMeals().subscribe(
          response => {
            this.dataSource = response;
          },
        )
    } else {
      this.mealService.filterMeals(illnessId)
        .subscribe(
          (response) => {
            this.dataSource = response;
          }
        );
    }
  }

}
