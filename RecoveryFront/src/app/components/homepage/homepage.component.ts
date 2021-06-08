import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.sass']
})
export class HomepageComponent implements OnInit {

  role = '';

  constructor() { }

  ngOnInit(): void {
  }

  logout(): void {
  }

  toSuperAdmin(): void {
  }

}
