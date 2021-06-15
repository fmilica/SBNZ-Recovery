import { Component, OnInit } from '@angular/core';
import { ViewTherapy } from 'src/app/model/view-therapy.model';
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

  constructor(
    private therapyService: TherapyService
  ) { }

  ngOnInit(): void {
    this.therapyService
      .getTherapies().subscribe(
        response => {
          this.dataSource = response;
        },
        error => {
        });
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
