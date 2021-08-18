import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ReportsComponent } from './reports/reports.component';

const routes: Routes = [
    {
      path: 'patients',
      loadChildren: () => import('./patients/patients.module').then(m => m.PatientsModule),
    },
    {
      path: 'ingredients',
      loadChildren: () => import('./ingredients/ingredients.module').then(m => m.IngredientsModule)
    },
    {
      path: 'meals',
      loadChildren: () => import('./meals/meals.module').then(m => m.MealsModule)
    },
    {
      path: 'therapies',
      loadChildren: () => import('./therapies/therapies.module').then(m => m.TherapiesModule)
    },
    {
      path: 'reports',
      component: ReportsComponent,
    },
  ];
  
  
  @NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
  })
  export class DoctorRoutingModule { }