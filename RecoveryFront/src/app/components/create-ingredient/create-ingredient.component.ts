import { Component, OnInit } from '@angular/core';

import { FormControl, FormGroup, FormGroupDirective, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Ingredient } from 'src/app/model/ingredient.model';
import { DoctorService } from 'src/app/services/doctor.service';

@Component({
  selector: 'app-create-ingredient',
  templateUrl: './create-ingredient.component.html',
  styleUrls: ['./create-ingredient.component.sass']
})
export class CreateIngredientComponent implements OnInit {

  newIngredientForm: FormGroup;

  constructor(
    private toastr: ToastrService,
    private router: Router,
    private doctorService: DoctorService
  ) { 
    this.newIngredientForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
      calories: new FormControl('', [Validators.required]),
      waterPercentage: new FormControl('', [Validators.required]),
      proteins: new FormControl('', [Validators.required]),
      carbonhydrates: new FormControl('', [Validators.required]),
      sugars: new FormControl('', [Validators.required]),
      fiber: new FormControl('', [Validators.required]),
      fat: new FormControl('', [Validators.required]),
    });
  }

  ngOnInit(): void {
  }

  onSubmit(createIngredientDirective: FormGroupDirective) {
    if (this.newIngredientForm.invalid) {
      return;
    }

    const ingredient: Ingredient = new Ingredient (
      this.newIngredientForm.value.name,
      this.newIngredientForm.value.calories,
      this.newIngredientForm.value.waterPercentage,
      this.newIngredientForm.value.proteins,
      this.newIngredientForm.value.carbonhydrates,
      this.newIngredientForm.value.sugars,
      this.newIngredientForm.value.fiber,
      this.newIngredientForm.value.fat
    );

    this.doctorService.createIngredient(ingredient)
    .subscribe(
      response => {
        this.toastr.success('Successfully created ingredient!');
        this.router.navigate(['homepage/create-ingredient']);
        this.newIngredientForm.reset();
        createIngredientDirective.resetForm();
      },
      error => {
        this.toastr.error(error.error.fieldErrors[0].defaultMessage)
        this.newIngredientForm.reset();
        createIngredientDirective.resetForm();
      });
  }

  getFieldErrorMessage(fieldName: string): string {
    if (this.newIngredientForm.controls[fieldName].touched) {
      if (this.newIngredientForm.controls[fieldName].hasError('required')) {
        return 'Required field'
      }
    }
    return '';
  }


}
