import { Component, OnInit } from '@angular/core';
import { PatientRegister } from 'src/app/model/patient-register.model';
import { PatientService } from 'src/app/services/patient.service';

@Component({
  selector: 'app-view-patients',
  templateUrl: './view-patients.component.html',
  styleUrls: ['./view-patients.component.sass']
})
export class ViewPatientsComponent implements OnInit {

  displayedColumns: string[] = ['name', 'surname', 'dateOfBirth', 'gender', 'height', 'weight', 'physicalActivity'];
  dataSource: PatientRegister[] = [];

  constructor(
    private patientService: PatientService
  ) { }

  ngOnInit(): void {
    this.patientService
      .getPatients().subscribe(
        response => {
          this.dataSource = response;
        },
        error => {
        });
  }

}
