import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { NavItem } from '../menu-list-item/nav-item';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.sass']
})
export class HomepageComponent implements OnInit {

  role = '';
  navDoctorData: NavItem[] = [
    {
      displayName: 'Patients',
      iconName: 'person',
      route: 'homepage/patient',
      children: [
        {
          displayName: 'View Patients',
          iconName: 'groups',
          route: 'homepage/patient/view-patients',
        },
      ]
    },
    {
      displayName: 'Ingredients',
      iconName: 'restaurant',
      route: 'homepage/ingredient',
      children: [
        {
          displayName: 'View Ingredients',
          iconName: 'view_list',
          route: 'homepage/ingredient/view-ingredients',
        },
        {
          displayName: 'Create Ingredient',
          iconName: 'add_circle_outline',
          route: 'homepage/ingredient/create-ingredient',
        },
      ]
    },
    {
      displayName: 'Meals',
      iconName: 'menu_book',
      route: 'homepage/meal',
      children: [
        {
          displayName: 'View Meals',
          iconName: 'view_list',
          route: 'homepage/meal/view-meals',
        },
        {
          displayName: 'Create Meal',
          iconName: 'add_circle_outline',
          route: 'homepage/meal/create-meal',
        },
      ]
    },
    {
      displayName: 'Therapies',
      iconName: 'healing',
      route: 'homepage/therapy',
      children: [
        {
          displayName: 'View Therapies',
          iconName: 'view_list',
          route: 'homepage/therapy/view-therapies',
        },
        {
          displayName: 'Create Therapy',
          iconName: 'add_circle_outline',
          route: 'homepage/therapy/create-therapy',
        },
      ]
    },
    {
      displayName: 'Reports',
      iconName: 'bar_chart',
      route: 'homepage/reports',
      children: [
        {
          displayName: 'Create Report',
          iconName: 'description',
          route: 'homepage/reports',
        },
      ]
    },
  ];

  navPatientData = [
    {
      displayName: 'Medical History',
      iconName: 'home',
      route: 'homepage/patient',
      children: [
        {
          displayName: 'About Me',
          iconName: 'person',
          route: 'homepage/patient/about-patient',
        },
        {
          displayName: 'View Medical History',
          iconName: 'local_hospital',
          route: 'homepage/patient/medical-history',
        },
        {
          displayName: 'Add Injury',
          iconName: 'personal_injury',
          route: 'homepage/patient/add-injury',
        },
      ]
    },
    {
      displayName: 'Daily ToDo',
      iconName: 'today',
      route: 'homepage/daily',
      children: [
        {
          displayName: 'See Today',
          iconName: 'healing',
          route: 'homepage/daily/patient-todo',
        },
      ]
    },
  ]

  constructor(
    private authService: AuthenticationService,
    private toastr: ToastrService
  ) { }

  ngOnInit(): void {
    this.role = this.authService.getLoggedInUserAuthority();
  }

  logout(): void {
    this.authService.logout();
    this.toastr.info('Logged out successfully!');
  }

  toggle(nav: any) {
    nav.open = !nav.open;
  }
}
