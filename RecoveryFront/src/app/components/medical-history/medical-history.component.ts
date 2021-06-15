import { Component, OnInit } from '@angular/core';
import { Injury } from 'src/app/model/injury.model';
import { InjuryService } from 'src/app/services/injury.service';

@Component({
  selector: 'app-medical-history',
  templateUrl: './medical-history.component.html',
  styleUrls: ['./medical-history.component.sass'],
})
export class MedicalHistoryComponent implements OnInit {

  dataSource: Injury[] = [];

  constructor(
    private injuryService: InjuryService
  ) { }

  ngOnInit(): void {
    this.injuryService
      .findAllForPatient().subscribe(
        response => {
          this.dataSource = response;
        },
        error => {
        });
  }

}
