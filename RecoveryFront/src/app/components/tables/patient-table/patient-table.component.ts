import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Patient } from 'src/app/model/patient.model';

@Component({
  selector: 'app-patient-table',
  templateUrl: './patient-table.component.html',
  styleUrls: ['./patient-table.component.sass']
})
export class PatientTableComponent implements OnInit {

  displayedColumns: string[] = ['name', 'surname', 'dateOfBirth', 'gender', 'height', 'weight', 'physicalActivity'];
  @Input() dataSource: Patient[] = [];

  constructor(
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  patientView(patient: Patient) {
    this.router.navigate(['homepage/patient-view'], { state: { data: { patient } } });
  }
}
