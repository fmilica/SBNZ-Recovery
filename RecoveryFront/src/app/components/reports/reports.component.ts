import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { InjuryCount } from 'src/app/model/injury-count.model';
import { InjuryType } from 'src/app/model/injury-type.model';
import { Patient } from 'src/app/model/patient.model';
import { DoctorService } from 'src/app/services/doctor.service';
import { InjuryTypeService } from 'src/app/services/injury-type.service';

@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.sass']
})
export class ReportsComponent implements OnInit {

  reportForm: FormGroup;
  dataSource: Patient[] = [];
  noResult: boolean = false;

  allInjuryTypes: InjuryType[] = [];
  injuryCount: number = 0;

  patientTableView: boolean = true;

  constructor(
    private doctorService: DoctorService,
    private injuryTypeService: InjuryTypeService
  ) {
    this.reportForm = new FormGroup({
      report: new FormControl('', [Validators.required]),
      injuryType: new FormControl(''),
      startDate: new FormControl(''),
      endDate: new FormControl(''),
      lowerAge: new FormControl(''),
      upperAge: new FormControl('')
    })
  }

  ngOnInit(): void {
    this.injuryTypeService.findAll()
      .subscribe(
        (response) => {
          this.allInjuryTypes = response;
        }
      );
  }

  getReport(): void {
    if (this.reportForm.invalid) {
      return;
    }

    let injuryCount: InjuryCount;
    const report = this.reportForm.value.report;
    switch (report) {
      case "ABUSE":
        this.doctorService.getAbuseReport()
          .subscribe(
            response => {
              response.length === 0 ? this.noResult = true : this.noResult = false;
              this.dataSource = response;
              this.patientTableView = true;
            }
          )
        break;
      case "ATROPHY":
        this.doctorService.getAtrophyReport()
          .subscribe(
            response => {
              response.length === 0 ? this.noResult = true : this.noResult = false;
              this.dataSource = response;
              this.patientTableView = true;
            }
          )
        break;
      case "EATING":
        this.doctorService.getEatingDisorderReport()
          .subscribe(
            response => {
              response.length === 0 ? this.noResult = true : this.noResult = false;
              this.dataSource = response;
              this.patientTableView = true;
            }
          )
        break;
      case "AGE-GROUP":
        injuryCount =
          new InjuryCount(
            this.reportForm.value.startDate,
            this.reportForm.value.endDate,
            -1,
            this.reportForm.value.lowerAge,
            this.reportForm.value.upperAge
          );
        this.doctorService.getInjuryCountByAgeGroup(injuryCount)
          .subscribe(
            response => {
              this.injuryCount = response;
              this.noResult = false;
              this.patientTableView = false;
            }
          )
        break;
      case "INJURY-TYPE":
        injuryCount =
          new InjuryCount(
            this.reportForm.value.startDate,
            this.reportForm.value.endDate,
            this.reportForm.value.injuryType
            - 1,
            -1
          );
        this.doctorService.getInjuryCountByInjuryType(injuryCount)
          .subscribe(
            response => {
              this.injuryCount = response;
              this.noResult = false;
              this.patientTableView = false;
            }
          )
        break;
    }
  }

}
