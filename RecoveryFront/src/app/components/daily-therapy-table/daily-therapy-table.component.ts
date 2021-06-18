import { Component, Input, OnInit } from '@angular/core';
import { AppliedTherapy } from 'src/app/model/applied-therapy.model';

@Component({
  selector: 'app-daily-therapy-table',
  templateUrl: './daily-therapy-table.component.html',
  styleUrls: ['./daily-therapy-table.component.sass']
})
export class DailyTherapyTableComponent implements OnInit {

  displayedColumns: string[] = ['dayTherapy', 'therapyName'];
  @Input() dataSource: AppliedTherapy[] = [];
  dayTherapy: Date = new Date();

  constructor() { }

  ngOnInit(): void {
  }

}
