import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutPatientComponent } from './about-patient/about-patient.component';
import { AddInjuryComponent } from './add-injury/add-injury.component';
import { MedicalHistoryComponent } from './medical-history/medical-history.component';
import { PatientTodoComponent } from './patient-todo/patient-todo.component';

const routes: Routes = [
    {
        path: 'about-me',
        component: AboutPatientComponent,
    },
    {
        path: 'add-injury',
        component: AddInjuryComponent,
    },
    {
        path: 'medical-history',
        component: MedicalHistoryComponent,
    },
    {
        path: 'daily/patient-todo',
        component: PatientTodoComponent,
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})

export class PatientRoutingModule { }