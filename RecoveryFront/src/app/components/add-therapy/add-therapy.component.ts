import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormGroupDirective, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Therapy } from 'src/app/model/therapy.model';
import { TherapyService } from 'src/app/services/therapy.service';

@Component({
  selector: 'app-add-therapy',
  templateUrl: './add-therapy.component.html',
  styleUrls: ['./add-therapy.component.sass']
})
export class AddTherapyComponent implements OnInit {

  addTherapyForm: FormGroup;

  constructor(
    private router: Router,
    private toastr: ToastrService,
    private therapyService: TherapyService
  ) {
    this.addTherapyForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
      therapyType: new FormControl('', [Validators.required]),
      maximumMonthlyApplication: new FormControl('', [Validators.required]),
      temperature: new FormControl('', [Validators.required]),
      intensity: new FormControl('', [Validators.required]),
    })
  }

  ngOnInit(): void {
  }

  onSubmit(addTherapyDirective: FormGroupDirective): void {
    if (this.addTherapyForm.invalid) {
      return;
    }

    const therapy: Therapy =
      new Therapy(
        this.addTherapyForm.value.name,
        this.addTherapyForm.value.therapyType,
        this.addTherapyForm.value.maximumMonthlyApplication,
        this.addTherapyForm.value.temperature,
        this.addTherapyForm.value.intensity
      );

    this.therapyService.createTherapy(therapy)
      .subscribe(
        response => {
          this.toastr.info('Hope it helps a lot of people.', 'Succesfully created new therapy!');
          this.addTherapyForm.reset();
          addTherapyDirective.resetForm();
          this.router.navigate(['homepage/view-therapies'])
        },
        error => {
          if (error.error.message) {
            this.toastr.error(error.error.message);
          } else {
            this.toastr.error('503 Server Unavailable');
          }
          this.addTherapyForm.reset();
          addTherapyDirective.resetForm();
        });
  }

  getRequiredFieldErrorMessage(fieldName: string): string {
    if (this.addTherapyForm.controls[fieldName].touched) {
      return this.addTherapyForm.controls[fieldName].hasError('required') ? 'Required field' : '';
    }
    return '';
  }

}
