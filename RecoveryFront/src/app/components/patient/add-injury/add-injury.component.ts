import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormGroupDirective, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { InjuryType } from 'src/app/model/injury-type.model';
import { Injury } from 'src/app/model/injury.model';
import { InjuryTypeService } from 'src/app/services/injury-type.service';
import { InjuryService } from 'src/app/services/injury.service';

@Component({
  selector: 'app-add-injury',
  templateUrl: './add-injury.component.html',
  styleUrls: ['./add-injury.component.sass']
})
export class AddInjuryComponent implements OnInit {

  addInjuryForm: FormGroup;
  allInjuryTypes: InjuryType[] = [];

  constructor(
    private toastr: ToastrService,
    private injuryService: InjuryService,
    private injuryTypeService: InjuryTypeService
  ) {
    this.addInjuryForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
      startDate: new FormControl('', [Validators.required]),
      description: new FormControl(''),
      injuryType: new FormControl('', [Validators.required]),
      injuryBodyPart: new FormControl('', [Validators.required]),
    })
  }

  ngOnInit(): void {
    this.injuryTypeService.findAll()
      .subscribe(
        (response) => {
          this.allInjuryTypes = response;
        },
        (error) => {
          this.toastr.error('503 Server Unavailable');
        }
      );
  }

  onSubmit(addInjuryDirective: FormGroupDirective): void {
    if (this.addInjuryForm.invalid) {
      return;
    }

    const injury: Injury =
      new Injury(
        this.addInjuryForm.value.name,
        this.addInjuryForm.value.startDate,
        this.addInjuryForm.value.description,
        this.addInjuryForm.value.injuryType,
        this.addInjuryForm.value.injuryBodyPart
      );

    this.injuryService.addInjury(injury)
      .subscribe(
        response => {
          this.toastr.info('Hope you feel better soon.', 'Succesfully added new injury!');
          this.addInjuryForm.reset();
          addInjuryDirective.resetForm();
        },
        error => {
          if (error.error.message) {
            this.toastr.error(error.error.message);
          } else {
            this.toastr.error('503 Server Unavailable');
          }
          this.addInjuryForm.reset();
          addInjuryDirective.resetForm();
        });
  }

  getRequiredFieldErrorMessage(fieldName: string): string {
    if (this.addInjuryForm.controls[fieldName].touched) {
      return this.addInjuryForm.controls[fieldName].hasError('required') ? 'Required field' : '';
    }
    return '';
  }
}
