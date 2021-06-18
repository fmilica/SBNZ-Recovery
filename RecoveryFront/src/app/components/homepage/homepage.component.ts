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
      iconName: 'recent_actors',
      route: 'homepage/patient',
      children: [
        {
          displayName: 'View Patients',
          iconName: 'group',
          route: 'homepage/patient/view-patients',
        },
      ]
    },
    {
      displayName: 'Ingredients',
      iconName: 'recent_actors',
      route: 'homepage/ingredient',
      children: [
        {
          displayName: 'View Ingredients',
          iconName: 'group',
          route: 'homepage/ingredient/view-ingredients',
        },
        {
          displayName: 'Create Ingredient',
          iconName: 'speaker_notes',
          route: 'homepage/ingredient/create-ingredient',
        },
      ]
    },
    {
      displayName: 'Meals',
      iconName: 'recent_actors',
      route: 'homepage/meal',
      children: [
        {
          displayName: 'View Meals',
          iconName: 'group',
          route: 'homepage/meal/view-meals',
        },
        {
          displayName: 'Create Meal',
          iconName: 'speaker_notes',
          route: 'homepage/meal/create-meal',
        },
      ]
    },
    {
      displayName: 'Therapies',
      iconName: 'recent_actors',
      route: 'homepage/therapy',
      children: [
        {
          displayName: 'View Therapies',
          iconName: 'group',
          route: 'homepage/therapy/view-therapies',
        },
        {
          displayName: 'Create Therapy',
          iconName: 'speaker_notes',
          route: 'homepage/therapy/create-therapy',
        },
      ]
    },
    {
      displayName: 'Reports',
      iconName: 'recent_actors',
      route: 'homepage/reports',
      children: [
        {
          displayName: 'Create Report',
          iconName: 'group',
          route: 'homepage/reports',
        },
      ]
    },
  ];

  navPatientData = [
    {
      displayName: 'Medical History',
      iconName: 'recent_actors',
      route: 'homepage/patient',
      children: [
        {
          displayName: 'About Me',
          iconName: 'group',
          route: 'homepage/patient/about-patient',
        },
        {
          displayName: 'View Medical History',
          iconName: 'group',
          route: 'homepage/patient/medical-history',
        },
        {
          displayName: 'Add Injury',
          iconName: 'speaker_notes',
          route: 'homepage/patient/add-injury',
        },
      ]
    },
    {
      displayName: 'Daily ToDo',
      iconName: 'recent_actors',
      route: 'homepage/daily',
      children: [
        {
          displayName: 'See Today',
          iconName: 'group',
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
