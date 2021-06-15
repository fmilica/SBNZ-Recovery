import { animate, state, style, transition, trigger } from '@angular/animations';
import { Component, Input, OnInit } from '@angular/core';
import { AppliedTherapy } from 'src/app/model/applied-therapy.model';
import { Injury } from 'src/app/model/injury.model';
import { InjuryService } from 'src/app/services/injury.service';

@Component({
  selector: 'app-injury-table',
  templateUrl: './injury-table.component.html',
  styleUrls: ['./injury-table.component.sass'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({ height: '0px', minHeight: '0' })),
      state('expanded', style({ height: '*' })),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ]
})
export class InjuryTableComponent implements OnInit {

  displayedColumns: string[] = ['name', 'startDate', 'endDate', 'description', 'injuryType', 'injuryBodyPart'];
  displayedTherapyColumns: string[] = ['applicationDate', 'therapyName', 'therapyType'];
  @Input() dataSource: Injury[] = [];
  expandedElement: any | null;
  chosenInjuryTherapies: AppliedTherapy[] = [];

  constructor(
    private injuryService: InjuryService
  ) { }

  ngOnInit(): void {
  }

  getAppliedTherapies(injuryId: number): void {
    this.injuryService
      .findTherapiesForPatientForInjury(injuryId).subscribe(
        response => {
          this.chosenInjuryTherapies = response;
        },
        error => {
        });
  }

  formatInjuryBodyPart(injuryBodyPart: string): string {
    return injuryBodyPart.charAt(0) + injuryBodyPart.slice(1).toLowerCase();
  }

  formatTherapyType(therapyType: string): string {
    return therapyType.charAt(0) + therapyType.slice(1).toLowerCase();
  }
}
