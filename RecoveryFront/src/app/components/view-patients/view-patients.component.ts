import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Patient } from 'src/app/model/patient.model';
import { PatientService } from 'src/app/services/patient.service';

@Component({
  selector: 'app-view-patients',
  templateUrl: './view-patients.component.html',
  styleUrls: ['./view-patients.component.sass']
})
export class ViewPatientsComponent implements OnInit {

  dataSource: Patient[] = [];

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
