import { Component, OnInit } from '@angular/core';
import { Injury } from 'src/app/model/injury.model';
import { InjuryService } from 'src/app/services/injury.service';

@Component({
  selector: 'app-medical-history',
  templateUrl: './medical-history.component.html',
  styleUrls: ['./medical-history.component.sass']
})
export class MedicalHistoryComponent implements OnInit {

  displayedColumns: string[] = ['name', 'startDate', 'endDate', 'description', 'injuryType', 'injuryBodyPart'];
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

  formatInjuryBodyPart(injuryBodyPart: string): string {
    return injuryBodyPart.charAt(0) + injuryBodyPart.slice(1).toLowerCase();
  }

}
