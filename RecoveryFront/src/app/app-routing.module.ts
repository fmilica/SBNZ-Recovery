import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RoleGuard } from './guards/role-guard.service';

const routes: Routes = [
  {
    path: '',
    loadChildren: () => import('./components/core/core.module').then(m => m.CoreModule),
    pathMatch: 'full'
  },
  {
    path: 'doctor',
    loadChildren: () => import('./components/doctor/doctor.module').then(m => m.DoctorModule),
    // canActivate: [RoleGuard],
    // data: { expectedRoles: 'ROLE_DOCTOR' },
  },
  {
    path: 'patient',
    loadChildren: () => import('./components/patient/patient.module').then(m => m.PatientModule),
    // canActivate: [RoleGuard],
    // data: { expectedRoles: 'ROLE_PATIENT' },
  }
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
