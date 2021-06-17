import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormGroupDirective, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { IngredientAmount } from 'src/app/model/ingredient-amount.model';
import { Ingredient } from 'src/app/model/ingredient.model';
import { Meal } from 'src/app/model/meal.model';
import { DoctorService } from 'src/app/services/doctor.service';

class Type {
  ingredient: Ingredient;
  allowed: boolean;
  
  constructor (ingredient: Ingredient, allowed: boolean) {
    this.ingredient = ingredient;
    this.allowed = allowed;
  }
}

@Component({
  selector: 'app-create-meal',
  templateUrl: './create-meal.component.html',
  styleUrls: ['./create-meal.component.sass']
})
export class CreateMealComponent implements OnInit {

  newMealForm: FormGroup;
  options: Array<Type> = [];
  values : Array<IngredientAmount> = [];
  constructor(
    private doctorService: DoctorService,
    private router: Router,
    private toastr: ToastrService
  ) { 
    this.newMealForm = new FormGroup({
      name0: new FormControl('', [Validators.required]),
      amount0: new FormControl('', [Validators.required]),
      nameMeal: new FormControl('', [Validators.required]),
      description: new FormControl('', [Validators.required])
    });
    this.values.push(new IngredientAmount(new Ingredient("",0,0,0,0,0,0,0), 0.00));
  }

  ngOnInit(): void {
    let that = this
    this.doctorService.getIngredients()
    .subscribe(
      response => {
        response.forEach(function (value) {
          that.options.push(new Type(value, false))
        }); 
      },
    )
  }

  removevalue(i: number){
    this.newMealForm.removeControl('name'+i);
    this.newMealForm.removeControl('amount'+i);
    this.values.splice(i,1);
  }

  addvalue(){
    this.values.push(new IngredientAmount(new Ingredient("",0,0,0,0,0,0,0), 0));
    let name = "name" + (this.values.length-1)
    let amount = "amount" + (this.values.length-1)
    this.newMealForm.addControl(name, new FormControl('', [Validators.required]))
    this.newMealForm.addControl(amount, new FormControl('', [Validators.required]))
  }

  onSubmit(createMealDirective: FormGroupDirective) {
    if (this.newMealForm.invalid) {
      return;
    }
    let mealDto = new Meal(this.newMealForm.value.nameMeal, this.values, this.newMealForm.value.description, 0)

    this.doctorService.createMeal(mealDto).subscribe(
      response => {
        this.toastr.success('Successfully created meal!');
        this.router.navigate(['homepage/create-meal']);
        this.newMealForm.reset();
        createMealDirective.resetForm();
        this.values = [];
        this.values.push(new IngredientAmount(new Ingredient("",0,0,0,0,0,0,0), 0.00));
      },
      error => {
        this.toastr.error(error.error.fieldErrors[0].defaultMessage)
        this.newMealForm.reset();
        createMealDirective.resetForm();
        this.values = [];
        this.values.push(new IngredientAmount(new Ingredient("",0,0,0,0,0,0,0), 0.00));
      });
  }

  setIngredient(index: number, ingredientId: number) {
    this.values[index].ingredient.id = ingredientId
    this.options.forEach(function(value){
      if(value.ingredient.id === ingredientId){
        value.allowed = true
      }
    })
  }

  setAmount(index: number, event: any) {
    this.values[index].amount = event.target.value
  }

  getFieldErrorMessage(fieldName: string): string {
    if (this.newMealForm.controls[fieldName].touched) {
      if (this.newMealForm.controls[fieldName].hasError('required')) {
        return 'Required field'
      }
    }
    return '';
  }
}
