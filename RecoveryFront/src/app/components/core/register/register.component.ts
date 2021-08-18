import { STEPPER_GLOBAL_OPTIONS } from '@angular/cdk/stepper';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Illness } from 'src/app/model/illness.model';
import { PatientRegister } from 'src/app/model/patient-register.model';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { IllnessService } from 'src/app/services/illness.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.sass'],
  providers: [{
    provide: STEPPER_GLOBAL_OPTIONS, useValue: { showError: true }
  }]
})
export class RegisterComponent implements OnInit {

  personalInfoForm: FormGroup;
  healthStateForm: FormGroup;

  physicalActivityDescription: string = '';
  allIllnesses: Illness[] = [];

  constructor(
    private router: Router,
    private toastr: ToastrService,
    private authService: AuthenticationService,
    private illnessService: IllnessService
  ) {
    this.personalInfoForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required]),
      name: new FormControl('', [Validators.required]),
      surname: new FormControl('', [Validators.required]),
      dateOfBirth: new FormControl('', [Validators.required]),
      gender: new FormControl('', [Validators.required])
    })
    this.healthStateForm = new FormGroup({
      height: new FormControl('', [Validators.required]),
      weight: new FormControl('', [Validators.required]),
      physicalActivity: new FormControl('', [Validators.required]),
      anamnesis: new FormControl('')
    })
  }

  ngOnInit(): void {
    this.illnessService.findAll()
      .subscribe(
        (response) => {
          this.allIllnesses = response;
        },
        (error) => {
          this.toastr.error('503 Server Unavailable');
        }
      );
  }

  register(): void {
    if (this.personalInfoForm.invalid || this.healthStateForm.invalid) {
      return;
    }

    let anamnesis = this.healthStateForm.value.anamnesis;
    if (anamnesis === '') {
      anamnesis = null;
    }

    const patient: PatientRegister =
      new PatientRegister(
        this.personalInfoForm.value.email,
        this.personalInfoForm.value.password,
        this.personalInfoForm.value.name,
        this.personalInfoForm.value.surname,
        this.personalInfoForm.value.dateOfBirth,
        this.personalInfoForm.value.gender,
        this.healthStateForm.value.height,
        this.healthStateForm.value.weight,
        this.healthStateForm.value.physicalActivity,
        anamnesis,
      );

    this.toastr.info('Your registration is being processed.', 'Please wait', {
      timeOut: 10000,
      progressBar: true,
      progressAnimation: 'increasing'
    })

    this.authService.register(patient)
      .subscribe(
        response => {
          this.toastr.success('Succesfully registered! You can log in now!');
          this.personalInfoForm.reset();
          this.healthStateForm.reset();
          this.router.navigate(['login']);
        },
        error => {
          if (error.error.message) {
            this.toastr.error(error.error.message);
          } else {
            this.toastr.error('503 Server Unavailable');
          }
          this.personalInfoForm.reset();
          this.healthStateForm.reset();
        });
  }

  onActivitySelected(activity: string) {
    switch (activity) {
      case "SEDENTARY":
        this.physicalActivityDescription = "Less than 30 minutes of daily exercise.";
        break;
      case "LIGHT_ACTIVE":
        this.physicalActivityDescription = "At least 30 minutes of daily exercise (jogging, skiing).";
        break;
      case "MODERATE":
        this.physicalActivityDescription = "Up to an hour of daily excercise.";
        break;
      case "VERY_ACTIVE":
        this.physicalActivityDescription = "Up to 2 hours of daily excercise (gym, sports).";
        break;
      case "EXTRA_ACTIVE":
        this.physicalActivityDescription = "At least 3 hours of daily exercise (two excercise sessions per day).";
        break;
    }
  }

  getRequiredPersonalInfoErrorMessage(fieldName: string): string {
    if (this.personalInfoForm.controls[fieldName].hasError('required')) {
      return 'Required field';
    }
    if (this.personalInfoForm.controls[fieldName].hasError('email')) {
      return 'Invalid email format';
    }
    return '';
  }

  getRequiredHealthStateErrorMessage(fieldName: string): string {
    if (this.healthStateForm.controls[fieldName].hasError('required')) {
      return 'Required field'
    }
    return '';
  }

  getPersonalInfoFormErrors(): string {
    if (this.personalInfoForm.get("email")?.hasError('required')) {
      return 'Email is required';
    }
    if (this.personalInfoForm.get("email")?.hasError('email')) {
      return 'Invalid email format';
    }
    if (this.personalInfoForm.get("password")?.hasError('required')) {
      return 'Password is required';
    }
    if (this.personalInfoForm.get("name")?.hasError('required')) {
      return 'Name is required';
    }
    if (this.personalInfoForm.get("surname")?.hasError('required')) {
      return 'Surname is required';
    }
    if (this.personalInfoForm.get("dateOfBirth")?.hasError('required')) {
      return 'Date of birth is required';
    }
    if (this.personalInfoForm.get("gender")?.hasError('required')) {
      return 'Gender is required';
    }
    return '';
  }

  getHealthStateFormErrors(): string {
    if (this.healthStateForm.get("height")?.hasError('required')) {
      return 'Height is required';
    }
    if (this.personalInfoForm.get("weight")?.hasError('required')) {
      return 'Weight is required';
    }
    if (this.personalInfoForm.get("physicalActivity")?.hasError('required')) {
      return 'Physical activity is required';
    }
    return '';
  }
}
