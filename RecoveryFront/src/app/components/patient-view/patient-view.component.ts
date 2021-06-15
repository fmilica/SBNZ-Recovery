import { Component, OnInit } from '@angular/core';
import { Injury } from 'src/app/model/injury.model';
import { Meal } from 'src/app/model/meal.model';
import { Patient } from 'src/app/model/patient.model';
import { InjuryService } from 'src/app/services/injury.service';
import { MealService } from 'src/app/services/meal.service';

@Component({
  selector: 'app-patient-view',
  templateUrl: './patient-view.component.html',
  styleUrls: ['./patient-view.component.sass']
})
export class PatientViewComponent implements OnInit {

  patient: Patient = {} as Patient;
  injuryDataSource: Injury[] = [];
  mealDataSource: Meal[] = [];
  applyMeals: boolean = false;

  constructor(
    private injuryService: InjuryService,
    private mealService: MealService
  ) {
  }

  ngOnInit(): void {
    this.patient = history.state.data.patient;
    this.injuryService
      .findAllForGivenPatient(this.patient.id).subscribe(
        response => {
          this.injuryDataSource = response;
        },
        error => {
        });
    this.mealService
      .getMeals().subscribe(
        response => {
          this.mealDataSource = response;
        },
        error => {
        });
  }

  getPatientName(): string {
    return this.patient.name + " " + this.patient.surname;
  }

  assignTherapy(): void {
    console.log(this.patient);
  }

  assignMeals(): void {
    console.log(this.patient);
    this.applyMeals = true;
    // dobavi sve obroke pogodne za korisnika
    this.mealService
      .getMeals().subscribe(
        response => {
          this.mealDataSource = response;
        },
        error => {
        });
  }
}
