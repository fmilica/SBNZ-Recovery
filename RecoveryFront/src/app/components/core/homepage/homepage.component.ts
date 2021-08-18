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
      route: 'doctor/patients',
      children: [
        {
          displayName: 'View Patients',
          iconName: 'groups',
          route: 'doctor/patients/view-patients',
        },
      ]
    },
    {
      displayName: 'Ingredients',
      iconName: 'restaurant',
      route: 'doctor/ingredients',
      children: [
        {
          displayName: 'View Ingredients',
          iconName: 'view_list',
          route: 'doctor/ingredients/view-ingredients',
        },
        {
          displayName: 'Create Ingredient',
          iconName: 'add_circle_outline',
          route: 'doctor/ingredients/create-ingredient',
        },
      ]
    },
    {
      displayName: 'Meals',
      iconName: 'menu_book',
      route: 'doctor/meals',
      children: [
        {
          displayName: 'View Meals',
          iconName: 'view_list',
          route: 'doctor/meals/view-meals',
        },
        {
          displayName: 'Create Meal',
          iconName: 'add_circle_outline',
          route: 'doctor/meals/create-meal',
        },
      ]
    },
    {
      displayName: 'Therapies',
      iconName: 'healing',
      route: 'doctor/therapies',
      children: [
        {
          displayName: 'View Therapies',
          iconName: 'view_list',
          route: 'doctor/therapies/view-therapies',
        },
        {
          displayName: 'Create Therapy',
          iconName: 'add_circle_outline',
          route: 'doctor/therapies/create-therapy',
        },
      ]
    },
    {
      displayName: 'Reports',
      iconName: 'bar_chart',
      route: 'doctor/reports',
      children: [
        {
          displayName: 'Create Report',
          iconName: 'description',
          route: 'doctor/reports',
        },
      ]
    },
  ];

  navPatientData = [
    {
      displayName: 'Medical History',
      iconName: 'home',
      route: 'patient',
      children: [
        {
          displayName: 'About Me',
          iconName: 'person',
          route: 'patient/about-me',
        },
        {
          displayName: 'View Medical History',
          iconName: 'local_hospital',
          route: 'patient/medical-history',
        },
        {
          displayName: 'Add Injury',
          iconName: 'personal_injury',
          route: 'patient/add-injury',
        },
      ]
    },
    {
      displayName: 'Daily ToDo',
      iconName: 'today',
      route: 'patient/daily',
      children: [
        {
          displayName: 'See Today',
          iconName: 'healing',
          route: 'patient/daily/patient-todo',
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
