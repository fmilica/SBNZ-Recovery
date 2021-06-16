import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Injury } from 'src/app/model/injury.model';
import { Meal } from 'src/app/model/meal.model';
import { Patient } from 'src/app/model/patient.model';
import { InjuryService } from 'src/app/services/injury.service';
import { MealService } from 'src/app/services/meal.service';
import { TherapyService } from 'src/app/services/therapy.service';

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
    private therapyService: TherapyService,
    private mealService: MealService,
    private toastr: ToastrService
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
    this.therapyService
      .assignTherapy(this.patient.id).subscribe(
        response => {
          this.toastr.success("Succesfully assign therapy for " + this.patient.name);
        },
        error => {
          if (error.error.message) {
            this.toastr.error(error.error.message);
          } else {
            this.toastr.error('503 Server Unavailable');
          }
        });
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
