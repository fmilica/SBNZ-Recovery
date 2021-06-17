import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Illness } from 'src/app/model/illness.model';
import { InjuryType } from 'src/app/model/injury-type.model';
import { ViewTherapy } from 'src/app/model/view-therapy.model';
import { IllnessService } from 'src/app/services/illness.service';
import { InjuryTypeService } from 'src/app/services/injury-type.service';
import { TherapyService } from 'src/app/services/therapy.service';

@Component({
  selector: 'app-view-therapies',
  templateUrl: './view-therapies.component.html',
  styleUrls: ['./view-therapies.component.sass']
})
export class ViewTherapiesComponent implements OnInit {

  displayedColumns: string[] = ['name', 'therapyType', 'maximumMonthlyApplication',
    'temperature', 'intensity', 'applicableIllness', 'applicableInjury'];
  dataSource: ViewTherapy[] = [];

  allIllnesses: Illness[] = [];
  allInjuryTypes: InjuryType[] = []
  filterForm: FormGroup;

  constructor(
    private therapyService: TherapyService,
    private illnessService: IllnessService,
    private injuryTypeService: InjuryTypeService
  ) {
    this.filterForm = new FormGroup({
      illness: new FormControl(''),
      injuryType: new FormControl('')
    })
  }

  ngOnInit(): void {
    this.therapyService
      .getTherapies().subscribe(
        response => {
          this.dataSource = response;
        },
        error => {
        });
    this.illnessService.findAll()
      .subscribe(
        (response) => {
          this.allIllnesses = response;
          this.allIllnesses.unshift({ "id": -1, "name": "" })
        }
      );
    this.injuryTypeService.findAll()
      .subscribe(
        (response) => {
          this.allInjuryTypes = response;
          this.allInjuryTypes.unshift({ "id": -1, "name": "" })
        }
      );
  }

  onFilter(): void {
    let illnessId = this.filterForm.value.illness;
    let injuryTypeId = this.filterForm.value.injuryType;
    illnessId === '' ? illnessId = -1 : illnessId = illnessId;
    injuryTypeId === '' ? injuryTypeId = -1 : injuryTypeId = injuryTypeId;
    this.therapyService
      .filterTherapies(illnessId, injuryTypeId).subscribe(
        response => {
          this.dataSource = response;
        },
      )
  }

  formatTherapyType(therapyType: string): string {
    return therapyType.charAt(0) + therapyType.slice(1).toLowerCase();
  }

  formatStringList(illnessList: string[]): string {
    return illnessList.map((illness) => {
      return illness;
    }).join(", ");
  }

}
