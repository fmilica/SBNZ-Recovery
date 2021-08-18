import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AssignMealComponent } from './assign-meal/assign-meal.component';
import { PatientViewComponent } from './patient-view/patient-view.component';
import { ViewPatientsComponent } from './view-patients/view-patients.component';

const routes: Routes = [
    {
        path: 'assign-meal',
        component: AssignMealComponent,
    },
    {
        path: 'view-patients',
        component: ViewPatientsComponent,
    },
    {
        path: 'patient-view',
        component: PatientViewComponent,
    },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})

export class PatientsRoutingModule { }